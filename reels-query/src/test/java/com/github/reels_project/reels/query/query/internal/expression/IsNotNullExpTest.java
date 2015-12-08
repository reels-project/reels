/**
 * 
 */
package com.github.reels_project.reels.query.query.internal.expression;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;


import org.junit.Test;

import com.github.reels_project.reels.query.meta.IDBTable;
import com.github.reels_project.reels.query.meta.impl.DBTableImpl;
import com.github.reels_project.reels.query.meta.impl.StringDBColumnImpl;
import com.github.reels_project.reels.query.query.Exp;
import com.github.reels_project.reels.query.query.QueryContext;
import com.github.reels_project.reels.query.query.internal.DefaultQueryContext;


/**
 * @author Takahiko Sato(MOSA architect Inc.)
 *
 */
public class IsNotNullExpTest{
	
	@Test
	public void ok_withColumn(){
		IDBTable t = new DBTableImpl("table1");
		Exp exp = new IsNotNullExp<String>(new StringDBColumnImpl(t,"name"));
		
		QueryContext context = new DefaultQueryContext(t);
		String actual = exp.getSQL(context); 
		
		assertThat(actual, notNullValue());
		assertThat(actual, is("table1.name IS NOT NULL"));
	}
	
	@Test(expected=NullPointerException.class)
	public void ng_leftNull(){
		new IsNotNullExp<String>(null);
	}
}
