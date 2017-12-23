package Modules

import geb.Module

class AlertsModule extends Module {

    static content = {
        all { $("body").$(".alert") }
        brand { all.filter(".alert-brand") }

        //these others just as examples, I left them here for demonstration purposes.
        warning { all.filter(".alert-warning") }
        error { all.filter(".alert-danger") }
    }
}
