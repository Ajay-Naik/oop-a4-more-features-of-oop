package dcst.model;

public class NonAcademicActivity extends Activity {
    public NonAcademicActivity(String name, ActivityType type, int year) {
        super(name, type, year);
    }

    @Override
    public String getCategory() { return "Non-Academic"; }
}
