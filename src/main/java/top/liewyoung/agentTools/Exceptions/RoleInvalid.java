package top.liewyoung.agentTools.Exceptions;

public class RoleInvalid extends Exception {
    public RoleInvalid(String errorRole) {
        super(errorRole+"Is not exist");
    }
}
