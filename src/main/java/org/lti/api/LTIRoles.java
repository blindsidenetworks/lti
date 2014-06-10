package org.lti.api;

/**
 * LTI Role definitions.
 *
 * @author Jesus Federico
 */
public class LTIRoles {

    public static String STUDENT = "Student";
    public static String FACULTY = "Faculty";
    public static String MEMBER = "Member";
    public static String LEARNER = "Learner";
    public static String INSTRUCTOR = "Instructor";
    public static String MENTOR = "Mentor";
    public static String STAFF = "Staff";
    public static String ALUMNI = "Alumni";
    public static String PROSPECTIVESTUDENT = "ProspectiveStudent";
    public static String GUEST = "Guest";
    public static String OTHER = "Other";
    public static String ADMINISTRATOR = "Administrator";
    public static String OBSERVER = "Observer";
    public static String NONE = "None";

    public static String URN_SYSTEM_ROLE = "urn:lti:sysrole:ims/lis/";
    public static String URN_INSTITUTION_ROLE = "urn:lti:instrole:ims/lis/";
    public static String URN_CONTEXT_ROLE = "urn:lti:role:ims/lis/";

    public static boolean EXCLUSIVE = true;
    public static boolean NONEXCLUSIVE = false;

    public static boolean isLearner(String _roles){
        boolean response = false;
        String[] roles = _roles.split(",");
        for( int i=0; i < roles.length; i++){
            if( roles[i].equals(LEARNER) ||
                roles[i].equals(URN_INSTITUTION_ROLE + LEARNER) ||
                roles[i].equals(URN_CONTEXT_ROLE + LEARNER)
                ){
                response = true;
                break;
            }
        }
        return response;
    }

    public static boolean isLearner(String _roles, boolean exclusive){
        if( exclusive ){
            boolean response = true;
            String[] roles = _roles.split(",");
            for( int i=0; i < roles.length; i++){
                if( !roles[i].equals(LEARNER) &&
                    !roles[i].equals(URN_INSTITUTION_ROLE + LEARNER) &&
                    !roles[i].equals(URN_CONTEXT_ROLE + LEARNER)
                    ){
                    response = false;
                    break;
                }
            }
            return response;
        } else {
            return isLearner(_roles);
        }
    }

    public static boolean isStudent(String _roles){
        boolean response = false;
        String[] roles = _roles.split(",");
        for( int i=0; i < roles.length; i++){
            if( roles[i].equals(STUDENT) ||
                roles[i].equals(URN_INSTITUTION_ROLE + STUDENT) ||
                roles[i].equals(URN_CONTEXT_ROLE + STUDENT)
                ){
                response = true;
                break;
            }
        }
        return response;
    }

    public static boolean isStudent(String _roles, boolean exclusive){
        if( exclusive ){
            boolean response = true;
            String[] roles = _roles.split(",");
            for( int i=0; i < roles.length; i++){
                if( !roles[i].equals(STUDENT) &&
                    !roles[i].equals(URN_INSTITUTION_ROLE + STUDENT) &&
                    !roles[i].equals(URN_CONTEXT_ROLE + STUDENT)
                    ){
                    response = false;
                    break;
                }
            }
            return response;
        } else {
            return isStudent(_roles);
        }
    }

    public static boolean isAdministrator(String _roles){
        boolean response = false;
        String[] roles = _roles.split(",");
        for( int i=0; i < roles.length; i++){
            if( roles[i].equals(ADMINISTRATOR) ||
                roles[i].equals(URN_SYSTEM_ROLE + ADMINISTRATOR) ||
                roles[i].equals(URN_INSTITUTION_ROLE + ADMINISTRATOR) ||
                roles[i].equals(URN_CONTEXT_ROLE + ADMINISTRATOR)
                ){
                response = true;
                break;
            }
        }
        return response;
    }

    public static boolean isAdmin(String _roles){
        return isAdministrator(_roles);
    }

    public static boolean isA(String _roles, String _role){
        boolean response = false;
        String[] roles = _roles.split(",");
        for( int i=0; i < roles.length; i++){
            if( roles[i].equals(_role) ||
                roles[i].equals(URN_SYSTEM_ROLE + _role) ||
                roles[i].equals(URN_INSTITUTION_ROLE + _role) ||
                roles[i].equals(URN_CONTEXT_ROLE + _role)
                ){
                response = true;
                break;
            }
        }
        return response;
    }

    public static boolean isA(String _roles, String _role, boolean exclusive){
        if( exclusive ){
            boolean response = true;
            String[] roles = _roles.split(",");
            for( int i=0; i < roles.length; i++){
                if( !roles[i].equals(_role) &&
                    !roles[i].equals(URN_SYSTEM_ROLE + _role) &&
                    !roles[i].equals(URN_INSTITUTION_ROLE + _role) &&
                    !roles[i].equals(URN_CONTEXT_ROLE + _role)
                    ){
                    response = false;
                    break;
                }
            }
            return response;
        } else {
            return isA(_roles, _role);
        }
    }

    public static boolean isAny(String _roles, String _role){
        String[] roles = _roles.split(",");
        String[] role = _role.split(",");
        for( int i=0; i < roles.length; i++){
            for( int j=0; j < role.length; j++){
                if( roles[i].equals(role[j]) ||
                    roles[i].equals(URN_SYSTEM_ROLE + role[j]) ||
                    roles[i].equals(URN_INSTITUTION_ROLE + role[j]) ||
                    roles[i].equals(URN_CONTEXT_ROLE + role[j])
                    ){
                    return true;
                }
            }
        }
        return false;
    }

}
