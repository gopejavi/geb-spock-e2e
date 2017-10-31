package DataObjects

import groovy.transform.AutoClone
import groovy.transform.TupleConstructor

@AutoClone
@TupleConstructor
class SignupData {
    String name
    String email
    String password
    String confirmPassword
    boolean agreeTermsConditions
}
