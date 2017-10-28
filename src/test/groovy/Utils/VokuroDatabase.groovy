package Utils

class VokuroDatabase {

    static restoreOriginal() {
        println "Restoring Vokuro's DB..."
        Global.mySqlConnection.execute(new File('src/test/groovy/Data/vokuro.sql').text)
        println "Done"
    }
}
