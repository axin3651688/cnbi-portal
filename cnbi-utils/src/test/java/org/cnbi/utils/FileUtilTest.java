/**
 * 
 */
package org.cnbi.utils;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import org.cnbi.utils.file.FileUtil;
import org.cnbi.utils.json.JsonUtil;
import org.cnbi.utils.pojo.FileBean;
import org.cnbi.utils.pojo.XmlSqlBean;
import org.cnbi.utils.tools.Constants;
import org.cnbi.utils.xml.XMLUtils;
import org.junit.Test;

/**
 * Copyright © 2014中国经邦. All rights reserved.
 * @author 龚佳新
 * @date 2014年4月11日上午9:28:03
 * @cnbi-utils
 */
public class FileUtilTest {
	@Test
	public void testFile(){
		System.out.println(System.getProperty("user.dir")+"");
		String filePath = "E:\\gjx\\keplerworkspace\\cnbi-portal\\cnbi-web\\src\\main\\resources\\xml\\sql\\sql-*";

		int index = filePath.lastIndexOf("\\")+1;
		String fileRealPath = filePath.substring(0, index), prefix = filePath.substring(index, filePath.length());
		File [] files = new File(fileRealPath).listFiles();
		boolean flag = false;
		String []temp = prefix.split("\\"+Constants.MIDDLELINE);
		String splitFileName = temp[0]+Constants.MIDDLELINE;
		if(temp.length == 2 && temp[1].equals(Constants.STAR)){
			flag = true;
		} 
		for(int i=0,len = files.length;i<len;i++){
			String fileName = files[i].getName(), keyName = fileName.replace(".xml", "");
			if(prefix.length() == 0 || prefix.equals(Constants.STAR)|| flag == true || fileName.contains(prefix)){
				//XmlSqlBean beans = XMLUtils.getConfigXmlBean(files[i]);
				if(flag && !fileName.contains(splitFileName)){
					
				}else
				System.out.println("执行了"+fileName);
			}
			/*if(prefix.length() == 0 || prefix.equals(Constants.STAR) || fileName.contains(prefix) || flag == true ){
				
			}
			 */
		}
		
		
		//ArrayList<FileBean> list = FileUtil.getFileList(filePath, ".xml");
		//System.out.println(JsonUtil.generateJson(list));
	//	System.out.println(FileUtil.class.getClassLoader().getSystemResourceAsStream(name));
		//E:\gjx\keplerworkspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\cnbi-web\WEB-INF\classes\xml\sql\
	}

}
