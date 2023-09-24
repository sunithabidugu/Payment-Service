pipeline {
    agent any
    tools{
        maven 'maven'
        jdk 'jdk-11'
    }

 
    stages {
        stage('Checkout') {
            steps {
                script {
                    // Use the Git tool named "Default Git" (or the name you configured)
                    // and the credential with ID "github_credential"
                    checkout([$class: 'GitSCM', branches: [[name: '*/main']],
                              doGenerateSubmoduleConfigurations: false,
                              extensions: [[$class: 'CloneOption', gitToolName: 'Default Git', credentialsId: 'github_credential']],
                              submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/sunithabidugu/Payment-Service.git']]])
                }
            }
        }
      
    }


 

 

        stage('Build'){
            steps {
                bat "mvn clean install -DskipTests"
            }
        }

 

 

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                    bat 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.8.0.2131:sonar -Dsonar.login=admin -Dsonar.password=Sunitha@2028'
                }
        }

 

 

       stage("Deployment") {
           steps{
    bat 'start /B mvnw spring-boot:run -Dserver.port=8001'
}
       }
    }
}

has context menu
