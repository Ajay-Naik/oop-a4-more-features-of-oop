package dcst.model;

public class NonTeachingStaff extends Member {
    private final String designation;

    public NonTeachingStaff(String name, String designation) {
        super(name);
        this.designation = designation;
    }

    @Override
    public String getRole() { return "Non-Teaching Staff"; }

    @Override
    public String getDetails() {
        return super.getDetails() + " | " + designation;
    }
}
