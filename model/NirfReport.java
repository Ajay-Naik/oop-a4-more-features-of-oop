package dcst.model;

import java.util.ArrayList;
import java.util.List;

public class NirfReport implements Reportable {
    private final String discipline;
    private final int fromYear;
    private final int toYear;
    private final List<Member> members = new ArrayList<>();
    private final List<Activity> activities = new ArrayList<>();
    private final List<Course> courses = new ArrayList<>();

    public NirfReport(String discipline, int fromYear, int toYear) {
        this.discipline = discipline;
        this.fromYear = fromYear;
        this.toYear = toYear;
    }

    public void addMember(Member m) { members.add(m); }
    public void addActivity(Activity a) { activities.add(a); }
    public void addCourse(Course c) { courses.add(c); }

    public void print() {
        System.out.println("NIRF submission - " + discipline + " Discipline, DCST, Goa University (" + fromYear + "-" + toYear + ")");
        System.out.println("\nStudents and Staff:");
        for (Member m : members) System.out.println("  " + m.getDetails());
        System.out.println("\nAcademic Activities:");
        for (Activity a : activities) if (a.getType().isAcademic()) System.out.println("  " + a.getReportLine());
        System.out.println("\nNon-Academic Activities:");
        for (Activity a : activities) if (!a.getType().isAcademic()) System.out.println("  " + a.getReportLine());
        System.out.println("\nCourses and Subjects:");
        for (Course c : courses) System.out.println("  " + c);
    }

    @Override
    public String getReportLine() {
        return "NIRF report | " + discipline + " | " + members.size() + " members, "
                + activities.size() + " activities, " + courses.size() + " courses";
    }
}
