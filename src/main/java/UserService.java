import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Created by spatail on 6/16/16.
 */
public class UserService {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^\\w*?@\\w*?\\.\\w{3}");

    public static String fileName = "userdata.csv";

    private boolean isEmailValid(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public void save(Contact contact, View view) {
        if (isEmailValid(contact.getEmail())) {
            saveContactToFile(contact);
        } else {
            view.setError("Invalid email: " + contact.getEmail());
        }
    }

    private void saveContactToFile(Contact contact) {
        try {
            BufferedWriter out = null;
            try {
                FileWriter fstream = new FileWriter(fileName, true);
                out = new BufferedWriter(fstream);
                out.write(contact.toString());
                out.write('\n');

                // Set the error text
                System.out.println("Successfully saved");
            } catch (IOException ioe) {
                System.err.println("Error: " + ioe.getMessage());
            } finally {
                if (out != null) {
                    out.close();
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
