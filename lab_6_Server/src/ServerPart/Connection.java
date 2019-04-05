package ServerPart;

import collectionPart.CommandManager;
import collectionPart.ThingManager;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Соеденение с сервером
 */
public class Connection{
    private int host;
    private ConcurrentSkipListMap<String, String> map;
    ObjectInputStream inObj = null;
    ObjectOutputStream outObj = null;
    DataInputStream in = null;
    DataOutputStream out = null;

    ServerSocket server = null;
    Socket fromclient = null;


    /**
     * Конструктор с инициализацией сокета
     * @param host - хост соединения
     */
    public Connection(int host) {
        this.host = host;
        try {
            server = new ServerSocket(this.host) ;
        } catch (IOException e) {
            System.out.println("Couldn't listen to port 8989");
            System.exit(-1);
        }
        try {
            System.out.print("Waiting for a client...");
            fromclient = server.accept();
            System.out.println("Client connected");
        } catch (IOException e) {
            System.out.println("Can't accept");
            System.exit(-1);
        }
        connecting();
    }

    private void connecting(){

        //инициализирует стримы объектов

        System.out.println();
        CommandManager commandManager = new CommandManager(new ThingManager(new File("CollectionDefault.json"), fromclient));
        commandManager.direct();
        //принимает комманду с клиента
        //ConcurrentSkipListMap<String, String> map = new ConcurrentSkipListMap<>();
        //System.out.println("Ждет объект");
        /*try {
            map = (ConcurrentSkipListMap) inObj.readObject();
            System.out.println("Получен");

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (String keys : map.keySet()) {
            System.out.println(map.get(keys));
        }*/

       /* try {
            out.close();
            in.close();
            fromclient.close();
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException exc) {
            System.out.println("какой-то нуль-поинтер");
        } */
    }

    private void close(){
        try {
            out.close();
            in.close();
            fromclient.close();
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /*Socket socket;
    BufferedReader in;
    PrintStream out;
    ServerPart.ServerThread pars;

    public ServerPart.Connection(Socket socket){
        this.socket = socket;
        this.pars = new ServerPart.ServerThread(socket);
        try{
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintStream(socket.getOutputStream());
        } catch (IOException exc){
            try{
                socket.close();
            } catch (IOException exc1){
                System.out.println("Ошибка при запуске сервера: " + exc);
                return;
            }
        } finally {
            System.out.println("Вы вышли из сервера");
        }

        pars.start();
    } */
/* Socket clientSocket = null;
        ServerSocket serverSocket = null;
        this.host = host;

        try{
            serverSocket = new ServerSocket(host);
            System.out.println("Сервер запущен");
            System.out.println("Ожидается подключение...");

            while (true){
                clientSocket = serverSocket.accept();
                ServerPart.ServerThread handler = new ServerPart.ServerThread(clientSocket, this, host);
                new Thread(handler).start();
            }
        } catch (IOException exc){
            exc.printStackTrace();
        } finally {
            try {
                clientSocket.close();
                System.out.println("Сервер остановлен");
                serverSocket.close();
            } catch (IOException exc){
                exc.printStackTrace();
            }
        } */
}



