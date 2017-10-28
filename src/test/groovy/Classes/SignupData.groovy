package Classes

import groovy.transform.AutoClone
import groovy.transform.TupleConstructor

@AutoClone
@TupleConstructor
class SignupData {
    def name
    def email
    def password
    def confirmPassword
}
