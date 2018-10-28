pipeline {
    agent any



    stages {
        stage('preparando ambiente') {
            steps {
                script {
                    // checkout scm
                    checkout([$class: 'GitSCM', branches: [[name: '/master/']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'LocalBranch', localBranch: "**"]], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'dd99fa52-d886-4980-bf85-9a3eac462bd8', url: 'https://github.com/lucianoyamane/oauth.git']]])
                    echo '**************************'
                    echo env.BRANCH_NAME
                }
            }
        }
    }
}
