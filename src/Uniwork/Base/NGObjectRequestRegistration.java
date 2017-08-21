package Uniwork.Base;

public interface NGObjectRequestRegistration {

    NGObjectRequestMethod registerObjectRequest(String aName, Object aObject, String aMethod, String aObjectMethod);
    NGObjectRequestMethod registerObjectRequest(String aName, Object aObject, String aMethod, String aObjectMethod, String aDescription);

}
