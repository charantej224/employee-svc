# EmployeeService
Employee service to perform CRUD operations on the employee database.
## Running the application

###### 1. clone employee service using below command
git clone https://github.com/charantej224/employee-svc.git

###### 2. change the directory
cd employee-svc/

###### 3. Build,compile,tests the application
./gradlew build
* view jacoco test coverage reports at path  *
./build/reports/jacoco/test/html/index.html
Note: Only Service and controllers are covered as part of test coverage.

###### 4. Run the application
./gradlew

Swagger API contracts:
http://localhost:9094/employee-service/v2/api-docs

## Testing the application:
** Note: Before proceding this step, event service "Running the application" steps are to be completed. **
curl commands (referred below) available at : curl.txt

* step1: signup user, un-secured route, use CURL command 1 from the text file.
* step2: login the user, un-secured route, but credentials authenticated. use CURL command 2. 
    - Note: Secure the token returned in the header as response after successful authentication.
* step3: use the bearer token returned from step2, to create department. use CURL command 3.
* step4: use the bearer token returned from step3, to create employee. use CURL command 4.
* step5: use the bearer token returned from step4, to update employee, user CURL command 5.
* step6: use the bearer token returned from step5, to get the employee, use CURL command 6.
* step7: use the bearer token returned from step6, to delete the employee, use CURL command 7.

Negative tests:
* try testing without bearer token.
* try updating non-existant user.
* try creating an existing user.
* try sending no employee email.

###### additional: sonar reports if required:

docker-compose -f src/main/docker/sonar.yml up -d

./gradlew -Pprod clean test sonarqube
