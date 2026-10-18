package dcst.model;

public class Professor extends TeachingStaff implements Teacher, Researcher {

    public Professor(String name, String department) {
        super(name, department);
    }

    @Override
    public String getRole() { return "Professor"; }

    @Override
    public String describe() {
        return Teacher.super.describe() + " + " + Researcher.super.describe();
    }
}
