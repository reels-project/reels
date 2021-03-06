package com.github.reels_project.reels.query.query.internal.expression;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import com.github.reels_project.reels.query.Q;
import com.github.reels_project.reels.query.meta.IDBColumn;
import com.github.reels_project.reels.query.meta.IDBTable;
import com.github.reels_project.reels.query.meta.impl.DBTableImpl;
import com.github.reels_project.reels.query.meta.impl.StringDBColumnImpl;

public class NotLikeExpTest {
	@Test
	public void ok_constructors(){
		IDBTable t = new DBTableImpl("table1");
		IDBColumn<String> left = new StringDBColumnImpl(t, "left");
		
		new NotLikeExp(left, Q.param());
		new NotLikeExp(left, "to");
		new NotLikeExp(left, Q.select());
	}
	
	@Test
	public void ok_getSQL(){
		IDBTable t = new DBTableImpl("table1");
		IDBColumn<String> left = new StringDBColumnImpl(t, "left");
		
		String actual = new NotLikeExp(left, "LIKE").getSQL("'left'", "'right%'");
		assertThat(actual, notNullValue());
		assertThat(actual, is("'left' NOT LIKE 'right%' ESCAPE '!'"));
	}
}
