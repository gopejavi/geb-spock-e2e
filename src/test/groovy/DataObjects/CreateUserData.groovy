package DataObjects

import groovy.transform.AutoClone
import groovy.transform.TupleConstructor

@AutoClone
@TupleConstructor
class CreateUserData {
    String name,
           email,
           profile
}
