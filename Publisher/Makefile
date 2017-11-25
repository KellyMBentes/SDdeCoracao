compilar:
	mkdir -p dist
	find ./src -name "*.java" > .classpath
	javac -d dist/ @.classpath

iniciar:
	java -cp dist/ publisher.PublisherGenerator -ip 192.168.122.10