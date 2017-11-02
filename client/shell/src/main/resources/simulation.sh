#! /bin/bash
JAVA_OPT="-Xmx4g -Xms4g -Xmn2g"
JAR_NAME="simulation.jar"

echo "--- Start Simulation ---"

java $JAVA_OPT -jar $JAR_NAME $*

echo "--- End Simulation ---"