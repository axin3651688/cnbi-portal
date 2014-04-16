package org.cnbi.utils.xml;

import java.util.HashMap;
import java.util.Map;

import org.cnbi.utils.pojo.XmlSqlBean;
import org.dom4j.Document;
import org.dom4j.Element;

public class SqlXmlParser {

	private XmlSqlBean bean;
	private Document doc;

	public Object execute() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// Element rootEl = this.doc.getRootElement();
		Element sqlConfigEl = XMLUtils.getElementById("sql", this.doc); // 导航节点
		XMLUtils.getCommonData(map, sqlConfigEl);
		String debugStr = XMLUtils.attr(sqlConfigEl, "debug"), name = XMLUtils.attr(sqlConfigEl, "name");
		boolean debug = Boolean.valueOf(debugStr);
		bean = new XmlSqlBean(debug, name, map);
		return bean;
	}

	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}

}
