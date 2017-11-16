package Utils

// https://github.com/lindenbaum/geb-multibrowser
import geb.Browser
import geb.Configuration
import geb.ConfigurationLoader
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise

abstract class MultiBrowserGebSpec extends Specification {
    String gebConfEnv = null
    String gebConfScript = null

    private @Shared
            _browsers = []
    private @Shared
            _defaultBrowser = null
    private def currentBrowser


    def newBrowser() {
        def browser = createBrowser()
        _browsers << browser
        browser
    }

    def using(browser, Closure c) {
        currentBrowser = browser
        def returnedValue = c.call()
        currentBrowser = null
        returnedValue
    }

    private Browser createBrowser() {
        new Browser(createConf())
    }

    private Configuration createConf() {
        def conf = new ConfigurationLoader(gebConfEnv).getConf(gebConfScript)
        conf.cacheDriver = false
        return conf
    }

    void quitBrowser(Browser browser) {
        if (browser?.config?.autoClearCookies) {
            browser?.clearCookiesQuietly()
        }
        browser?.quit()
    }

    private void resetBrowsers() {
        _browsers.each { browser ->
            quitBrowser(browser)
        }
        quitBrowser(_defaultBrowser)
        _browsers = []
        _defaultBrowser = null
    }

    private Browser defaultBrowser() {
        if (!_defaultBrowser) {
            _defaultBrowser = createBrowser()
        }
        _defaultBrowser
    }

    def propertyMissing(String name) {
        if (currentBrowser) {
            currentBrowser."$name"
        } else {
            defaultBrowser()."$name"
        }
    }

    def propertyMissing(String name, value) {
        if (currentBrowser) {
            currentBrowser."$name" = value
        } else {
            defaultBrowser()."$name" = value
        }
    }

    def methodMissing(String name, args) {
        if (currentBrowser) {
            currentBrowser."$name"(*args)
        } else {
            defaultBrowser()."$name"(*args)
        }
    }

    private isSpecStepwise() {
        this.class.getAnnotation(Stepwise) != null
    }

    def cleanup() {
        if (!isSpecStepwise()) resetBrowsers()
    }

    def cleanupSpec() {
        if (isSpecStepwise()) resetBrowsers()
    }
}