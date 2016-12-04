package org.datasays.wes;

import org.datasays.wes.vo.EsItem;

import java.util.Date;

/**
 * Created by watano on 2016/11/21.
 */
public class TestDoc extends EsItem {
	public TestDoc(String index, String type) {
		super(index, type);
	}

	public TestDoc(String index, String type, String id) {
		super(index, type, id);
	}

	public int tint = 1;
	public double tdouble = 1.0d;
	public float tfloat = 1.0f;
	public boolean tbool = false;
	public Date tdate = null;
	public String tString = "abc中文测试乒乓球";
	public TestDoc obj = null;
}
