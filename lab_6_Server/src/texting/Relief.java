//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package texting;

import java.util.Date;

public class Relief extends Thing implements HasColoring, Lumen {
    private ToShine toShine;
    private Color color;
    private int height;
    private String type;
    private int layer;
    private String nameRel;
    private Property property;
    private Planet planet;
    private int valueRel;
    private String dateRelief;
    private int[] coord = new int[3];

    /**
     * for call of methods
     */
    public Relief() {
        this.property = Property.NONE;
    }

    public void Beamed(Thing temp, Ray temp1) {
        Relief temp2 = (Relief)temp;
        temp2.HasColor(temp2);
        this.toShine = ToShine.SELF;
        System.out.println(", потому что" + this.toShine.getName() + " Землей");
    }

    /**
     * for text
     *
     * @param temp1 what planet on
     * @param temp2 height
     * @param temp3 property if shine
     */
    public Relief(Planet temp1, int temp2, ToShine temp3) {
        this.property = Property.NONE;
        this.nameRel = temp1.getNamePlan();
        this.height = temp2;
        this.toShine = temp3;
        if (this.height > 5) {
            this.type = "Гора";
        } else if (this.height > 0) {
            this.type = " пещеры";
        } else if (this.height == 0) {
            this.type = "Равнина";
        } else {
            this.type = " моря";
        }

    }

    /**
     * for collection
     *
     * @param name of rel
     * @param planet of rel
     * @param value height
     */
    public Relief(String name, Planet planet, int value) {
        this.property = Property.NONE;
        this.nameRel = name;
        this.planet = planet;
        this.valueRel = value;
        Date d = new Date();
        this.dateRelief = d.toString();
        for(int i = 0; i <= 2; i++) coord[i] = (int)(Math.random() * 2000 + 234);
    }

    public void HasColor(Thing temp) {
        String allColor = " ";
        Relief temp1 = (Relief)temp;
        if (temp1.toShine == ToShine.SELF && temp1.height > 5) {
            temp1.color = Color.RED;
            allColor = temp1.color.getColor() + " цвет: От ";
            temp1.color = Color.BRIGHT_RED;
            allColor = allColor + temp1.color.getColor() + " или ";
            temp1.color = Color.WINE_RED;
            allColor = allColor + temp1.color.getColor() + " и до ";
            temp1.color = Color.DARK_RED;
            allColor = allColor + temp1.color.getColor();
        } else if (temp1.toShine == ToShine.SHADOW) {
            temp1.property = Property.FLICKERING;
            temp1.color = Color.GREEN;
            temp1.toShine = ToShine.LUMINOUS;
            allColor = temp1.toShine.getName() + " и " + temp1.property.getPropertion() + temp1.color.getColor() + "цвет";
        } else {
            temp1.color = Color.NONE;
        }

        System.out.print(temp1.getType() + " (" + temp1.nameRel + ") иммет " + allColor);
    }

    public String toString() {
        return super.toString();
    }

    public boolean equals(Object o) {
        return super.equals(o);
    }

    public int hashCode() {
        int temp = 13;
        temp = temp + this.valueRel;
        temp += this.nameRel.hashCode();
        temp += this.planet.hashCode();
        return temp;
    }

    @Override
    public int getValueThing() {
        return this.valueRel;
    }

    public String getType() {
        return this.type;
    }

    public String getNamePlan() {
        return this.nameRel;
    }

    public Planet getPlanet() {
        return this.planet;
    }

    public int getValueRel() {
        return this.valueRel;
    }

    public String getDateRelief() {
        return dateRelief;
    }

    public int[] getCoord() {
        return coord;
    }
}
