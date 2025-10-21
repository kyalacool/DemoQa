FROM jenkins/jenkins:2.528.1-lts-jdk21

USER root
RUN apt update && apt install -y maven docker-cli

USER jenkins


#   Build it :
#       docker build -t jenkins-with-maven .

#   Run it :
#     docker run -d -p 8181:8080 --name jenkins -v /var/run/docker.sock:/var/run/docker.sock jenkins-with-maven
