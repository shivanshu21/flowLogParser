#!/bin/bash

LOG_FILE_PATH="testdata/log.txt"
TAG_FILE_PATH="testdata/tagfile.csv"

mvn clean
mvn compile
mvn exec:java -Dexec.mainClass="com.illumio.flowLogParser" -Dexec.args="-L ${LOG_FILE_PATH} -T ${TAG_FILE_PATH}"
