package org.datasays.util.xml;

import jodd.util.ClassLoaderUtil;
import org.datasays.util.collection.StrMap;
import org.datasays.util.lang.ValuePlus;
import org.datasays.util.text.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlCoder {
	private static final Logger LOG = LoggerFactory.getLogger(XmlCoder.class);
	protected Element root;
	protected StrMap ns;
	public XPathEvaluator evaluator;

	private void init() {
		evaluator = new XPathEvaluator();
		evaluator.init();
	}

	public String getNodeText(String exp) {
		return evaluator.getNodeText(root, exp);
	}

	public String getNodeAttr(String exp, String attrName) {
		return evaluator.getNodeAttr(root, exp, attrName);
	}

	public String[] getNodesAttr(String exp, String attrName) {
		return evaluator.getNodesAttr(root, exp, attrName);
	}

	public Node findOneNode(Node node, String exp) {
		NodeList nodes = evaluator.findNodes(node, exp);
		if (nodes != null && nodes.getLength() > 0) {
			return nodes.item(0);
		}
		return null;
	}

	public NodeList findNodes(String exp) {
		return evaluator.findNodes(root, exp);
	}

	public Node findOneNode(String exp) {
		return findOneNode(root, exp);
	}

	public void parse(String filePath) {
		try {
			init();
			root = XmlUtil.parseRootElement(ClassLoaderUtil.getResourceAsStream(filePath));
			ns = XmlUtil.parseNameSpaces(root);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	public String getNsPrefix(String node, String url) {
		for (String nskey : ns.keySet()) {
			String nsurl = ValuePlus.strValue(ns.get(nskey), null);
			node = TextUtils.prefix(node);
			if (nsurl != null && nsurl.equals(url) && nskey.startsWith(node)) {
				return nskey.substring(node.length());
			}
		}
		return "";
	}
}
