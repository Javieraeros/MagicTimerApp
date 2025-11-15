pipeline {
    agent any

    stages {
        stage('Clean') {
            steps {
                sh 'chmod +x ./gradlew'
                sh './gradlew clean'
            }
        }
        stage('Build') {
            steps {
                sh 'chmod +x ./gradlew'
                sh './gradlew assembleDebug'
            }
        }
        stage('Test') {
            steps {
                sh 'chmod +x ./gradlew'
                sh './gradlew test --stacktrace'
            }
        }
    }

    triggers {
        pollSCM('* * * * *')
    }

    post {
        success {
            notify("success")
        }
        failure {
            notify("failure")
        }
    }
}

def notify(status) {
    def jobName = env.JOB_NAME + ' ' + env.GIT_BRANCH
    def buildNo = env.BUILD_NUMBER

    sh "echo ${jobName} Build #${buildNo} ${status}"
}