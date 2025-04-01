:: @ before the command means do not print the command when running it
:: echo. means output a blank line
@echo ^+----------------------------^+
@echo ^|   Stop Local Environment   ^|
@echo ^+----------------------------^+
@echo.
@docker-compose -f docker-compose-local.yml down --remove-orphans

@echo.
@echo ^+----------------------------^+
@echo ^|   Start Local Environment   ^|
@echo ^+----------------------------^+
@echo.
@docker-compose -f docker-compose-local.yml up -d
@set dbServer=localhost
@set dbPort=1433
@set dbUsername=sa
@set dbPassword=docker123!
@set dbName=demo

@rem @echo delay
@rem ping localhost -n 10

@echo.
@echo ^+----------------------------^+
@echo ^|   Init liquibase           ^|
@echo ^+----------------------------^+
@call gradlew buildLiquibase
@echo.

@echo ^+----------------------------^+
@echo ^|   Init database           ^|
@echo ^+----------------------------^+
@echo.
@cd ../build/liquibase/changesets
@java -cp "../libs/*" liquibase.integration.commandline.Main --username="%dbUsername%" --password="%dbPassword%" --url="jdbc:sqlserver://%dbServer%:%dbPort%;databaseName=tempdb" --changeLogFile="./setup/db.create.xml" update
@echo.

@echo ^+----------------------------^+
@echo ^|   Init database           ^|
@echo ^+----------------------------^+
@echo.
@java -cp "../libs/*" liquibase.integration.commandline.Main --username="%dbUsername%" --password="%dbPassword%" --url="jdbc:sqlserver://%dbServer%:%dbPort%;databaseName=%dbName%" --changeLogFile="db.app.changelog-master.xml" update

@echo.




