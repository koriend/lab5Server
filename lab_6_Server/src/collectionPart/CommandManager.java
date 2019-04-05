package collectionPart;

import org.jetbrains.annotations.NotNull;
import texting.*;
import texting.Planet.TypeOfPlanet;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ConcurrentSkipListMap;

public class CommandManager {
    private ThingManager collectionManager = new ThingManager();
    private Thing thingAct = null;
    private boolean needExit;
    private boolean access = true;
    private Command invoke;
    //private Parsable parse;
    private ConcurrentSkipListMap<String, String> commandMap;
    //---------!!!!!---------//
    ObjectInputStream inObj = null;
    ObjectOutputStream outObj = null;


    public CommandManager(ThingManager collectionManager) {
        if (collectionManager != null) {
            this.collectionManager = collectionManager;
        }
        //-----!!-------//
        try {
            outObj = new ObjectOutputStream(collectionManager.getSocket().getOutputStream());
            inObj = new ObjectInputStream(collectionManager.getSocket().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //----!!!----//
        this.needExit = false;

    }
    //-------!!!-----//
    private ConcurrentSkipListMap<String, String> readingObject(){
        ConcurrentSkipListMap<String, String> map = new ConcurrentSkipListMap<>();
        try{
            map = (ConcurrentSkipListMap<String, String>)inObj.readObject();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return  map;
    }

    private Thing inObject(@NotNull ConcurrentSkipListMap<String, String> map){
        Thing temp = new Thing();
        switch (Integer.parseInt(map.get("idOfObject"))) {
            case 1:
                temp = new Thing(new Machine(map.get("nameOfObject"), new Planet(map.get("nameOfPlanet"), Integer.parseInt(map.get("valueOfPlanet")), TypeOfPlanet.valueOf(map.get("typeOfPlanet")), Integer.parseInt(map.get("idOfPlanet"))), Integer.parseInt(map.get("valueOfObject"))));
                break;
            case 2:
                temp = new Thing(new Planet(map.get("nameOfPlanet"), Integer.parseInt(map.get("valueOfPlanet")), TypeOfPlanet.valueOf(map.get("typeOfPlanet")), Integer.parseInt(map.get("idOfPlanet"))));
                break;
            case 3:
                temp = new Thing(new Relief(map.get("nameOfObject"), new Planet(map.get("nameOfPlanet"), Integer.parseInt(map.get("valueOfPlanet")), TypeOfPlanet.valueOf(map.get("typeOfPlanet")), Integer.parseInt(map.get("idOfPlanet"))), Integer.parseInt(map.get("valueOfObject"))));
                break;
            case 4:
                temp = new Thing(new Star(map.get("nameOfObject"), Integer.parseInt(map.get("valueOfObject"))));
        }
         return temp;
    }
    //-----!!---->
    public void direct(/*ConcurrentSkipListMap<String, String> map*/) {
        while(!this.needExit) {
            commandMap = readingObject();
            thingAct = inObject(commandMap);
            if (this.access) {
                switch (commandMap.get("command")) {
                    case "insert":
                        invoke = () -> collectionManager.insert(commandMap.get("key"), thingAct);
                        break;
                    case "add_if_min":
                        invoke = () -> collectionManager.add_if_min(thingAct);
                        break;
                    case "remove_greater_key":
                        invoke = () -> collectionManager.remove_greater_key(commandMap.get("key"));
                        break;
                    case "import":
                        invoke = () -> collectionManager.importMap(new File(commandMap.get("path")));
                        break;
                    case "remove":
                        invoke = () -> collectionManager.remove(commandMap.get("key"));
                        break;
                    case "exit":
                        invoke = () -> collectionManager.saveFile(collectionManager.getFileEntry());
                        needExit = true;
                        break;
                    case "help":
                        invoke = () -> collectionManager.help();
                        break;
                    case "info":
                        invoke = () -> collectionManager.info();
                        break;
                    case "show":
                        invoke = () -> collectionManager.show();
                        break;
                    case "info_about_id":
                        invoke = () -> collectionManager.infoAboutId();
                        break;
                }
            }
            invoke.execute();
        }
    }
}
