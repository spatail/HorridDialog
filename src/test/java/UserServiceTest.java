import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.fail;

/**
 * Created by spatail on 6/16/16.
 */
public class UserServiceTest {

    @Test
    public void shouldSetErrorInViewWhenEmailIsInvalid() {
        Contact contact = new Contact("first", "last", "email");
        boolean[] isError = {false};
        View view = error -> isError[0] = true;
        UserService userService = new UserService();
        userService.save(contact, view);

        assertThat("Error should be set in view", isError[0], is(true));
    }

    @Test
    public void shouldSaveContactWhenEmailIsValid() {
        Contact contact = new Contact("first", "last", "abc@xyz.com");
        View view = e -> System.out.println("");
        UserService userService = new UserService();

        try {
            userService.save(contact, view);
            List<String> lines = Files.readAllLines(Paths.get(UserService.fileName));
            assertThat("Should have saved contact", lines.size() == 1 && lines.get(0).equals(contact.toString()), is(true));
        } catch (Exception e) {
            fail("Could not save due to error: " + e);
        } finally {
            try {
                Files.delete(Paths.get(UserService.fileName));
            } catch (Exception e) {
                // swallow error
            }
        }
    }
}
