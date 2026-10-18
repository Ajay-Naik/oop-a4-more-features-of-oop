package dcst.model;

import java.io.Serializable;

public abstract class Activity implements Reportable, Serializable {
    private final String name;
    private final ActivityType type;
    private final int year;

    public Activity(String name, ActivityType type, int year) {
        this.name = name;
        this.type = type;
        this.year = year;
    }

    public abstract String getCategory();

    public String getName() { return name; }
    public ActivityType getType() { return type; }
    public int getYear() { return year; }

    @Override
    public String getReportLine() {
        return getCategory() + " | " + type.getLabel() + " | " + name + " | " + year;
    }
}
