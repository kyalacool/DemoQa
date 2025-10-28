# Jenkins agent :

FROM docker:24-dind

RUN apk add --no-cache \
    git \
    maven \
    bash \
    curl \
    openjdk17  # Maven futtatásához Java kell


RUN adduser -D -u 1000 jenkins
USER jenkins
WORKDIR /home/jenkins

ENV MAVEN_OPTS="-Dmaven.repo.local=/home/jenkins/.m2/repository"

#   Build it :
#       docker build -t jenkins-agent agent/


