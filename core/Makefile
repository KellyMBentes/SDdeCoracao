API_VERSAO = 1.1.2
CLASSPATH = ./TrabSD/src/
DEST_PATH = dist/
TOKEN = 9cd30cfbaef383e3bce82ceffe38da11d9da4a1c

limpar-dependencias:
	rm -f ./lib/*.jar

instalar: limpar-dependencias
	mkdir -p ./lib
	cd ./lib/ && \
	curl --header 'Authorization: token $(TOKEN)' \
	     --header 'Accept: application/vnd.github.v3.raw' \
	     --remote-name \
	     --location https://api.github.com/repos/KellyMBentes/SDdeCoracao/contents/api-comunicacao/jars/api_comunicacao_$(API_VERSAO).jar \
	     && \
	cd ../
	

compilar:
	mkdir -p ./lib
	mkdir -p TrabSD/src/lib
	mkdir -p $(DEST_PATH)
	cp lib/*.jar TrabSD/src/lib/.
	find $(CLASSPATH) -name "*.jar" > .classpath
	find $(CLASSPATH) -name "*.java" >> .classpath
	javac -d $(DEST_PATH) -cp @.classpath

iniciar:
	java -cp .:lib/api_comunicacao_1.0.0.jar:dist TrabSD -ipLocal 192.168.122.10 -debug true