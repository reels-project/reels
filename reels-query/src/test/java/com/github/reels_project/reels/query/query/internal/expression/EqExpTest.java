package com.github.reels_project.reels.query.query.internal.expression;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import com.github.reels_project.reels.query.Q;
import com.github.reels_project.reels.query.meta.IDBColumn;
import com.github.reels_project.reels.query.meta.IDBTable;
import com.github.reels_project.reels.query.meta.impl.DBTableImpl;
import com.github.reels_project.reels.query.meta.impl.StringDBColumnImpl;

public class EqExpTest {
	@Test
	public void ok_constructors(){
		IDBTable t = new DBTableImpl("table1");
		IDBColumn<String> left = new StringDBColumnImpl(t, "left");
		IDBColumn<String> col1 = new StringDBColumnImpl(t, "col1");
		
		new EqExp<>(left, col1);
		new EqExp<>(left, Q.param());
		new EqExp<>(left, "to");
		new EqExp<>(left, Q.select());
	}
	
	@Test
	public void ok_getSQL(){
		IDBTable t = new DBTableImpl("table1");
		IDBColumn<String> left = new StringDBColumnImpl(t, "left");
		IDBColumn<String> col1 = new StringDBColumnImpl(t, "col1");
		
		String actual = new EqExp<>(left, col1).getSQL("'left'", "'right'");
		assertThat(actual, notNullValue());
		assertThat(actual, is("'left' = 'right'"));
	}
}
