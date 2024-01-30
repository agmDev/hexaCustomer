# Project
Simple Api using H2 in memory database containing Customer table.
It was made following the hexagonal design patern and spring boot 3

This Api expose 2 endpoints:
- Get /customers/{mail} allowing to retrieve a specific customer based on his email adresse
- Post /customers allowing to create a customer


# Lauch application
from root folder :  
`mvn spring-boot:run -D"spring-boot.run.profiles"=local -f ./Application`  
from Application folder :  
`mvn spring-boot:run -D"spring-boot.run.profiles"=local`

