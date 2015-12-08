package com.github.reels_project.reels.query.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.reels_project.reels.query.ConnectionHolder;
import com.github.reels_project.reels.query.jdbc.mapper.ResultMapper;
import com.github.reels_project.reels.query.query.QueryException;

public class DefaultSQLRunner implements SQLRunner{
	private static Logger logger = LoggerFactory.getLogger(DefaultSQLRunner.class);
	
	private Connection con;
	private String sql;
	private PreparedStatement ps;
	
	private boolean closed;
	
	DefaultSQLRunner(String sql) {
		this.sql = Objects.requireNonNull(sql);
		//v0.0.3 遅延対応 ここでコネクションを取得するとインスタンスを作るスレッドと実行スレッドが別の場合別のコネクションが使われる。。
	}
	
	@Override
	public <T> Optional<T> get(List<Object> params,ResultMapper<T> mapper){
		if(closed){
			throw new IllegalStateException("SQLRunner is already closed.");
		}
		try {
			createStatement();
			logger.info("SQL={} PARAM={} QUERY_RUNNER={} PREPARED_STATEMENT={} CONNECTION={}",sql,params,this,ps,con);
			prepareParams(ps, params);
			ResultSet rs = null;
			try{
				rs = ps.executeQuery();
				if(rs.next()){
					return Optional.of(mapper.map(rs));
				}
				return Optional.empty();
			}finally{
				if(rs != null){
					rs.close();
				}
			}
		} catch (SQLException e) {
			throw new QueryException("ExecuteQuery failed.", e);
		}
	}
	
	@Override
	public <T> List<T> getList(List<Object> params,ResultMapper<T> mapper){
		if(closed){
			throw new IllegalStateException("SQLRunner is already closed.");
		}
		try {
			createStatement();
			logger.info("SQL={} PARAM={} QUERY_RUNNER={} PREPARED_STATEMENT={} CONNECTION={}",sql,params,this,ps,con);
			prepareParams(ps, params);
			ResultSet rs = null;
			try{
				rs = ps.executeQuery();
				List<T> list = new ArrayList<T>();
				while(rs.next()){
					list.add(mapper.map(rs));
				}
				return list;
			}finally{
				if(rs != null){
					rs.close();
				}
			}
		} catch (SQLException e) {
			throw new QueryException("ExecuteQuery failed.", e);
		}
	}
	
	@Override
	public int executeUpdate(List<Object> params){
		if(closed){
			throw new IllegalStateException("SQLRunner is already closed.");
		}
		try {
			createStatement();
			logger.info("SQL={} PARAM={} QUERY_RUNNER={} PREPARED_STATEMENT={} CONNECTION={}",sql,params,this,ps,con);
			prepareParams(ps, params);
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw new QueryException("ExecuteUpdate failed.", e);
		}
	}
	
	@Override
	public Long insertWithGeneratedKey(List<Object> params){
		if(closed){
			throw new IllegalStateException("SQLRunner is already closed.");
		}
		try {
			createStatement(Statement.RETURN_GENERATED_KEYS);
			logger.info("SQL={} PARAM={} QUERY_RUNNER={} PREPARED_STATEMENT={} CONNECTION={}",sql,params,this,ps,con);
			prepareParams(ps, params);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if(!rs.next()){
				throw new QueryException("Did not return a Generated key...");
			}
			Object res = rs.getObject(1);
			return ((Number)res).longValue();
		} catch (SQLException e) {
			throw new QueryException("InsertWithGeneratedKey failed.", e);
		}
	}
	
	@Override
	public void addBatch(List<Object> params){
		if(closed){
			throw new IllegalStateException("SQLRunner is already closed.");
		}
		try {
			createStatement();
			logger.info("SQL={} PARAM={} QUERY_RUNNER={} PREPARED_STATEMENT={} CONNECTION={}",sql,params,this,ps,con);
			prepareParams(ps, params);
			ps.addBatch();
		} catch (SQLException e) {
			throw new QueryException("Add batch failed.",e);
		}
	}
	
	@Override
	public void executeBatch(){
		if(closed){
			throw new IllegalStateException("SQLRunner is already closed.");
		}
		try {
			//if ps is null.
			logger.info("QUERY EXECUTE BATCH");
			ps.executeBatch();
		} catch (SQLException e) {
			throw new QueryException("Execute batch failed.",e);
		}
	}
	
	@Override
	public void close() {
		try {
			if(ps != null){
				ps.close();
			}
			closed = true;
		} catch (SQLException e) {
			throw new QueryException("Cannot close statement.", e);
		}
	}
	
	private void createStatement(){
		createStatement(null);
	}

	private void createStatement(Integer option){
		if(con == null){
			con = ConnectionHolder.getInstance().get();
			logger.debug("Got connection {}.",con);
		}
		
		if(ps == null){
			try {
				if(option == null){
					ps = con.prepareStatement(sql);
				}else{
					ps = con.prepareStatement(sql,option);
				}
			} catch (SQLException e) {
				throw new QueryException("Cannot create a PreparedStatement.", e);
			}
		}
	}

	private void prepareParams(PreparedStatement preparedStatement,List<Object> params) throws SQLException{
		//Optional及びDateTime判定済み
		Objects.requireNonNull(preparedStatement).clearParameters();
		int index = 1;
		for(Object o : params){
			preparedStatement.setObject(index++, o);
		}
	}
}
