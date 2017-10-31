package Modules

import geb.Module

class HeaderLoggedModule extends Module {

    static content = {
        header { $("#header") }
        brand { header.$("a.brand") }

        homeLink { header.$("a", href: "/index") }

        logoutLink { header.$("a", href: "/session/logout") }
    }
}
