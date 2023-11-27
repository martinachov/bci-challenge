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

***  
### - Crear nuevo Usuario:

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

- Si el usuario ya existe, la API responde:

  ```json

        response:
                {
                    "message": "User already exist !!"
                }
                
              
***
### - Recuperar Usuario:

  - endpoint: http://localhost:8080/api/user/retrieve/ff57cd15-d585-4bb5-adef-c66136b0d579
 
  - El endpoint recibe el UUID del usuario que quiere recuperar y un token

     - En caso que existe el UUID y el token es valido, la API responde:
   
         ```json

               response:
                   {
                      "user": {
                          "name": "martin",
                          "email": "martin@mail.com",
                          "password": "$2a$10$jKaoyEnxmzzCL8V94ztqI.LJ46t1HGQSe/SMhZHHbPPLXVK0Gg9w.",
                          "phones": [
                              {
                                  "number": 123456789,
                                  "cityCode": 90,
                                  "countryCode": "AR"
                              }
                          ]
                      },
                      "created": "2023-11-26T14:01:45.97",
                      "lastLogin": "2023-11-27T02:42:46.339",
                      "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYXJ0aW5AbWFpbC5jb20iLCJpYXQiOjE3MDEwNjM3NjYsImV4cCI6MTcwMTA2NDA2Nn0.imNBn1bhTAJRgEcT8GI4RHjT1nbBgnO-lxtDcZYuqpo",
                      "isActive": true
                  }


     - En caso que el UUID no exista y el token es valido, la API responde:

         ```json

               response:
                     {
                          "message": "User with id: ff57cd15-d585-4bb5-adef-0c66136b0d57 not found!!"
                      }

     - En caso que el token sea invalido o este expirado, la API responde:
       
         ```json
    
               response:
                    {
                        "message": "Full authentication is required to access this resource. Please check token validity and try again!!"
                    }
***
### - Actualizar datos de Usuario:

  - endpoint: http://localhost:8080/api/user/update/ff57cd15-d585-4bb5-adef-c66136b0d579
 
  - El endpoint recibe el UUID del usuario que se quiere actualizar, los datos a actualizar y un token
    
  - En caso que existe el UUID y el token es valido, la API responde:

      ```json
              body:
  
                        {
                            "name":"martin_modificado",
                            "email":"martin@mail.com",
                            "password":"AAAbbbb123",
                            "phones": [
                                {
                                    "number":1234,
                                    "cityCode":90,
                                    "countryCode":"AR"
                                },
                                {
                                    "number":5678,
                                    "cityCode":90,
                                    "countryCode":"AR"
                                }
                            ]
                        }

              response:
      
                        {
                          "user": {
                              "name": "martin_modificado",
                              "email": "martin@mail.com",
                              "password": "$2a$10$sLis9M8xVyXnF2nBAcWDAe.Rr1YqHp0Vojp6C1LwYNcpQf5BisHbu",
                              "phones": [
                                  {
                                      "number": 1234,
                                      "cityCode": 90,
                                      "countryCode": "AR"
                                  },
                                  {
                                      "number": 5678,
                                      "cityCode": 90,
                                      "countryCode": "AR"
                                  }
                              ]
                          },
                          "created": "2023-11-26T14:01:45.97",
                          "lastLogin": "2023-11-27T02:48:43.042",
                          "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYXJ0aW5AbWFpbC5jb20iLCJpYXQiOjE3MDEwNjQxMjMsImV4cCI6MTcwMTA2NDQyM30.aTbQzUFSVJf4RnTPbx7hCav4vP9kCgUhVJT_hmU7d3Y",
                          "isActive": true
                      }
  
***
### - Eliminar  Usuario:

  - endpoint: http://localhost:8080/api/user/delete/ff57cd15-d585-4bb5-adef-c66136b0d579
 
  - El endpoint recibe el UUID del usuario que se quiere eliminar y un token
    
  - En caso que existe el UUID y el token es valido, la API responde:

      ```json




***
### - SignIn

        ```json

            {
                "message": "Full authentication is required to access this resource. Please check token validity and try again!!"
            }


            {
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYXJ0aW5AbWFpbC5jb20iLCJpYXQiOjE3MDEwNjQxMjMsImV4cCI6MTcwMTA2NDQyM30.aTbQzUFSVJf4RnTPbx7hCav4vP9kCgUhVJT_hmU7d3Y"
}

