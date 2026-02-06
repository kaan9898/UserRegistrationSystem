public class User {
    private final int id;
    private final String email;
    private final int age;

    public User(int id, String email, int age) {
        this.id = id;
        this.email = email;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User [ID=" + id + ", Email=" + email + ", Age=" + age + "]";
    }
}