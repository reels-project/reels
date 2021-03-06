/**
 * 
 */
package com.github.reels_project.reels.query.query.internal.expression;

import com.github.reels_project.reels.query.meta.IDBColumn;
import com.github.reels_project.reels.query.query.Exp;
import com.github.reels_project.reels.query.query.QueryContext;

/**
 * @author Takahiko Sato(MOSA architect Inc.)
 *
 */
public class IsNullExp<T extends Comparable<? super T>> implements Exp{
	
	private IDBColumn<T> c;
	
	public IsNullExp(IDBColumn<T> c) {
		if(c == null){
			throw new NullPointerException();
		}
		this.c = c;
	}
	
	@Override
	public String getSQL(QueryContext context) {
		return String.format("%s IS NULL",context.getColumnPath(c));
	}
}
