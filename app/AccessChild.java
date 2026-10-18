package dcst.app;

import dcst.model.AccessDemo;

public class AccessChild extends AccessDemo {
    public void showFromChild() {
        System.out.println("  Subclass (other package) -> public:    " + publicField);
        System.out.println("  Subclass (other package) -> protected: " + protectedField);

    }
}
