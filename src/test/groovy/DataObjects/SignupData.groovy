package DataObjects

import groovy.transform.AutoClone
import groovy.transform.TupleConstructor

@AutoClone
@TupleConstructor
class SignupData {
    String name,
           email,
           password,
           confirmPassword
    boolean agreeTermsConditions
}
