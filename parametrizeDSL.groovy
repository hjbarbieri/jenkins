job('example2-job-DSL') {
  description('JOB DSL example for course')
  parameters {
    stringParam('nombre', defaultValue = 'Javier', description = 'String Job Parameter for the name')
    booleanParam('agente', false)
    choiceParam('planeta', ['Mercurio', 'Tierra', 'Neptuno'])
  }
  scm {
    git('https://github.com/hjbarbieri/jenkins.git', 'main') { node -> 
      node / gitConfigName('hjbarbieri')
      node / gitConfigEmail('jbarbieri@morean.co')
    }
  }
  triggers {
  	cron('H/7 * * * *')
    githubPush()
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
