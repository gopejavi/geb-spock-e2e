package Modules

import geb.Module

class HeaderModule extends Module {

    static content = {
        all { $("header") }
        aplazameLogo { all.$(".logo-link") }
        demoLink { all.$(".demo-link") }
    }
}
