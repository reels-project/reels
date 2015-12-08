/**
 * 
 */
package com.github.reels_project.reels.query.query.internal.function;

import java.util.Objects;

import com.github.reels_project.reels.query.query.Func;
import com.github.reels_project.reels.query.query.QueryContext;

/**
 * @author Takahiko Sato(MOSA architect Inc.)
 *
 */
public class TrimFunc implements Func {

	@Override
	public String getSQL(QueryContext context,
			String expression) {
		return String.format("TRIM(%s)", Objects.requireNonNull(expression));
	}
}
