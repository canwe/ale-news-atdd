package net.caimito.ale_news.web_client;

import cucumber.api.java.After;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertThat;

public class StepDefinitions {
    private DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
    private PhantomJSDriver driver = new PhantomJSDriver(capabilities);

    @When("^I open ALE News$")
    public void i_open_ALE_News() throws Throwable {
        driver.get("http://localhost:8080/web-client");
    }

    @Then("^I see a list of articles$")
    public void i_see_a_list_of_articles() throws Throwable {
        assertThat(driver.findElements(By.id("articleMetadata")), is(not(empty()))) ;
    }

    @After
    public void takeScreenshot() throws IOException {
        File screenshot = driver.getScreenshotAs(OutputType.FILE) ;
        FileUtils.copyFileToDirectory(screenshot, new File("target"));
    }
}
