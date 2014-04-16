package org.cnbi.utils.license;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.zip.GZIPInputStream;





import org.apache.log4j.Logger;
import org.cnbi.utils.date.DateUtil;
import org.cnbi.utils.pojo.MsgBean;
import org.cnbi.utils.properties.PropertyUtil;
import org.cnbi.utils.string.StringUtil;
public class LicenseUtil {
	Logger logger = Logger.getLogger(LicenseUtil.class.getName());
	private int userCount = 0;
	private int maxRunningVusers = 0;
	private int maxCompanyNumbers = 0;
	private int reportCount = 0;
	private int modelCount = 0;
	private long longStartDate = 0;
	private long longEndDate = 0;
	private Object useingDate = null;
	private String MAC = null;
	private Properties props;
	
	private MsgBean msgBean;

	/**
	 * @return the msgBean
	 */
	public MsgBean getMsgBean() {
		return msgBean;
	}
	public LicenseUtil(String sql,String path) {
		  this.props=  PropertyUtil.getProperties(path);//companyInfo
		  String verificationKey = props.getProperty("verification"),//验证编码的key
		   needLicenseCheckStr = props.getProperty(verificationKey);
		  this.msgBean = new MsgBean();
			if(needLicenseCheckStr.equals("0") || needLicenseCheckStr.equalsIgnoreCase("y")){
				logger.info("开始验证license信息！");
				 licenseCheckAll(sql);
			}else{
				String msg = "本次启动不需要验证license信息！";
				logger.info(msg);
				msgBean.setText(msg);
				msgBean.setFlag(false);
			}
	}
	public  void  licenseCheckAll(String sql){
		 msgBean = licenseCheck();
		if(!msgBean.getFlag()){
			String  errorMsg = "";
			if(!checkMac()){
				errorMsg = props.getProperty("info.mac");
			}else if(!checkOutTime()){
				errorMsg = props.getProperty("info.outtime");
			}
			msgBean.setText(errorMsg);
		}
	}
	public MsgBean  licenseCheck(){
		 MsgBean errorMsg = new MsgBean(false,"");
		 FileInputStream bais = null;
		 GZIPInputStream gzin = null;
		 ObjectInputStream in = null;
		try {
			String mypath = LicenseUtil.class.getClassLoader().getResource("").getPath();
			// 建立字节数组输入流
			String licensePath = StringUtil.pathRemoveBlank(mypath)+ "license/license";
			logger.info("licensePath=" + licensePath);
			bais = new FileInputStream(licensePath);
			// 建立gzip解压输入流
			gzin = new GZIPInputStream(bais);
			// 建立对象序列化输入流
			in = new ObjectInputStream(new BufferedInputStream(gzin));
			SecurityEncrypt encrypt = new SecurityEncrypt();
			Map<?, ?> map = (HashMap<?, ?>) in.readObject();
			String version = (String) map.get("version");
			String startDateStr = new String(encrypt.createDecryptor((byte[]) map.get("startDate")));
			Date startDate =DateUtil.convertDate(startDateStr, null);
			String endDateStr = new String(encrypt.createDecryptor((byte[]) map.get("endDate")));
			Date endDate = DateUtil.convertDate(endDateStr, null);
			useingDate = map.get("useingDate" + version);
			MAC = new String(encrypt.createDecryptor((byte[]) map.get("HDSerial")));
			userCount = Integer.valueOf(new String(encrypt.createDecryptor((byte[]) map.get("userCountInt"))));
			maxRunningVusers = Integer.valueOf(new String(encrypt.createDecryptor((byte[]) map.get("maxRunningVusers"))));
			maxCompanyNumbers = Integer.valueOf(new String(encrypt.createDecryptor((byte[]) map.get("maxCompanyNumbers"))));
			reportCount = Integer.valueOf(new String(encrypt.createDecryptor((byte[]) map.get("maxReportNumbers"))));
			modelCount = Integer.valueOf(new String(encrypt.createDecryptor((byte[]) map.get("maxModelNumbers"))));
			longEndDate = endDate.getTime();
			longStartDate = startDate.getTime();
		} catch (FileNotFoundException ex){
			errorMsg.setText(props.getProperty("info.nonexistent"));
			errorMsg.setFlag(true);
		} catch (IOException ex) {
			errorMsg.setText(props.getProperty("info.mac"));
			errorMsg.setFlag(true);
		} catch (Exception e) {
			errorMsg.setText(e.getMessage());
			errorMsg.setFlag(true);
		} finally {
			try {
				if (bais != null) {
					bais.close();
				}
				if (gzin != null) {
					gzin.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		}
		return errorMsg;
	}
	public void reRead() {
		userCount = 0;
		maxRunningVusers = 0;
		maxCompanyNumbers = 0;
		reportCount = 0;
		modelCount = 0;
		longStartDate = 0;
		longEndDate = 0;
		useingDate = null;
		MAC = null;
		licenseCheck();
	}
	public int getMaxRunningVusers() {
		return maxRunningVusers;
	}
	public int getMaxCompanyNumbers() {
		return maxCompanyNumbers;
	}
	public int getReportCount() {
		return reportCount;
	}
	public int getModelCount() {
		return modelCount;
	}
	public long getLongStartDate() {
		return longStartDate;
	}
	public long getLongEndDate() {
		return longEndDate;
	}
	public Object getUseingDate() {
		return useingDate;
	}
	public String getMAC() {
		return MAC;
	}
	public int getUserCount() {
		return userCount;
	}
	public  boolean checkOutTime() {
		 long now = new Date().getTime(),
		endDate = getLongEndDate(),
		startDate = getLongStartDate();
		if (now > endDate || now <= startDate) {
			return false;
		}
		return true;
	}
	public  boolean  checkMac() {
		String localhostMAC = MacAddressUtil.getMacAddress();
		String mac = getMAC();
		if (mac == null || !(mac.trim().equals(localhostMAC.trim()))) {
			return false;
		}
		return true;
	}
	/**
	 * @return the props
	 */
	public Properties getProps() {
		return props;
	}
	/**
	 * @param props the props to set
	 */
	public void setProps(Properties props) {
		this.props = props;
	}
	
	

}
