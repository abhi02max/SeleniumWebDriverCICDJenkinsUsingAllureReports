# Selenium Test Automation with Jenkins and Allure

A Java-based UI automation project demonstrating how Selenium tests can be executed through a CI/CD workflow and reported with Allure.

## What this project demonstrates

- Browser automation with Selenium WebDriver
- Test organisation and execution using TestNG
- Maven-based dependency and build management
- Automated test execution from Jenkins
- Allure result generation and readable test reports
- Reusable test structure for regression suites

## Technology

Java · Selenium WebDriver · TestNG · Maven · Jenkins · Allure

## Run locally

```bash
mvn clean test
```

After execution, generate or serve the Allure report using an installed Allure CLI:

```bash
allure serve allure-results
```

## CI workflow

Jenkins checks out the repository, installs dependencies through Maven, executes the tests, preserves results, and publishes the Allure report. Browser-driver and runtime requirements may vary by environment.

## Repository notes

Generated report and build directories are retained here for training/reference purposes. In a production framework they would normally be excluded from source control and published as CI artifacts.
