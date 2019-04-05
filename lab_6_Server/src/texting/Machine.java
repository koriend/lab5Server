package texting;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.Date;

public class Machine extends Thing implements HasColoring, Lumen {
    private String nameMach;
    private Planet planetMach;
    private Relief reliefMach;
    private Color colorMach;
    private Machine.Distance distMach;
    private ToShine toShinyMach;
    private int valueMach;
    private String dateMach;
    private int[] coord = new int[3];


    public void Beamed(Thing temp, Ray temp1) {
        Machine temp2 = (Machine)temp;
        temp2.toShinyMach = ToShine.GLOW;
        System.out.println(temp2.getNamePlan() + temp2.toShinyMach.getName());
    }

    /**
     *  constructor for text
     *
     * @param temp1 name of machine
     * @param temp2 what planet on
     * @param temp3 what relief on
     * @param temp4 color of machine
     * @param temp5 distance from heroes
     */
    public Machine(String temp1, Planet temp2, Relief temp3, Color temp4, Machine.Distance temp5) {
        this.distMach = Machine.Distance.LONG_AWAY;
        this.nameMach = temp1;
        this.planetMach = temp2;
        this.reliefMach = temp3;
        this.colorMach = temp4;
        this.distMach = temp5;
        this.valueMach = 0;
    }

    /**
     * constructor for collection
     *
     * @param name of machine
     * @param planet of machine
     * @param value height of machine
     */
    public Machine(String name, Planet planet, int value) {
        this.distMach = Machine.Distance.LONG_AWAY;
        this.nameMach = name;
        this.planetMach = planet;
        this.valueMach = value;
        Date d = new Date();
        this.dateMach = d.toString();
        for(int i = 0; i <= 2; i++) coord[i] = (int)(Math.random() * 2000 + 234);
    }


    @Override
    public int getValueThing() {
        return this.valueMach;
    }

    public void HasColor(Thing temp1) throws WrongLinkException {
        Machine temp = (Machine)temp1;
        temp.colorMach = Color.BRIGHT_BLUE;
        System.out.println(temp.getNamePlan() + " имеет " + temp.colorMach.getColor() + " цвет, словно кусочек весеннего земного неба");

        try {
            if (temp.getNamePlan().equals((Object)null)) {
                throw new WrongLinkException("Поле имя");
            }
        } catch (WrongLinkException var4) {
            System.out.println(var4.toString());
        }

    }



    public String toString() {
        String ofClass = "Planet";
        String valueStr = "diametr: " + this.getValueMach();
        return "Name: " + this.getNamePlan() + "  -------  class: " + ofClass + "  ------  " + valueStr;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && !(o instanceof Thing)) {
            Machine temp01 = (Machine)o;
            if (!this.nameMach.equals(temp01.nameMach)) {
                return false;
            } else if (!this.planetMach.equals(temp01.planetMach)) {
                return false;
            } else {
                return this.valueMach == temp01.valueMach;
            }
        } else {
            return false;
        }
    }

    public int hashCode() {
        int temp = 13;
        temp = temp + this.valueMach;
        temp += this.nameMach.hashCode();
        temp += this.planetMach.hashCode();
        return temp;
    }



    String getDistMach() {
        return this.distMach.getDistance();
    }

    int getValueMach() {
        return this.valueMach;
    }

    public String getNamePlan() {
        return this.nameMach;
    }

    void setNameMach(String nameMach) {
        this.nameMach = nameMach;
    }

    Planet getPlanetMach() {
        return this.planetMach;
    }

    public String getDateMach() {
        return dateMach;
    }

    public enum Distance {
        NEAR(" близко "),
        LONG_AWAY(" вдалеке"),
        NONE("");

        private String distance;

        private Distance(String far) {
            this.distance = far;
        }

        public String getDistance() {
            return this.distance;
        }
    }

    public int[] getCoord() {
        return coord;
    }
}
