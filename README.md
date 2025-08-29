# Rest_Assured_CRUD_API

Projeto de testes automatizados de APIs utilizando **RestAssured** e **JUnit**.

API testada: [Reqres](https://reqres.in/)  
Propósito: exemplificar testes **CRUD** de forma prática para o dia a dia de QA.

# Estrutura
- `BaseTest.java` → Configuração global do RestAssured (baseURI e headers).
- `Endpoints.java` → Endpoints centralizados da API.
---
# Tecnologias
- Java 11
- Maven
- RestAssured
- JUnit 4
- Hamcrest
---
# Como rodar
# Pelo Maven
mvn clean test