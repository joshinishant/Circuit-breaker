Circuit-breaker repository contains two Spring boot projects - 
1. EmployeeRegistration - Exposes Rest endpoints to create,update,search and delete employee information
2. GatewayApplication - This application wraps endpoints exposed by EmployeeRegistration application with Circuit-breaker pattern using Resilince4j


Below are the rest endpoint exposed by Gateway Application
	1. Create an employee (POST)
		Endpoint - '/gateway'
		Payload Structure-	
					{
						"id":"",
						"firstName":"",
						"middleName":"",
						"lastName":"",
						"dateOfBirth":"",
						"address":"",
						"contactNumber":""
					}
		sample payload -
					{
						"id":"ac72cd97-bbf7-442e-8673-90ae6d137809"
						"firstName":"Nishant",
						"middleName":"M",
						"lastName":"Joshi",
						"dateOfBirth":"01/01/1990",
						"address":"India",
						"contactNumber":"123-456-7891"
					}
					
	2. Update employee details (PUT)
		Endpoint - '/gateway'
		Payload Structure-	
					{
						"firstName":"",
						"middleName":"",
						"lastName":"",
						"dateOfBirth":"",
						"address":"",
						"contactNumber":""
					}
		sample payload -
					{
						"firstName":"Nishant",
						"middleName":"M",
						"lastName":"J",
						"dateOfBirth":"02/02/1980",
						"address":"India",
						"contactNumber":"223-476-7891"
					}		
	3. Find an Employee(unique)(GET)
		Endpoint - '/gateway?firstName=""&&middleName=""&&lastName=""'
		sample - '/gateway?firstName="Nishant"&&middleName="M"&&lastName="Joshi"'
	
	4. Find All employees (GET)
		Endpoint - '/gateway/All'
		
	5. Delete an employees(DELETE)
		Endpoint - '/gateway/{id}'
		sample - '/gateway/{ac72cd97-bbf7-442e-8673-90ae6d137809}'
		
Note - 
1. EmployeeRegistration uses h2 database to store employee details
2. For the sake simplicity the exposed rest endpoints don't require any authentication for now
