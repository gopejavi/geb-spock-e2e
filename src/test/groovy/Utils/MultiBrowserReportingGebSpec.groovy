package Utils

// https://github.com/lindenbaum/geb-multibrowser
import geb.report.ReporterSupport
import org.junit.Rule
import org.junit.rules.TestName
import spock.lang.Shared

abstract class MultiBrowserReportingGebSpec extends MultiBrowserGebSpec {
    // Ridiculous name to avoid name clashes
    @Rule
    TestName _gebReportingSpecTestName
    def _gebReportingPerTestCounter = 1
    @Shared
            _gebReportingSpecTestCounter = 1
    @Shared
            haveCleanedReportDir

    def cleanup() {
        report "end"
    }

    void report(String label = "") {
        if (!haveCleanedReportDir) {
            haveCleanedReportDir = true
            browser.cleanReportGroupDir()
        }
        def testClass = getClass()
        def baseReportName = ReporterSupport.toTestReportLabel(_gebReportingSpecTestCounter++, _gebReportingPerTestCounter++, _gebReportingSpecTestName.methodName, label)
        def i = 1
        _browsers.each { browser ->
            browser.reportGroup testClass
            browser.report(baseReportName + "[$i]")
            i += 1
        }
        _defaultBrowser?.reportGroup testClass
        _defaultBrowser?.report(baseReportName)
    }
}