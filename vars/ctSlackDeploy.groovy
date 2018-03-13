#!/usr/bin/groovy

def call(params, eventType) {

  def color
  switch (eventType) {
     case "started":
     case "succeeded":
      color = "good"
      break
     case "failed":
      color = "danger"
      break
     case "aborted":
      color = "warning"
      break
     default:
      color = "warning"
      break
  }

  if (params.environment == 'prod') {
    slackSend channel: '#production', color: "${color}", message: "<${BUILD_URL}|Deployment> of *${params.release}* ${eventType} for environment *${params.environment}*", teamDomain: 'comtravo', tokenCredentialId: 'slack'
  }
  else {
    slackSend channel: '#deploy', color: "${color}", message: "<${BUILD_URL}|Deployment> of *${params.release}* ${eventType} for environment *${params.environment}*", teamDomain: 'comtravo', tokenCredentialId: 'slack'
  }
}
