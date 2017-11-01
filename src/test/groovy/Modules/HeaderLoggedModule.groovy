package Modules

import geb.Module

class HeaderLoggedModule extends Module {

    static content = {
        header { $("#header") }
        brand { header.$("a.brand") }

        homeLink { header.$("a", href: "/index") }

        userDropdown { header.$(".dropdown") }//has con username?
        changePasswordLink { userDropdown.$("a", href: "/users/changePassword") }
        logoutLink { header.$("a", href: "/session/logout") }
    }
}
