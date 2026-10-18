package dcst.util;

public class Validator {

    public static void checkRollNo(String rollNo) throws InvalidMemberException {
        if (rollNo == null || !rollNo.matches("\\d{4}")) {
            throw new InvalidMemberException("Invalid roll number: " + rollNo);
        }
    }
}
