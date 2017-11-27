JSON_VERSION = 1.1.1
NOME_PACOTE = api_comunicacao_$(API_VERSION).jar
API_VERSION = 1.1.3
CLASSPATH = src/
DEST_PATH = dist/

instalar:
	mkdir -p lib

limpar:
	rm -r lib dist .classpath api_comunicacao_*.jar

compilar:
	mkdir -p lib
	mkdir -p $(DEST_PATH)
	mkdir -p jars
	find ./lib -name "*.jar" > .classpath
	find $(CLASSPATH) -name "*.java" >> .classpath
	javac -d $(DEST_PATH) @.classpath -cp $(CLASSPATH)
	jar cfm $(NOME_PACOTE) manifest.mf -C $(DEST_PATH) .
	mv $(NOME_PACOTE) ./jars/.

compilar-testes:
	javac -cp ./jars/$(NOME_PACOTE) tests/Test2.java
	javac -cp ./jars/$(NOME_PACOTE) tests/Test1.java

executar-teste-servidor:
	java -cp .:./jars/$(NOME_PACOTE) tests.Test1

executar-teste-cliente:
	java -cp .:./jars/$(NOME_PACOTE) tests.Test2