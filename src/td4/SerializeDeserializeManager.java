package td4;

import java.io.*;

public class SerializeDeserializeManager {
    public static void serialize(Serializable object, String fileName) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.close();
            fileOutputStream.close();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object deserialize(String fileName) {
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return objectInputStream.readObject();
        }catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
