package com.github.reels_project.reels.query;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.reels_project.reels.query.query.InvalidQueryException;
import com.github.reels_project.reels.query.query.QueryException;
import com.github.reels_project.reels.query.util.SQLUtils;
import com.sample.model.ApUser;
import com.sample.model.ApUser_;
import com.sample.model.Role;
import com.sample.model.RolePK;
import com.sample.model.Role_;
import com.sample.model.Unit_;
import com.sample.model.UserInfo_;

public class FinderTest {
	
	private Connection con;
	
	@Before
	public void before()throws Exception{
		con = DriverManager.getConnection("jdbc:h2:mem:db1","sa","");
		con.setAutoCommit(false);
		ConnectionHolder.getInstance().set(con);
		
		SQLUtils.executeResource(con, "/create-tables.sql");
		con.commit();
	}
	
	@After
	public void after()throws Exception{
		ConnectionHolder.getInstance().set(null);
		con.close();
	}

	
	@Test
	public void byId(){
		//単一主キー：正常-結果なし
		Optional<ApUser> user = ApUser_.find().byId("A0");
		
		assertThat(user, is(Optional.<ApUser>empty()));

		//単一主キー：正常-結果あり
		user = ApUser_.find().byId("A1");
		
		assertTrue(user.isPresent());
		ApUser u = user.get();
		assertThat(u.getUserId(), is("A1"));
		assertThat(u.getName(), is("ゆーざー1"));
		assertThat(u.getValidFrom().get().toString(), is("2015-01-10"));

		//複合主キー：正常-結果なし
		RolePK pk = new RolePK();
		pk.setRoleId("R0");
		pk.setUnitId("U0");
		Optional<Role> role = Role_.find().byId(pk);
		
		assertThat(role, is(Optional.<Role>empty()));
		
		//複合主キー：正常-結果あり
		pk = new RolePK();
		pk.setRoleId("R1");
		pk.setUnitId("U1");
		role = Role_.find().byId(pk);
		
		assertTrue(role.isPresent());
		Role r = role.get();
		assertNotNull(r.getId());
		assertThat(r.getId().getRoleId(), is("R1"));
		assertThat(r.getId().getUnitId(), is("U1"));
		assertThat(r.getName(), is("ろーる1"));
	}
	
	@Test
	public void byId_error(){
		//引数がnull
		try {
			ApUser_.find().byId(null);
			fail("例外が発生せず");
		} catch (NullPointerException e) {
		}
		
		//テーブルが存在しない
		try {
			UserInfo_.find().byId("A");
			fail("例外が発生せず");
		} catch (QueryException e) {
			assertThat(e.getCause(), is(instanceOf(SQLException.class)));
		}
	}
	
	@Test
	public void count(){
		long count = ApUser_.find().count();
		assertThat(count, is(4L));
	}
	
	@Test
	public void countWhere(){
		long count = ApUser_.find().countWhere(ApUser_.UNIT_ID.eq("U1"));
		assertThat(count, is(3L));
		
		count = ApUser_.find().countWhere(ApUser_.UNIT_ID.eq((String)null));
		assertThat(count, is(4L));
		
		try {
			ApUser_.find().countWhere(Unit_.UNIT_ID.eq((String)null));
			fail();
		} catch (InvalidQueryException e) {
		}
	}
	
	@Test
	public void list(){
		List<ApUser> users = ApUser_.find().list();
		
		assertFalse(users.isEmpty());
		assertThat(users.size(), is(4));
		
		ApUser user = users.get(0);
		assertNotNull(user);
		assertNotNull(user.getUserId());
		assertNotNull(user.getValidFrom());
	}
	
	@Test
	public void list_limit(){
		List<ApUser> users = ApUser_.find().list(2);
		
		assertFalse(users.isEmpty());
		assertThat(users.size(), is(2));
		assertThat(users.get(0).getUserId(), is("A1"));
		
		try {
			ApUser_.find().list(0);
			fail("例外発生せず");
		} catch (IllegalArgumentException e) {
		}
	}
	
	@Test
	public void list_limit_offset(){
		List<ApUser> users = ApUser_.find().list(2,2);
		
		assertFalse(users.isEmpty());
		assertThat(users.size(), is(2));
		assertThat(users.get(0).getUserId(), is("A3"));
		
		try {
			ApUser_.find().list(2,0);
			fail("例外発生せず");
		} catch (IllegalArgumentException e) {
		}
		
		try {
			ApUser_.find().list(-1,2);
			fail("例外発生せず");
		} catch (IllegalArgumentException e) {
		}
	}
	
	@Test
	public void singleWhere(){
		Optional<ApUser> op = ApUser_.find().where(ApUser_.UNIT_ID.eq("U2"));
		assertTrue(op.isPresent());

		op = ApUser_.find().where(ApUser_.UNIT_ID.eq("U0"));
		assertFalse(op.isPresent());

		op = ApUser_.find().where(ApUser_.UNIT_ID.eq((String)null));
		assertThat(op.get().getUserId(), is("A1"));
	}
	
	@Test
	public void listWhere(){
		List<ApUser> list = ApUser_.find().listWhere(ApUser_.UNIT_ID.eq("U1"));
		assertNotNull(list);
		assertThat(list.size(), is(3));
		
		list = ApUser_.find().listWhere(ApUser_.UNIT_ID.eq("U0"));
		assertNotNull(list);
		assertThat(list.size(), is(0));
		
		list = ApUser_.find().listWhere(ApUser_.UNIT_ID.eq((String)null));
		assertNotNull(list);
		assertThat(list.size(), is(4));
	}
	
	@Test
	public void listWhere_limit(){
		List<ApUser> list = ApUser_.find().listWhere(ApUser_.UNIT_ID.eq("U1"),2);
		assertNotNull(list);
		assertThat(list.size(), is(2));

		list = ApUser_.find().listWhere(ApUser_.UNIT_ID.eq("U0"),2);
		assertNotNull(list);
		assertThat(list.size(), is(0));

		list = ApUser_.find().listWhere(ApUser_.UNIT_ID.eq((String)null),2);
		assertNotNull(list);
		assertThat(list.size(), is(2));
		
		try {
			ApUser_.find().listWhere(ApUser_.UNIT_ID.eq("U1"),0);
			fail();
		} catch (IllegalArgumentException e) {
			
		}
	}
	
	@Test
	public void listWhere_limit_offset(){
		List<ApUser> list = ApUser_.find().listWhere(ApUser_.UNIT_ID.eq("U1"),2,2);
		assertNotNull(list);
		assertThat(list.size(), is(1));
		assertThat(list.get(0).getUserId(), is("A3"));

		list = ApUser_.find().listWhere(ApUser_.UNIT_ID.eq("U0"),2,2);
		assertNotNull(list);
		assertThat(list.size(), is(0));

		list = ApUser_.find().listWhere(ApUser_.UNIT_ID.eq((String)null),2,2);
		assertNotNull(list);
		assertThat(list.size(), is(2));
		assertThat(list.get(0).getUserId(), is("A3"));
		
		try {
			ApUser_.find().listWhere(ApUser_.UNIT_ID.eq("U1"),-1,0);
			fail();
		} catch (IllegalArgumentException e) {
			
		}
	}
}
