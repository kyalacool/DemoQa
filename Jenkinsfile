pipeline {

    agent any

    stages {

        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/kyalacool/DemoQa.git'
            }
        }

        stage('Start Selenium Grid') {
            steps {
                sh 'docker compose -f selenium-grid/docker-compose.yml up -d'
                sh 'sleep 10'
            }
        }

        stage('Run Maven Tests') {
            steps {
                sh "mvn clean test -D'remote.driver'=true"
            }
        }

        stage('Teardown Grid') {
            steps {
                sh 'docker compose -f selenium-grid/docker-compose.yml down'
            }
        }
    }
}