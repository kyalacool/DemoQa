# Jenkins-master :
# Build : docker run -d --name jenkins-master -p 8181:8080 -v jenkins_home:/var/jenkins_home jenkins/jenkins:lts

# Jankins-agent :

FROM maven:sapmachine

USER root
RUN apt-get update && \
    apt-get install -y --no-install-recommends docker-cli curl && \
    curl -L "https://github.com/docker/compose/releases/download/v2.28.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose && \
    chmod +x /usr/local/bin/docker-compose && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

USER jenkins

#   Build it :
#       docker build -t jenkins-maven-docker-agent .

#     Run it ??:
#      docker run -d -p 8181:8080 --name jenkins-master -v jenkins_home:/var/jenkins_home jenkins-maven-docker-agent
