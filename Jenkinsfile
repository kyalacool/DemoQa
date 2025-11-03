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
                echo "Test start with ${Browser} browser"
                sh "mvn clean test -Denv=ci -Dtest='HomePageTest' -Dbrowser=${Browser}"
            }
        }
        stage('Run ElementsPage Tests without download') {
            steps {
                echo "Test start with ${Browser} browser"
                sh "mvn test -Denv=ci -Dtest='ElementsPageTest' -Dgroups='!download' -Dbrowser=${Browser}"
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