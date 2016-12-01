#!/usr/bin/env bash
sudo apt-get install -y maven

cd /vagrant

mvn clean

mvn package -Dmaven.test.skip=true