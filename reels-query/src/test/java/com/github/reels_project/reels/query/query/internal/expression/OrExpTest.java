/**
 * 
 */
package com.github.reels_project.reels.query.query.internal.expression;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;


import org.junit.Test;

import com.github.reels_project.reels.query.meta.IDBTable;
import com.github.reels_project.reels.query.meta.impl.DBTableImpl;
import com.github.reels_project.reels.query.meta.impl.NumberDBColumnImpl;
import com.github.reels_project.reels.query.meta.impl.StringDBColumnImpl;
import com.github.reels_project.reels.query.query.Exp;
import com.github.reels_project.reels.query.query.QueryContext;
import com.github.reels_project.reels.query.query.internal.DefaultQueryContext;


/**
 * @author Takahiko Sato(MOSA architect Inc.)
 *
 */
public class OrExpTest {
	
	@Test
	public void ok(){
		IDBTable t = new DBTableImpl("table1");
		Exp[] exps = new Exp[2];
		exps[0] = new LtExp<Integer>(new NumberDBColumnImpl<Integer>(t, "age"), 245);
		exps[1] = new EqExp<String>(new StringDBColumnImpl(t, "name"), "piyo");
		Exp exp = new OrExp(exps);
		
		QueryContext context = new DefaultQueryContext(t);
		String actual = exp.getSQL(context); 
		
		assertThat(actual, notNullValue());
		assertThat(actual,is("(table1.age < 245 OR table1.name = 'piyo')"));
	}
	
	@Test
	public void ok_andInAnd(){
		IDBTable t = new DBTableImpl("table1");
		
		Exp[] inner = new Exp[3];
		inner[0] = new LtExp<Integer>(new NumberDBColumnImpl<Integer>(t, "age"), 245);
		inner[1] = new EqExp<String>(new StringDBColumnImpl(t, "name1"), "piyo");
		inner[2] = new LikeExp(new StringDBColumnImpl(t, "name2"), "%piyo");

		Exp[] exps = new Exp[2];
		exps[0] = new LtExp<Integer>(new NumberDBColumnImpl<Integer>(t, "age"), 12);
		exps[1] = new OrExp(inner);
		
		Exp exp = new OrExp(exps);
		
		QueryContext context = new DefaultQueryContext(t);
		String actual = exp.getSQL(context); 
		
		assertThat(actual, notNullValue());
		assertThat(actual,is("(table1.age < 12 OR (table1.age < 245 OR table1.name1 = 'piyo' OR table1.name2 LIKE '%piyo' ESCAPE '!'))"));
	}
	
	@Test(expected=NullPointerException.class)
	public void ng_expsNull(){
		Exp[] exps = null;
		new OrExp(exps);
	}
	
	@Test
	public void ok_exps_0(){
		IDBTable t = new DBTableImpl("table1");
		Exp[] exps = new Exp[0];
		
		Exp exp = new OrExp(exps);
		
		QueryContext context = new DefaultQueryContext(t);
		String actual = exp.getSQL(context);
		
		assertNull(actual);
	}
	@Test
	public void ok_exps_1(){
		IDBTable t = new DBTableImpl("table1");
		Exp[] exps = new Exp[1];
		exps[0] = new LtExp<Long>(new NumberDBColumnImpl<Long>(t, "age"), 245L);
		
		Exp exp = new OrExp(exps);
		
		QueryContext context = new DefaultQueryContext(t);
		String actual = exp.getSQL(context);
		
		assertThat(actual, notNullValue());
		assertThat(actual,is("table1.age < 245"));
	}
}
