package net.caimito.ale_news.web_client;

import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.caimito.ale_news.article_service.ArticleMetadata;
import org.apache.commons.io.FileUtils;
import org.hamcrest.CoreMatchers;
import org.hamcrest.collection.IsEmptyCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class StepDefinitions {
    private DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
    private PhantomJSDriver driver = new PhantomJSDriver(capabilities);

    @When("^I submit the following article metadata$")
    public void i_submit_the_following_article_metadata(List<ArticleMetadata> articleMetadataList) throws Throwable {
        ArticleMetadata metadata = articleMetadataList.get(0) ;

        driver.get("http://localhost:8080/app/#/article/add");

        driver.findElement(By.id("author")).sendKeys(metadata.getAuthor());
        driver.findElement(By.id("title")).sendKeys(metadata.getTitle());
        driver.findElement(By.id("location")).sendKeys(metadata.getLocation());
        driver.findElement(By.id("save")).click();
    }

    @When("^I open ALE News$")
    public void i_open_ALE_News() throws Throwable {
        driver.get("http://localhost:8080/app");
    }

    @Then("^I see a list of articles$")
    public void i_see_a_list_of_articles() throws Throwable {
        assertThat(driver.findElements(By.id("articleMetadata")), is(CoreMatchers.not(IsEmptyCollection.empty()))) ;
    }

    @Then("^the article metadata has been stored in the archive$")
    public void the_article_metadata_has_been_stored_in_the_archive() throws Throwable {
        GenericType<List<ArticleMetadata>> articleListType = new GenericType<List<ArticleMetadata>>() {};

        List<ArticleMetadata> actualArticles = ClientBuilder.newClient()
                .target("http://localhost:8080/article-service").path("/article")
                .request(MediaType.APPLICATION_JSON).get(articleListType) ;

        assertThat(actualArticles.size(), is(not(0))) ;
    }

    @After
    public void takeScreenshot(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            File screenshot = driver.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFileToDirectory(screenshot, new File("target"));
        }
    }

    @Given("^there are no articles in the archive$")
    public void there_are_no_articles_in_the_archive() throws Throwable {
        GenericType<List<ArticleMetadata>> articleListType = new GenericType<List<ArticleMetadata>>() {};

        List<ArticleMetadata> actualArticles = ClientBuilder.newClient()
                .target("http://localhost:8080/article-service").path("/article")
                .request(MediaType.APPLICATION_JSON).get(articleListType) ;

        for (ArticleMetadata metadata : actualArticles) {
            ClientBuilder.newClient().target("http://localhost:8080/article-service")
                    .path("/article/" + metadata.getId())
                    .request()
                    .delete() ;
        }
    }
}
