/**
 * 
 */
package com.github.reels_project.reels.query.query.internal.expression;

import org.junit.Test;

import com.github.reels_project.reels.query.Q;
import com.github.reels_project.reels.query.meta.IDBTable;
import com.github.reels_project.reels.query.meta.IDateDBColumn;
import com.github.reels_project.reels.query.meta.impl.DBTableImpl;
import com.github.reels_project.reels.query.meta.impl.StringDBColumnImpl;
import com.github.reels_project.reels.query.query.internal.ParamImpl;

/**
 * @author Takahiko Sato(MOSA architect Inc.)
 *
 */
public class BasicExpTest {
	
	@Test(expected=NullPointerException.class)
	public void ng_column_column_leftnull(){
		IDBTable t = new DBTableImpl("table1");
		
		new BasicExp<String>(null, new StringDBColumnImpl(t,"right")) {
			@Override
			protected String getSQL(String l, String r) {
				return null;
			}
		};
	}
	
	@Test(expected=NullPointerException.class)
	public void ng_column_column_rightnull(){
		IDBTable t = new DBTableImpl("table1");
		
		IDateDBColumn<String> c = null;
		new BasicExp<String>(new StringDBColumnImpl(t,"left"), c) {
			@Override
			protected String getSQL(String l, String r) {
				return null;
			}
		};
	}

	@Test(expected=NullPointerException.class)
	public void ng_column_value_columnnull(){
		new BasicExp<String>(null, "value") {
			@Override
			protected String getSQL(String l, String r) {
				return null;
			}
		};
	}
	
	@Test(expected=NullPointerException.class)
	public void ng_column_param_columnnull(){
		new BasicExp<String>(null, new ParamImpl()) {
			@Override
			protected String getSQL(String l, String r) {
				return null;
			}
		};
	}
	
	@Test(expected=NullPointerException.class)
	public void ng_column_query_columnnull(){
		new BasicExp<String>(null, Q.select()) {
			@Override
			protected String getSQL(String l, String r) {
				return null;
			}
		};
	}
}
