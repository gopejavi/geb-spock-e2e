package DataObjects

import groovy.transform.AutoClone
import groovy.transform.TupleConstructor

@AutoClone
@TupleConstructor
class ChangePasswordData {
    String password
    String confirmPassword
}
