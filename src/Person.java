import java.util.List;

public class Person {

    private String name;
    private int age;
    private Group group;
    private List<Group> vetos;

    public Person(String name,int age, List vetos){
        this.vetos = vetos;
        this.age = age;
        if(age<25) vetos.add(Group.ROVER);
        this.name = name;
    }

    public String getName(){
        return name;
    }
    public List<Group> getVetos() {
        return vetos;
    }

    public Group getGroup() {
        return group;
    }
    public void setGroup(Group group) {
        this.group = group;
    }
}

