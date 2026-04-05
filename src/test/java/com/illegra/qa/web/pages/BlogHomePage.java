package com.illegra.qa.web.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class BlogHomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final String BASE_URL = "https://blogdoagi.com.br/";

    public BlogHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public BlogHomePage open() {
        driver.get(BASE_URL);
        waitForPageReady();
        return this;
    }

    public SearchResultsPage pesquisar(String termo) {
        String termoEncode = URLEncoder.encode(termo, StandardCharsets.UTF_8);
        String searchUrl = BASE_URL + "?s=" + termoEncode;

        driver.get(searchUrl);
        waitForPageReady();

        return new SearchResultsPage(driver);
    }

    private void waitForPageReady() {
        wait.until(d -> {
            Object state = ((JavascriptExecutor) d).executeScript("return document.readyState");
            return "complete".equals(state);
        });
    }
}