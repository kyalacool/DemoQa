pipeline {
    agent any
    parameters {
        choice(
            name : 'Browser',
            choices : 'Chrome\nEdge\nFirefox',
            description : 'Set browser :'
            )
        booleanParam(name: 'Headless', defaultValue: true, description: 'Is this test run in headless mode?')
        booleanParam(name: 'Parallel', defaultValue: false, description: 'Is this test will be parallel?')
    }
    stages {
        stage('Build and Chekout') {
            steps{
                git branch : 'master',
                url : 'https://github.com/kyalacool/DemoQa.git'
            }
        }
        stage('Conditional Test Execution'){
            steps{
                script{
                    if(params.Parallel == false){
                        echo "HomePageTest start with ${params.Browser} browser in sequently mode."
                        sh "mvn clean test -Denv=ci -Dtest='HomePageTest' -Dbrowser=${params.Browser} -Dheadless=${params.Headless}"
                        echo "ElementsPageTest start with ${params.Browser} browser in sequently mode"
                        sh "mvn test -Denv=ci -Dtest='ElementsPageTest' -Dgroups='!download' -Dbrowser=${params.Browser} -Dheadless=${params.Headless}"
                    }
                    else{
                        echo "Test start with ${params.Browser} browser in parallel mode."
                        sh "mvn clean test -Denv=ci -D'surefire.suiteXmlFiles'='src/test/resources/parallel-without-download.xml' -Dbrowser=${params.Browser} -Dheadless=${params.Headless}"
                    }
                }
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