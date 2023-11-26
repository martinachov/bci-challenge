# BCI - challenge
## API RESTful de creación de usuarios.

- La API REST expone 5 endpoints, los cuales aceptan y devuelven JSON
  - 1
  - 2
  - 3
  - 4
  - 5

- La API puede ser probada con POSTMAN o tambien via SWAGGER:
  
   - Crear nuevo Usuario:
      - endpoint: http://localhost:8080/api/user/register
         ```json
         request:
        
                    {  "name":"martin",
                       "email":"martin@mail.com",
                       "password":"AAAbbbb123",
                       "phones": [
                              {"number":123456789,
                               "cityCode":90,
                               "countryCode":"AR"}
                          ]
                      }
        
        response:
        
                    {  "id": "bcd2f175-9f09-4709-bf6b-c4f9e6c7f892",
                      "created": "2023-11-25T23:28:55.658",
                      "lastLogin": "2023-11-25T23:28:55.659",
                      "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYXJ0aW4yQG1haWwuY2wiLCJpYXQiOjE3MDA5NjU3MzYsImV4cCI6MTcwMDk2NTc5Nn0.9Aobr76pZt7o49wh_xxKtudgAPQtms5QNDAqXz9v82I",
                      "isActive": true,
                      "email": "martin@mail.com"
                  }
      

      Si se crea el usuario correctamente, indicando correctamente el email y password (que respeta el formato indicado en la expresion regular) se devuelve el ID del usuario creado que es de tipo UUID, la fecha de creacion, ultimo login, si esta activo y el token para futuras llamadas a los endpoints protegidos de la API.

     
 
