package com.github.reels_project.reels.query.query.internal.function;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.github.reels_project.reels.query.meta.IDBTable;
import com.github.reels_project.reels.query.meta.impl.DBTableImpl;
import com.github.reels_project.reels.query.meta.impl.StringDBColumnImpl;
import com.github.reels_project.reels.query.query.internal.DefaultQueryContext;

public class CoalesceFuncTest {
	
	@Test
	public void ok_column(){
		IDBTable t = new DBTableImpl("table1");
		CoalesceFunc func = new CoalesceFunc(new StringDBColumnImpl(t, "name"));
		String actual = func.getSQL(new DefaultQueryContext(t), "table1.num");
		
		assertThat(actual, is("COALESCE(table1.num,table1.name)"));
	}
	
	@Test
	public void ok_column_null(){
		IDBTable t = new DBTableImpl("table1");
		CoalesceFunc func = new CoalesceFunc(null);
		String actual = func.getSQL(new DefaultQueryContext(t), "table1.num");
		
		assertThat(actual, is("COALESCE(table1.num,NULL)"));
	}

	
	@Test
	public void ok_value(){
		IDBTable t = new DBTableImpl("table1");
		CoalesceFunc func = new CoalesceFunc("name");
		String actual = func.getSQL(new DefaultQueryContext(t), "table1.num");
		
		assertThat(actual, is("COALESCE(table1.num,'name')"));
	}
	
	@Test
	public void ok_value_null(){
		IDBTable t = new DBTableImpl("table1");
		CoalesceFunc func = new CoalesceFunc(null);
		String actual = func.getSQL(new DefaultQueryContext(t), "table1.num");
		
		assertThat(actual, is("COALESCE(table1.num,NULL)"));
	}
}
