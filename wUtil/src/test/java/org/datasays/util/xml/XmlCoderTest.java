package org.datasays.util.xml;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class XmlCoderTest {
	private static final Logger LOG = LoggerFactory.getLogger(XmlCoderTest.class);
	private XmlCoder xmlcoder;

	@Before
	public void setUp() throws Exception {
		xmlcoder = new XmlCoder();
	}

	@Test
	public void testFindnode() {
		xmlcoder.parse("classpath:AndroidManifest.xml");
		Node uses_sdk = xmlcoder.findOneNode("/manifest/uses-sdk");
		assertNotNull(uses_sdk);
		assertEquals("8", XmlUtil.getAttribute(uses_sdk, "android:minSdkVersion"));

		NodeList activities = xmlcoder.findNodes("/manifest/application/activity[@name='com.android.music.MusicBrowserActivity']");
		assertNotNull(activities);
		assertEquals(1, activities.getLength());
		assertEquals("@android:style/Theme.NoTitleBar", XmlUtil.getAttribute(activities.item(0), "android:theme"));

		Node activity = xmlcoder.findOneNode("/manifest/application/activity[@name='com.android.music.MusicBrowserActivity']");
		assertNotNull(activity);
		assertEquals("@android:style/Theme.NoTitleBar", XmlUtil.getAttribute(activity, "android:theme"));
		assertEquals("@android:style/Theme.NoTitleBar", xmlcoder.getNodeAttr("/manifest/application/activity[@name='com.android.music.MusicBrowserActivity']", "android:theme"));

		activities = xmlcoder.findNodes("/manifest/application/activity");
		assertNotNull(activities);
		for (int i = 0; i < activities.getLength(); i++) {
			String name = XmlUtil.getAttribute(activities.item(i), "android:name");
			LOG.error(name);
			if ("com.android.music.MusicBrowserActivity".equals(name)) {
				assertEquals("@android:style/Theme.NoTitleBar", XmlUtil.getAttribute(activities.item(i), "android:theme"));
				NodeList cnodes = xmlcoder.evaluator.findNodes(activities.item(i), "intent-filter/action");
				assertEquals(2, cnodes.getLength());
				assertEquals("android.intent.action.MAIN", XmlUtil.getAttribute(cnodes.item(0), "android:name"));
				assertEquals("android.intent.action.MUSIC_PLAYER", XmlUtil.getAttribute(cnodes.item(1), "android:name"));
			}
		}

		//
		xmlcoder.parse("classpath:strings.xml");

		assertEquals("1 song", xmlcoder.getNodeText("/resources/string[@name='onesong']"));
		assertEquals("All songs by %s will be permanently deleted from the SD card.",
				xmlcoder.getNodeText("/resources/string[@name='delete_artist_desc']"));
		NodeList nodes = xmlcoder.findNodes("/resources/string[@name='delete_artist_desc']");
		assertNotNull(nodes);
		assertEquals(1, nodes.getLength());

		String[] attrs = xmlcoder.getNodesAttr("/resources/plurals[@name='NNNtrackstoplaylist']/item", "quantity");
		assertNotNull(attrs);
		assertEquals(2, attrs.length);
	}

	@Test
	public void testFindnodes() {
	}

	@Test
	public void testFindString() {
	}

	@Test
	public void testFindNumber() {

	}

	@Test
	public void testFindBool() {
	}
}
