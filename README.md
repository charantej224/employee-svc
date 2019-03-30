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
```
curl -X POST \
  http://localhost:9094/employee-service/sign-up \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 24a781f6-68b8-47cc-999d-03b1fd804c9d' \
  -H 'cache-control: no-cache' \
  -d '{
	"userName":"admin",
	"password":"admin"
}'
```

* step2: login the user, un-secured route, but credentials authenticated. use CURL command 2. 
    - Note: Secure the token returned in the header as response after successful authentication.
```
curl -X POST \
  http://localhost:9094/employee-service/login \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: cb8a76ba-ca4e-4017-978b-c23b16cfbc79' \
  -H 'cache-control: no-cache' \
  -d '{
	"userName":"admin",
	"password":"admin"
}'
```
* step3: use the bearer token returned from step2, to create department. use CURL command 3.
```
curl -X POST \
  http://localhost:9094/employee-service/api/departments \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 84dd5df0-253a-4bca-9be3-6c7ef63632e5' \
  -H 'cache-control: no-cache' \    
  -d '{
	"name":"consulting"
}'
```
* step4: use the bearer token returned from step3, to create employee. use CURL command 4.
```
curl -X POST \
  http://localhost:9094/employee-service/api/employees \
  -H 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU1NDYxODU1OH0.LDSaoDtHQ5p8OuFrI7howleX7nLDiNrPrQiznBn45GwFOS5fNYs5Rc0G3Rj5vFCILt1wXmojrIl86twwB4E2iA' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 21b7a443-bae9-4c8c-9a1d-dbc98dd6ee9e' \
  -H 'cache-control: no-cache' \
  -d '{
	"email":"charantej.career@gmail.com",
	"fullName":"Charan Tej Thota",
	"birthDay":"06.07.1988",
	"departmentName":"consulting"
}'
```
* step5: use the bearer token returned from step4, to update employee, user CURL command 5.
```
curl -X PUT \
  http://localhost:9094/employee-service/api/employees \
  -H 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU1NDUzOTk2Mn0.Cua5wZeZqv08cNOk6ZdkVhnAwGED2Yom5uBt0VbKq2gmrwegjZQADhnbjZhq3UJdBMyeGiO-JXi_xSwo3ufgSQ,Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU1NDYxODU1OH0.LDSaoDtHQ5p8OuFrI7howleX7nLDiNrPrQiznBn45GwFOS5fNYs5Rc0G3Rj5vFCILt1wXmojrIl86twwB4E2iA' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: dc639183-3270-475c-83d9-ce318cc30ffa,64b39a15-e64f-4cf7-9ba8-f8f1ef6fbd77' \
  -H 'cache-control: no-cache,no-cache' \
  -d '{
	"email":"charantej.career@gmail.com",
	"fullName":"Mr.Charan Tej Thota",
	"birthDay":"07.07.1988",
	"departmentName":"consulting"
}'
```
* step6: use the bearer token returned from step5, to get the employee, use CURL command 6.
```
curl -X GET \
  http://localhost:9094/employee-service/api/employees/6 \
  -H 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU1NDYxODU1OH0.LDSaoDtHQ5p8OuFrI7howleX7nLDiNrPrQiznBn45GwFOS5fNYs5Rc0G3Rj5vFCILt1wXmojrIl86twwB4E2iA' \
  -H 'Postman-Token: 28cdb43e-3ce3-441b-aa40-81d96a109001' \
  -H 'cache-control: no-cache'
 ```
* step7: use the bearer token returned from step6, to delete the employee, use CURL command 7.
```
curl -X DELETE \
  http://localhost:9094/employee-service/api/employees/6 \
  -H 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU1NDUzOTk2Mn0.Cua5wZeZqv08cNOk6ZdkVhnAwGED2Yom5uBt0VbKq2gmrwegjZQADhnbjZhq3UJdBMyeGiO-JXi_xSwo3ufgSQ,Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU1NDYxODU1OH0.LDSaoDtHQ5p8OuFrI7howleX7nLDiNrPrQiznBn45GwFOS5fNYs5Rc0G3Rj5vFCILt1wXmojrIl86twwB4E2iA' \
  -H 'Postman-Token: cc26abf4-1448-4598-9f75-61bf6415902d,312f7c72-8cbf-467b-90d8-01dfd5bb9ae7' \
  -H 'cache-control: no-cache,no-cache'
```
Negative tests:
* try testing without bearer token.
* try updating non-existant user.
* try creating an existing user.
* try sending no employee email.

###### additional: sonar reports if required:

docker-compose -f src/main/docker/sonar.yml up -d

./gradlew -Pprod clean test sonarqube
