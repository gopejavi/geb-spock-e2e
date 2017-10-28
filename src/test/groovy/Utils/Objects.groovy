package Utils

class Objects {

    static createDataFrom(Object object, Map overrides = [:]) {
        def copiedObject = object.clone()
        overrides.each { String key, value ->
            if (copiedObject.hasProperty(key)) {
                copiedObject.setProperty(key, value)
            } else {
                println "Error: Trying to add property that doesn't exist"
            }
        }
        return copiedObject
    }
}
