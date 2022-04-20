CLASES = ./main/Domain/DomainModel/*.java ./main/Domain/DomainControllers/*.java
DRIVERS_INTEGRATION = ./drivers/integration/*.java
DRIVERS_COMPONENTS = ./drivers/components/*/*.java
JUNIT_TESTS = ./test/*.java
EJECUTABLES = ../../EXE/out/
LIB = ../../lib/junit-4.12.jar:../../lib/mockito-all-1.10.19.jar
IMPORTS = ./main/Domain/*/*.java

build:
		javac -Xlint:unchecked -target 11 -cp $(LIB) -d $(EJECUTABLES) -g $(CLASES) $(DRIVERS_COMPONENTS)
    	javac -Xlint:unchecked -target 11 -d $(EJECUTABLES) -g $(DRIVERS_COMPONENTS)
    	javac -Xlint:unchecked -target 11 -d $(EJECUTABLES) -g $(DRIVERS_INTEGRATION)
    	javac -cp $(LIB) -d $(EJECUTABLES) $(IMPORTS)
    	javac -cp $(LIB) -d $(EJECUTABLES) $(JUNIT_TESTS)

JUnit:
		java -cp $(LIB):$(EJECUTABLES) org.junit.runner.JUnitCore TestRunner

Main:
		java -cp $(EJECUTABLES) drivers.integration.MainTest

ControladorBloqueDriverInteractivo:
		java -cp $(EJECUTABLES) drivers.components.ControladorBloque.DriverControladorBloque

help:
		@echo "Opciones:"
		@echo "build: Compila el código entero."
		@echo "JUnit: Ejecuta los tests de JUnit."
		@echo "Main: Se ejecuta el Driver principal de manera interactiva."
		@echo "ControladorBloqueDriverInteractivo: Se ejecuta el Driver del ControladorBloque de manera interactiva."
		@echo "clean: Borra todos los ejecutables de /EXE/out/."

clean:
		rm -rf $(EJECUTABLES)