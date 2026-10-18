package dcst.model;

public class TeachingStaff extends Member {
    private final String department;

    public TeachingStaff(String name, String department) {
        super(name);
        this.department = department;
    }

    @Override
    public String getRole() { return "Teaching Staff"; }

    @Override
    public String getDetails() {
        return super.getDetails() + " | " + department;
    }

    public String getDepartment() { return department; }
}
