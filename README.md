# HepsiBuradaCaseAPI

This is a demo project for case given by HepsiBurada.

### Stack:
- Language: Java
- Framework: Rest Assured, Newman
- Build Tool: Gradle
- Test Framework: TestNG
- Reporting: Allure


### How to use ?
- To execute tests with Gradle, `./gradlew test` command can be used.
- To display report after test execution, `allure serve allure-results` command can be used.
- To execute tests with Newman,
  - Install newman -> `$ npm install -g newman`
  - Then execute tests with `newman run src/test/java/tests/newman/HepsiBuradaAPI.postman_collection.json `
