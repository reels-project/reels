/**
 * 
 */
package com.github.reels_project.reels.query.query.internal.function;

import java.util.Objects;

import com.github.reels_project.reels.query.query.Func;
import com.github.reels_project.reels.query.query.QueryContext;
import com.github.reels_project.reels.query.query.internal.QueryUtils;

/**
 * @author Takahiko Sato(MOSA architect Inc.)
 *
 */
public class ToNumberFunc implements Func {
	
	private String format;
	
	public ToNumberFunc() {
	}
	
	public ToNumberFunc(String format) {
		this.format = format;
	}
	
	@Override
	public String getSQL(QueryContext context,
			String expression) {
		Objects.requireNonNull(expression);
		if(format != null){
			return String.format("TO_NUMBER(%s,%s)", expression,QueryUtils.literal(format));
		}
		return String.format("TO_NUMBER(%s)", expression);
	}
}
