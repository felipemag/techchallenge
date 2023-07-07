# Four Lanches App

## Reference Documentation

### Tecnologias
* Java 17 
* Spring Boot
* Postgres SQL
* Docker
* [Swagger](http://localhost:8080/swagger-ui/index.html)

### Pre-requisitos para rodar a aplicação
* [Java Version: 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) 
* [Gradle](https://gradle.org/install/)
* [IDE IntelliJ](https://www.jetbrains.com/idea/)
* [Docker](https://www.docker.com/)

### Para buildar, executar as migration e rodar o app no docker pela primeira vez

`docker compose up --build -d`

### Para executar o app com docker após a primeira vez
Após rodar a primeira o comando acima, execute o seguinte comando abaixo para que apenas execute 
os containers sem a etapa de build e migration.

`docker compose up -d app db`

### Necessário um arquivo .env na raiz do projeto com a seguinte conteúdo:
```
DATABASE_USERNAME=username_do_banco  
DATABASE_PASSWORD=password_do_banco
```

### Testando com insomnia
Para executar as requisições com o insomnia, basta importar o arquivo `insomnia-collection.json`
no seu insomnia que a collection estará pronta para ser usada.

### Links Adicionais
