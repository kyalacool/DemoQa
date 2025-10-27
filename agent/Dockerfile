# Jenkins agent :

FROM maven:3.9.4-eclipse-temurin-17

RUN apt-get update && apt-get install -y docker.io curl \
    && rm -rf /var/lib/apt/lists/*

RUN DOCKER_COMPOSE_VERSION=2.23.0 && \
    curl -SL "https://github.com/docker/compose/releases/download/v${DOCKER_COMPOSE_VERSION}/docker-compose-linux-x86_64" -o /usr/local/bin/docker-compose && \
    chmod +x /usr/local/bin/docker-compose

RUN useradd -m -d /home/jenkins -u 1000 -s /bin/bash jenkins
USER jenkins
WORKDIR /home/jenkins


#   Build it :
#       docker build -t jenkins-maven-docker-agent .


