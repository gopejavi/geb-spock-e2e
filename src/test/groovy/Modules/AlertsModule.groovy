package Modules

import geb.Module

class AlertsModule extends Module {

    static content = {
        all { $(".container").$(".alert") }
        success { all.filter(".alert-success") }
        info { all.filter(".alert-info") }
        warning { all.filter(".alert-warning") }
        error { all.filter(".alert-danger") }
    }
}
