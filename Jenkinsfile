pipeline {

    agent {
            docker {
                image 'jenkins-agent'
                args '-v /var/run/docker.sock:/var/run/docker.sock'
            }
    }

    stages {

        stage('Debug') {
            steps {
                sh 'mvn -version'
                sh 'docker --version'
                sh 'docker compose version'
                }
        }

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
                  sh 'mvn clean test -Dremote.driver=true'
            }
        }

        stage('Teardown Grid') {
            steps {
                sh 'docker compose -f selenium-grid/docker-compose.yml down'
            }
        }
    }
}