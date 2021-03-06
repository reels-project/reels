/**
 * 
 */
package com.github.reels_project.reels.query.query.internal;


import com.github.reels_project.reels.query.meta.IDBColumn;
import com.github.reels_project.reels.query.query.InvalidQueryException;
import com.github.reels_project.reels.query.query.Order;
import com.github.reels_project.reels.query.query.QueryContext;

/**
 * @author Takahiko Sato(MOSA architect Inc.)
 *
 */
public class OrderImpl implements Order{
	private IDBColumn<?> c;
	private Type type;
	
	public OrderImpl(IDBColumn<?> c,Type type) {
		if(c == null){
			throw new InvalidQueryException("join target is null.");
		}
		
		if(type == null){
			type = Type.ASC;
		}
		
		this.c = c;
		this.type = type;
	}

	@Override
	public String getOrder(QueryContext context) {
		if(type == Type.DESC){
			return context.getColumnPath(c) + " DESC";
		}
		return context.getColumnPath(c) + " ASC";
	}
}
