pipeline {
    agent {
            docker {
                image 'maven:sapmachine'

                args '-v maven_repo:/root/.m2'

                network 'selenium_network'
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