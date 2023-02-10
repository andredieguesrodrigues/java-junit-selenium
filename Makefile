up:
	@docker-compose up --detach
	@docker build -t sauce:latest .
	@docker run --name sauce sauce:latest mvn clean site