# BCI - challenge
## API RESTful de creación de usuarios.

- La API REST expone 5 endpoints, los cuales aceptan y devuelven JSON
  - http://localhost:8080/api/user/register
  - http://localhost:8080/api/auth/signin 
  - http://localhost:8080/api/user/retrieve/{id} (requiere token)
  - http://localhost:8080/api/user/update/{id} (requiere token)
  - http://localhost:8080/api/user/delete/{id} (requiere token)
    
De los 5 endpoints expuestos, hay 3 que estan segurizados y requieren de un token JWT para poder acceder a los recursos.
Para la segurizacion de la API se utilizo SpringSecurity, el cual filtra los request entrantes y verifica la validez del token.

La validez del token esta configurada en el archivo properties.yml:


El funcionamiento de como actua SpringSecurity para proteger la API se describe en el diagrama de la carpeta Diagramas.


- La API puede ser probada con POSTMAN o tambien via SWAGGER (http://localhost:8080/swagger-ui/index.html)
  
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

- Recuperar Usuario:
  - endpoint: http://localhost:8080/api/user/retrieve/ff57cd15-d585-4bb5-adef-c66136b0d579
  - El endpoint recibe y UUID del usuario que quiere recuperar y un token

     - En caso que existe el UUID y el token es valido, la API responde:


     - En caso que el UUID no exista y el token es valido, la API responde:
   

     - En caso que el token sea invalido o este expirado, la API responde:
       
 
