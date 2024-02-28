.DEFAULT_GOAL := help

help:
	@echo "Usage: make [command]"
	@echo "Commands:"
	@echo "run				Boots up the tomcat server"
	@echo "verify			Verify the code by running Detekt"
	@echo "ktlint           Lint checking using ktlint"
	@echo "format			Format files using ktlint"
	@echo "detekt			Code analysis using detekt"


run:
	@echo "Running the application..."
	@mvn spring-boot:run

verify:
	@echo "Running detekt..."
	@mvn verify

ktlint:
	@echo "Lint check using ktlint..."
	@mvn antrun:run@ktlint

format:
	@echo "Formatting files using ktlint..."
	@mvn antrun:run@ktlint-format

detekt:
	@echo "Code analysis using detekt..."
	@mvn antrun:run@detekt
