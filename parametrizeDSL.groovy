job('example-job-DSL') {
  description('JOB DSL example for course')
  parameters {
    stringParam('nombre', defaultValue = 'Javier', description = 'String Job Parameter for the name')
    booleanParam('agente', false)
    choiceParam('planeta', ['Mercurio', 'Tierra', 'Neptuno'])
  }
  scm {
    git('https://github.com/macloujulian/jenkins.job.parametrizado.git', 'main') { node -> 
      node / gitConfigName('macloujulian')
      node / gitConfigEmail('macloujulian@gmail.com')
    }
  }
  triggers {
  	cron('H/7 * * * *')
  }
  steps {
  	shell("bash jobscript.sh")
  }
  publishers {
    mailer('jbarbieri@morean.co')
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
