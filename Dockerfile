FROM jenkins/jenkins:2.528.1-lts-jdk21

USER root
RUN apt update && apt install -y maven

USER jenkins

# Build it : docker run -d -p 8181:8080 --name jenkins-with-maven -e DOCKER_HOST=tcp://host.docker.internal:2375 jenkins-with-maven