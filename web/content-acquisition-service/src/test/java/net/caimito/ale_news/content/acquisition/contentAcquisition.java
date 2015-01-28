package net.caimito.ale_news.content.acquisition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ContentAcquisition {
    protected static String serviceBaseUrl = "http://localhost:8080/content-acquisition" ;

    protected String sourceId ;

    @Given("^the HTML source \"([^\"]*)\"$")
    public void the_HTML_source(String sourceUrl) throws Throwable {
        HtmlContentSourceResource htmlContentSourceResource = new HtmlContentSourceResource() ;
        htmlContentSourceResource.setSourceType(ContentSourceType.HTML) ;
        htmlContentSourceResource.setLocation(sourceUrl) ;

        HtmlContentSourceResource sourceCreated = ClientBuilder.newClient()
                .target(serviceBaseUrl).path("source")
                .request(MediaType.APPLICATION_JSON).post(Entity.entity(htmlContentSourceResource, MediaType.APPLICATION_JSON))
                .readEntity(HtmlContentSourceResource.class);
        assertThat(sourceCreated, is(notNullValue())) ;

        sourceId = sourceCreated.getSourceId() ;
    }

    @When("^a poll is triggered$")
    public void a_poll_is_triggered() throws Throwable {
        HtmlContentSourceResource htmlContentSourceResource = new HtmlContentSourceResource() ;
        htmlContentSourceResource.setActive(true) ;

        ClientBuilder.newClient()
                .target(serviceBaseUrl).path("source/" + sourceId)
                .request().method("PATCH", Entity.entity(htmlContentSourceResource, MediaType.APPLICATION_JSON)) ;

    }

}
