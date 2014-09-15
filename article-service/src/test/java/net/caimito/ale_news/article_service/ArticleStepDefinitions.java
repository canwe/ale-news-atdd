package net.caimito.ale_news.article_service;

import static org.junit.Assert.assertThat ;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ArticleStepDefinitions {
	
	protected ArticleMetadata expectedMetadata ;
	protected ArticleMetadata actualMetadata ;
	
	@Given("^I have scooped an article on the Internet with the following metadata:$")
	public void i_have_scooped_an_article_on_the_Internet_with_the_following_metadata(List<ArticleMetadata> metadata) throws Throwable {
		expectedMetadata = metadata.get(0) ;
	}
	
	@When("^I call the article service with URI \"(.*?)\" and verb \"(.*?)\"$")
	public void i_call_the_article_service_with_URI_and_verb(String arg1, String arg2) throws Throwable {
		actualMetadata = new ArticleMetadata() ;
	}

	@Then("^the article metadata has been stored in the archive$")
	public void the_article_metadata_has_been_stored_in_the_archive() throws Throwable {
		assertThat(actualMetadata, equalTo(expectedMetadata)) ;
	}
}
