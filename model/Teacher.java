package dcst.model;

public interface Teacher {
    default void teach() {
        System.out.println("  Teacher.teach(): delivering a lecture");
    }

    default String describe() {
        return "Teacher";
    }
}
