
import java.util.Scanner; 
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Login {
    private String username;
    private String password;
    private String cellPhoneNumber;
    private boolean isLoggedIn = false;

    public Login() {
        // Default constructor
    }

    public Login(String username, String password, String cellPhoneNumber) {
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public boolean checkUserName() {
        return username != null && username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity() {
        if (password == null || password.length() < 8) {
            return false;
        }
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;
        String specialCharacters = "!@#$%^&*()_+=-`~[]\\{}|;':\",./<>?";

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasCapital = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            } else if (specialCharacters.contains(String.valueOf(c))) {
                hasSpecial = true;
            }
        }
        return hasCapital && hasNumber && hasSpecial;
    }

    public boolean checkCellPhoneNumber() {
        if (cellPhoneNumber == null) {
            return false;
        }
        // Regular expression to check for international country code (+ followed by digits)
        // followed by at least one more digit. Adjust the [0-9]{7,} part if needed
        // for specific South African number lengths after the country code.
        Pattern pattern = Pattern.compile("^\\+\\d{1,}[0-9]{7,}$");
        Matcher matcher = pattern.matcher(cellPhoneNumber);
        return matcher.matches() && cellPhoneNumber.length() > 10;
    }

    public String registerUser(String newUsername, String newPassword, String newCellPhoneNumber) {
        this.username = newUsername;
        this.password = newPassword;
        this.cellPhoneNumber = newCellPhoneNumber;

        if (!checkUserName()) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber()) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        return "User registered successfully.\nUsername successfully captured.\nPassword successfully captured.\nCell phone number successfully added.";
    }

    public boolean loginUser(String enteredUsername, String enteredPassword) {
        if (this.username != null && this.password != null && this.username.equals(enteredUsername) && this.password.equals(enteredPassword)) {
            this.isLoggedIn = true;
            return true;
        }
        return false;
    }

    public String returnLoginStatus(String firstName, String lastName) {
        if (isLoggedIn) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    boolean checkPasswordComplexity(String chsecke99) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    boolean checkCellPhoneNumber(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

public class AccountApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login userAccount = null;
        String firstName = "";
        String lastName = "";

        // Registration
        System.out.println("--- Account Registration ---");
        System.out.print("Enter username (e.g., user_1): ");
        String username = scanner.nextLine();
        System.out.print("Enter password (at least 8 chars, 1 capital, 1 number, 1 special): ");
        String password = scanner.nextLine();
        System.out.print("Enter South African cell phone number (e.g., +27831234567): ");
        String cellPhoneNumber = scanner.nextLine();
        System.out.print("Enter your first name: ");
        firstName = scanner.nextLine();
        System.out.print("Enter your last name: ");
        lastName = scanner.nextLine();

        userAccount = new Login();
        String registrationResult = userAccount.registerUser(username, password, cellPhoneNumber);
        System.out.println("\n--- Registration Result ---");
        System.out.println(registrationResult);

        // Login
        if (userAccount.getUsername() != null) {
            System.out.println("\n--- Account Login ---");
            System.out.print("Enter your username: ");
            String loginUsername = scanner.nextLine();
            System.out.print("Enter your password: ");
            String loginPassword = scanner.nextLine();

            if (userAccount.loginUser(loginUsername, loginPassword)) {
                System.out.println("\n--- Login Status ---");
                System.out.println(userAccount.returnLoginStatus(firstName, lastName));
            } else {
                System.out.println("\n--- Login Status ---");
                System.out.println(userAccount.returnLoginStatus("", "")); // First and last name not relevant for failed login message
            }
        }

        scanner.close();
    }
}
// Unit Tests 
class LoginTest {
    public static void main(String[] args) {
        LoginTest tests = new LoginTest();
        tests.testUsernameCorrectlyFormatted();
        tests.testUsernameIncorrectlyFormatted();
        tests.testPasswordMeetsComplexityRequirements();
        tests.testPasswordDoesNotMeetComplexityRequirements();
        tests.testCellPhoneCorrectlyFormatted();
        tests.testCellPhoneNumberIncorrectlyFormatted();
        tests.testLoginSuccessful();
        tests.testLoginFailed();
        tests.testCheckUsernameTrue();
        tests.testCheckUsernameFalse();
        tests.testCheckPasswordComplexityTrue();
        tests.testCheckPasswordComplexityFalse();
        tests.testCheckCellPhoneNumberTrue();
        tests.testCheckCellPhoneNumberFalse();
    }

