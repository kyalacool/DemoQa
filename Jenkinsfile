pipeline {
    agent any
    stages {
        stage('Run Maven Tests') {
            steps {
                sh 'mvn clean test -D"remote.driver"=true -Dheadless=false'
            }
        }
    }
}