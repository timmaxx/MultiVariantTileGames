package timmax.tilegame.basemodel.credential;

//  Пользователь
public class User {
    private final String id;
    private final String password;

    //  ToDo:   Сделать поле перечень матчей, в которых этот пользователь является участником и под каким номером.

    public User(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", password='" + "*" + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!id.equals(user.id)) return false;
        return password.equals(user.password);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}
