#!/usr/bin/bash

mvn install -DskipTests
docker build -t ontoologydd/local .
