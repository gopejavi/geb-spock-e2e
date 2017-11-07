package Modules

import geb.Module

class HeaderLoggedModule extends Module {

    static content = {
        header { $("#header") }
        brand { header.$("a.brand") }

        homeLink { header.$("a", href: "/index") }
        usersLink { header.$("a", href: "/users") }

        userDropdown { header.$(".dropdown") }//select with username? "has"...
        changePasswordLink { userDropdown.$("a", href: "/users/changePassword") }
        logoutLink { header.$("a", href: "/session/logout") }
    }

}
