FROM jenkins/jenkins:jdk21

USER root
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

RUN mkdir -p /home/jenkins/.m2 && \
    chown -R jenkins:jenkins /home/jenkins/.m2

USER jenkins

COPY plugins.txt /usr/share/jenkins/ref/plugins.txt

RUN jenkins-plugin-cli -f /usr/share/jenkins/ref/plugins.txt