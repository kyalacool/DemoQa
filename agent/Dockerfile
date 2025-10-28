# Jenkins agent :

FROM jenkins/inbound-agent:jdk17
USER root

RUN apt-get update && apt-get install -y maven \
    && rm -rf /var/lib/apt/lists/*

RUN apt-get update && \
    apt-get install -y lsb-release && \
    curl -fsSLo /usr/share/keyrings/docker-archive-keyring.asc \
        https://download.docker.com/linux/debian/gpg && \
    echo "deb [arch=$(dpkg --print-architecture) \
        signed-by=/usr/share/keyrings/docker-archive-keyring.asc] \
        https://download.docker.com/linux/debian \
        $(lsb_release -cs) stable" > /etc/apt/sources.list.d/docker.list && \
    apt-get update && \
    apt-get install -y docker-ce-cli

USER jenkins


#   Build it :
#       docker build -t jenkins-agent:java-maven-selenium agent/


