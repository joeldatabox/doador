#!/usr/bin/env bash
sudo apt-get install -y maven

cd /vagrant

mvn package -Dmaven.test.skip=true