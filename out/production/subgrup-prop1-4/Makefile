CLASES = ./main/Domain/DomainModel/*.java ./main/Domain/DomainControllers/*.java ./main/Persistence/PersistenceClasses/*.java ./main/Persistence/PersistenceControllers/*.java ./main/Presentation/*.java ./main/Presentation/*/*/*.java ./main/Presentation/*/*/*/*.java
EJECUTABLES = ../../EXE/out/
LIB = ../../lib/junit-4.12.jar:../../lib/byte-buddy-1.12.9.jar:../../lib/byte-buddy-agent-1.12.9.jar:../../lib/mockito-core-4.5.0.jar:../../lib/objenesis-3.2.jar

build:
		javac -target 11 -cp $(LIB) -d $(EJECUTABLES) $(CLASES)

exec:
		cd ../../EXE/ && java -jar PropXcel.jar

help:
		@echo "Opciones:"
		@echo "build: Compila el código entero(No es necesario para que el programa funcione, sólo con motivo de depuración)."
		@echo "exec: Se ejecuta el software usando el ejecutable /EXE/out/main/Presentation/PropXcel.jar ."
		@echo "clean: Borra todos los ejecutables de /EXE/out/."

clean:
		rm -rf $(EJECUTABLES)