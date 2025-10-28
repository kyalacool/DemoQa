# Jenkins agent :

FROM docker:24-dind

RUN apk add --no-cache git maven bash curl

RUN adduser -D -u 1000 jenkins
USER jenkins
WORKDIR /home/jenkins


#   Build it :
#       docker build -t jenkins-agent:java-maven-selenium agent/


