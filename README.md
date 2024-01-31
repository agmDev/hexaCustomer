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

