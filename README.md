# demo

This is a simple command line JAVA application with database access using Spring Boot;
Based on an input from the command line provide the following functionality;
1. Add Person (id, firstName, surname)
2. Edit Person (firstName, surname)
3. Delete Person (id)
4. Count Number of Persons
5. List Persons


##Installation guide:
	
	A. Taken a Maven build:
		mvn clean instal
		
	
	B. Go into the project root folder and run the below command in command Line:
		java -jar ./target/demo-0.0.1-SNAPSHOT.jar ./src/main/java/com/example/person/DemoApplication
	
	
	C. Rest Endpoints

		1) To delete a person
		curl -X DELETE http://localhost:8090/deletePerson/1/
	
		2) To get the list of Person
		curl -i -X GET -H Accept:application/json http://localhost:8090/listPerson/
	
		3) To get the count of Person
		curl -X GET http://localhost:8090/personCount/
	
		4) To add a person
		Valid Case
		curl -d "{\"firstname\":\"Andrea\",\"surname\":\"Johnston\"}" -H "Content-Type: application/json"  http://localhost:8090/addPerson/
	
		Bad Data
		curl -d "{\"firstname\": null,\"surname\":\"Johnston\"}" -H "Content-Type: application/json"  http://localhost:8090/addPerson/
	
		5) Update user details
		curl -X PUT http://localhost:8090/updatePerson/3/ -H "Content-type:application/json" -d "{\"firstname\": \"Samwise\", \"surname\": \"Baggins\"}"
	
