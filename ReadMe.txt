-- Make the Network --

docker network create selenium_network

-- Jenkins with Dockerfile (Maven) --

open :
docker compose -f DemoQA-network/jenkins.yml up -d
close :
docker compose -f DemoQA-network/jenkins.yml down --rmi all

-- Selenium Grid --

open :
docker compose -f DemoQA-network/selenium-grid.yml up -d
close :
docker compose -f DemoQA-network/selenium-grid.yml down --rmi all

