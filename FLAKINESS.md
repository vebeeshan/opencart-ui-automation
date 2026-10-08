\# Flakiness



This document records known areas that may cause intermittent failures in UI automation and the approach used to reduce them.



\## Dynamic UI Elements



OpenCart pages may take time to load elements after navigation or user actions.



\### Mitigation

\- Use explicit waits where required.

\- Wait for elements to be visible or clickable before interacting.

\- Avoid unnecessary fixed delays.



\## Page Loading and Navigation



Some actions require the next page or UI component to finish loading.



\### Mitigation

\- Use Selenium explicit waits.

\- Validate the expected page state before continuing.



\## Test Data



Tests that depend on existing products, coupons, customers, or orders can become unreliable when application state changes.



\### Mitigation

\- Create test data at runtime where applicable.

\- Use unique test data when required.

\- Clean up created data after execution.



\## Browser and Environment Differences



Browser, Docker, and local execution environments may behave differently.



\### Mitigation

\- Centralize WebDriver creation in `DriverFactory`.

\- Keep browser configuration in the project configuration.

\- Support headless execution when required.



\## Failure Investigation



Screenshots and test reports are used to investigate failures and determine whether the cause is application behavior, timing, test data, or environment configuration.

