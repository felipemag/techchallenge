# Four Lanches App

## DOC

### 1. Tecnologias
* Java 17 
* Spring Boot
* Postgres SQL
* Docker
* [Swagger](http://localhost:8080/swagger-ui/index.html)

### 2. Pre-requisitos para rodar a aplicação
* [Java Version: 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) 
* [Gradle](https://gradle.org/install/)
* [IDE IntelliJ](https://www.jetbrains.com/idea/)
* [Docker](https://www.docker.com/)
* Kubernetes - K8S

### 3. Para executar localmente com Docker
#### 3.1. Para buildar, executar as migration e rodar o app no docker pela primeira vez

`docker compose up --build -d`

#### 3.2. Para executar o app com docker após a primeira vez
Após rodar a primeira o comando acima, execute o seguinte comando abaixo para que apenas execute 
os containers sem a etapa de build e migration.

`docker compose up -d app db`

#### 3.3. Necessário um arquivo .env na raiz do projeto com a seguinte conteúdo:
```
DATABASE_USERNAME=username_do_banco  
DATABASE_PASSWORD=password_do_banco
```

### 4. Para rodar com K8S

#### 4.1. Subindo o postgres como base de dados
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

#### 4.2. Executando a migração
Com o posgres executando, agora é fora de executar a migração para criação das tabelas.
```
kubectl apply -f k8s/migration/flyway-configmap.yml 

kubectl apply -f k8s/app/fourlanches-secrets.yml 

kubectl apply -f k8s/migration/flyway-job.yml 
```
#### 4.3. Subindo a aplicação
Agora com o postgres executando e com as tabelas criadas, basta executar os comandos abaixo:
```
kubectl apply -f k8s/app/fourlanches-configmap.yml 

kubectl apply -f k8s/app/fourlanches-deployment.yml

kubectl apply -f k8s/app/fourlanches-loadbalancer.yml

kubectl apply -f k8s/app/fourlanches-svc.yml         
```

### 5. Testando com insomnia
Para executar as requisições com o insomnia, basta importar o arquivo `insomnia-collection.json`
no seu insomnia que a collection estará pronta para ser usada.

#### 5.1. Nota para executar com Insomnia executando pelo Docker
Subindo a aplicação via docker, a mesma se encotrará disponível no localhost:8080 para ser acessada

#### 5.1. Nota para executar com Insomnia executando pelo K8S
Para testar os endpoints caso a execução seja realizada via K8S, deverá ser atualizada a URL com a PORTA correta exposta
pelo K8S. Por exemplo, caso use o minikube basta executar o comando `minikube service fourlanches-loadbalancer` e o mesmo
irá expor a url e porta correta e abrirá o link direto no nvaegador.

### 6. Links Adicionais
