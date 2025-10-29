pipeline {
    agent {
            docker {
                image 'maven:3.9.6-jdk-21'
                args '-v /root/.m2:/root/.m2'
            }
        }
    stages {
        stage('Run Maven Tests') {
            steps {
                sh 'mvn clean test -Dremote.driver=true'
            }
        }
    }
}