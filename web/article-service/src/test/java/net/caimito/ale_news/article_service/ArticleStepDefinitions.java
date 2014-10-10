package net.caimito.ale_news.article_service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Vector;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ArticleStepDefinitions {

	protected ArticleMetadata expectedMetadata;
	protected ArticleMetadata actualMetadata;
	protected ArticleId actualArticleId ;
	protected List<ArticleMetadata>  actualArticles ;

	@When("^I post the following article metadata to the article service with URI \"(.*?)\"$")
	public void postArticle(String uri, List<ArticleMetadata> metadata) throws Throwable {
		expectedMetadata = metadata.get(0);
		actualArticleId = ClientBuilder.newClient()
	            .target("http://localhost:8080/article-service").path(uri)
	            .request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(expectedMetadata, MediaType.APPLICATION_JSON))
	            .readEntity(ArticleId.class);
	}

	@Then("^an article ID is returned$")
	public void an_article_ID_is_returned() throws Throwable {
		assertThat(actualArticleId.getId(), is(notNullValue())) ;
	}
	
	@Then("^the article metadata has been stored in the archive$")
	public void the_article_metadata_has_been_stored_in_the_archive() throws Throwable {
		expectedMetadata.setId(actualArticleId.getId());
		actualMetadata = ClientBuilder.newClient()
	            .target("http://localhost:8080/article-service").path("/article/" + actualArticleId.getId())
	                        .request().get(ArticleMetadata.class);
		assertThat(actualMetadata, equalTo(expectedMetadata));
	}
	
	@Given("^some article metadata with key \"(.*?)\" exists in the archive$")
	public void some_article_metadata_with_key_exists_in_the_archive(String articleId) throws Throwable {
		expectedMetadata = new ArticleMetadata() ;
		expectedMetadata.setAuthor("Stephan") ;
		expectedMetadata.setLocation("http://localhost") ;
		expectedMetadata.setTitle("Something") ;
		expectedMetadata.setId(articleId);

		// todo: fake
		ArticleId id = ClientBuilder.newClient()
	            .target("http://localhost:8080/article-service").path("/article/fakeCreate")
	            .request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(expectedMetadata, MediaType.APPLICATION_JSON))
	            .readEntity(ArticleId.class);
	}
	
	@When("^I ask the article service with URI \"(.*?)\"$")
	public void i_ask_the_article_service_with_URI_for_article_id(String uri) throws Throwable {
		actualMetadata = ClientBuilder.newClient()
	            .target("http://localhost:8080/article-service").path(uri)
	                        .request().get(ArticleMetadata.class);
	}

	@Then("^I receive the article metadata$")
	public void i_receive_the_article_metadata() throws Throwable {
		assertThat(actualMetadata, equalTo(expectedMetadata));
	}
	
	@Given("^the article with key \"(.*?)\" exists in the archive$")
	public void the_article_with_key_exists_in_the_archive(String articleId, List<ArticleMetadata> metadata) throws Throwable {
		ArticleMetadata articleMetadata = metadata.get(0) ;
		articleMetadata.setId(articleId);
		
		ArticleId id = ClientBuilder.newClient()
	            .target("http://localhost:8080/article-service").path("/article/fakeCreate")
	            .request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(articleMetadata, MediaType.APPLICATION_JSON))
	            .readEntity(ArticleId.class);
	}

	@When("^I update details of the article with URI \"(.*?)\"$")
	public void i_update_details_of_the_article_with_URI(String uri, List<ArticleMetadata> metadata) throws Throwable {
		ArticleMetadata articleMetadata = metadata.get(0) ;
		ArticleId id = ClientBuilder.newClient()
	            .target("http://localhost:8080/article-service").path(uri)
	            .request(MediaType.APPLICATION_JSON_TYPE).put(Entity.entity(articleMetadata, MediaType.APPLICATION_JSON))
	            .readEntity(ArticleId.class);
	}

	@Then("^the metadata for article \"(.*?)\" in the archive is$")
	public void the_article_metadata_in_the_archive_is(String articleId, List<ArticleMetadata> metadata) throws Throwable {
		ArticleMetadata expectedMetadata = metadata.get(0) ;

		actualMetadata = ClientBuilder.newClient()
	            .target("http://localhost:8080/article-service").path("/article/" + articleId)
	                        .request().get(ArticleMetadata.class);
		assertThat(actualMetadata.getAuthor(), equalTo(expectedMetadata.getAuthor()));
	}
	
	@When("^I delete the article with key \"(.*?)\"$")
	public void i_delete_the_article_with_key(String id) throws Throwable {
		ClientBuilder.newClient()
	            .target("http://localhost:8080/article-service").path("/article/" + id)
	                        .request().delete();
	}

	@Then("^the article with key \"(.*?)\" does not exist$")
	public void the_article_with_key_does_not_exist(String id) throws Throwable {
		actualMetadata = ClientBuilder.newClient()
	            .target("http://localhost:8080/article-service").path("/article/" + id)
	                        .request().get(ArticleMetadata.class);
		assertThat(actualMetadata, is(nullValue()));
	}
	
	@Given("^some articles exist in the archive$")
	public void some_articles_exist_in_the_archive() throws Throwable {
		ArticleMetadata articleMetadata = new ArticleMetadata() ;
		articleMetadata.setAuthor("Joe");
		articleMetadata.setLocation("http://localhost");
		articleMetadata.setTitle("Something");
		articleMetadata.setId(UUID.randomUUID().toString());
		
		ArticleId id = ClientBuilder.newClient()
	            .target("http://localhost:8080/article-service").path("/article/fakeCreate")
	            .request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(articleMetadata, MediaType.APPLICATION_JSON))
	            .readEntity(ArticleId.class);
	}

	@When("^I ask for a list of articles with URI \"(.*?)\"$")
	public void i_ask_for_a_list_of_articles_with_URI(String uri) throws Throwable {
		GenericType<List<ArticleMetadata>> articleListType = new GenericType<List<ArticleMetadata>>() {};
		
		actualArticles = ClientBuilder.newClient()
	            .target("http://localhost:8080/article-service").path("/article")
	                        .request().get(articleListType) ;
	}

	@Then("^I will receive a list of articles$")
	public void i_will_receive_a_list_of_articles() throws Throwable {
		assertThat(actualArticles, is(notNullValue())) ;
		assertThat(actualArticles.size(), is(not(0))) ;
	}
}
