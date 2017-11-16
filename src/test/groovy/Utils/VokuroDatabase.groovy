package Utils

class VokuroDatabase {

    static restoreCommon() {
        print "Restoring Vokuro's DB..."
        Const.MYSQL_CONNECTION.execute(new File('src/test/groovy/Data/vokuro.sql').text)
        print " Done."
        println()
    }
}
