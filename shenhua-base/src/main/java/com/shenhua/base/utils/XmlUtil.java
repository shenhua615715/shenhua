package com.shenhua.base.utils;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class XmlUtil {
	public static Document getDoc(String xml) throws Exception {
		Document doc = DocumentHelper.parseText(xml);
		return doc;
	}

	public static Element getSafeElement(Element e, String name) {
		Element et = null;
		et = e.element(name);
		if (et != null) {
			return et;
		}
		et = e.element(name.toUpperCase());
		if (et != null) {
			return et;
		}
		et = e.element(name.toLowerCase());
		if (et != null) {
			return et;
		}
		return null;
	}

	public static String getSafeValue(Element e, String name) {
		String value = "";
		if (e != null) {
			if (e.element(name) != null) {
				value = e.element(name).getText();
			} else if (e.element(name.toLowerCase()) != null) {
				value = e.element(name.toLowerCase()).getText();
			} else if (e.element(name.toUpperCase()) != null) {
				value = e.element(name.toUpperCase()).getText();
			}
		}
		return value != null ? value.trim() : "";
	}

	public static Element addElementAndValue(Element father, String sonName, String sonValue) {
		Element sonElement = father.addElement(sonName);
		if (sonValue == null) {
			sonValue = "";
		}
		sonElement.setText(sonValue);
		return sonElement;
	}

	public static Element addNotElementAndValue(Element father, String sonName, String sonValue) {
		if (sonValue == null) {
			return null;
		}
		Element sonElement = father.addElement(sonName);
		sonElement.setText(sonValue);
		return sonElement;
	}

}
