# Jenkins master

FROM jenkins/jenkins:latest-jdk21

USER root

RUN apt-get update && apt-get install -y docker.io gosu && rm -rf /var/lib/apt/lists/*

COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
COPY jenkins-config.yaml /var/jenkins_home/casc_configs/jenkins-config.yaml

# RUN chmod +x /usr/local/bin/entrypoint.sh

# ENTRYPOINT ["/usr/local/bin/entrypoint.sh"]

USER jenkins
#Run under WSL2 Linux

# docker build -t jenkins-master-dfd master/
# docker run -d -p 8181:8080 --name jenkins-master -v /var/run/docker.sock:/var/run/docker.sock:rw -v jenkins-data:/var/jenkins_home -u root jenkins-master-dfd