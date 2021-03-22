#!/usr/bin/env bash
mvn clean install spring-boot:repackage
java -jar target/vmspinup-0.1.jar
