/**
 * 
 */
package com.github.reels_project.reels.query.meta;

import com.github.reels_project.reels.query.query.QueryContext;



/**
 * @author Takahiko Sato(MOSA architect Inc.)
 *
 */
public interface IDBTable {
	String getName();
	String getAlias();
	String getQuery(QueryContext context);
}
