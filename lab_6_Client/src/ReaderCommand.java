import org.jetbrains.annotations.NotNull;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.regex.Pattern;

public class ReaderCommand {
    private static ConcurrentSkipListMap<String, String> mapArg = new ConcurrentSkipListMap<>();
    //String[] comm = null;
    public ReaderCommand() {
    }

    static ConcurrentSkipListMap<String, String> readingCommand() {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите комманду: ");
        System.out.println();
        String commandLine = null;
        String att = "";

        /**
         * обработка ctrl+d
         */
        try{
            commandLine = in.nextLine();
        }catch (NoSuchElementException ex){
            att = "Завершен поток";
        }

            commandLine = commandLine.trim();

        String re1 = Pattern.quote(" {");
        String re2 = Pattern.quote("}");
        String[] command_args = new String[2];


        /*
        * try {
                if (command[0] == null) {
                    this.access = false;
                    this.collectionManager.saveFile(collectionManager.getFileEntry());
                    System.exit(0);
                }

                if (!command[0].equals("info_about_id") && !command[0].equals("info") && !command[0].equals("snake") && !command[0].equals("show") && !command[0].equals("exit") && !command[0].equals("help") && !command[0].equals("add_if_min") && !command[0].equals("remove") && !command[0].equals("remove_greater_key") && !command[0].equals("insert") && !command[0].equals("import")) {
                    throw new ArgumentFormatException("Input correct command");
                }

                if ((command[0].equals("info") || command[0].equals("show") || command[0].equals("exit") || command[0].equals("help")) && command.length > 1) {
                    this.access = false;
                    throw new ArgumentFormatException(command[0] + " has too many arguments");
                }

                if ((command[0].equals("add_if_min") || command[0].equals("remove") || command[0].equals("remove_greater_key")) && command.length == 1) {
                    this.access = false;
                    throw new ArgumentFormatException(command[0] + " has incorrect format of arguments");
                }

                if (!command[0].equals("remove") && !command[0].equals("remove_greater_key") && !command[0].equals("import") && command.length == 2) {
                    this.thingAct = this.fromJtoM(command[0], command[1]);
                }

                if (command[0].equals("insert") && command.length == 3) {
                    this.thingAct = this.fromJtoM(command[0], command[2]);
                } else if (command[0].equals("insert") && command.length < 3) {
                    this.access = false;
                    throw new ArgumentFormatException(command[0] + " has incorrect format of arguments");
                }
        *
        *
        * */
        try {
            if (commandLine.contains(" ")) {
                command_args = commandLine.split(" ", 2);
                if (!command_args[0].equals("info_about_id") && !command_args[0].equals("info") && !command_args[0].equals("show") && !command_args[0].equals("exit") && !command_args[0].equals("help") && !command_args[0].equals("add_if_min") && !command_args[0].equals("remove") && !command_args[0].equals("remove_greater_key") && !command_args[0].equals("insert") && !command_args[0].equals("import")) {
                    throw new ArgumentFormatException("Input correct command");
                }
                command_args[1] = command_args[1].replaceAll(re1, ",");
                command_args[1] = command_args[1].replaceAll(re2, ",");

            } else {
                command_args[0] = commandLine;
                if(command_args[0].equals("info") || command_args[0].equals("show") || command_args[0].equals("exit") || command_args[0].equals("help")) {
                    command_args[1] = Integer.toString(0);
                } else throw new ArgumentFormatException("Неверный формат комманды");
            }
        } catch (ArgumentFormatException e){
            e.getExc();
        }
        if(command_args[1].equals("0")){
            mapArg.put("command", command_args[0]);
        } else{
            mapArg = parseObj(command_args[1]);
            mapArg.put("command", command_args[0]);
        }

        //if(mapArg.get("command").equals())

        if (att.equals("Заврешен поток")) {
            System.out.println("\u001b[31m" + att + "\u001b[0m");
            System.exit(0);
        }
        return mapArg;
    }

    private static ConcurrentSkipListMap<String, String> parseObj(String arg){
        ConcurrentSkipListMap<String, String> temp = new ConcurrentSkipListMap<>();
        String[] baseArg;
        try {
            baseArg = baseObjectParsing(arg);
            temp.put("nameOfObject", baseArg[0]);
            temp.put("valueOfObject", baseArg[1]);
            temp.put("idOfObject", baseArg[2]);
            if(Integer.parseInt(temp.get("idOfObject")) != 4){
                if (arg.contains("\"type\":")){
                    temp.put("typeOfPlanet", arg.substring(arg.indexOf("\"type\":") + 7, arg.indexOf(",", arg.indexOf("\"type\":") + 7)));
                } else throw new ArgumentFormatException("Неверный формат");
                if(Integer.parseInt(temp.get("idOfObject")) != 2){
                    arg = arg.substring(arg.indexOf("\"planet\":") + 9, arg.indexOf("}") + 1);
                    baseArg = baseObjectParsing(arg);
                    temp.put("nameOfPlanet", baseArg[0]);
                    temp.put("valueOfPlanet", baseArg[1]);
                    temp.put("idOfPlanet", baseArg[2]);
                }
            }
        }catch (ArgumentFormatException e){
            System.out.println(e.getExc());
        }
        return temp;
    }

    private static String[] baseObjectParsing(@NotNull String arg){
        String[] temp = new String[3];
        try {
            if (arg.contains("\"name\":")) {
                temp[0] = arg.substring(arg.indexOf("\"name\":") + 7, arg.indexOf(",", arg.indexOf("\"name\":") + 7));
            } else throw new ArgumentFormatException("Неверный формат объекта");
            if (arg.contains("\"value\":")) {
                temp[1] = arg.substring(arg.indexOf("\"value\":") + 8, arg.indexOf(",", arg.indexOf("\"value\":") + 8));
            } else throw new ArgumentFormatException("Неверный формат");
            if (arg.contains("\"id\":")) {
                temp[2] = arg.substring(arg.indexOf("\"id\":") + 5, arg.indexOf(",", arg.indexOf("\"id\":") + 5));
            } else throw new ArgumentFormatException("Неверный формат");
        } catch (ArgumentFormatException e){
            System.out.println(e.getExc());
        }
        return temp;
    }

    private void checkingCommand(String s){
        s = s.trim();
        String re = Pattern.quote(" {");
        String[] command = null;
        if(s.contains(" ")){
            command = s.split(" ", 3);
            if(command[1].contains("{")){
                command = s.split(re, 2);
                command[1] = "{" + command[1];
            }
        } else {
            command = s.split(" ", 2);
        }
    }
}
