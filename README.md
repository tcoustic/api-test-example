### Java Api Testing Framework Example

This is example of REST API test written in Java using JUnit5 and Rest Assured

Tests target https://jsonplaceholder.typicode.com/ endpoints

Project is run via Maven:

    mvn clean test
    
or for report:

    mvn surefire-report:report
  
report will be created at [/target/site](/target/site)

