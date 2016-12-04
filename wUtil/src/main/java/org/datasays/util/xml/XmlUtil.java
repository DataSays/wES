package org.datasays.util.xml;

import jodd.typeconverter.Convert;
import jodd.util.StringUtil;
import org.datasays.util.collection.StrMap;
import org.datasays.util.lang.ArgCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

import static org.datasays.util.text.TextUtils.prefix;

public class XmlUtil {
	private static final Logger LOG = LoggerFactory.getLogger(XmlUtil.class);
	public static final String NamespacePrefix = "xmlns:";

	public static boolean isBlank(Node node) {
		return node != null && ((node.getNodeType() == Node.TEXT_NODE && StringUtil.isBlank(node.getNodeValue())) || (node.getNodeType() == Node.COMMENT_NODE));
	}

	public static boolean hasChildren(Node node) {
		return node != null && node.getChildNodes() != null && node.getChildNodes().getLength() > 0;
	}

	public static String getAttribute(Node node, String attrName) {
		try {
			if (node != null && node.getAttributes() != null && node.getAttributes().getNamedItem(attrName) != null) {
				return node.getAttributes().getNamedItem(attrName).getNodeValue();
			}
		} catch (Throwable e) {
		}
		return null;
	}

	public static Node getNotBlankChildNode(Node node, int index) {
		if (node != null) {
			NodeList nodes = node.getChildNodes();
			int nindex = 0;
			for (int i = 0; i < nodes.getLength(); i++) {
				Node n = nodes.item(i);
				if (!XmlUtil.isBlank(n)) {
					if (nindex == index) {
						return n;
					}
					nindex++;
				}
			}
		}
		return null;
	}

	public static int getNotBlankChildNodeSize(Node node) {
		if (node != null) {
			NodeList nodes = node.getChildNodes();
			int nindex = 0;
			for (int i = 0; i < nodes.getLength(); i++) {
				Node n = nodes.item(i);
				if (!XmlUtil.isBlank(n)) {
					nindex++;
				}
			}
			return nindex;
		}
		return 0;
	}

	public static StrMap getAttributes(Node node) {
		StrMap attrs = new StrMap();
		ArgCheck.checkNotNull(node);
		NamedNodeMap attrNodeMap = node.getAttributes();
		if (attrNodeMap != null) {
			for (int i = 0; i < attrNodeMap.getLength(); i++) {
				Node attNode = attrNodeMap.item(i);
				if (attNode.getNodeType() == Node.ATTRIBUTE_NODE) {
					attrs.put(attNode.getNodeName(), attNode.getNodeValue());
				} else {
					LOG.warn("error node type:" + attNode.getNodeType());
				}
			}
		}
		return attrs;
	}

	private static Document parseDocument(InputStream is) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(false);
			DocumentBuilder builder = factory.newDocumentBuilder();
			return builder.parse(is);
		} catch (Throwable e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	public static Element parseRootElement(InputStream is) {
		try {
			Document doc = parseDocument(is);
			return doc.getDocumentElement();
		} catch (Throwable e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	public static StrMap parseNameSpaces(Node doc) {
		StrMap ns = new StrMap();
		String rootNodeName = doc.getNodeName();
		//parse namespaces
		StrMap attrs = XmlUtil.getAttributes(doc);
		for (String attrName : attrs.keySet()) {
			if (attrName.trim().startsWith(NamespacePrefix)) {
				String value = Convert.toString(attrs.get(attrName));
				String name = attrName.substring(NamespacePrefix.length());
				ns.put(prefix(rootNodeName) + name, value);
			}
		}
		return ns;
	}
}
