package dcst.model;

import java.util.ArrayList;
import java.util.List;

public class Student extends Member implements Cloneable {
    private String rollNo;
    private String programme;
    private double marks;
    private transient String sessionNote;
    private List<Course> courses = new ArrayList<>();

    public Student(String name, String rollNo, String programme, double marks) {
        super(name);
        this.rollNo = rollNo;
        this.programme = programme;
        this.marks = marks;
        this.sessionNote = "logged-in";
    }

    public Student(String name, String rollNo) {
        this(name, rollNo, "MCA", 0.0);
    }

    @Override
    public String getRole() { return "Student"; }

    @Override
    public String getDetails() {
        return super.getDetails() + " | " + rollNo + " | " + programme;
    }

    public void addCourse(Course c) {
        courses.add(c);
    }

    public void addCourse(String code, String title, int credits) {
        courses.add(new Course(code, title, credits));
    }

    public String getRollNo() { return rollNo; }
    public double getMarks() { return marks; }
    public String getSessionNote() { return sessionNote; }
    public List<Course> getCourses() { return courses; }

    @Override
    public Student clone() {
        try {
            return (Student) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public Student deepClone() {
        Student copy = clone();
        copy.courses = new ArrayList<>();
        for (Course c : courses) {
            copy.courses.add(c.copy());
        }
        return copy;
    }
}
