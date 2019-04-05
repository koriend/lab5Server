

package texting;

import java.util.Date;

public class Star extends CosmosObj {
    private String nameStar;
    private int diametr;
    private int count;
    private String reallyCount;
    private Property property;/**
     *
     * @param machine class
     */
    //private SimpleDateFormat dateInitStar;
    private String dateStar;
    private int[] coord = new int[3];

    /**
     * for collection
     *
     * @param temp1 name
     * @param temp2 diametr
     */
    public Star(String temp1, int temp2) {
        this.nameStar = temp1;
        this.diametr = temp2;
        this.count = 1;
        Date d = new Date();
        this.dateStar = d.toString();
        for(int i = 0; i <= 2; i++) coord[i] = (int)(Math.random() * 2000 + 234);

    }

    /**
     * for text
     *
     * @param temp1 name
     * @param temp2 count
     */
    public Star(String temp1, String temp2) {
        this.nameStar = temp1;
        this.count = (int)(Math.random() * 300.0D + 100.0D);
        this.reallyCount = temp2;
        this.property = Property.SHINY;
    }

    public void Beamed(Thing temp, Ray temp1) {
        Star temp2 = (Star)temp;
        System.out.print(temp2.getNamePlan() + ToShine.TO_OTHER.getName() + " Землю " + temp1.getName() + " лучами,");
        temp1 = Ray.STRAIGHT;
        System.out.println("а не " + temp1.getName() + " лучами ");
    }

    public boolean equals(Object temp0) {
        if (this == temp0) {
            return true;
        } else if (temp0 != null && this.getClass() == temp0.getClass()) {
            Star temp01 = (Star)temp0;
            if (!this.nameStar.equals(temp01.nameStar)) {
                return false;
            } else if (this.diametr != temp01.diametr) {
                return false;
            } else {
                return this.count > 1 ? this.count != temp01.count : this.count == temp01.count;
            }
        } else {
            return false;
        }
    }

    @Override
    public int getValueThing() {
        return this.diametr;
    }

    public int hashCode() {
        int temp0 = 23;
        temp0 = 17 * temp0 + this.nameStar.hashCode();
        temp0 = 17 * temp0 + this.diametr;
        temp0 = 17 * temp0 + this.count;
        return temp0;
    }

    public String toString() {
        return super.toString();
    }

    public String getNamePlan() {
        return this.nameStar;
    }

    public String getReallyCount() {
        return this.reallyCount;
    }

    public String getProperty() {
        return this.property.getPropertion();
    }

    public String getDateStar() {
        return dateStar;
    }

    public int getDiametr() {
        return this.diametr;
    }

    public int[] getCoord() {
        return coord;
    }
}
