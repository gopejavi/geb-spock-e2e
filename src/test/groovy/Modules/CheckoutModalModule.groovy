package Modules

import geb.Module

class ResultModalModule extends Module {

    static content = {
        footer { $("footer") }
        //... implement this repeating component (and others) in the same way than header.
    }
}
