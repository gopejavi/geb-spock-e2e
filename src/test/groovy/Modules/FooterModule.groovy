package Modules

import geb.Module

class FooterModule extends Module {

    static content = {
        footer { $("footer") }
        privacyLink { footer.$("a", href: "/privacy") }
        termsLink { footer.$("a", href: "/terms") }
    }
}
