import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.concurrent.ConcurrentSkipListMap;

public class Main {
    public static void main(String[] args) {
        new ClientThread("localhost", 8989);
        /*ConcurrentSkipListMap<String, String> map = new ConcurrentSkipListMap<>();
        map.put("name", "Loi");
        map.put("value", "9898");
        map.put("id", "4");
        try {
            FileOutputStream fos = new FileOutputStream("/home/katrin/IdeaProjects/lab_6_Client/binary.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(map);
            oos.flush();
            oos.close();
            fos.close();
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    } */
    }
}
