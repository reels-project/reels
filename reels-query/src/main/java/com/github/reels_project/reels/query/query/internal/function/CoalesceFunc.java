/**
 * 
 */
package com.github.reels_project.reels.query.query.internal.function;

import com.github.reels_project.reels.query.meta.IDBColumn;
import com.github.reels_project.reels.query.query.Func;
import com.github.reels_project.reels.query.query.QueryContext;
import com.github.reels_project.reels.query.query.internal.QueryUtils;

/**
 * @author Takahiko Sato(MOSA architect Inc.)
 *
 */
public class CoalesceFunc implements Func {
	
	private IDBColumn<?> defValue;
	private Object defObject;
	
	public CoalesceFunc(IDBColumn<?> defValue) {
		this.defValue = defValue;
	}
	public CoalesceFunc(Object defObject) {
		this.defObject = defObject;
	}

	@Override
	public String getSQL(QueryContext context,
			String expression) {
		if(defValue == null && defObject == null){
			return "COALESCE(" + expression + ",NULL)";
		}
		
		if(defValue != null){
			return "COALESCE(" + expression + "," + context.getColumnPath(defValue) + ")";
		}
		return "COALESCE(" + expression + "," + QueryUtils.literal(defObject) + ")";
	}
}
