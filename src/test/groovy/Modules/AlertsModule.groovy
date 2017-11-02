package Modules

import geb.Module

class AlertsModule extends Module {

    static content = {
        all { $(".container").$(".alert") }
        info { all.filter(".alert-info") }
        success { all.filter(".alert-success") }
        error { all.filter(".alert-danger") }
    }
}
