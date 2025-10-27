# Jenkins-master :
# Build : docker run -d --name jenkins-master -p 8181:8080 -v jenkins_home:/var/jenkins_home jenkins/jenkins:lts

# Jankins-agent :

FROM maven:3.9.4-eclipse-temurin-17

RUN apt-get update && apt-get install -y \
    docker.io \
    docker-compose \
    && rm -rf /var/lib/apt/lists/*

RUN useradd -m -d /home/jenkins -u 1000 -s /bin/bash jenkins
USER jenkins
WORKDIR /home/jenkins


#   Build it :
#       docker build -t jenkins-maven-docker-agent .

#     Run it ??:
#      docker run -d -p 8181:8080 --name jenkins- -v /var/run/docker.sock:/var/run/docker.sock jenkins-maven-docker-agent

