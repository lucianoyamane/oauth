pipeline {
    agent any
    }


    stages {
        stage('preparando ambiente') {
            steps {
                script {
                    checkout scm
                    echo '**************************'
                    echo env.BRANCH_NAME
                }
            }
        }
    }
}
