# Four Lanches App

## DOC

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
* Kubernetes - K8S

### Para executar localmente com Docker
#### Para buildar, executar as migration e rodar o app no docker pela primeira vez

`docker compose up --build -d`

#### Para executar o app com docker após a primeira vez
Após rodar a primeira o comando acima, execute o seguinte comando abaixo para que apenas execute 
os containers sem a etapa de build e migration.

`docker compose up -d app db`

#### Necessário um arquivo .env na raiz do projeto com a seguinte conteúdo:
```
DATABASE_USERNAME=username_do_banco  
DATABASE_PASSWORD=password_do_banco
```

### Para rodar com K8S

#### Subindo o postgres como base de dados
Antes de subir os pods com a aplicação é necessário confirmar que o K8S esteja executando e depois
executar os seguintes comandos a partir da pasta raiz do projeto para subir o pod do Postgres. 
A ordem dos comandos precisa ser mantida.
```
kubectl apply -f k8s/db/postgres-db-pv.yml 

kubectl apply -f k8s/db/postgres-db-pvc.yml

kubectl apply -f k8s/db/postgres-db-secrets.yml 

kubectl apply -f k8s/db/postgres-db-configmap.yml         

kubectl apply -f k8s/db/postgres-db-deployment.yml

kubectl apply -f k8s/db/postgres-db-svc.yml 
```
Para confirmar que tudo deu certo, basta confirmar se tudo subiu corretamente executando um
`kubectl get all` e validar se consegue visualizar o pod e service em execução.

#### Executando a migração
Com o posgres executando, agora é fora de executar a migração para criação das tabelas.
```
kubectl apply -f k8s/migration/flyway-configmap.yml 

kubectl apply -f k8s/app/fourlanches-secrets.yml 

kubectl apply -f k8s/migration/flyway-job.yml 
```
#### Subindo a aplicação
Agora com o postgres executando e com as tabelas criadas, basta executar os comandos abaixo:
```
kubectl apply -f k8s/app/fourlanches-configmap.yml 

kubectl apply -f k8s/app/fourlanches-deployment.yml

kubectl apply -f k8s/app/fourlanches-svc.yml         
```

### Testando com insomnia
Para executar as requisições com o insomnia, basta importar o arquivo `insomnia-collection.json`
no seu insomnia que a collection estará pronta para ser usada.

#### Nota para executar com Insomnia executando pelo K8S
Para testar os endpoints caso a execução seja realizada via K8S, deverá ser atualizada a URL com a PORTA correta exposta
pelo K8S.

### Links Adicionais
