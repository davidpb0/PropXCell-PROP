CLASES = ./main/Domain/DomainModel/*.java ./main/Domain/DomainControllers/*.java ./main/Persistence/PersistenceClasses/*.java ./main/Persistence/PersistenceControllers/*.java ./main/Presentation/*.java ./main/Presentation/*/*/*.java
EJECUTABLES = ../../EXE/out/
LIB = ../../lib/junit-4.12.jar:../../lib/byte-buddy-1.12.9.jar:../../lib/byte-buddy-agent-1.12.9.jar:../../lib/mockito-core-4.5.0.jar:../../lib/objenesis-3.2.jar

build:
		javac -target 11 -cp $(LIB) -d $(EJECUTABLES) $(CLASES)

Main:
		java -cp $(LIB):$(EJECUTABLES) main.presentation.MainProgram

help:
		@echo "Opciones:"
		@echo "build: Compila el código entero."
		@echo "Main: Se ejecuta el método principal del programa."
		@echo "clean: Borra todos los ejecutables de /EXE/out/."

clean:
		rm -rf $(EJECUTABLES)