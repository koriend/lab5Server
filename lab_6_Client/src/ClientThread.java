import java.io.*;
import java.net.*;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ConcurrentSkipListMap;

public class ClientThread  extends Thread{
    private int port;
    private String host;
    SocketChannel socketChannel;
    private ConcurrentSkipListMap<String, String> mapArgs;
    private Selector selector;
    DataInputStream in = null;
    DataOutputStream out = null;
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;
    private Socket socket;
    //ClientThread(){}

    ClientThread( String host, int port){
        this.host = host;
        this.port = port;
        //new Thread(this).start();
        connecting();
    }


    private void connecting(){
        try{
            InetAddress ipAdress = InetAddress.getByName(this.host);
            System.out.println("Подключается к серверу...");
            //Socket socket;
            try {
                this.socket = new Socket(ipAdress, port);
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
                out.writeUTF("Клиент-1 подключен");
            } catch (ConnectException | UnknownHostException exc){
                System.out.println("Ошибка при подключении к серверу");
                System.exit(0);
            }
           // String line;

            //System.out.println(": ");
            boolean flag = true;
            while (flag){
                ConcurrentSkipListMap<String, String> map;
                ConcurrentSkipListMap<String, String> temp = new ConcurrentSkipListMap<>();

                map = ReaderCommand.readingCommand();
                //ConcurrentSkipListMap<String, String> map = ReaderCommand.readingCommand();
                System.out.println("Отправляем комманду серверу...");
                /**
                 * РАБОТАЮЩАЯ СЕРИАЛИЗАЦИЯ
                 */
                try{
                    oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(map);
                    oos.flush();
                    oos.close();
                    System.out.println("Объект отправлен");
                } catch (IOException exc){
                    exc.printStackTrace();
                }

                /*f(map.get("command").equals("send")) {
                    temp.put("name", "Loi");
                    temp.put("value", "9898");
                    temp.put("id", "4");
                    ConcMap lop = new ConcMap();
                    lop.setMap(temp);
                    try {
                        //out.writeUTF(map.get("command"));
                        //FileOutputStream fos = new FileOutputStream("/home/katrin/IdeaProjects/lab_6_Client/binary.txt");
                        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                        oos.writeObject(temp);
                        oos.flush();
                        oos.close();
                        //fos.close();
                        System.out.println("Объект отправлен");
                    } catch (IOException exc) {
                        exc.printStackTrace();
                    }
                } */
                if(map.get("command").equals("exit")){
                    flag = false;
                }
                String msg = in.readUTF();
                System.out.println(msg);
                /*out.writeUTF(command[1]);
                out.fluString[] comman9sh();
                command[1] = in.readUTF();
                System.out.println("Server said: ");
                System.out.println(in.readUTF()); */
            }
        } catch (IOException exc){
            exc.printStackTrace();
        }
    }
    ///////////////////для многопоточности////////////////////////
    /*@Override
    public void run() {
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            socketChannel.connect(new InetSocketAddress(this.host, this.port));

            mapArgs = ReaderCommand.readingCommand();


        } catch (IOException e){
            e.getMessage();
        }
    } */
        // End of packet terminator strings, line startsWith "aabbcc" string.


    /*DataInputStream dis = null;
    Socket s = null;

    public ClientThread(){
        try{
            Socket s = new Socket("localhost", 13);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            boolean more = true;
            while(more) {
                String line = in.readLine();
                if (line == null) more = false;
                else System.out.println(line);
            }
        } catch (IOException exc){
            exc.printStackTrace();
        }
    } */
    ///////////////////////////////////////////старый тест на сериализацию///////////////////////////////////////////
 /*   public static void main(String[] args) {
        int port = 8989;
        String adress = "localhost";



    } */
}
