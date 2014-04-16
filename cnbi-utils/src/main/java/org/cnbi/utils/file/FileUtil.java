package org.cnbi.utils.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cnbi.utils.exception.BusinessException;
import org.cnbi.utils.pojo.FileBean;

public class FileUtil {
	private static final Log logger = LogFactory.getLog(FileUtil.class);

	/**
	 * 创建文件夹
	 * 
	 * @param folderPath
	 */
	public static void createFolder(String folderPath) {
		try {
			String filePath = System.getProperty("user.dir") + folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			if (!myFilePath.exists()) {// myFilePath.m
				myFilePath.mkdirs();
			}
		} catch (Exception e) {
			logger.error("创建" + folderPath + "出错了！");
			throw new RuntimeException("创建" + folderPath + "出错了！\n" + e);
		}
	}

	/**
	 * out.print("{success:true,msg:'生成json文件成功！'}");
	 * 
	 * @把json格式的字符串写到文件
	 * @param filePath
	 * @param sets
	 * @throws IOException
	 */
	public static void writeFile(String filePath, String json) {
		filePath = filePath.replace("/", File.separator);
		OutputStreamWriter osw = null;
		FileOutputStream fos = null;
		try {
			int index = filePath.lastIndexOf("\\")+1;
			String str1 = filePath.substring(0, index);
			File myFile = new File(str1);
			if (!myFile.exists() && !myFile.isDirectory()) {
				myFile.mkdirs();
			}
			fos = new FileOutputStream(filePath);
			osw = new OutputStreamWriter(fos, "UTF-8");
			osw.write(json);
			osw.flush();
			osw.close();
			logger.info("成功写入" + filePath + "文件!");
		} catch (Exception e) {
			logger.error("写入" + filePath + "文件出错了！");
			throw new RuntimeException("写" + filePath + "文件发生了错误！\n" + e);
		}
	}

	public static ArrayList<FileBean> getFileListChildren(String filePath,String suffix) {
		File dir = new File(filePath);
		File[] files = dir.listFiles();
		if (files == null){
			throw new BusinessException("找不到文件【"+filePath+"】");
		}
		ArrayList<FileBean> list = new ArrayList<FileBean>();
		for (int i = 0; i < files.length; i++) {
			FileBean bean = new FileBean();
			File fs = files[i];
			if (!fs.isDirectory() && fs.getName().contains(suffix)){
					setFileBean(bean, fs);
					list.add(bean);
			}
		}
		return list;
	}
	
    /**
     * 文件路径
     * @param filePath
     * @return
     */
	public static ArrayList<FileBean> getFileList(String filePath,String suffix) {
		ArrayList<FileBean> list = new ArrayList<FileBean>();
		ArrayList<FileBean> childrenList = new ArrayList<FileBean>();
		File[] files = getFiles(filePath);
		for (int i = 0; i < files.length; i++) {
			FileBean bean = new FileBean();
			File file = files[i];
			if (file.isDirectory()) {
				bean.setDirectory(file.getName());
				childrenList = getFileListChildren(file.getAbsolutePath(),suffix);
				bean.setList(childrenList);
			}
			setFileBean(bean, file);
			list.add(bean);
		}

		return list;
	}

	/**
	 * @param filePath
	 * @return
	 */
	private static File[] getFiles(String filePath) {
		File dir = new File(filePath);
		File[] files = dir.listFiles();
		if (null == files){
			throw new BusinessException("找不到【"+filePath+"】文件！");
		}
		return files;
	}

	private static void setFileBean(FileBean bean, File file) {
		String name = file.getName();
		String scode = name;
		if (name.contains(".")) {
			scode = name.substring(0, name.lastIndexOf("."));
		}		
		//String temp[] = Util.getFileDetail(fs);
		String temp[] = new String[3];
		bean.setName(name);
		bean.setScode(scode);
		//bean.setCreateTime(temp[0]);
		//bean.setModifyTime(temp[1]);
		//bean.setSize(temp[2]);
		//System.out.println(System.currentTimeMillis() - a);
	}

	/**
	 * JAVA读取外部资源的方法 从当前的工作目录中读取:
	 * 
	 * @param fileName
	 */
	public static void readFromWorkSpace(String fileName) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
			String str;
			while ((str = in.readLine()) != null) {
				System.out.println(str);
			}
			in.close();
		} catch (IOException e) {
		}
	}

	/**
	 * 从classpath中读取(读取找到的第一个符合名称的文件):
	 * 
	 * @param fileName
	 *            Xclass.class.getClassLoader().getResourceAsStream("file.txt")
	 *            velocity.properties
	 */
	public static void readFromClasspath(String fileName) {
		try {
			InputStream stream = FileUtil.class.getClassLoader().getResourceAsStream(fileName);
			BufferedReader in = new BufferedReader(new InputStreamReader(stream));
			String str;
			while ((str = in.readLine()) != null) {
				System.out.println(str);
			}
			in.close();
		} catch (IOException e) {
		}
	}

	/**
	 * 从URL中读取(读取找到的第一个符合名称的文件):
	 * 
	 * @param url
	 *            http://www.hao123.com
	 */
	public static void readFromUrl(URL url) {
		try {
			// URL url = new URL("http://blog.csdn.net/kkdelta");
			System.out.println(url);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String str;
			while ((str = in.readLine()) != null) {
				System.out.println(str);
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
