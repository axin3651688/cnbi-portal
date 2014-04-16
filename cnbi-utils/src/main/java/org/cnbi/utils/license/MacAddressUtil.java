package org.cnbi.utils.license;

import java.io.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 经邦mac地址获取类
 * 
 * @author hhl
 * 
 */
public class MacAddressUtil {
	final static Log logger = LogFactory.getLog(MacAddressUtil.class);

	/**
	 * 获取Mac地址
	 * 
	 * @return
	 */
	public static String getMacAddress() {
		String macAddress = "";
		// 得到IP，输出CHINA-4A4387B23/192.168.1.192
		try {
			InetAddress ia = InetAddress.getLocalHost();
			macAddress = getMacByIpAddress(ia);
		} catch (UnknownHostException e) {
			macAddress = getMacByCommand();
		}
		return macAddress;
	}

	/**
	 * 通过命令获取mac地址
	 * 
	 * @return
	 */
	private static String getMacByCommand() {
		String line = "";
		String command = "/sbin/ifconfig";

		String sOsName = System.getProperty("os.name");
		if (sOsName.startsWith("Windows")) {
			command = "ipconfig /all";
		} else {
			if ((sOsName.startsWith("Linux")) || (sOsName.startsWith("Mac")) || (sOsName.startsWith("HP-UX"))) {
				command = "/sbin/ifconfig";
			} else if (sOsName.startsWith("AIX")) {
				command = "netstat -v";
			} else {
				logger.info("The current operating system '" + sOsName + "' is not supported.");
			}
		}

		Pattern p = Pattern.compile("([a-fA-F0-9]{1,2}(-|:)){5}[a-fA-F0-9]{1,2}");
		try {
			Process pa = Runtime.getRuntime().exec(command);
			// pa.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(pa.getInputStream()));

			Matcher m;
			while ((line = reader.readLine()) != null) {
				m = p.matcher(line);

				if (!m.find())
					continue;
				line = m.group();
				break;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return line.replaceAll("-", ":");
	}

	/**
	 * 通过IP信息获取Mac地址
	 * 
	 * @param ia
	 * @return
	 */
	private static String getMacByIpAddress(InetAddress ia) {
		// 获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。
		try {
			byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
			// System.out.println("mac数组长度：" + mac.length);
			// 下面代码是把mac地址拼装成String
			StringBuffer sb = new StringBuffer("");
			for (int i = 0; i < mac.length; i++) {
				if (i != 0) {
					sb.append(":");
				}
				// mac[i] & 0xFF 是为了把byte转化为正整数
				int temp = mac[i] & 0xff;
				String str = Integer.toHexString(temp);
				// System.out.println("每8位:" + str);
				if (str.length() == 1) {
					sb.append("0" + str);
				} else {
					sb.append(str);
				}
			}
			// 把字符串所有小写字母改为大写成为正规的mac地址并返回
			return sb.toString().toUpperCase();
		} catch (SocketException e) {
			e.printStackTrace();

			return getMacByCommand();
		}
	}

	/*public static void main(String[] args) {
		getMacAddress();
	}*/
}
