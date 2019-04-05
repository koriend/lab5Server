package collectionPart;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.Contract;
import texting.*;

import java.io.*;
import java.lang.reflect.Type;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class ThingManager {
    private ConcurrentSkipListMap<String, Thing> thingConcurrentSkipListMap;
    private Date date;
    private File fileEntry;
    private boolean emptFlag = true;
    private TreeMap<String, Thing> tempMap = new TreeMap<>();
    private ConcurrentSkipListMap<String, Thing> tempConcMap = new ConcurrentSkipListMap<>();
    //----------!!!--------//
    private DataInputStream in = null;
    private DataOutputStream out = null;
    Socket socket = null;



    ThingManager() {
        this.date = new Date();
        this.thingConcurrentSkipListMap = new ConcurrentSkipListMap<>();
        this.fileEntry = null;

    }

    /**
     * конструктор для инициализации коллекции
     * @param fileCollection входящая коллекция
     * @param socket используемый сокет
     */
    public ThingManager(File fileCollection, Socket socket) {
        //System.out.println("\u001b[0mВнимание,  вводите объект в формате:\n{\"name\":, \"value\":, \"planet\":{\"nameOfPlanet\":,  \"diametr\":, \"type\":, \"PlanetId\": 2},  \"ThingId\":  }\u001b[0m");
        //System.out.println();
        this.thingConcurrentSkipListMap = this.importMap(fileCollection);
        this.date = new Date();
        this.fileEntry = fileCollection;
        //----------!!!--------//
        try{
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * вывод информации о коллекции
     *
     * непотокобезопасный метод size
     */
    void info() {
        try {
            out.writeUTF("\u001b[0;33mТип элементов в коллекции: Machine, Planet, Relief, Star");
            out.writeUTF("Последняя инициализация: " + this.date);
            if (!isEmptFlag()) {
                out.writeUTF("Размер: " + this.thingConcurrentSkipListMap.size() + "\u001b[0m");
            } else {
                out.writeUTF("Размер = 0");
            }

            out.writeUTF("\n");
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    /*private <T extends Thing> TreeMap<String, ? extends Thing> choose(T element, Class <T> tClass){
        clearTempMap();
        thingConcurrentSkipListMap.keySet().stream().filter(key -> thingConcurrentSkipListMap.get(key).getId() == element.getId()).forEach(key -> tempMap.put(key, thingConcurrentSkipListMap.get(key)));
        thingConcurrentSkipListMap.values().stream().filter(value -> value.getClass() == tClass).min(Comparator.comparing(Thing::getValueThing)).get();
        return tempMap;

    } */

    void add_if_min(Thing thingElement) {
        try {
            if (!isEmptFlag()) {
                int minThing = thingConcurrentSkipListMap.values().stream().filter(value -> value.getId() == thingElement.getId()).min(Comparator.comparing(Thing::getValueThing)).get().getValueThing();
                if (minThing > thingElement.getValueThing()) {

                    this.thingConcurrentSkipListMap.put(thingElement.getNamePlan(), thingElement);
                    out.writeUTF("\u001b[33mЭлемент " + thingElement.getNamePlan() + " добавлен" + "\u001b[0m");
                } else {
                    out.writeUTF("\u001b[31mЭтот элемент больше текущего минимального\u001b[0m");
                }
            } else {
                this.thingConcurrentSkipListMap.put(thingElement.getNamePlan(), thingElement);
                out.writeUTF("\u001b[33mЭлемент " + thingElement.getNamePlan() + " добавлен" + "\u001b[0m");
            }
        } catch (IOException e){
            e.printStackTrace();
        }

    }


     void insert(String key, Thing thingElement) {
        try {
            //System.out.println(thingElement.getNamePlan());
            if (isEmptFlag()) {
                this.thingConcurrentSkipListMap = new ConcurrentSkipListMap<>();
                this.thingConcurrentSkipListMap.put(key, thingElement);
                out.writeUTF("\u001b[33mЭлемент " + key + " добавлен" + "\u001b[0m");
                setEmptFlag(false);
                this.show();
            } else if (this.thingConcurrentSkipListMap.containsKey(thingElement.getNamePlan())) {
                out.writeUTF("\u001b[31mВнимание: элемент будет переписан\u001b[0m");
                out.writeUTF("\u001b[31mВы согласны? [Y/N]\u001b[0m");
                String agree = in.readUTF();

                try {
                    if (agree.equals("N")) {
                        out.writeUTF("\u001b[31mЭлемент не переписан\u001b[0m");
                    } else {
                        if (!agree.equals("Y")) {
                            throw new ArgumentFormatException("\u001b[31mНеккоректный ответ\u001b[0m");
                        }

                        out.writeUTF("\u001b[33mЭлемент " + key + " переписан" + "\u001b[0m");
                        this.show();
                    }
                } catch (ArgumentFormatException var6) {
                    out.writeUTF(var6.getExc());
                }
            } else if (this.thingConcurrentSkipListMap.containsValue(thingElement)) {
                out.writeUTF("\u001b[31mЭтот элемент уже существует\u001b[0m");
            } else {
                this.thingConcurrentSkipListMap.put(key, thingElement);
                out.writeUTF("\u001b[33mЭлемент " + key + " добавлен" + "\u001b[0m");
                out.writeUTF("\u001b[33mТекущее состояние коллекции: \u001b[0m");
                this.show();
            }
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    void remove(String name) {
        try {
            try {
                if (isEmptFlag()) {
                    throw new ArgumentFormatException("File is empty");
                }

                if (this.thingConcurrentSkipListMap.remove(name) == null) {
                    out.writeUTF("\u001b[31mКлюч не найден\u001b[0m");
                } else {
                    this.thingConcurrentSkipListMap.remove(name);
                    out.writeUTF("\u001b[33mЭлемент " + name + " удален" + "\u001b[0m");
                    out.writeUTF("\u001b[33mТеккущее состояние коллекции: \u001b[0m");
                    this.show();
                }
            } catch (ArgumentFormatException var3) {
                out.writeUTF(var3.toString());
            }
        } catch (IOException e){
            e.printStackTrace();
        }

    }


    void remove_greater_key(String name) {
        try {
            try {
                if (isEmptFlag()) {
                    throw new ArgumentFormatException("File is empty");
                }

                tempMap = new TreeMap<>();
                thingConcurrentSkipListMap.keySet().stream().filter(key -> key.compareToIgnoreCase(name) < 0).forEach(key -> tempConcMap.put(key, thingConcurrentSkipListMap.get(key)));

                thingConcurrentSkipListMap = new ConcurrentSkipListMap<>(tempConcMap);
                out.writeUTF("\u001b[33mТеккущее состояние коллекции: \u001b[0m");
                this.show();
            } catch (ArgumentFormatException var3) {
                out.writeUTF(var3.toString());
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    void show() {
        try {
            try {
                if (isEmptFlag()) {
                    throw new ArgumentFormatException("Файл пуст");
                } else {
                /*for(String keys: thingConcurrentSkipListMap.keySet()){
                    System.out.println(thingConcurrentSkipListMap.get(keys));
                    System.out.println(thingConcurrentSkipListMap.get(keys).getClass());
                } */
                    thingConcurrentSkipListMap.entrySet().stream().forEach(out::writeUTF);
                }
            } catch (ArgumentFormatException var3) {
                out.writeUTF(var3.toString());
            }
            out.writeUTF("\n");
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    ConcurrentSkipListMap<String, Thing> importMap(File file) {
        try {
            if (!file.isFile()) {
                setEmptFlag(true);
                System.exit(0);
                throw new FileNotFoundException("Input incorrect path");
            }

            if (file.length() == 0L) {
                setEmptFlag(true);
                throw new FileNotFoundException("File is empty");
            } else {
                setEmptFlag(false);
            }

            if (!file.canRead()) {
                setEmptFlag(true);
                System.exit(0);
                throw new SecurityException("You need a root");
            }

            String jsonToRead = Thing.pullStingFromJson(file);
            //System.out.println(jsonToRead);
            //System.out.println(jsonToRead);
            if (!this.mapFromJson(jsonToRead).isEmpty()) {
                try {
                    this.tempMap = this.mapFromJson(jsonToRead);
                    thingConcurrentSkipListMap = new ConcurrentSkipListMap<>(tempMap);
                } catch (NullPointerException var4) {
                    System.out.println("\u001b[31mError: check your file\u001b[0m");
                    System.exit(0);
                }

                System.out.println("\u001b[32mCollection was imported\u001b[0m");
            } else {
                System.out.println("\u001b[31mError: Collection isn't imported\u001b[0m");
                System.exit(0);
            }
        } catch (IOException | JsonSyntaxException | SecurityException var5) {
            System.out.println("\u001b[31m" + var5.getMessage() + "\u001b[0m");
        }
        //System.out.println(emptFlag);
        // System.out.println(thingTreeMap);

        return thingConcurrentSkipListMap;

    }

    private TreeMap<String, Thing> mapFromJson(String jsonStr) {
        TreeMap<String, Thing> map = new TreeMap();
        Type type = (new TypeToken<TreeMap<String, Thing>>() {
        }).getType();
        map = (new Gson()).fromJson(jsonStr, type);

        return map;
    }

    void saveFile(File fileCollection) {
        Gson gson = new Gson();
        File saveFile = fileCollection;

        try {
            BufferedOutputStream buff = new BufferedOutputStream(new FileOutputStream(saveFile));
                buff.write(gson.toJson(this.thingConcurrentSkipListMap).getBytes());
                buff.flush();
                System.out.println("\u001b[33mКоллекция сохранена в файл: \u001b[0m" + saveFile.getAbsolutePath());

        } catch (JsonSyntaxException | NullPointerException | IOException var37) {
            System.out.println(var37.getMessage());
            SimpleDateFormat form = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss");
            saveFile = new File("save_" + form + ".json");

            try {
                BufferedOutputStream buff = new BufferedOutputStream(new FileOutputStream(saveFile));
                    buff.flush();
                    System.out.println("\u001b[33mКоллекция сохранена в файл: \u001b[0m" + saveFile);

            } catch (IOException var35) {
                System.out.println("\u001b[31mКолекцию не удалось сохранить\u001b[0m");
            }
        }

        System.exit(0);
    }

    void help() {
        try {
            out.writeUTF("\u001b[33mФормат элемента: { name, value, planet, id }\u001b");
            out.writeUTF("\u001b[33madd_if_min {element}\u001b[0m: добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
            out.writeUTF("\u001b[33minsert {String key} {element}\u001b[0m: добавить новый элемент с заданным ключом");
            out.writeUTF("\u001b[33mremove {String key}\u001b[0m: удалить элемент из коллекции по его ключу");
            out.writeUTF("\u001b[33mremove_greater_key {String key}\u001b[0m: удалить из коллекции все элементы, ключ которых превышает заданный");
            out.writeUTF("\u001b[33mshow:\u001b[0m вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
            out.writeUTF("\u001b[33mimport {String path}\u001b[0m: добавить в коллекцию все данные из файла");
            out.writeUTF("\u001b[33minfo\u001b[0m: вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
            out.writeUTF("\u001b[33mexit\u001b[0m: выйти из программу, сохранив коллекцию в файл");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Contract(pure = true)
    private boolean isEmptFlag() {
        return emptFlag;
    }

    private void setEmptFlag(boolean emptFlag) {
        this.emptFlag = emptFlag;
    }

    File getFileEntry() {
        return fileEntry;
    }

    void infoAboutId() {
        try {
            out.writeUTF("Machine.class: 1");
            out.writeUTF("Planet.class: 2");
            out.writeUTF("Relief.class: 3");
            out.writeUTF("Star.class: 4");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }
}
