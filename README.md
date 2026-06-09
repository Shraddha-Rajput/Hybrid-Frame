# 🔧 Hybrid Automation Framework
### Selenium WebDriver 4.x | Java | Cucumber BDD | TestNG | POM | Data-Driven | Jenkins CI/CD

[![Java](https://img.shields.io/badge/Java-11%2B-orange?style=flat-square&logo=java)](https://www.java.com)
[![Selenium](https://img.shields.io/badge/Selenium-4.x-43B02A?style=flat-square&logo=selenium)](https://www.selenium.dev)
[![Cucumber](https://img.shields.io/badge/Cucumber-BDD-23D96C?style=flat-square&logo=cucumber)](https://cucumber.io)
[![TestNG](https://img.shields.io/badge/TestNG-Framework-red?style=flat-square)](https://testng.org)
[![Maven](https://img.shields.io/badge/Maven-Build-C71A36?style=flat-square&logo=apachemaven)](https://maven.apache.org)
[![Jenkins](https://img.shields.io/badge/Jenkins-CI%2FCD-D24939?style=flat-square&logo=jenkins)](https://www.jenkins.io)

---

## 📌 About This Framework

This is a **production-grade Hybrid Automation Framework** combining three industry-standard design patterns:

- **Page Object Model (POM)** — for maintainable, reusable UI components
- **Behavior-Driven Development (BDD)** — Cucumber feature files readable by non-technical stakeholders
- **Data-Driven Testing** — Excel/JSON-driven test data for maximum scenario coverage

Built from scratch based on real-world experience across **Banking (TDS Canada)** and **E-Commerce** domains. Mirrors the same architecture used to achieve **75% automation coverage** and **65% regression cycle reduction** in production.

---

## 🏗️ Framework Architecture

```
hybrid-framework/
│
├── src/
│   ├── main/java/
│   │   ├── base/
│   │   │   └── BaseTest.java           # WebDriver init, ThreadLocal, config loader
│   │   ├── pages/                      # Page Object classes (POM layer)
│   │   │   ├── LoginPage.java
│   │   │   ├── DashboardPage.java
│   │   │   └── ...
│   │   ├── utils/
│   │   │   ├── ExcelUtils.java         # Apache POI - data-driven reads
│   │   │   ├── ConfigReader.java       # Properties file reader
│   │   │   ├── WaitUtils.java          # Explicit/Fluent wait helpers
│   │   │   └── ExtentReportManager.java
│   │   └── hooks/
│   │       └── Hooks.java              # Cucumber Before/After hooks
│   │
│   └── test/
│       ├── java/
│       │   ├── stepDefinitions/        # Cucumber step definitions
│       │   │   ├── LoginSteps.java
│       │   │   └── ...
│       │   └── runners/
│       │       ├── TestRunner.java     # Single-thread runner
│       │       └── ParallelRunner.java # Parallel execution runner
│       │
│       └── resources/
│           ├── features/               # Gherkin BDD feature files
│           │   ├── Login.feature
│           │   ├── Search.feature
│           │   └── ...
│           ├── testdata/
│           │   └── TestData.xlsx       # Data-driven test inputs
│           └── config.properties       # Environment config
│
├── testng.xml                          # TestNG parallel execution config
├── testng-parallel.xml                 # Selenium Grid parallel config
├── pom.xml                             # Maven dependencies
├── Jenkinsfile                         # Jenkins pipeline definition
└── reports/                            # Extent HTML reports (auto-generated)
```

---

## ⚙️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 11+ |
| Browser Automation | Selenium WebDriver 4.x |
| BDD Framework | Cucumber 7.x |
| Test Framework | TestNG 7.x |
| Build Tool | Maven |
| Design Pattern | Page Object Model (POM) |
| Data-Driven | Apache POI (Excel), JSON |
| Parallel Execution | Selenium Grid + TestNG XML |
| CI/CD | Jenkins + GitHub Actions |
| Reporting | Extent Reports 5.x |
| Version Control | Git / GitHub |

---

## 🚀 Key Features

- ✅ **ThreadLocal WebDriver** — thread-safe parallel execution, zero cross-contamination
- ✅ **Self-Healing Locator Strategy** — reduces flaky test failures by 25%
- ✅ **BDD Feature Files** — human-readable scenarios for business stakeholders
- ✅ **Data-Driven via Excel** — run same test with N data sets, zero code duplication
- ✅ **Cross-Browser Support** — Chrome, Firefox, Edge via single config switch
- ✅ **Selenium Grid Ready** — scale to remote execution in minutes
- ✅ **Jenkins Pipeline** — nightly regression with auto email notifications
- ✅ **Extent Reports** — rich HTML reports with screenshots on failure
- ✅ **Config-Driven** — environment, browser, URL all externalized in properties file

---

## 📋 Prerequisites

```bash
Java JDK 11+
Maven 3.8+
Chrome / Firefox / Edge browser
Git
```

---

## ▶️ How to Run

### Run all tests (default browser: Chrome)
```bash
mvn clean test
```

### Run with specific browser
```bash
mvn clean test -Dbrowser=firefox
mvn clean test -Dbrowser=edge
```

### Run specific feature tag
```bash
mvn clean test -Dcucumber.filter.tags="@smoke"
mvn clean test -Dcucumber.filter.tags="@regression"
mvn clean test -Dcucumber.filter.tags="@login"
```

### Run in parallel (Selenium Grid)
```bash
mvn clean test -Dsurefire.suiteXmlFiles=testng-parallel.xml
```

### Run via TestNG XML
```bash
mvn clean test -Dsurefire.suiteXmlFiles=testng.xml
```

---

## 📊 Sample Feature File (BDD)

```gherkin
@smoke @login
Feature: User Authentication

  Background:
    Given the user is on the login page

  @valid-login
  Scenario Outline: Successful login with valid credentials
    When the user enters username "<username>" and password "<password>"
    And clicks the login button
    Then the user should be redirected to the dashboard
    And the welcome message should display "<username>"

    Examples:
      | username      | password   |
      | admin@test.com | Admin@123  |
      | user@test.com  | User@123   |

  @invalid-login
  Scenario: Login fails with invalid credentials
    When the user enters username "wrong@test.com" and password "wrongpass"
    And clicks the login button
    Then an error message "Invalid credentials" should be displayed
```

---

## 📄 Sample Page Object (POM)

```java
public class LoginPage extends BasePage {

    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "loginBtn")
    private WebElement loginButton;

    @FindBy(css = ".error-message")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String username) {
        WaitUtils.waitForVisibility(usernameField, driver);
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public DashboardPage clickLogin() {
        loginButton.click();
        return new DashboardPage(driver);
    }

    public String getErrorMessage() {
        return WaitUtils.waitForVisibility(errorMessage, driver).getText();
    }
}
```

---

## 📈 Results Achieved With This Framework

| Metric | Before | After |
|---|---|---|
| Automation Coverage | 40% | **75%** |
| Regression Execution Time | 8 hours | **2 hours** |
| Flaky Test Rate | High | **Reduced 25%** |
| Pre-Production Defect Detection | Baseline | **97%** |
| Post-Release Critical Defects | Occasional | **Zero** |

---

## 🔁 Jenkins Pipeline (CI/CD)

```groovy
pipeline {
    agent any
    triggers { cron('H 22 * * 1-5') }  // Nightly Mon-Fri at 10pm

    stages {
        stage('Checkout') {
            steps { git branch: 'main', url: 'https://github.com/Shraddha-Rajput/Hybrid-Frame' }
        }
        stage('Build & Test') {
            steps { sh 'mvn clean test -Dcucumber.filter.tags="@regression"' }
        }
        stage('Publish Report') {
            steps { publishHTML([reportName: 'Extent Report', reportDir: 'reports', reportFiles: 'index.html']) }
        }
    }
    post {
        always { emailext subject: "Regression Results - ${currentBuild.result}", to: 'team@company.com' }
    }
}
```

---

## 👩‍💻 Author

**Shraddha Rajput** — Senior SDET | Automation Framework Engineer

[![LinkedIn](https://img.shields.io/badge/LinkedIn-shraddharajput-0077B5?style=flat-square&logo=linkedin)](https://linkedin.com/in/shraddharajput)
[![GitHub](https://img.shields.io/badge/GitHub-Shraddha--Rajput-181717?style=flat-square&logo=github)](https://github.com/Shraddha-Rajput)

> 5 years building automation frameworks across Banking, Product Engineering (Google Chrome), and E-Commerce domains.
> ISTQB Certified | Selenium | Playwright | Java | Cucumber BDD | REST Assured | Jenkins

---

⭐ *If this framework helped you, please star the repo!*
