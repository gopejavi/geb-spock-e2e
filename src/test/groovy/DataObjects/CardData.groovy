package DataObjects

import groovy.transform.AutoClone
import groovy.transform.TupleConstructor

@AutoClone
@TupleConstructor
class CardData {
    String number,
           expiration,
           cvv
}
