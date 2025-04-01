@echo ^+----------------------------^+
@echo ^|   Init database           ^|
@echo ^+----------------------------^+
@echo.
@cd ../build/liquibase/changesets
@java -cp "../libs/*" liquibase.integration.commandline.Main --username="sa" --password="docker123!" --url="jdbc:sqlserver://localhost:1433;databaseName=demo" --changeLogFile="db.app.changelog-master.xml" update
@echo "database updated!""
@echo.