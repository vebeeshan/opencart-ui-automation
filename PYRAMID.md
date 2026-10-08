\# Test Pyramid



The automation suite follows the test pyramid principle by keeping lower-level tests faster and using UI tests for important end-to-end business flows.



\## Test Levels



\### Unit Tests



Unit tests are normally the fastest level and validate individual application components.



They are outside the scope of this UI automation assignment because the application under test is provided as an OpenCart environment.



\### API / Service Tests



API tests are useful for validating backend behavior without depending on the browser UI.



They can provide faster feedback than full UI tests and are suitable for future expansion of this framework.



\### UI / End-to-End Tests



The main focus of this assignment is UI automation using:



\- Java

\- Selenium WebDriver

\- TestNG

\- Maven

\- Page Object Model



The UI tests validate important customer and administrator workflows such as:



\- Storefront interactions

\- Product operations

\- Coupon creation and application

\- Customer order placement

\- Administrator order verification

\- Test-data cleanup



\## Why UI Tests Are Included



The assignment requires validation of complete user workflows through the OpenCart interface. UI tests therefore provide end-to-end coverage of the required scenarios.



\## Future Improvement



If this framework were expanded for a larger production project, more API/service-level tests could be added to reduce the number of browser-based tests while keeping only critical business journeys at the UI level.

