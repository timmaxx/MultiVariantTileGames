package timmax.tilegame.basemodel.credential;

//  Пользователь
public class User {
//  ToDo:   Создать класс Players, в котором будут храниться упорядоченные игроки (с индексами 0 и 1)
//          и который будет использоваться в классе GameMatch.

    private final String userName;
    private final String password;

    //  ToDo:   Сделать поле перечень матчей, в которых этот пользователь является участником и под каким номером.
    //  MayBeToDo:  доступные матчи и номер игрока в ней.

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + "*" + '\'' +
                //  ", availableGameTypeSet=" + availableGameTypeSet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!userName.equals(user.userName)) return false;
        return password.equals(user.password);
    }

    @Override
    public int hashCode() {
        int result = userName.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}
