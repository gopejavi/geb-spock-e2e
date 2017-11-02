package Utils

class VokuroDatabase {

    static restoreOriginal() {
        print "Restoring Vokuro's DB..."
        Global.mySqlConnection.execute(new File('src/test/groovy/Data/vokuro.sql').text)
        print " Done."
        println()
    }
}
