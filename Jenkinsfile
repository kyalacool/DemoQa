pipeline {
    agent {
        docker {
            image 'docker:24-dind'
            args '--privileged -v /home/jenkins/.docker:/home/jenkins/.docker'
        }
    }

    environment {
        MAVEN_OPTS = "-Dmaven.repo.local=/home/jenkins/.m2/repository"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM',
                          branches: [[name: '*/master']],
                          userRemoteConfigs: [[url: 'https://github.com/kyalacool/DemoQa.git']]])
            }
        }

        stage('Debug') {
            steps {
                sh 'git --version'
                sh 'docker --version'
                sh 'docker compose version || echo "v1 compose fallback"'
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