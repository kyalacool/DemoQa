-- Selenium Grid --

open :
docker compose -f selenium-grid/docker-compose.yml up -d
close :
docker compose -f selenium-grid/docker-compose.yml down

-- Jenkins --

docker run -p 8181:8080 -p 50000:50000 --name jenkins_server -d jenkins/jenkins:lts-jdk21