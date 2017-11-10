package DataObjects

import groovy.transform.AutoClone
import groovy.transform.TupleConstructor

@AutoClone
@TupleConstructor
class UserSearchData {
    String name,
           email,
           profile
}
