package com.github.reels_project.reels.query.query.internal.function;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.github.reels_project.reels.query.meta.impl.DBTableImpl;
import com.github.reels_project.reels.query.query.internal.DefaultQueryContext;

public class LowerFuncTest {
	
	@Test
	public void ok(){
		LowerFunc func = new LowerFunc();
		String actual = func.getSQL(new DefaultQueryContext(new DBTableImpl("table1")), "table1.num");
		
		assertThat(actual, is("LOWER(table1.num)"));
	}
	
	@Test(expected=NullPointerException.class)
	public void ng_null(){
		LowerFunc func = new LowerFunc();
		func.getSQL(new DefaultQueryContext(new DBTableImpl("table1")), null);
	}
}
