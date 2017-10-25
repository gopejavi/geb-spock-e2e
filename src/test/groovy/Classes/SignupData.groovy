package Classes

import groovy.transform.TupleConstructor

@TupleConstructor
class SignupData {
    def name
    def email
    def password
    def confirmPassword
}
