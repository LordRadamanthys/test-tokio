# Custumer Service

### Requisitos

1. JDK 8
1. Maven 3

### Rodando

1. Clone o projeto: `https://github.com/leonardohenrique/tokio-test.git`
1. Entre na pasta `tokio-test` e execute: `mvn spring-boot:run`
1. Acesse: `http://localhost:8080/customers`

### Descrição da API por Mateus Lima

Endpoints:

1. Listar todos customers
Metodo HTTP: GET
 - ``` http://localhost:8080/customers/ ```
 
formato do retorno:
```
[
  {
    "id": 2,
    "name": "Joãozinho",
    "email": "joaozinho@email.com",
    "address": []
  },
  {
    "id": 1,
    "name": "zé",
    "email": "mariazinha@email.com",
    "address": []
  }
]
```
2. Listar customers com paginação:
 Metodo HTTP: GET
 - ``` http://localhost:8080/customers/page?page={numero da pagina} ```
 Ele retorna 5 registros por pagina
 
formato do retorno:
```
{
  "content": [
    {
      "id": 1,
      "name": "zé",
      "email": "mariazinha@email.com",
      "address": []
    },
    {
      "id": 2,
      "name": "Joãozinho",
      "email": "joaozinho@email.com",
      "address": []
    }
  ],
  "pageable": {
    "sort": {
      "sorted": false,
      "unsorted": true,
      "empty": true
    },
    "offset": 0,
    "pageSize": 5,
    "pageNumber": 0,
    "paged": true,
    "unpaged": false
  },
  "totalPages": 1,
  "totalElements": 2,
  "last": true,
  "size": 5,
  "number": 0,
  "sort": {
    "sorted": false,
    "unsorted": true,
    "empty": true
  },
  "first": true,
  "numberOfElements": 2,
  "empty": false
}
```
3. Listar um customers pelo id:
 Metodo HTTP: GET
 - ``` http://localhost:8080/customers/{id do customer} ```
 
formato do retorno:
```
{
  "id": 1,
  "name": "zé",
  "email": "mariazinha@email.com",
  "address": []
}
```

4. Inserir customer:
 Metodo HTTP: POST
 - ``` http://localhost:8080/customers/{id do customer} ```
 
formato da ser enviado:
```
{
	
	"name": "William",
	"email": "will@email.com",
	"cep":["08041020"]
}
```

retorno será status 200 e uma menssage: "Criado com sucesso"

Caso não mande nenhum dos campos a resposta será um bad request com informações do erro, por exemplo e-mail inválido.
Caso o e-mail ja exista, ou cep inválido, o retorno será um bad request.


5. Atualizar customer:
Metodo HTTP: PUT
-  ``` http://localhost:8080/customers/{id do customer} ```
formato a ser enviado:
```
{
	
	"name": "Marcos",
	"email": "Marcos@email.com",
	"cep":["01001000","08041020"]
}
```
retorno será status 200

6. apagar customer:
Metodo HTTP: DELETE
- ``` http://localhost:8080/customers/{id do customer} ```
Se apagado retorna 200
Se não for encontrado o usuario com o id fornecido retornara um bad request
