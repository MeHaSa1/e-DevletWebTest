import groovy.json.JsonSlurper

pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    stages {
        stage('Clean') {
            steps {
                sh 'rm -rf allure-results/*'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn clean test || true '
            }
        }

        stage('Allure') {
            steps {
                allure includeProperties: false, jdk: '', results: [[path: 'allure-results']]
            }
        }

        stage('Mail') {
            steps {
                script {
                    def summary = readJSON file: 'allure-report/widgets/summary.json'
                    def resultsdir = new File("${WORKSPACE}/allure-results")
                    def failedtests = []
                    for(file in resultsdir.listFiles()) {
                        if(file.name.endsWith('result.json')) {
                            def json = new groovy.json.JsonSlurper().parse(file)
                            if(json.status == "failed" || json.status == "broken") {
                                failedtests << [
                                    suite: json.labels?.find { it.name == "suite" }?.value ?: "Unknown Suite",
                                    scenario: json.name,
                                    status: json.status
                                ]
                            }
                        }
                    }
                    def resultMessage = "Failed cases found!"
                    def failedTable = ""
                    if(summary.statistic.passed == summary.statistic.total) {
                        resultMessage = "All cases passed!"
                    } else {
                        def failedRows = failedtests.collect { t ->
                            "<tr><td>${t.suite}</td><td>${t.scenario}</td><td>${t.status}</td></tr>"
                        }.join("\n")
                        failedTable = """
                        <h3> Failed Test Case Details: </h3>
                        <table border='1' cellpadding='5' cellspacing='0'>
                        <tr><th>Suite</th><th>Scenario</th><th>Status</th></tr>
                        ${failedRows}
                        </table>
                        """
                    }
                    def  mailbody = """
                    <h2> Test Completed! </h2>
                    <h2> ${resultMessage} </h2>
                    <h3> Test Summary: </h3>
                    <table border='1' cellpadding='5' cellspacing='0'>
                        <tr>
                            <th>Passed</th>
                            <th>Failed</th>
                            <th>Broken</th>
                            <th>Skipped</th>
                            <th>Total</th>
                        </tr>
                        <tr>
                            <td>${summary.statistic.passed}</td>
                            <td>${summary.statistic.failed}</td>
                            <td>${summary.statistic.broken}</td>
                            <td>${summary.statistic.skipped}</td>
                            <td>${summary.statistic.total}</td>
                        </tr>
                    </table>
                    ${failedTable}
                    <p>
                        <a href="${BUILD_URL}allure" style="
                            background-color: #4CAF50;
                            color: white;
                            padding: 10px 20px;
                            text-align: center;
                            text-decoration: none;
                            display: inline-block;
                            border-radius: 5px;
                            font-weight: bold;
                        ">View Allure Report</a>
                    </p>
                    """

                    emailext (
                        subject: "Test Results",
                        body: mailbody,
                        to: "hakansavas564@gmail.com",
                        mimeType: 'text/html'
                        )
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'allure-report/**', fingerprint: true
        }
    }
}