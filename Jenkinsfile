pipeline {
    agent any
    stages {
        stage('Run Maven Tests') {
            steps {
                sh 'mvn clean test -Dremote.driver=true'
            }
        }
    }
}