compilar:
	mkdir -p ./lib
	cp ./TrabSD/lib/* ./lib/.
	mkdir -p ./dist
	find ./TrabSD/lib -name "*.jar" > .classpath
	find ./TrabSD/src -name "*.java" >> .classpath
	javac -d dist/ -cp @.classpath

iniciar:
	java -cp .:lib:dist TrabSD -ipLocal 192.168.122.10 -debug true