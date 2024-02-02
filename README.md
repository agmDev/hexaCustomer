# Project
Simple Api using H2 in memory database containing Customer table.
It was made following the hexagonal design pattern and spring boot 3

This Api expose 2 endpoints:
- Get /customers/{mail} allowing to retrieve a specific customer based on his email address
- Post /customers allowing to create a customer


# Lauch application
before the first launch :
`mvn clean install`  
from root folder :  
`mvn spring-boot:run -D"spring-boot.run.profiles"=local -f ./application`  
from application folder :  
`mvn spring-boot:run -D"spring-boot.run.profiles"=local`

It is possible to access database [here](http://localhost:8080/h2-console), JDBC Url will appear on the starting logs (it contains random UUID), usr="admin, password ="admin".
Swagger is accessible [here](http://localhost:8080/swagger-ui.html).

