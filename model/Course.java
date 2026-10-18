package dcst.model;

import java.io.Serializable;

public class Course implements Serializable {
    private final String code;
    private String title;
    private final int credits;

    public Course(String code, String title, int credits) {
        this.code = code;
        this.title = title;
        this.credits = credits;
    }

    public String getCode() { return code; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public int getCredits() { return credits; }

    public Course copy() {
        return new Course(code, title, credits);
    }

    @Override
    public String toString() {
        return code + ": " + title + " (" + credits + " credits)";
    }
}
