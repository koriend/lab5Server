

package texting;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import texting.Planet.TypeOfPlanet;

import java.io.*;

public class Thing implements Comparable<Thing> {
    private String name;
    private int id;
    private int valueThing;
    private Planet planet;
    private TypeOfPlanet typePlanet;
    private String date;
    private int[] coordinate = new int[3];

    /**
     * base constr for calling methods
     */
    public Thing() {
        this.name = null;
        this.planet = null;
    }



    public Thing(@NotNull Machine machine) {
        this.name = machine.getNamePlan();
        this.valueThing = machine.getValueMach();
        this.planet = machine.getPlanetMach();
        date = machine.getDateMach();
        this.id = 1;
        this.coordinate = machine.getCoord();
    }

    public Thing(@NotNull Planet planet) {
        this.name = planet.getNamePlan();
        this.valueThing = planet.getDiametrPlan();
        this.typePlanet = planet.getTypePlanet();
        this.date = planet.getDatePlanet();
        this.id = 2;
        this.coordinate = planet.getCoord();
    }

    public Thing(@NotNull Relief relief) {
        this.name = relief.getNamePlan();
        this.valueThing = relief.getValueRel();
        this.planet = relief.getPlanet();
        this.date = relief.getDateRelief();
        this.id = 3;
        this.coordinate = relief.getCoord();
    }

    public Thing(@NotNull Star star) {
        this.name = star.getNamePlan();
        this.valueThing = star.getDiametr();
        this.date = star.getDateStar();
        this.id = 4;
        this.coordinate = star.getCoord();
    }

    public String toString() {
        String ofClass = "";
        String valueStr = "";
        String planStr = "";
        String type = "";
        switch(this.getId()) {
            case 1:
                ofClass = "Machine";
                valueStr = "weight: " + this.getValueThing();
                planStr = this.getPlanet().getNamePlan();
                break;
            case 2:
                ofClass = "Planet";
                valueStr = "diametr: " + this.getValueThing();
                type = this.getTypePlan().getTypeOfPlan();
                break;
            case 3:
                ofClass = "Relief";
                valueStr = "height " + this.getValueThing();
                planStr = this.getPlanet().getNamePlan();
                break;
            case 4:
                ofClass = "Star";
                valueStr = "weight " + this.getValueThing();
                break;
            default:
                ofClass = null;
        }

        if (ofClass.equals("Planet")) {
            return "\u001b[33mName: " + this.name + "    ······    class: " + ofClass + "  ------  type: " + type + "  ------  " + valueStr + "  ------  " + "Date: " + this.date + "\u001b[0m";
        } else if (ofClass.equals("Star")) {
            return "\u001b[33mName: " + this.name + "    ······    class: " + ofClass + "   ······    " + valueStr  + "  ------  " + "Date: " + this.date + "\u001b[0m";
        } else {
            return ofClass != null && this.valueThing >= 0 ? "\u001b[33mName: " + this.name + "    ······    class: " + ofClass + "   ······    planet: " + planStr + "   ······    " + valueStr  + "  ------  " + "Date: " + this.date: "Неккоректно задан id/toString\u001b[0m";
        }
    }

    public static String pullStingFromJson(File file) throws IOException {
        BufferedInputStream br = null;
        String strJson = "";

        try {
            br = new BufferedInputStream(new FileInputStream(file));
        } catch (FileNotFoundException var4) {
            System.out.println(var4.toString());
        }

        int i;
        if (br != null) {
            while((i = br.read()) != -1) {
                strJson = strJson + (char)i;
            }
        }

        return strJson;
    }

    @Contract(value = "null -> false", pure = true)
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && !(o instanceof Thing)) {
            Thing temp01 = (Thing)o;
            if (!this.getNamePlan().equals(temp01.getNamePlan())) {
                return false;
            } else if (!this.getPlanet().equals(temp01.getPlanet())) {
                return false;
            } else {
                return this.getValueThing() == temp01.getValueThing();
            }
        } else {
            return false;
        }
    }

    public int compareTo(Thing temp) {
        if (this.valueThing == temp.valueThing) {
            return 0;
        } else {
            return this.valueThing > temp.valueThing ? 1 : -1;
        }
    }

    public int hashCode() {
        int temp0 = 13;
        temp0 = temp0 + this.valueThing;
        temp0 += this.name.hashCode();
        temp0 += this.id;
        temp0 += this.planet.hashCode();
        return temp0;
    }

    public String getNamePlan() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public int getValueThing() {
        return this.valueThing;
    }

    public Planet getPlanet() {
        return this.planet;
    }

    public TypeOfPlanet getTypePlan() {
        return this.typePlanet;
    }
}
