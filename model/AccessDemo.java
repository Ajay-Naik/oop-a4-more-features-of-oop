package dcst.model;

public class AccessDemo {
    public String publicField = "I am public";
    private String privateField = "I am private";
    protected String protectedField = "I am protected";

    public static void insideDemo() {
        AccessDemo d = new AccessDemo();
        System.out.println("  Inside class -> public:    " + d.publicField);
        System.out.println("  Inside class -> private:   " + d.privateField);
        System.out.println("  Inside class -> protected: " + d.protectedField);
    }
}
