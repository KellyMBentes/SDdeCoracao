JSON_VERSION = 1.1.1
NOME_PACOTE = api_comunicacao_$(API_VERSION).jar
API_VERSION = 1.0.0
CLASSPATH = src

instalar:
	mkdir -p lib

limpar:
	rm -r lib dist .classpath api_comunicacao_*.jar

compilar:
	mkdir -p lib
	mkdir -p dist
	mkdir -p jars
	find ./lib -name "*.jar" > .classpath
	find ./src -name "*.java" >> .classpath
	javac -d dist/ -cp @.classpath
	jar cfm $(NOME_PACOTE) manifest.mf -C dist .
	mv $(NOME_PACOTE) ./jars/.

compilar-testes:
	javac -cp ./jars/$(NOME_PACOTE) tests/Test2.java
	javac -cp ./jars/$(NOME_PACOTE) tests/Test1.java

executar-teste-servidor:
	java -cp .:./jars/$(NOME_PACOTE) tests.Test1

executar-teste-cliente:
	java -cp .:./jars/$(NOME_PACOTE) tests.Test2