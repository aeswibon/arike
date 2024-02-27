.DEFAULT_GOAL := help

help:
	@echo Usage: make [command]
	@echo Commands:
	@echo run                        Boots up the tomcat server


run:
	@echo Running the application
	@mvnw spring-boot:run