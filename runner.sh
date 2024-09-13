#!/bin/bash
mvn clean
mvn compile
mvn exec:java -Dexec.mainClass="com.illumio.flowLogParser" -Dexec.args="-L testdata/log.txt -T testdata/tagfile.csv"
#mvn exec:java -Dexec.mainClass="com.illumio.flowLogParser" -Dexec.args="-L testdata/logs1000.txt -T testdata/tagfile.csv"
