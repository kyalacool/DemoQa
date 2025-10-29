-- Selenium Grid --

open :
docker compose -f selenium-grid/docker-compose.yml up -d
close :
docker compose -f selenium-grid/docker-compose.yml down

-- Jenkins --

docker run -d -p 8181:8080 -p 50000:50000 -v jenkins_home:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock --name jenkins_server jenkins/jenkins:jdk21
close :
docker rm -f jenkins_server