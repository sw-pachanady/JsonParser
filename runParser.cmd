cd target
echo  off
set PATH=.;%PATH%
# make sure JAVA_HOME is set and java and mvn are available in $PATH and exported
java -jar JsonParser-1.0-jar-with-dependencies.jar %1 %2
cd ..