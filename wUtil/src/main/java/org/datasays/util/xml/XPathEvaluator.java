package org.datasays.util.xml;

import org.datasays.util.lang.ArgCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.namespace.QName;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class XPathEvaluator {
	private static final Logger LOG = LoggerFactory.getLogger(XPathEvaluator.class);
	private XPathFactory factory = null;
	private XPath xpath = null;
	private Map<String, XPathExpression> expCache;

	public XPathEvaluator() {
		init();
	}

	public void init() {
		factory = XPathFactory.newInstance();
		xpath = factory.newXPath();
		expCache = new Hashtable<String, XPathExpression>();
	}

	/**
	 * @param node
	 * @param exp
	 * @param returnType XPathConstants.NODESET
	 * @return
	 */
	public Object evaluate(Node node, String exp, QName returnType) {
		try {
			XPathExpression expr = getExpr(exp);
			if (expr != null) {
				return expr.evaluate(node, returnType);
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	public Object evaluate(Node node, String exp) {
		try {
			XPathExpression expr = getExpr(exp);
			if (expr != null) {
				return expr.evaluate(node);
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	public NodeList findNodes(Node node, String exp) {
		return (NodeList) evaluate(node, exp, XPathConstants.NODESET);
	}

	public Node findOneNode(Node node, String exp) {
		NodeList nodes = findNodes(node, exp);
		if (nodes != null && nodes.getLength() > 0) {
			return nodes.item(0);
		}
		return null;
	}

	public XPathExpression getExpr(String exp) {
		XPathExpression expr = expCache.get(exp);
		if (expr == null) {
			try {
				expr = xpath.compile(exp);
				if (expr != null) {
					expCache.put(exp, expr);
				}
				return expr;
			} catch (XPathExpressionException e) {
				LOG.error(e.getMessage(), e);
				return null;
			}
		}
		return expr;
	}

	public String[] getNodesAttr(Node node, String exp, String attrName) {
		List<String> qs = new ArrayList<String>();
		try {
			NodeList nodes = findNodes(node, exp);
			if (nodes != null && nodes.getLength() > 0) {
				for (int i = 0; i < nodes.getLength(); i++) {
					qs.add(XmlUtil.getAttribute(nodes.item(i), attrName));
				}
			}
		} catch (Exception e) {
		}
		return qs.toArray(new String[]{});
	}

	public String getNodeAttr(Node node, String exp, String attrName) {
		Node n = findOneNode(node, exp);
		ArgCheck.checkNotNull(n);
		return XmlUtil.getAttribute(n, attrName);
	}

	public String getNodeText(Node node, String exp) {
		Node n = findOneNode(node, exp);
		ArgCheck.checkNotNull(n);
		return n.getTextContent();
	}
}
