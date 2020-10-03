package base.domain;

public class Customer {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;

    // Constructor, getters and setters...

    @Override
    public String toString() {
        return "Customer["
                + "id=" + id
                + ", firstName=" + firstName
                + ", lastName=" + lastName
                + ", email=" + email
                + ']';
    }
}
