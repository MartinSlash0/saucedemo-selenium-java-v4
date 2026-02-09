                        ---=== Selenium TestNG Automation Framework ===---
                                   made by MartinSlash0

                         This project is a UI test automation framework
                       built with Java, Selenium WebDriver and TestNG,
                         following the Page Object Model (POM) pattern.

                      The framework automates end-to-end UI test scenarios for:
                      - Login functionality (positive & negative cases)
                      - Shopping cart and checkout flow
                      - Error handling and validation messages

                                        TESTED WEBSITE:
                      - https://www.saucedemo.com

                                           TECH STACK:

                      - Java 11                    - TestNG
                      - Selenium WebDriver 4       - Maven
                      - ChromeDriver               - Page Object Model (POM)
                      - SLF4J + Logback            - Apache Commons IO

                                       PROJECT STRUCTURE:

                  selenium-java-framework/
                  │
                  ├── src/
                  │   ├── main/java/org/example/
                  │   │   ├── BasePage.java          # Base page with reusable methods
                  │   │   ├── LoginPage.java         # Login page object
                  │   │   ├── StorePage.java         # Store page object
                  │   │   ├── Config.java            # Configuration and test data
                  │   │   └── Locators.java          # Centralized element locators
                  │   │
                  │   └── test/
                  │       ├── java/org/example/
                  │       │   └── SeleniumTest.java  # Test suite
                  │       └── resources/
                  │           └── logback.xml        # Logging configuration
                  │
                  ├── screenshots/                   # Auto-captured test failure screenshots
                  ├── logs/                          # Test execution logs
                  │   ├── current-test.log           # Latest test run
                  │   └── test-YYYY-MM-DD.log        # Daily archived logs
                  │
                  ├── pom.xml                        # Maven dependencies
                  ├── testng.xml                     # TestNG suite configuration
                  ├── .gitignore                     # Git ignore rules
                  └── README.md                      # This file

                                       HOW TO RUN TESTS:

                      1) Clone the repository

                      --> git clone https://github.com/your-username/your-repo-name.git
                      --> cd your-repo-name

                      2) Build the project and download dependencies

                      --> mvn clean install

                      3) Run all tests

                      --> mvn test

                                   TEST SCENARIOS COVERED:

                      - Valid Login
                      = Successful authentication verification

                      - Invalid Login
                      = Error message validation
                      = Required field validation

                      - Logout Flow
                      = User logout verification

                      - Shopping Cart Flow
                      = Add item to cart
                      = Cart content verification

                      - Checkout Flow
                      = User information validation
                      = Order completion verification

                                   SCREENSHOTS ON FAILURE:

                      - Screenshots are automatically captured on test failure
                      - Stored in the screenshots/ directory
                      - Filenames include test name and timestamp
                      - Logging is handled via SLF4J + Logback

                      ========================================================

                        This project was built as part of my QA Automation
                        learning journey, with the goal of writing clean,
                        maintainable and stable UI automation tests following
                         real-world best practices and design patterns.

                       The framework focuses on:
                       - Readable test scenarios
                       - Reusable Page Objects
                       - Explicit waits and flakiness reduction
                       - Proper logging and error handling

                         Thank you for taking the time to review this project.
                         I hope it can serve as inspiration or reference for
                           anyone starting their path in QA Automation.

