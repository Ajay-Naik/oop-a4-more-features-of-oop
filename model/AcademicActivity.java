package dcst.model;

public class AcademicActivity extends Activity {
    public AcademicActivity(String name, ActivityType type, int year) {
        super(name, type, year);
    }

    @Override
    public String getCategory() { return "Academic"; }
}
