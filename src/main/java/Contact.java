/**
 * Created by spatail on 6/16/16.
 */
public class Contact {
    private final String first;
    private final String last;
    private final String email;

    public Contact(String first, String last, String email) {
        this.first = first;
        this.last = last;
        this.email = email;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s", first, last, email);
    }
}
