package Utils

class DataObjectsHelper {

    /***
     * This copies an object, changing only the properties passed as params.
     * @param object The object to copy.
     * @param overrides the properties of the object wich will be overrided.
     * @return a copy of the object with the specified params changed.
     */
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
