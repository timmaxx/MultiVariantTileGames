package timmax.tilegame.basemodel.credential;

//  Пользователь
public class User {
    private final String userName;
    private final String password;
    //  //  Доступные типы игр
    //  private final Set<GameType> availableGameTypeSet;
    //  MayBeToDo:  доступные матчи и номер игрока в ней.

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        //  this.availableGameTypeSet = new HashSet<>();
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
