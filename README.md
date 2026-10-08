\# OpenCart UI Automation



\## Overview



This project contains UI automation tests for an OpenCart application using Java, Selenium WebDriver, TestNG, and Maven.



The framework follows the Page Object Model (POM) to separate test logic from page-specific locators and actions.



\## Tech Stack



\- Java

\- Selenium WebDriver

\- TestNG

\- Maven

\- Docker / Docker Compose

\- Page Object Model



\## Project Structure



```text

ui-tests/

├── src/

│   └── test/

│       ├── java/

│       │   ├── base/

│       │   ├── driver/

│       │   ├── pages/

│       │   ├── tests/

│       │   └── utils/

│       └── resources/

│           └── config/

├── screenshots/

├── allure-results/

├── docker-compose.yml

├── pom.xml

├── testng.xml

├── FLAKINESS.md

├── PYRAMID.md

├── DECISIONS.md

└── README.md

```



\## Prerequisites



Install the following:



\- Java 17 or later

\- Maven

\- Docker Desktop

\- Git



Verify Java:



```bash

java -version

```



Verify Maven:



```bash

mvn -version

```



\## Configuration



Test configuration is maintained in:



```text

src/test/resources/config/config.properties

```



The configuration should contain only test data suitable for the OpenCart test environment.



Do not add production credentials, API keys, tokens, or other sensitive information to the repository.



\## Run the UI Test Suite



From the project root directory, run:



```bash

mvn clean test -DsuiteXmlFile=testng.xml

```



The TestNG suite is defined in:



```text

testng.xml

```



\## Run with Docker



Start the Docker environment:



```bash

docker compose up -d

```



Run the test suite:



```bash

mvn clean test -DsuiteXmlFile=testng.xml

```



Stop the Docker environment after execution:



```bash

docker compose down

```



\## Test Coverage



The automation covers important OpenCart customer and administrator workflows, including:



\- Storefront interactions

\- Product operations

\- Coupon creation

\- Coupon application

\- Customer order placement

\- Administrator order verification

\- Product cleanup



\## Test Data and Cleanup



Where applicable, tests create test data at runtime rather than depending entirely on pre-existing application data.



Created data is cleaned up after execution where the workflow supports cleanup.



\## Test Execution Evidence



Screenshots and test execution results are generated during execution where applicable.



\## Known Flakiness



Known UI timing and environment-related flakiness is documented in:



`FLAKINESS.md`



\## Test Pyramid



The test-level strategy and future testing improvements are documented in:



`PYRAMID.md`



\## Technical Decisions



Framework decisions, trade-offs, deliberately skipped scope, and AI tool usage are documented in:



`DECISIONS.md`



\## API Test Suite



An API test suite was not included in this submission.



The available implementation scope was prioritized toward completing and stabilizing the OpenCart UI automation scenarios.



The project can be extended with API tests in the future.



\## AI Tool Usage



AI tools were used as development assistance for:



\- Reviewing automation code structure

\- Troubleshooting Java and Selenium issues

\- Suggesting test scenarios and edge cases

\- Improving documentation

\- Reviewing framework organization



The final implementation and execution decisions were reviewed against the actual application behavior.

