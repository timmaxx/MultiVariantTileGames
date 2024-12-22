package timmax.common;

import java.io.*;

import static timmax.common.Asserts._assertNotNull;

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

    // Как по другому сделать приведение типа в строке
    // result = (T) objectInput.readObject();
    // чтобы не появлялось
    // Warning:(41, 22) Unchecked cast: 'java.lang.Object' to 'T'
    // я не разобрался.
    @SuppressWarnings("unchecked")
    public <T> T readValue(ByteArrayInputStream byteArrayInputStream) {
        _assertNotNull("byteArrayInputStream", byteArrayInputStream);

        T result = null;
        ObjectInput objectInput;
        try {
            objectInput = new ObjectInputStream(byteArrayInputStream);
/*
            //  См. комментарий выше перед
            //  @SuppressWarnings("unchecked")
            //  Такой вариант не подошёл:
            Object object = objectInput.readObject();
            if (Classes.isInstanceOf(object, result.getClass())) {
                result = (T) object;
            } else {
                throw new ClassNotFoundException();
            }
*/
            result = (T) objectInput.readObject();
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
}
