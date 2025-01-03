package timmax.tilegame.basemodel.dto;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class UserDtoIdPassword extends UserDtoId {
    private String password;

    public UserDtoIdPassword() {
        super();
    }

    public UserDtoIdPassword(String id, String password) {
        super(id);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void clearPassword() {
        password = "";
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeObject(password);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        password = (String) in.readObject();
    }

    @Override
    public String toString() {
        return "UserDtoIdPassword{" +
                "id='" + getId() + '\'' +
                ", password='" + "*" + '\'' +
                '}';
    }
}
