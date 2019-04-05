package ServerPart;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ConcurrentSkipListMap;


public class ServerThread extends Thread {
/*  private Connection connection;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private static final String HOST = "localhost";
    private int host;
    private Socket clientSocket;
    private int clients_count = 0;

    ServerThread(Socket socket,  Connection server, int host){
        clients_count++;
        this.connection = server;
        try {
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());
            try {
                ConcurrentSkipListMap<String, String> map = (ConcurrentSkipListMap) in.readObject();
                for(String keys: map.keySet()){
                    System.out.println(map.get(keys));
                    System.out.println(map.get(keys).getClass());
                }
            } catch (ClassNotFoundException exc){
                exc.printStackTrace();
            }
        } catch (IOException exc){
            exc.printStackTrace();
        }
    }


    @Override
    public void run() {

    }

    /* public static void main(String[] args) {
        try{
            ServerSocket socketServ = new ServerSocket(8189);
            System.out.println("Waiting for a client...");

            Socket socket = socketServ.accept();
            System.out.println();

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            String line;
            while (true){
                line = in.readUTF();
                System.out.println("Client told: " + line);
                System.out.println("Send it back");

                System.out.println(line);

                out.writeUTF(line);
                out.flush();

                System.out.println("Waiting for the next sms...");
                System.out.println();
            }
        } catch (IOException exc){
            exc.printStackTrace();
        }
    } */
}
