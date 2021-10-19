# restapisb
restapi springboot


##Base de Datos H2

##Gradle 
Ejecutar en las raiz del proyecto para que arranque
gradle bootRun 


##Endpoints
POST /user
PUT  /user
DELETE 	/user/delete/{id}
DELETE 	/user/deleteAll
GET 	/user/{id}
GET	/user/getAll
POST	/user/login

##CURLS PARA EL FUNCIONAMIETO
1-EJECUTAR PARA CREAR LOS USUARIOS EN LA BASE (TIENE LOS PERMISOS LIBERADOS YA QUE NI NO PODRIAMOS CREARLOS). SE REALIZAN VALIDACIONES DE EMAIL Y PASSWORD
curl --location --request POST 'http://localhost:7001/user' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=0205FFEEEEF2A4EB5B3253C83B3880A1' \
--data-raw '{
"name": "Juan Rodriguez",
"email": "juan@rodriguez.org",
"password": "hunterMM22",
"phones": [{"number": "1234567","citycode": "1","contrycode": "57"}]}'

2-REALIZAR EL LOGIN PARA OBTENER EL TOKEN (SOLO VALIDA CON EL MAIL YA Q ES SOLO UNA PRUEBA)
curl --location --request POST 'http://localhost:7001/user/login' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--header 'Cookie: JSESSIONID=0205FFEEEEF2A4EB5B3253C83B3880A1' \
--data-urlencode 'password=hunterMM22' \
--data-urlencode 'email=juan@rodriguez.org'

3-DEMAS ENDPOINTS SE DEBE ENVIAR CON EL TOKEN EN EL HEADER DE LAS REQUEST.
curl --location --request GET 'http://localhost:7001/user/1' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoianVhbkByb2RyaWd1ZXoub3JnIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTYzNDY0Mjc3MCwiZXhwIjoxNjM0NjQzMzcwfQ.ju1LwQjni773K-j2I6icE-XLvJCF-UI2h9tRcXlvloGh_NyU1uvw2bEtmx9pK1N9PbQwCcJpPct1C1eFet8hoQ' \
--header 'Cookie: JSESSIONID=8202D0AFC73B507B7CFDE4F91669166F'
