package com.github.reels_project.reels.faces.view;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;


public class NavigationTest {
	
	@Test(expected=IllegalArgumentException.class)
	public void constructor_emptyViewId(){
		new Navigation("");
	}

	@Test(expected=IllegalArgumentException.class)
	public void constructor_emptyViewId2(){
		new Navigation("",true);
	}

	@Test(expected=NullPointerException.class)
	public void constructor_nullViewId(){
		new Navigation(null);
	}
	
	@Test(expected=NullPointerException.class)
	public void constructor_nullViewId2(){
		new Navigation(null,true);
	}
	
	@Test
	public void redirect(){
		String outcome = new Navigation("index.xhtml",true).getOutcome();
		
		assertNotNull(outcome);
		assertThat(outcome, is("index.xhtml?faces-redirect=true"));
	}
	
	@Test
	public void redirectWithParam(){
		String outcome = new Navigation("index.xhtml",true)
				.withParam("a", "b")
				.getOutcome();
		
		assertNotNull(outcome);
		assertThat(outcome, is("index.xhtml?faces-redirect=true&a=b"));
	}

	@Test
	public void render(){
		String outcome = new Navigation("index.xhtml").getOutcome();
		
		assertNotNull(outcome);
		assertThat(outcome, is("index.xhtml"));
	}
	
	@Test
	public void renderWithParam(){
		String outcome = new Navigation("index.xhtml")
				.withParam("a", "b")
				.getOutcome();
		
		assertNotNull(outcome);
		assertThat(outcome, is("index.xhtml?a=b"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void withParam_keyIsNull(){
		new Navigation("index.xhtml")
				.withParam(null, "b");
	}

	@Test(expected=IllegalArgumentException.class)
	public void withParam_keyIsEmpty(){
		new Navigation("index.xhtml")
				.withParam(null, "");
	}
	
	@Test
	public void withParam_overwriteKey(){
		String outcome = new Navigation("index.xhtml")
				.withParam("a", "hoge")
				.withParam("a", "piyo")
				.getOutcome();
		
		assertNotNull(outcome);
		assertThat(outcome, is("index.xhtml?a=piyo"));
	}
	
	@Test
	public void withParam_multipulParams(){
		String outcome = new Navigation("index.xhtml")
				.withParam("a", "hoge")
				.withParam("b", "piyo")
				.getOutcome();
		
		assertNotNull(outcome);
		assertThat(outcome, is("index.xhtml?a=hoge&b=piyo"));
	}

	@Test
	public void withParam_valueIsNull(){
		String outcome = new Navigation("index.xhtml")
				.withParam("a", null)
				.getOutcome();
		
		assertNotNull(outcome);
		assertThat(outcome, is("index.xhtml"));
	}

}
