pipeline {
    agent any
    stages {
        stage('Build and Chekout') {
            steps{
                git branch : 'master',
                    url : 'https://github.com/kyalacool/DemoQa.git'
            }
        }
        stage('Run HomePage Tests') {
            steps {
               sh "mvn clean test -Dremote.driver=true -Dtest='HomePageTest'"
            }
        }
        stage('Run ElementsPage Tests without download') {
            steps {
               sh "mvn test -Dremote.driver=true -Dtest='ElementsPageTest' -Dgroups='!download'"
            }
        }
    }
    post {
        always {
            echo 'Generating Allure Report...'
            allure([
                results: [[path: 'target/allure-results']],
                commandline: 'allure',
                reportBuildPolicy: 'ALWAYS'
            ])
            echo 'Allure Report generated.'
        }
    }
}