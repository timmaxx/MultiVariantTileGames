package timmax.tilegame.basemodel.protocol;

import java.io.*;

// Класс похож на ObjectMapper из Jackson
public class ObjectMapperOfMvtg {
    /*
    package com.fasterxml.jackson.databind;

    public class ObjectMapper extends ObjectCodec implements Versioned, Serializable {
    }
    */

    //  В ObjectMapper:
    //  public void writeValue(Writer w, Object value)

    // public void writeValue(OutputStream w, Externalizable externalizable)
    public void writeValue(ByteArrayOutputStream byteArrayOutputStream, Externalizable externalizable) {
        _assertNotNull("byteArrayOutputStream", byteArrayOutputStream);
        try {
            ObjectOutput objectOutput = new ObjectOutputStream(byteArrayOutputStream);
            objectOutput.writeObject(externalizable);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            // throw new RuntimeException(ioe);
            System.exit(1);
        }
    }

    //  В ObjectMapper:
    //  public <T> T readValue(String content, Class<T> valueType) throws JsonProcessingException, JsonMappingException

    public <T> T readValue(ByteArrayInputStream byteArrayInputStream, Class<T> valueType) {
        _assertNotNull("byteArrayInputStream", byteArrayInputStream);

        T result = null;
        ObjectInput objectInput;
        try {
            objectInput = new ObjectInputStream(byteArrayInputStream);
            result = (T)objectInput.readObject();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            // throw new RuntimeException(ioe);
            System.exit(1);
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            // throw new RuntimeException(e);
            System.exit(1);
        }

        return result;
    }

    // ToDo: этот метод следует вне этого класса разместить.
    protected static void _assertNotNull(String paramName, Object src) {
        if (src == null) {
            throw new IllegalArgumentException(String.format("argument \"%s\" is null", paramName));
        }
    }
}
