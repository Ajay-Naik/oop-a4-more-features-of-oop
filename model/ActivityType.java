package dcst.model;

public enum ActivityType {
    ISA("In-Semester Assessment", true),
    MIDPOINT("Midpoint Assessment", true),
    SEA("Semester End Assessment", true),
    STUDY_TOUR("Study Tour", true),
    PLACEMENT("Placement Drive", true),
    INFOFEST("InfoFest", false),
    PLATEAUNIA("Plateaunia", false),
    PLAY_ON("Play-On", false);

    private final String label;
    private final boolean academic;

    ActivityType(String label, boolean academic) {
        this.label = label;
        this.academic = academic;
    }

    public String getLabel() { return label; }
    public boolean isAcademic() { return academic; }
}
