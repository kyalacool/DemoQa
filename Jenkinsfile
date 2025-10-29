pipeline {
    agent any
    stages {

        stage('Debug') {
            steps {
                sh 'git --version'
                sh 'docker --version'
                sh 'docker compose version || echo "v1 compose fallback"'
            }
        }

        stage('Run Maven Tests') {
            steps {
                sh 'mvn clean test -Dremote.driver=true'
            }
        }
    }
}