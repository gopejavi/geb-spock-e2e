package Utils

class VokuroDatabase {

    static restoreOriginal() {
        println "Restoring Vokuro's DB..."
        Global.DBconnection.execute(new File('src/test/groovy/Data/vokuro.sql').text)
        println "Done"
    }
}
