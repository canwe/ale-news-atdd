package net.caimito.ale_news.article_service;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ArticleStepDefinitions {

	protected ArticleMetadata expectedMetadata;
	protected ArticleMetadata actualMetadata;

	@Given("^I have scooped an article on the Internet with the following metadata:$")
	public void i_have_scooped_an_article_on_the_Internet_with_the_following_metadata(List<ArticleMetadata> metadata)
			throws Throwable {
		expectedMetadata = metadata.get(0);
	}

	@When("^I post the article metadata to the article service with URI \"(.*?)\"$")
	public void i_call_the_article_service_with_URI_and_verb(String uri) throws Throwable {
		Client client = ClientBuilder.newClient();

//		WebTarget target = client.target("http://localhost:8080/article-service").path("/helloworld");
//		Response response = target.request(MediaType.TEXT_PLAIN).get() ;
//		assertThat(response.getStatus(), equalTo(200)) ;
		
		WebTarget target = client.target("http://localhost:8080/article-service").path(uri);

		Form form = new Form();
		form.param("author", expectedMetadata.author);
		form.param("location", expectedMetadata.location);
		form.param("title", expectedMetadata.title) ;

		actualMetadata = target.request(MediaType.APPLICATION_JSON).post(
				Entity.entity(form, MediaType.APPLICATION_JSON), ArticleMetadata.class);
	}

	@Then("^the article metadata has been stored in the archive$")
	public void the_article_metadata_has_been_stored_in_the_archive() throws Throwable {
		assertThat(actualMetadata, equalTo(expectedMetadata));
	}
	
	@Given("^some article metadata with key \"(.*?)\" exists in the archive$")
	public void some_article_metadata_with_key_exists_in_the_archive(String arg1) throws Throwable {
		expectedMetadata = new ArticleMetadata() ;
		expectedMetadata.author = "Stephan" ;
		expectedMetadata.location = "http://localhost" ;
		expectedMetadata.title = "Something" ;
	}
	
	@When("^I ask the article service with URI \"(.*?)\" for article id \"(.*?)\"$")
	public void i_ask_the_article_service_with_URI_for_article_id(String uri, String articleId) throws Throwable {
		actualMetadata = ClientBuilder.newClient()
	            .target("http://localhost:8080/article-service").path(uri)
	                        .request().get(ArticleMetadata.class);
	}

	@Then("^I receive the article metadata$")
	public void i_receive_the_article_metadata() throws Throwable {
		assertThat(actualMetadata, equalTo(expectedMetadata));
	}
}
