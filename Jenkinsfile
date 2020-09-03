def R_NAME = "sino-dc/paperless-meeting-system"  // Docker Image space/name
def WEBHOOK_TOKEN = "paperless-meeting-system"  // http://10.3.69.41:8080/generic-webhook-trigger/invoke?token=${WEBHOOK_TOKEN}
def S_NAME = 'paperless-meeting-system'
//变量已经方法定义
def remote = [:]
remote.name = 'Machine27'
remote.host = '10.3.69.27'
remote.allowAnyHosts = true
//构建流程定义
pipeline {
    agent none//后续步骤的执行环境 这个位置是全局的执行环境，如果配置了，后续步骤都是这个执行环境，该文件中不同步骤执行环境不同，所以此处配置为none
    triggers { // Generic Webhook Trigger 配置， 第一次需要手动触发让配置保存至jenkins任务
        GenericTrigger(
            genericVariables: [//从webhook的请求中获取相应的参数
              [key: 'ref', value: '$.ref']
            ],
            token: WEBHOOK_TOKEN ,//webhook的触发token，不同的分支可以设置不同的token进行区分
            causeString: ' Triggered on ref' ,
            printContributedVariables: true,
            printPostContent: true//打印webhook的请求参数
        )
    }
    stages {
        stage('sonar analysis') { //stage定义一个构建阶段
            agent {//配置一个maven的环境
                docker {
                    image 'maven:3-alpine'
                    args '-v /root/.m2:/root/.m2'
                }
            }
            steps {
                script {
                    try{
                        sh 'mvn sonar:sonar -Dsonar.projectKey=paperless-meeting-system -Dsonar.host.url=http://10.3.69.41:9000  -Dsonar.login=79bbdefc046d69e52ec5153b2f4011e8f7f0de5e'
                     }catch(Exception e){
                        println 'Exception：'+e
                     }
                }
            }
        }
        stage('package') { //stage定义一个构建阶段
            agent {//配置一个maven的环境
                docker {
                    image 'maven:3-alpine'
                    args '-v /root/.m2:/root/.m2'
                }
            }
            steps {
                script {
                        sh 'echo hello dev'
                        sh 'mvn clean package -Dmaven.test.skip=true'
                        sh 'mv ./target/paperless-meeting-system.jar ./paperless-meeting-system.jar'
                }
            }
        }
        stage('Build  Image') {
            agent any
            steps {
                script {
                        env.tagv = ref.substring(18)
                        docker.withRegistry('https://registry.cn-hangzhou.aliyuncs.com/','registry-aliyun-credentials') {//登录到远程docker仓库，第二个参数是在Jenkins中配置的凭据，详情见官网
                            def image = docker.build(R_NAME)
                            image.push("latest")
                            image.push(env.tagv)
                            sh "echo docker image  push success"
                        }
                    }
            }
        }
        stage('deploy') {
            agent any
            steps {
                script {
                //ssh到远程机器上执行一段自身机器上的脚本
                        withCredentials([usernamePassword(credentialsId: 'wuhan_vm', passwordVariable: 'password', usernameVariable: 'username')]) {
                            remote.user = userName
                            remote.password = password
                            sshScript remote: remote, script: './jenkins/deploy.sh'//dev_deploy脚本是在Jenkins机器上
                        }
                }
            }
        }
        stage('Notification'){ // 通知企微机器人
            agent any
            steps {
                script{
                    def requestBody = """
                        {"msgtype": "markdown","markdown": {"content": "## ${S_NAME} ${env.tagv} is released"  }}
                    """
                    def response = httpRequest acceptType: 'APPLICATION_JSON', contentType: 'APPLICATION_JSON', httpMode: 'POST', requestBody: requestBody, url: "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=60fa799c-4893-4620-9ff0-6471de8944a5"
                    sh "echo $response.content"
                }
            }
        }

      }
    }
