package top.liewyoung.network;
/*
* @author: LiewYoung
* @date: 25/12/1
* @todo:
*   add network for agent
* */
public class agent {
    public String name;
    public String personality;

    public agent(String firstName,String lastName, String personality) {
        this.name = firstName+" "+lastName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personality = personality;
        this.sex = "男";
    }
    public agent(String firstName,String lastName, String personality,String sex) {
        this.name = firstName+" "+lastName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personality = personality;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "name=" + name + ", personality=" + personality;
    }

    protected String firstName;
    protected String lastName;
    protected String sex;
}
