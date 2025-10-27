# Jenkins master

FROM jenkins/jenkins:lts

USER root

RUN apt-get update && apt-get install -y docker.io \
    && rm -rf /var/lib/apt/lists/*

USER jenkins

# docker build -t jenkins-docker-master .
# docker run -d -p 8181:8080 --name jenkins-master -v /var/run/docker.sock:/var/run/docker.sock -v jenkins_home:/var/jenkins_home jenkins-docker-master