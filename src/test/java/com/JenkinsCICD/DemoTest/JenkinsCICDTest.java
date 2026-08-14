package com.JenkinsCICD.DemoTest;

import java.time.Duration;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import org.testng.SkipException;
import org.testng.annotations.Test;

public class JenkinsCICDTest {

    private boolean isJenkins() {
        return System.getenv("JENKINS_HOME") != null
                || System.getenv("BUILD_NUMBER") != null;
    }

    // ================= CHROME =================

    private WebDriver createChromeDriver() {

        ChromeOptions options = new ChromeOptions();

        // Don't wait for every image/script/resource
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        if (isJenkins()) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-first-run");
        }

        return new ChromeDriver(options);
    }


    // ================= FIREFOX =================

    private WebDriver createFirefoxDriver() {

        FirefoxOptions options = new FirefoxOptions();

        // Important for heavy websites like YouTube
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        if (isJenkins()) {
            options.addArguments("-headless");
            options.addArguments("--width=1920");
            options.addArguments("--height=1080");
        }

        return new FirefoxDriver(options);
    }


    // ================= EDGE =================

    private WebDriver createEdgeDriver() {

        EdgeOptions options = new EdgeOptions();

        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        return new EdgeDriver(options);
    }


    // ================= COMMON METHOD =================

    private void openWebsite(WebDriver driver, String url)
            throws InterruptedException {

        try {

            driver.manage()
                  .timeouts()
                  .pageLoadTimeout(Duration.ofSeconds(90));

            driver.get(url);

            Thread.sleep(2000);

        } finally {

            if (driver != null) {
                driver.quit();
            }
        }
    }


    // ==================================================
    // TEST CASES
    // ==================================================

    @Test(priority = 0)
    public void ChromeGoogleTest() throws InterruptedException {

        WebDriver driver = createChromeDriver();

        openWebsite(
                driver,
                "https://www.google.com/"
        );
    }


    @Test(priority = 1)
    public void FirefoxYouTubeTest() throws InterruptedException {

        WebDriver driver = createFirefoxDriver();

        openWebsite(
                driver,
                "https://www.youtube.com/"
        );
    }


    @Test(priority = 2)
    public void ChromeWikipediaTest() throws InterruptedException {

        WebDriver driver = createChromeDriver();

        openWebsite(
                driver,
                "https://www.wikipedia.org/"
        );
    }


    @Test(priority = 3)
    public void FirefoxStackOverflowTest() throws InterruptedException {

        WebDriver driver = createFirefoxDriver();

        openWebsite(
                driver,
                "https://stackoverflow.com/"
        );
    }


    @Test(priority = 4)
    public void EdgeGitHubTest() throws InterruptedException {

        /*
         * Jenkins is currently running as Windows SYSTEM.
         * Edge is crashing before WebDriver session creation.
         *
         * Run Edge normally from Eclipse,
         * but skip it in this Jenkins environment.
         */
        if (isJenkins()) {
            throw new SkipException(
                    "Edge skipped in Jenkins SYSTEM service environment"
            );
        }

        WebDriver driver = createEdgeDriver();

        openWebsite(
                driver,
                "https://github.com/"
        );
    }


    @Test(priority = 5)
    public void ChromeLinkedInTest() throws InterruptedException {

        WebDriver driver = createChromeDriver();

        openWebsite(
                driver,
                "https://www.linkedin.com/"
        );
    }


    @Test(priority = 6)
    public void FirefoxTwitterTest() throws InterruptedException {

        WebDriver driver = createFirefoxDriver();

        openWebsite(
                driver,
                "https://twitter.com/"
        );
    }


    @Test(priority = 7)
    public void ChromeRedditTest() throws InterruptedException {

        WebDriver driver = createChromeDriver();

        openWebsite(
                driver,
                "https://www.reddit.com/"
        );
    }
}