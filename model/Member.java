package dcst.model;

import java.io.Serializable;

public abstract class Member implements Reportable, Serializable {
    private static int count = 0;
    private final int id;
    protected String name;

    public Member(String name) {
        this.name = name;
        count++;
        this.id = count;
    }

    public static void showCount() {
        System.out.println("  Members created so far: " + count);
    }

    public static void resetCount() {
        count = 0;
    }

    public abstract String getRole();

    public String getDetails() {
        return id + " | " + name + " | " + getRole();
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public String getReportLine() {
        return getDetails();
    }
}
