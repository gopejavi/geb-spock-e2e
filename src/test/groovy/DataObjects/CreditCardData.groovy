package DataObjects

import groovy.transform.AutoClone
import groovy.transform.TupleConstructor

@AutoClone
@TupleConstructor
class CreditCardData {
    String number,
           expiration,
           cvv
}
