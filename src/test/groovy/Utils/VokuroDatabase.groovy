package Utils

class VokuroDatabase {

    static restoreOriginal() {
        print "Restoring Vokuro's DB..."
        Const.MYSQL_CONNECTION.execute(new File('src/test/groovy/Data/vokuro.sql').text)
        print " Done."
        println()
    }
}
