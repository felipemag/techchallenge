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

### Para rodar o app no docker
`docker compose up --build -d`

### Necessário um arquivo .env na raiz do projeto com a seguinte conteúdo:
```
DATABASE_USERNAME=username_do_banco  
DATABASE_PASSWORD=password_do_banco
```

### Flyway
Para atualizar migrações do banco, apenas rode:
```
./gradlew flywayMigrate -i
```

### .env -> Environemnt Variables
Rode o comando abaixo para carregar o arquivo .env como Environment Variable (em unix-like shells)  
Importante fazer isso para poder rodar o flyway 
```
./export-dot-env
```

### Additional Links

