job('example-parameter-job-DSL') {
  description('Job DSL de ejemplo para el curso de Jenkins')
    scm {
        git('https://github.com/merq-rodriguez/jenkins.job.parametrizado.git', 'main') { node ->
          node / gitConfigName('merq-rodriguez')
          node / gitConfigEmail('merq.rodriguez@udla.edu.co')
        }
    }

  parameters {
    stringParam('nombre', defaultValue = 'Julian', description = 'Parametro de cadena para el Job Booleano')
    choiceParam('planeta', ['Mercurio', 'Venus', 'Tierrra', 'Marte', 'Jupiter', 'Saturno', 'Urano', 'Neptuno'])
    booleanParam('agente', false)
  }

  triggers {
    cron('H/7 * * * *')
  }

  steps {
    shell('bash jobscript.sh')
  }

  publishers {
    mailer('stiivcode@gmail.com', true, true)
    slackNotifier {
          notifyAborted(true)
          notifyEveryFailure(true)
          notifyNotBuilt(false)
          notifyUnstable(false)
          notifyBackToNormal(true)
          notifySuccess(false)
          notifyRepeatedFailure(false)
          startNotification(false)
          includeTestSummary(false)
          includeCustomMessage(false)
          customMessage(null)
          sendAs(null)
          commitInfoChoice('NONE')
          teamDomain(null)
          authToken(null)
    }
  }
}
