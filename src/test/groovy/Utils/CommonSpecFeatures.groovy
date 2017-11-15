package Utils

import geb.spock.GebReportingSpec

abstract class CommonSpecFeatures extends GebReportingSpec {

    def setupSpec() {
        VokuroDatabase.restoreCommon()
    }
}