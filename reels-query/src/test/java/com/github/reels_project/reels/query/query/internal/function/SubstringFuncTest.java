package com.github.reels_project.reels.query.query.internal.function;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.github.reels_project.reels.query.meta.IDBTable;
import com.github.reels_project.reels.query.meta.INumberDBColumn;
import com.github.reels_project.reels.query.meta.impl.DBTableImpl;
import com.github.reels_project.reels.query.meta.impl.NumberDBColumnImpl;
import com.github.reels_project.reels.query.query.internal.DefaultQueryContext;

public class SubstringFuncTest {
	
	@Test
	public void ok_column_from(){
		IDBTable t = new DBTableImpl("table1");
		SubstringFunc func = new SubstringFunc(new NumberDBColumnImpl<>(t, "frm"));
		String actual = func.getSQL(new DefaultQueryContext(t), "table1.name");
		
		assertThat(actual, is("SUBSTR(table1.name,table1.frm)"));
	}
	
	@Test
	public void ok_column_from_to(){
		IDBTable t = new DBTableImpl("table1");
		SubstringFunc func = new SubstringFunc(new NumberDBColumnImpl<>(t, "frm"),new NumberDBColumnImpl<>(t, "t"));
		String actual = func.getSQL(new DefaultQueryContext(t), "table1.name");
		
		assertThat(actual, is("SUBSTR(table1.name,table1.frm,table1.t)"));
	}
	
	@Test
	public void ok_column_null(){
		IDBTable t = new DBTableImpl("table1");
		INumberDBColumn<Integer> c = null;
		SubstringFunc func = new SubstringFunc(c);
		String actual = func.getSQL(new DefaultQueryContext(t), "table1.num");
		
		assertThat(actual, is("table1.num"));
	}

	
	@Test
	public void ok_value_from(){
		IDBTable t = new DBTableImpl("table1");
		SubstringFunc func = new SubstringFunc(0);
		String actual = func.getSQL(new DefaultQueryContext(t), "table1.num");
		
		assertThat(actual, is("SUBSTR(table1.num,0)"));
	}
	@Test
	public void ok_value_from_to(){
		IDBTable t = new DBTableImpl("table1");
		SubstringFunc func = new SubstringFunc(0,5);
		String actual = func.getSQL(new DefaultQueryContext(t), "table1.num");
		
		assertThat(actual, is("SUBSTR(table1.num,0,5)"));
	}
	
	@Test
	public void ok_value_null(){
		IDBTable t = new DBTableImpl("table1");
		Integer i = null;
		SubstringFunc func = new SubstringFunc(i);
		String actual = func.getSQL(new DefaultQueryContext(t), "table1.num");
		
		assertThat(actual, is("table1.num"));
	}
}
