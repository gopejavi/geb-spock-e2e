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

    // ALERT MESSAGES

    static final String NAME_REQUIRED = "The name is required",
                        EMAIL_REQUIRED = "The e-mail is required",
                        EMAIL_NOT_VALID = "The e-mail is not valid",
                        EMAIL_ALREADY_REGISTERED = "The email is already registered",
                        PASS_REQUIRED = "The password is required",
                        PASS_TOO_SHORT = "Password is too short. Minimum 8 characters",
                        CONFIRM_PASS_REQUIRED = "The confirmation password is required",
                        PASS_DOESNT_MATCH = "Password doesn't match confirmation",
                        PASS_CHANGED = "Your password was successfully changed",
                        ACCEPT_TERMS = "Terms and conditions must be accepted",
                        WRONG_EMAIL_PASS = "Wrong email/password combination",
                        DONT_HAVE_ACCESS = "You don't have access to this module: private"
}
