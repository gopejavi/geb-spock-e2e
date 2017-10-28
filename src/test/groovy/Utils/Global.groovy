package Utils

import groovy.sql.Sql

class Global {
    static final String ip = System.getProperty('geb.build.baseUrl').replace("http://", "")
    static final Sql mySqlConnection = Sql.newInstance(
            "jdbc:mysql://$ip/vokuro?allowMultiQueries=true",
            "root",
            "",
            "com.mysql.cj.jdbc.Driver"
    )
}
