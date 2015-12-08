package com.github.reels_project.reels.query.query;

import com.github.reels_project.reels.query.meta.IDBTable;

/**
 * JOINをあらわすインターフェースです。
 * @author Takahiko Sato(MOSA architect Inc.)
 *
 */
public interface Join<T>{
	
	/**
	 * ON句を指定します。
	 * @param exp 式
	 * @return T
	 */
	T on(Exp exp);
	
	/**
	 * JOIN対象テーブルを返します
	 * @return JOIN対象テーブル
	 */
	IDBTable getTargetTable();
	
	/**
	 * ON句を返します
	 * @return ON句
	 */
	Exp getOn();
}
