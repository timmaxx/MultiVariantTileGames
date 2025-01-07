package timmax.tilegame.basemodel.credential;

//  Пользователь
public class User extends BaseEntity {
    private final String password;

    //  ToDo:   Сделать поле перечень матчей, в которых этот пользователь является участником и под каким номером.

    public User(String id, String password) {
        super(id);
        this.password = password;
    }

    @Override
    public String toString() {
        return
                User.class.getSimpleName()
                        // getClass().getSimpleName()
                        + "{" +
                        (super.toString().equals(getClass().getName() + "@" + Integer.toHexString(hashCode()))
                                ? ""
                                : ("{" + super.toString() + "}, ")
                        ) +
                        "password='" + "*" + '\'' +
                        '}';
    }
}
