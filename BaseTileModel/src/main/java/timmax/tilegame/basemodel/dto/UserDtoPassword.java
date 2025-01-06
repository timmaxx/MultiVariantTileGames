package timmax.tilegame.basemodel.dto;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class UserDtoPassword extends BaseDtoId {
    private String password;

    public UserDtoPassword() {
        super();
    }

    public UserDtoPassword(String id, String password) {
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
