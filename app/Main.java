package dcst.app;

import dcst.model.*;
import dcst.util.*;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        if (args.length > 0) {
            if (args[0].equals("report")) nirfReport();
            else run(Integer.parseInt(args[0]));
        } else {
            for (int i = 1; i <= 10; i++) run(i);
            nirfReport();
        }
    }

    private static void run(int item) {
        switch (item) {
            case 1: item1(); break;
            case 2: item2(); break;
            case 3: item3(); break;
            case 4: item4(); break;
            case 5: item5(); break;
            case 6: item6(); break;
            case 7: item7(); break;
            case 8: item8(); break;
            case 9: item9(); break;
            case 10: item10(); break;
            default: System.out.println("Pick a number from 1 to 10, or 'report'");
        }
    }

    private static void header(String title) {
        Member.resetCount();
        System.out.println("\n" + title + " -");
    }

    private static List<Member> sampleMembers() {
        List<Member> list = new ArrayList<>();
        list.add(new Student("Ajay", "2606", "MCA", 78.5));
        list.add(new Student("Riya", "2607", "MCA", 82.0));
        list.add(new TeachingStaff("Dr. Naik", "CST"));
        list.add(new NonTeachingStaff("Mr. Fernandes", "Lab Assistant"));
        list.add(new Professor("Dr. Rao", "CST"));
        return list;
    }

    private static List<Activity> sampleActivities() {
        List<Activity> list = new ArrayList<>();
        list.add(new AcademicActivity("ISA 1", ActivityType.ISA, 2025));
        list.add(new AcademicActivity("Midpoint Exam", ActivityType.MIDPOINT, 2025));
        list.add(new AcademicActivity("SEA Semester 1", ActivityType.SEA, 2025));
        list.add(new AcademicActivity("Industrial Study Tour", ActivityType.STUDY_TOUR, 2025));
        list.add(new AcademicActivity("Campus Placement", ActivityType.PLACEMENT, 2026));
        list.add(new NonAcademicActivity("InfoFest 2025", ActivityType.INFOFEST, 2025));
        list.add(new NonAcademicActivity("Plateaunia 2025", ActivityType.PLATEAUNIA, 2025));
        list.add(new NonAcademicActivity("Play-On 2026", ActivityType.PLAY_ON, 2026));
        return list;
    }

    private static void item1() {
        header("Item 1: Object Referencing & Constructors");
        Student a = new Student("Ajay", "2606", "MCA", 78.5);
        Student b = a;
        System.out.println("  Before: a = " + a.getDetails());
        b.setName("Changed via b");
        System.out.println("  After b.setName(...): a = " + a.getDetails() + "   <-- a changed too (side effect)");
        System.out.println("  a == b ? " + (a == b));

        Student c = new Student("Ajay", "2606", "MCA", 78.5);
        System.out.println("  c = " + c.getDetails() + "   <-- separate object, unaffected");
        System.out.println("  a == c ? " + (a == c));
    }

    private static void item2() {
        header("Item 2: Static Attributes, Methods & Enums");
        new Student("Ajay", "2606");
        new Student("Riya", "2607");
        new TeachingStaff("Dr. Naik", "CST");
        new NonTeachingStaff("Mr. Fernandes", "Lab Assistant");
        Member.showCount();
        System.out.println("  Enum ActivityType values:");
        for (ActivityType t : ActivityType.values()) {
            System.out.println("    " + t + " -> " + t.getLabel() + " ("
                    + (t.isAcademic() ? "Academic" : "Non-Academic") + ")");
        }
    }

    private static void item3() {
        header("Item 3: Multiple Inheritance");
        Professor p = new Professor("Dr. Rao", "CST");
        p.teach();
        p.research();
        System.out.println("  describe() = " + p.describe() + "   <-- class resolved the clash");
        System.out.println("  Multiple IMPLEMENTATION inheritance (extends A, B): not allowed in Java.");
        System.out.println("  Multiple INTERFACE inheritance (implements A, B): allowed.");
        System.out.println("  Difference: classes carry state + code, so two parents could clash (diamond problem);");
        System.out.println("  interfaces carry only contracts/default methods, and a clash must be resolved explicitly.");
    }

    private static void item4() {
        header("Item 4: Access Modifiers");
        AccessDemo.insideDemo();
        AccessDemo d = new AccessDemo();
        System.out.println("  Outside class (Main) -> public:    " + d.publicField);

        System.out.println("  Outside class (Main) -> private/protected: blocked by the compiler");
        new AccessChild().showFromChild();
    }

    private static void item5() {
        header("Item 5: Abstract Classes & Interfaces");

        System.out.println("  Abstract class Member, derived classes implement getRole() differently:");
        for (Member m : sampleMembers()) {
            System.out.println("    " + m.getRole() + " -> " + m.getDetails());
        }
        System.out.println("  Abstract class Activity, derived classes implement getCategory() differently:");
        for (Activity a : sampleActivities()) {
            System.out.println("    " + a.getCategory() + " -> " + a.getName());
        }
        System.out.println("  Interface Reportable.getReportLine():");
        Reportable r = new Student("Ajay", "2606");
        System.out.println("    " + r.getReportLine());
    }

    private static void generateReport(Reportable r) {
        System.out.println("  Report >> " + r.getReportLine());
    }

    private static void item6() {
        header("Item 6: Class as a Type & Message Passing");
        generateReport(new Student("Ajay", "2606"));
        generateReport(new Professor("Dr. Rao", "CST"));
        generateReport(new AcademicActivity("ISA 1", ActivityType.ISA, 2025));
        generateReport(new NonAcademicActivity("InfoFest 2025", ActivityType.INFOFEST, 2025));
    }

    private static void item7() {
        header("Item 7: Polymorphism");
        System.out.println("  Overriding (reference type Member, object decides which getDetails() runs):");
        Member[] ms = { new Student("Ajay", "2606"), new TeachingStaff("Dr. Naik", "CST"),
                        new NonTeachingStaff("Mr. Fernandes", "Lab Assistant") };
        for (Member m : ms) System.out.println("    " + m.getDetails());

        System.out.println("  Overloading (same method name, different parameters):");
        Student s = new Student("Riya", "2607");
        s.addCourse(new Course("CSA-5001", "Object Oriented Programming", 4));
        s.addCourse("CSA-5002", "Database Systems", 4);
        for (Course c : s.getCourses()) System.out.println("    " + c);
    }

    private static void item8() {
        header("Item 8: Generics and Collection Frameworks");
        Student s1 = new Student("Ajay", "2606");
        Student s2 = new Student("Riya", "2607");
        Student s3 = new Student("Sam", "2608");

        System.out.println("  Generic class Registry<Student>:");
        Registry<Student> registry = new Registry<>();
        registry.add(s1);
        registry.add(s2);
        registry.add(s3);
        System.out.println("    size = " + registry.size() + ", findById(" + s2.getId() + ") = " + registry.findById(s2.getId()).getName());

        System.out.println("  Generic methods printAll / firstOf:");
        Registry.printAll(registry.getAll());
        System.out.println("    firstOf = " + Registry.firstOf(registry.getAll()).getName());

        System.out.println("  List (ArrayList of members):");
        List<Member> list = sampleMembers();
        System.out.println("    " + list.size() + " members, first = " + list.get(0).getName());

        System.out.println("  Queue (placement interview order, FIFO):");
        Queue<Student> queue = new LinkedList<>();
        queue.offer(s1);
        queue.offer(s2);
        queue.offer(s3);
        while (!queue.isEmpty()) System.out.println("    interviewing: " + queue.poll().getName());

        System.out.println("  Set (unique course codes, duplicates rejected):");
        Set<String> codes = new HashSet<>();
        System.out.println("    add CSA-5001 -> " + codes.add("CSA-5001"));
        System.out.println("    add CSA-5002 -> " + codes.add("CSA-5002"));
        System.out.println("    add CSA-5001 again -> " + codes.add("CSA-5001") + " (duplicate)");

        System.out.println("  Map (activity name -> participants):");
        Map<String, List<Student>> participants = new HashMap<>();
        participants.computeIfAbsent("InfoFest", k -> new ArrayList<>()).add(s1);
        participants.computeIfAbsent("InfoFest", k -> new ArrayList<>()).add(s2);
        participants.computeIfAbsent("Play-On", k -> new ArrayList<>()).add(s3);
        for (Map.Entry<String, List<Student>> e : participants.entrySet()) {
            StringBuilder names = new StringBuilder();
            for (Student st : e.getValue()) names.append(st.getName()).append(" ");
            System.out.println("    " + e.getKey() + " -> " + names.toString().trim());
        }
        System.out.println("    lookup \"InfoFest\" has " + participants.get("InfoFest").size() + " participants");
    }

    private static void enroll(Student s, int credits) throws InvalidMemberException {
        if (credits <= 0) {
            throw new InvalidMemberException("Credits must be positive for " + s.getName());
        }
        System.out.println("    enrolled " + s.getName() + " for " + credits + " credits");
    }

    private static void item9() {
        header("Item 9: Exception Handling");

        System.out.println("  try / catch / finally with a custom exception (bad input):");
        try {
            Validator.checkRollNo("12AB");
            System.out.println("    roll number valid");
        } catch (InvalidMemberException e) {
            System.out.println("    caught custom exception: " + e.getMessage());
        } finally {
            System.out.println("    finally block runs");
        }

        System.out.println("  try / catch / finally (good input, no exception):");
        try {
            Validator.checkRollNo("2606");
            System.out.println("    roll number valid");
        } catch (InvalidMemberException e) {
            System.out.println("    caught custom exception: " + e.getMessage());
        } finally {
            System.out.println("    finally block runs (even with no exception)");
        }

        System.out.println("  Throwing from a method:");
        Student s = new Student("Ajay", "2606");
        try {
            enroll(s, 4);
            enroll(s, 0);
        } catch (InvalidMemberException e) {
            System.out.println("    caught: " + e.getMessage());
        }

        System.out.println("  Managing built-in runtime errors:");
        try {
            int x = 10 / Integer.parseInt("0");
            System.out.println(x);
        } catch (ArithmeticException e) {
            System.out.println("    ArithmeticException: " + e.getMessage());
        }
        try {
            Integer.parseInt("abc");
        } catch (NumberFormatException e) {
            System.out.println("    NumberFormatException: " + e.getMessage());
        }
        try {
            int[] arr = new int[2];
            arr[5] = 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("    ArrayIndexOutOfBoundsException: " + e.getMessage());
        }
        System.out.println("    program kept running after all errors");
    }

    private static void item10() {
        header("Item 10: Reflection, Persistence, I/O, Cloning");

        System.out.println("  Reflection on class Student:");
        Class<?> cls = Student.class;
        System.out.println("    class name: " + cls.getName());
        for (Field f : cls.getDeclaredFields()) {
            System.out.println("    field:  " + Modifier.toString(f.getModifiers()) + " "
                    + f.getType().getSimpleName() + " " + f.getName());
        }
        Method[] methods = cls.getDeclaredMethods();
        Arrays.sort(methods, Comparator.comparing(Method::getName));
        for (Method m : methods) {
            if (m.isSynthetic()) continue;
            StringBuilder sig = new StringBuilder(m.getName()).append("(");
            Class<?>[] ps = m.getParameterTypes();
            for (int i = 0; i < ps.length; i++) {
                if (i > 0) sig.append(", ");
                sig.append(ps[i].getSimpleName());
            }
            System.out.println("    method: " + sig.append(")"));
        }
        try {
            Student rs = new Student("Ajay", "2606");
            Method m = cls.getMethod("getRollNo");
            System.out.println("    invoked getRollNo() through reflection -> " + m.invoke(rs));
        } catch (Exception e) {
            System.out.println("    reflection error: " + e);
        }

        System.out.println("  RTTI (runtime type identification):");
        for (Member m : sampleMembers()) {
            String kind;
            if (m instanceof Professor) kind = "a Professor (also TeachingStaff)";
            else if (m instanceof TeachingStaff) kind = "a TeachingStaff";
            else if (m instanceof Student) kind = "a Student";
            else if (m instanceof NonTeachingStaff) kind = "a NonTeachingStaff";
            else kind = "something else";
            System.out.println("    " + m.getName() + " -> " + m.getClass().getSimpleName() + ", " + kind);
        }

        System.out.println("  Console I/O:");
        Scanner sc = new Scanner(System.in);
        System.out.print("    Enter a student name: ");
        String typed = sc.hasNextLine() ? sc.nextLine().trim() : "";
        if (typed.isEmpty()) typed = "Console Student";
        System.out.println("    You entered: " + typed);
        Student consoleStudent = new Student(typed, "2606");

        System.out.println("  File I/O (text file report.txt):");
        try (PrintWriter out = new PrintWriter(new FileWriter("report.txt"))) {
            out.println(consoleStudent.getDetails());
            out.println("Written by the NIRF demo");
        } catch (IOException e) {
            System.out.println("    write error: " + e.getMessage());
        }
        try (BufferedReader in = new BufferedReader(new FileReader("report.txt"))) {
            String line;
            while ((line = in.readLine()) != null) System.out.println("    read back: " + line);
        } catch (IOException e) {
            System.out.println("    read error: " + e.getMessage());
        }

        System.out.println("  Persistence (serialization to members.dat):");
        List<Member> saved = sampleMembers();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("members.dat"))) {
            oos.writeObject(saved);
            System.out.println("    saved " + saved.size() + " members");
        } catch (IOException e) {
            System.out.println("    save error: " + e.getMessage());
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("members.dat"))) {
            @SuppressWarnings("unchecked")
            List<Member> loaded = (List<Member>) ois.readObject();
            for (Member m : loaded) System.out.println("    loaded: " + m.getDetails());
            Student first = (Student) loaded.get(0);
            System.out.println("    transient field sessionNote after loading = " + first.getSessionNote() + " (was not saved)");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("    load error: " + e.getMessage());
        }

        System.out.println("  Shallow vs deep cloning:");
        Student orig = new Student("Ajay", "2606");
        orig.addCourse("CSA-5001", "Object Oriented Programming", 4);
        Student shallow = orig.clone();
        Student deep = orig.deepClone();
        orig.getCourses().get(0).setTitle("CHANGED TITLE");
        orig.addCourse("CSA-5002", "Database Systems", 4);
        System.out.println("    original courses: " + orig.getCourses());
        System.out.println("    shallow  courses: " + shallow.getCourses() + "   <-- affected (shared list)");
        System.out.println("    deep     courses: " + deep.getCourses() + "   <-- unaffected (own copy)");
    }

    private static void nirfReport() {
        header("NIRF Case Study: Full Report");
        NirfReport r = new NirfReport("CST", 2023, 2026);
        for (Member m : sampleMembers()) r.addMember(m);
        for (Activity a : sampleActivities()) r.addActivity(a);
        r.addCourse(new Course("CSA-5001", "Object Oriented Programming", 4));
        r.addCourse(new Course("CSA-5002", "Database Systems", 4));
        r.addCourse(new Course("CSA-5003", "Data Structures", 4));
        r.print();
    }
}
