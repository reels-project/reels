/**
 * 
 */
package com.github.reels_project.reels.query;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import com.github.reels_project.reels.query.helper.BatchQueryExecutorHelper;
import com.github.reels_project.reels.query.helper.ReusableQueryExecutorHelper;
import com.github.reels_project.reels.query.meta.IComparableDBColumn;
import com.github.reels_project.reels.query.meta.IDBColumn;
import com.github.reels_project.reels.query.meta.INumberDBColumn;
import com.github.reels_project.reels.query.meta.IStringDBColumn;
import com.github.reels_project.reels.query.query.BatchQueryExecutor;
import com.github.reels_project.reels.query.query.Exp;
import com.github.reels_project.reels.query.query.NamedQuery;
import com.github.reels_project.reels.query.query.Param;
import com.github.reels_project.reels.query.query.SearchedCase;
import com.github.reels_project.reels.query.query.SimpleCase;
import com.github.reels_project.reels.query.query.StringQuery;
import com.github.reels_project.reels.query.query.TypesafeQuery;
import com.github.reels_project.reels.query.query.TypesafeQueryFactory;
import com.github.reels_project.reels.query.query.internal.DefaultNamedQuery;
import com.github.reels_project.reels.query.query.internal.DefaultSearchedCase;
import com.github.reels_project.reels.query.query.internal.DefaultSimpleCase;
import com.github.reels_project.reels.query.query.internal.DefaultStringQuery;
import com.github.reels_project.reels.query.query.internal.ParamImpl;
import com.github.reels_project.reels.query.query.internal.ReusableQueryExecutor;
import com.github.reels_project.reels.query.query.internal.expression.AndExp;
import com.github.reels_project.reels.query.query.internal.expression.ExistsExp;
import com.github.reels_project.reels.query.query.internal.expression.NotExistsExp;
import com.github.reels_project.reels.query.query.internal.expression.OrExp;

/**
 * シンプルなクエリを記述するためのヘルパークラスです。
 * @author Takahiko Sato(MOSA architect Inc.)
 *
 */
public final class Q {
	
	private Q() {}
	
	/**
	 * {@link TypesafeQuery}を返します。
	 * @return {@link TypesafeQuery}
	 */
	public static TypesafeQuery select(){
		return TypesafeQueryFactory.get();
	}
	
	/**
	 * {@link TypesafeQuery}を返します。
	 * @param columns select対象のカラム
	 * @return {@link TypesafeQuery}
	 */
	public static TypesafeQuery select(IDBColumn<?>... columns){
		return TypesafeQueryFactory.get(columns);
	}
	
	/**
	 * {@link NamedQuery}を返します。
	 * @param name クエリ名
	 * @return {@link NamedQuery}
	 */
	public static NamedQuery namedQuery(String name){
		return new DefaultNamedQuery(name);
	}
	
	public static StringQuery stringQuery(String sql){
		return new DefaultStringQuery(sql);
	}
	
	public static ReusableQueryExecutorHelper prepare(ReusableQueryExecutor executor){
		return new ReusableQueryExecutorHelper(executor);
	}
	
	public static BatchQueryExecutorHelper prepare(BatchQueryExecutor executor){
		return new BatchQueryExecutorHelper(executor);
	}
	
	public static SearchedCase case_(){
		return new DefaultSearchedCase();
	}
	
	public static <T> SimpleCase<T> case_(IDBColumn<T> col){
		return new DefaultSimpleCase<T>(col);
	}

	/**
	 * {@link Param}を返します。
	 * @return {@link Param}
	 */
	public static Param param(){
		return new ParamImpl();
	}
	
	/**
	 * エイリアス付きの{@link IDBColumn}を返します。
	 * @param alias エイリアス
	 * @param v DBカラム
	 * @param <T> DBカラムの型
	 * @param <V> DBカラム
	 * @return {@link IDBColumn}
	 */
	
	public static <T,V extends IDBColumn<T>> V $(String alias,V v){
		return v.createFromTableAlias(alias);
	}
	
	/**
	 * AND式を返します。
	 * @param exps ANDで結合する条件
	 * @return AND
	 */
	public static Exp and(Exp...exps){
		return new AndExp(exps);
	}
	
	/**
	 * OR式を返します。
	 * @param exps ORで結合する条件
	 * @return OR
	 */
	public static Exp or(Exp...exps){
		return new OrExp(exps);
	}
	
	/**
	 * EXISTS式を返します。
	 * @param exp サブクエリ
	 * @return EXISTS
	 */
	public static Exp exists(TypesafeQuery exp){
		return new ExistsExp(exp);
	}
	
	/**
	 * NOT EXISTS式を返します。
	 * @param exp サブクエリ
	 * @return NOT EXISTS
	 */
	public static Exp notExists(TypesafeQuery exp){
		return new NotExistsExp(exp);
	}
	
	//--->comparable functions
	/**
	 * coalsesce式を返します。
	 * @param r デフォルト値
	 * @param v 対象DBカラム
	 * @param <V> DBカラムの型
	 * @param <R> DBカラム
	 * @return coalsesce
	 */
	public static <V extends Comparable<? super V>,R extends IComparableDBColumn<V>> R coalsesce(R r,V v){
		return r.coalesce(v);
	}
	
	/**
	 * coalsesce式を返します。
	 * @param r デフォルトDBカラム
	 * @param v 対象DBカラム
	 * @param <V> DBカラムの型
	 * @param <R> DBカラム
	 * @return coalsesce
	 */
	public static <V extends Comparable<? super V>,R extends IComparableDBColumn<V>> R coalsesce(R r,R v){
		return r.coalesce(v);
	}
	
