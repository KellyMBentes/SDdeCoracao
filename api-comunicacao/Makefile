JSON_VERSION = 1.1.1
API_VERSION = 1.0.0
CLASSPATH = src

install:
	##########################
	## INSTALLING DEPENDENCIES
	##########################
	### JSON Java
	mkdir -p lib/json-java
	#wget https://search.maven.org/remotecontent?filepath=org/json/json/$(JSON_VERSION)/json-$(JSON_VERSION).jar -O lib/json-java.jar
	wget http://central.maven.org/maven2/com/googlecode/json-simple/json-simple/$(JSON_VERSION)/json-simple-$(JSON_VERSION).jar -O lib/json-java/json-java.jar
	##########################
	## END DEPENDECIES
	##########################

clear:
	##########################
	## DEPENDECIES
	##########################
	rm -r lib dist .classpath
	##########################
	## END DEPENDENCIES
	##########################
setup:
	##########################
	## COMPILE
	##########################
	mkdir -p dist
	find ./lib -name *.jar > .classpath
	find ./src -name *.java >> .classpath
	# echo "${CLASSPATH}"
	# echo @sources_list.txt
	javac -d dist -cp @.classpath -Xmaxerrs 5
	# mv dist/src/* dist/
	# rm -r dist/src
	jar cf api_comunicacao_$(API_VERSION).jar dist/** conf/** lib/**
	##########################
	## END COMPILATION
	##########################
