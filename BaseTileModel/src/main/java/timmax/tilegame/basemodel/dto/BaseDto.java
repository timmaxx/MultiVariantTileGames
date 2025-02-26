package timmax.tilegame.basemodel.dto;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class BaseDto implements Externalizable {
    private String id;

    public BaseDto() {
    }

    public BaseDto(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public boolean isIdNullOrEmpty() {
        return id == null || id.isEmpty();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(id);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        id = (String) in.readObject();
    }

    @Override
    public String toString() {
        return
                BaseDto.class.getSimpleName()
                        // getClass().getSimpleName()
                        + "{" +
                        (super.toString().equals(getClass().getName() + "@" + Integer.toHexString(hashCode()))
                                ? ""
                                : ("{" + super.toString() + "}, ")
                        ) +
                        "id='" + id + '\'' +
                        '}';
    }
}
