# Octo_Events
O OctoEventsAplication recebe evetos da issue do gitHube via WebHooks e disponibiliza via Api-Rest para uso futuro. 

Abaixo imagem ilustrativa do ciclo de vida da requicição:

![alt text](imgs/octo_events.png)

## Executando e testando o projeto

### Pré-requisitos
* PostgresSQL (O banco que foi testado).
* Java 8
* Ngrok.
* Ter uma conta no Github.
* Sistema Operacional Windows Pois este tutorial não irá abordar instalação em outros SO.

### Passo a passo

#### 1 - Instalar o PostgresSQL:

A página oficial contém [links para download](https://www.postgresql.org/download/) do SGBD para diversas plataformas.


1.1 Instalar o PostgreSQL:

1.2 É bastante simples, basta dar um duplo clique no executável que foi baixado no passo a cima e seguir os passos do instalador.



#### 2 - Configurar as propriedades da aplicação 

Caminho até o arquivo de propriedades da aplicação:
`recrutamento-kotlin-jya-infortic/src/main/resources/application.yml`

O usuário e a senha cadastrados durante a instalação do BD deveram ser informados neste arquivo.  

Por exemplo, a string de conexão com o banco `postgres` rodando local na porta `8084`
seria `jdbc:postgresql://localhost:5432/recrutamento`. Para configurar usuário `postgres` e senha `cleiton` uma configuração
válida seria a abaixo:

```
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/recrutamento    
    username: postgres     
    password: cleiton


#### 3 - Buildar e executar a aplicação

Dentro do diretório root da aplicação executar os passos abaixo.

3.1 Para rodar os testes:

##Obs para rodar os testes lembre-se que o maven precisa estar instalado. 

`$ mvn test #Para rodar os testes`

3.2 Para buildar a aplicação:
`$ mvn clean install #buildando o pacote da aplicação`

3.3 Para executar a aplicação:

O Maven através do `spring-boot-maven-plugin` gera o jar executável.
Então basta entrar no diretório `target/` e executar:

`$ java -jar octoevents-0.0.1-SNAPSHOT.jar`

A partir deste ponto se tudo ocorrer corretamente a aplicação estará rodando 
e atendendo requisições através da porta cadastrada no arquivo `application.yml` (se não foi alterado estará na porta 8080).

É possível utilizar o client do swagger através da url [http://localhost:8084/swagger-ui.html]


#### 4 Criar o tunel para acessar os endpoints externamente

Utilizamos o `ngrok` para criar uma ponte para da nossa API externamente.

Faça o download do ngrok no seguinte link: https://ngrok.com/download 

Após o dowload execute o arquivo baixado e entre com  a seguinte linha de comando 

`$ ngrok http 8084` 

Retornarão as urls (http ou https) pelas quais se poderá acessar a aplicação externamente.

#### 5 Criar um repositório e cadastrar o webhook no github.

Cadastrar o endpoint /events como webhook e escolher a opção de escutar 
apenas os eventos de `issues`.

* Webhooks Overview: https://developer.github.com/webhooks/ 
* Creating Webhooks : https://developer.github.com/webhooks/creating/

#### 6 - Testar a aplicação
* Realizar as operações que deverão disparar eventos no repositório do Github ao qual foi cadastrado o webhook:
    1) criar nova issue
    2) Adicionar labels
    3) Adicionar Assigne
    4) Adicionar mais Assignees
    5) Criar Milestone
    6) fechar a issue.
    7) Atualizar o nome da issue.
    
* Acompanhar nos logs as chamadas realizadas.

* Utiliar um client ou Acessar a inteface web do swagger gerada pela aplicação para testar o endpoint `GET issues/{issueId}/events` 
para recuperar os eventos gravados.

**Observação:**

É possível acompanhar o `id`'s das `issues` através do payload das chamadas ao `/events` logadas, porém
a saída não está muito formatada. Alternativamente podemos buscar
os ids no banco.

É possível através da query abaixo obter estes id's.

`SELECT DISTINCT id FROM github_issue_snapshot`


## Endpoints 


`GET /issues/{issueId}/events`

`POST /events`


## Principais tecnologias e frameworks utilizados

- Java 8
- Spring Boot 2
- JUnit 5
- Mockito
- Hibernate
- Swagger
