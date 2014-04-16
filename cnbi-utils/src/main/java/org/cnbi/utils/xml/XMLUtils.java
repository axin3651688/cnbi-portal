package org.cnbi.utils.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cnbi.utils.pojo.RecordBean;
import org.cnbi.utils.pojo.XmlSqlBean;
import org.cnbi.utils.string.StringUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XMLUtils {
	final static Log logger = LogFactory.getLog(XMLUtils.class);

	public static void getCommonData(Map<String, Object> map, Element el) {
		List<Element> ele_list = el.elements("items");
		for (Element sqlItems : ele_list) {
			String ids = XMLUtils.attr(sqlItems, "id");
			List<Object> itemsList = (List<Object>) XMLUtils.getItemsList(sqlItems);
			if (null != itemsList && itemsList.size() > 0) {
				map.put(ids, itemsList);
			}
		}
	}

	public static Object getItemsList(Element itemsEl) {
		List<Element> itemEl_List = itemsEl.elements("item");
		if (null == itemEl_List || itemEl_List.size() == 0) {
			// System.out.println("系统检测到没有配置项目节点");
			return null;
		}
		List<Object> items = new ArrayList<Object>();
		for (Element itemEl : itemEl_List) {
			Object b = XMLUtils.getItemBean(itemEl);
			items.add(b);
		}
		return items;
	}

	public static Object getItemBean(Element element) {
		String id = XMLUtils.attr(element, "id"), name = XMLUtils.attr(element, "name"), fomular = XMLUtils.attr(element, "fomular"), attributes = element.getStringValue();
		if (id.equals("") || name.equals("")) {
			throw new RuntimeException("没有配制【" + name + id + "】正确的item标签！");
		}
		RecordBean bean = new RecordBean(id.trim(), name.trim(), fomular);// replaceLeftAndRightBlank
		if (null != attributes && !"".equals(attributes)) {
			if (fomular.equalsIgnoreCase("no")) {
				attributes = StringUtil.replaceLeftAndRightBlank(attributes);
				// System.out.println("attributes=="+attributes);
				attributes = attributes.trim();
			} else {
				attributes = StringUtil.replaceBlank(attributes);
			}
			bean.setAttributes(attributes);
			// System.out.println("attributes=="+attributes);
		}
		return bean;
	}

	public static String attr(Element node, String attrName) {
		String result = "", value = node.attributeValue(attrName);
		if (null != value) {
			result = value;
		}
		return result;
	}

	/**
	 * 将xml类型的字符串转换成document对象进行操作
	 * 
	 * @param xml
	 * @return
	 */
	public static Document parseStringToXml(String xml) {
		try {
			return DocumentHelper.parseText(xml);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 读取xml文件
	 * 
	 * @param filepath
	 * @return
	 */
	public static Document getDocumentByClassPathStrging(String filepath) {
		if (!filepath.contains(".xml")) {
			filepath = filepath + ".xml";
		}
		
		SAXReader saxReader = new SAXReader();
		Document doc = null;
		try {
			InputStream in = XMLUtils.class.getClassLoader().getResourceAsStream(filepath);
			// InputStream in =
			// Thread.currentThread().getContextClassLoader().getSystemResourceAsStream(filepath);
			if (null == in)
				throw new RuntimeException("找不到【" + filepath + "】文件");
			doc = saxReader.read(in);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}

	public static Document getDocumentByFile(File file) {
		SAXReader saxReader = new SAXReader();
		Document doc = null;
		try {
			FileInputStream in = new FileInputStream(file);
			doc = saxReader.read(in);
		} catch (DocumentException e) {
			e.printStackTrace();
		}catch (FileNotFoundException e) {
			throw new RuntimeException("找不到【" + file.getAbsolutePath() + "】文件");
		}
		return doc;
	}

	/**
	 * 读取xml文件
	 * 
	 * @param filepath
	 * @return
	 */
	public static Document parseFileToXml(String filepath) {
		SAXReader saxReader = new SAXReader();
		Document doc = null;
		try {
			doc = saxReader.read(new File(filepath));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}

	/**
	 * 获取webapp 相对路径下的xml文件
	 * 
	 * @param filename
	 * @return
	 */
	public static Document parseFIOToXml(String filename) {
		SAXReader saxReader = new SAXReader();
		Document doc = null;
		try {
			InputStream in = new FileInputStream(new File(filename));
			doc = saxReader.read(in);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}

	/**
	 * 根据xpath 获取指定的元素
	 * 
	 * @param xpath
	 * @param doc
	 * @return
	 */
	public static Element getElementByXpath(String xpath, Document doc) {
		List<Element> parameterList = doc.selectNodes(xpath);
		if (parameterList != null && parameterList.size() > 0) {
			return (Element) parameterList.get(0);
		}
		return null;
	}

	/**
	 * 获取指定id的xml元素(单个xml中id唯一的情况下使用)
	 * 
	 * @param id
	 * @param doc
	 * @return
	 */
	public static Element getElementById(String id, Document doc) {
		return getElementByXpath("//*[@id='" + id + "']", doc);
	}

	public static Element getElementByName(String tagName, Element parent) {
		return parent.element(tagName);
	}

	public static List<Element> getListByName(String tagName, Element parent) {
		return parent.elements(tagName);
	}

	/**
	 * 在指定id的元素后面插入元素
	 * 
	 * @param pid
	 * @param newele
	 * @param doc
	 * @return
	 */
	public static Document insertElement(String pid, Element newele, Document doc) {
		return insertElement(pid, newele, doc, 1);
	}

	/**
	 * 在指定id的元素后面或前面插入元素
	 * 
	 * @param pid
	 * @param newele
	 * @param doc
	 * @param i
	 *            为1表示在坐标元素之后----- 为0 则在坐标元素之前
	 * @return
	 */
	public static Document insertElement(String pid, Element newele, Document doc, int i) {
		Element element = getElementById(pid, doc);// 坐标元素

		List<Element> list = element.getParent().content();// 获取坐标元素父元素下的所有元素

		// list.indexOf(valueEle)+1, +1 表示在坐标元素之后； 不+1，则在坐标元素之前
		list.add(list.indexOf(element) + i, newele);

		return doc;
	}

	/**
	 * 删除文档doc的指定路径下的所有子节点（包含元素，属性等） <br/>
	 * 如果路径相同一并删除
	 * 
	 * @param doc
	 *            文档对象
	 * @param xpath
	 *            指定元素的路径 根据路径可删除元素、属性
	 * @return 删除成功时返回true，否则false
	 */
	public static boolean deleteNodes(Document doc, String xpath) {
		boolean flag = true;
		try {
			List<Node> nlist = doc.selectNodes(xpath);
			for (Node node : nlist) {
				node.detach();
			}
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 删除一个父元素下所有的子节点（包含元素，属性等）
	 * 
	 * @param element
	 *            父元素
	 * @return 删除成功时返回true，否则false
	 */
	public static boolean deleteChildren(Element element) {
		boolean flag = true;
		try {
			List<Node> nlist = element.elements();
			for (Node node : nlist) {
				node.detach();
			}
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 删除指定的元素
	 * 
	 * @param document
	 * @param ele
	 * @return
	 */
	public static boolean deleteElement(Element ele) {
		List<Element> list = ele.getParent().content();
		list.remove(list.indexOf(ele));
		return true;
	}

	/**
	 * 保存xml
	 * 
	 * @param filepath
	 * @param document
	 * @return 操作标识符
	 */
	public static boolean saveDocument(String filepath, Document document) {
		try {
			XMLWriter writer = new XMLWriter(new FileWriter(new File(filepath)));
			writer.write(document);
			writer.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public static XmlSqlBean getConfigXmlBean(String xmlPath) {
		Document doc = XMLUtils.getDocumentByClassPathStrging(xmlPath);
		SqlXmlParser parser = new SqlXmlParser();
		parser.setDoc(doc);
		try {
			XmlSqlBean beans = (XmlSqlBean) parser.execute();
			return beans;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("初始【" + xmlPath + "】文件时出错了！");
		}
		return null;
	}

	public static XmlSqlBean getConfigXmlBean(File file) {
		Document doc = XMLUtils.getDocumentByFile(file);
		SqlXmlParser parser = new SqlXmlParser();
		parser.setDoc(doc);
		try {
			XmlSqlBean beans = (XmlSqlBean) parser.execute();
			return beans;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("初始【" + file.getAbsolutePath() + "】文件时出错了！");
		}
		return null;
	}

	public static void getConfigXmlMap(Map<String, String> sqlMap, XmlSqlBean beans) {
		Map<String, Object> map = beans.getMap();
		Iterator<String> iter = map.keySet().iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			List<RecordBean> list = (List<RecordBean>) map.get(key);
			for (int i = 0, len = list.size(); i < len; i++) {
				RecordBean bean = list.get(i);
				String k = key + bean.getId();// key+StringUtil.toUpperCaseFirstOne(bean.getId());
				sqlMap.put(k, bean.getAttributes());
				logger.info(k + "====" + bean.getAttributes());
			}
		}
	}

}
