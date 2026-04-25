#!/bin/bash
JAR="target/GoldRush-1.0-SNAPSHOT.jar"

if [ ! -f "$JAR" ]; then
    echo "JAR not found. Run 'mvn package' first to build it."
    exit 1
fi

java -jar "$JAR"