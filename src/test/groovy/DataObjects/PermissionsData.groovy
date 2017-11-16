package DataObjects

import groovy.transform.AutoClone
import groovy.transform.TupleConstructor

@AutoClone
@TupleConstructor
class PermissionsData {
    boolean accessUsers,
            searchUsers,
            editUsers,
            createUsers,
            deleteUsers,
            changePasswordUsers,
            accessProfiles,
            searchProfiles,
            editProfiles,
            createProfiles,
            deleteProfiles,
            accessPermissions
}
