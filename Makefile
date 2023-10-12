DOCKER_PROJECT_NAME := fourlanches
DB_USER := root
DB_PASSWORD := password

build-image: ## Build Project Image
	docker compose -p $(DOCKER_PROJECT_NAME) build --build-arg DATABASE_USER="$(DB_USER)" --build-arg DATABASE_PASSWORD="$(DB_PASSWORD)" --no-cache --pull

env-start: ## Start project containers defined in docker-compose
	docker compose -p $(DOCKER_PROJECT_NAME) up -d

env-stop: ## Stop project containers defined in docker-compose
	docker compose -p $(DOCKER_PROJECT_NAME) stop

env-destroy: ## Destroy all project containers
	docker compose -p $(DOCKER_PROJECT_NAME) down -v

k8s-apply-db: ## Apply database in k8s environment
	{ \
  	kubectl apply -f k8s/db/postgres-db-pv.yml ;\
    kubectl apply -f k8s/db/postgres-db-pvc.yml ;\
    kubectl apply -f k8s/db/postgres-db-secrets.yml ;\
    kubectl apply -f k8s/db/postgres-db-configmap.yml ;\
    kubectl apply -f k8s/db/postgres-db-deployment.yml ;\
    kubectl apply -f k8s/db/postgres-db-svc.yml ;\
    }

k8s-apply-migration: ## Apply migration in k8s environment
	{ \
  	kubectl apply -f k8s/migration/flyway-configmap.yml ;\
    kubectl apply -f k8s/app/fourlanches-secrets.yml  ;\
    kubectl apply -f k8s/migration/flyway-job.yml  ;\
    }

k8s-apply-app: ## Apply application in k8s environment
	{ \
	kubectl apply -f k8s/app/fourlanches-configmap.yml ;\
    kubectl apply -f k8s/app/fourlanches-deployment.yml  ;\
    kubectl apply -f k8s/app/fourlanches-loadbalancer.yml  ;\
    kubectl apply -f k8s/app/fourlanches-svc.yml ;\
    }

minikube-expose-app: ## Expose app endpoint to localhost and open it in default browser
	minikube service fourlanches-loadbalancer

help: ## Display this help text
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'