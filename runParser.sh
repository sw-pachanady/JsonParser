#!/bin/bash
cd target
# make sure JAVA_HOME is set and java and mvn are available in $PATH and exported(in linux)
java -jar JsonParser-1.0-SNAPSHOT-jar-with-dependencies.jar %1 %2
cd ..