job('Aplicacion Node.js DSL 2') {
    description('Aplicación Node JS DSL para el curso de Jenkins')
    scm {
        git('https://github.com/hjbarbieri/jenkins.git', 'main') { node ->
            node / gitConfigName('hjbarbieri')
            node / gitConfigEmail('jbarbieri@morean.co')
        }
    }
    triggers {
        scm('H/7 * * * *')
    }
    wrappers {
        nodejs('nodejs')
    }
    steps {
        shell("npm install")
    }
    publishers {
	slackNotifier {
            notifyAborted(true)
            notifyEveryFailure(true)
            notifyNotBuilt(false)
            notifyUnstable(false)
            notifyBackToNormal(true)
            notifySuccess(true)
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