	//--->numeric functions
	/**
	 * MAXを返します。
	 * @param c 対象DBカラム
	 * @param <T> DBカラムの型
	 * @return MAX
	 */
	public static <T extends Number & Comparable<? super T>> INumberDBColumn<T> max(INumberDBColumn<T> c){
		return c.max();
	}
	
	/**
	 * MINを返します。
	 * @param c 対象DBカラム
	 * @param <T> DBカラムの型
	 * @return MIN
	 */
	public static <T extends Number & Comparable<? super T>> INumberDBColumn<T> min(INumberDBColumn<T> c){
		return c.min();
	}
	
	/**
	 * ABSを返します。
	 * @param c 対象DBカラム
	 * @param <T> DBカラムの型
	 * @return ABS
	 */
	public static <T extends Number & Comparable<? super T>> INumberDBColumn<T> abs(INumberDBColumn<T> c){
		return c.abs();
	}
	
	/**
	 * AVGを返します。
	 * @param c 対象DBカラム
	 * @param <T> DBカラムの型
	 * @return AVG
	 */
	public static <T extends Number & Comparable<? super T>> INumberDBColumn<T> avg(INumberDBColumn<T> c){
		return c.avg();
	}
	
	/**
	 * SQRTを返します。
	 * @param c 対象DBカラム
	 * @param <T> DBカラムの型
	 * @return SQRT
	 */
	public static <T extends Number & Comparable<? super T>> INumberDBColumn<T> sqrt(INumberDBColumn<T> c){
		return c.sqrt();
	}
	
	//---->string functions
	/**
	 * LOWERを返します。
	 * @param c 対象DBカラム
	 * @return LOWER
	 */
	public static IStringDBColumn lower(IStringDBColumn c){
		return c.lower();
	}
	
	/**
	 * UPPERを返します。
	 * @param c 対象DBカラム
	 * @return UPPER
	 */
	public static IStringDBColumn upper(IStringDBColumn c){
		return c.upper();
	}
	
	/**
	 * TRIMを返します。
	 * @param c 対象DBカラム
	 * @return TRIM
	 */
	public static IStringDBColumn trim(IStringDBColumn c){
		return c.trim();
	}
	
	/**
	 * LENGTHを返します。
	 * @param c 対象DBカラム
	 * @return LENGTH
	 */
	public static INumberDBColumn<Integer> length(IStringDBColumn c){
		return c.length();
	}
	
	/**
	 * CONCATを返します。
	 * @param c 対象DBカラム
	 * @param s 結合対象カラム
	 * @return CONCAT
	 */
	public static IStringDBColumn concat(IStringDBColumn c,IStringDBColumn s){
		return c.concat(s);
	}
	
	/**
	 * CONCATを返します。
	 * @param c 対象DBカラム
	 * @param s 結合文字列
	 * @return CONCAT
	 */
	public static IStringDBColumn concat(IStringDBColumn c,String s){
		return c.concat(s);
	}
	
	/**
	 * TO_NUMBERを返します。
	 * @param c 対象DBカラム
	 * @return TO_NUMBER
	 */
	public static INumberDBColumn<Long> toNumber(IStringDBColumn c){
		return c.toNumber();
	}
	
	/**
	 * TO_NUMBERを返します。
	 * @param c 対象DBカラム
	 * @param format フォーマット
	 * @return TO_NUMBER
	 */
	public static INumberDBColumn<Long> toNumber(IStringDBColumn c,String format){
		return c.toNumber(format);
	}
	
	/**
	 * TO_NUMBERを返します。
	 * @param c 対象DBカラム
	 * @param cls 型
	 * @param <T> DBカラムの型
	 * @return TO_NUMBER
	 */
	public static <T extends Number & Comparable<? super T>> INumberDBColumn<T> toNumber(IStringDBColumn c,Class<T> cls){
		return c.toNumber(cls);
	}
	
	/**
	 * TO_NUMBERを返します。
	 * @param c 対象DBカラム
	 * @param cls 型
	 * @param format フォーマット
	 * @param <T> DBカラムの型
	 * @return TO_NUMBER
	 */
	public static <T extends Number & Comparable<? super T>> INumberDBColumn<T> toNumber(IStringDBColumn c,Class<T> cls,String format){
		return c.toNumber(cls,format);
	}
	
	/**
	 * SUBSTRを返します。
	 * @param c 対象DBカラム
	 * @param from 開始位置
	 * @return SUBSTR
	 */
	public static IStringDBColumn substr(IStringDBColumn c,int from){
		return c.substring(from);
	}
	
	/**
	 * SUBSTRを返します。
	 * @param c 対象DBカラム
	 * @param from 開始位置
	 * @param to 終了位置
	 * @return SUBSTR
	 */
	public static IStringDBColumn substr(IStringDBColumn c,int from,int to){
		return c.substring(from, to);
	}
	
	/**
	 * SUBSTRを返します。
	 * @param c 対象DBカラム
	 * @param from 開始位置
	 * @return SUBSTR
	 */
	public static IStringDBColumn substr(IStringDBColumn c,INumberDBColumn<Integer> from){
		return c.substring(from);
	}
	
	/**
	 * SUBSTRを返します。
	 * @param c 対象DBカラム
	 * @param from 開始位置
	 * @param to 終了位置
	 * @return SUBSTR
	 */
	public static IStringDBColumn substr(IStringDBColumn c,INumberDBColumn<Integer> from,INumberDBColumn<Integer> to){
		return c.substring(from, to);
	}
	
	/**
	 * UPDATE文のset句を返します。
	 * @param eqExps セットする値
	 * @return SET
	 */
	public static Set<Exp> set(Exp...eqExps){
		if(eqExps == null){
			return new HashSet<Exp>();
		}
		Set<Exp> set = new LinkedHashSet<Exp>();
		Collections.addAll(set, eqExps);
		return set;
	}
}