    public void assertEquals(String expected, String actual) {
        if (expected.equals(actual)) {
            System.out.println("Test Passed: Expected '" + expected + "', Got '" + actual + "'");
        } else {
            System.err.println("Test Failed: Expected '" + expected + "', Got '" + actual + "'");
        }
    }

    public void assertTrue(boolean condition) {
        if (condition) {
            System.out.println("Test Passed: Expected true, Got true");
        } else {
            System.err.println("Test Failed: Expected true, Got false");
        }
    }

    public void assertFalse(boolean condition) {
        if (!condition) {
            System.out.println("Test Passed: Expected false, Got false");
        } else {
            System.err.println("Test Failed: Expected false, Got true");
        }
    }

    public void testUsernameCorrectlyFormatted() {
        Login login = new Login();
        login.registerUser("kyl_1", "P@sswOrd123", "+27838968976");
        assertEquals("Welcome <user first name>, <user last name> it is great to see you again.", login.returnLoginStatus("user", "name"));
    }

    public void testUsernameIncorrectlyFormatted() {
        Login login = new Login();
        assertEquals("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.", login.registerUser("kyle!!!!!!", "P@sswOrd123", "+27838968976"));
    }

    public void testPasswordMeetsComplexityRequirements() {
        Login login = new Login();
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    public void testPasswordDoesNotMeetComplexityRequirements() {
        Login login = new Login();
        assertFalse(login.checkPasswordComplexity("password"));
    }

    public void testCellPhoneCorrectlyFormatted() {
        Login login = new Login();
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    public void testCellPhoneNumberIncorrectlyFormatted() {
        Login login = new Login();
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    public void testLoginSuccessful() {
        Login login = new Login("test_1", "SecureP@ss1", "+27821112222");
        assertTrue(login.loginUser("test_1", "SecureP@ss1"));
    }

    public void testLoginFailed() {
        Login login = new Login("test_1", "SecureP@ss1", "+27821112222");
        assertFalse(login.loginUser("wrong_user", "wrong_password"));
    }

    public void testCheckUsernameTrue() {
        Login login = new Login("abc_1", "", "");
        assertTrue(login.checkUserName());
    }

    public void testCheckUsernameFalse() {
        Login login = new Login("abcdef", "", "");
        assertFalse(login.checkUserName());
    }

    public void testCheckPasswordComplexityTrue() {
        Login login = new Login("", "P@ss123!", "");
        assertTrue(login.checkPasswordComplexity());
    }

    public void testCheckPasswordComplexityFalse() {
        Login login = new Login("", "weak", "");
        assertFalse(login.checkPasswordComplexity());
    }

    public void testCheckCellPhoneNumberTrue() {
        Login login = new Login("", "", "+27765432109");
        assertTrue(login.checkCellPhoneNumber());
    }

    public void testCheckCellPhoneNumberFalse() {
        Login login = new Login("", "", "0765432109");
        assertFalse(login.checkCellPhoneNumber());
    }
}
//Reference List//

//OpenAI. 2025. Chat-GPT (Version 3.5). [Large language model]. Available at: https://openai.com/index/chatgpt/ [Accessed: 14 April 2025]. 
//Gemimi. 2025. Gemini (Version 2.0). [Large language model]. Available at: https://gemini.google.com/ [Accessed: 14 April 2025].

    
        
