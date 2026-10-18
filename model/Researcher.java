package dcst.model;

public interface Researcher {
    default void research() {
        System.out.println("  Researcher.research(): working on a research paper");
    }

    default String describe() {
        return "Researcher";
    }
}
