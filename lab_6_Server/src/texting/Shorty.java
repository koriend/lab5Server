package texting;//WAR

public class Shorty extends StoryTelling {


    private String name;
    private CosmosObj planet;
    private int count;

    public Shorty(String temp1, CosmosObj temp2) {
        this.name = temp1;
        this.planet = temp2;
        this.count = 1;
        //System.out.println(NEW_PERS + this.name);
    }

    public Shorty(String temp1, CosmosObj temp2, int shorCount) {
        this.name = temp1;
        this.planet = temp2;
        this.count = shorCount;
    }

    public Shorty() {
        this.name = "";
        this.planet = null;
        this.count = 0;
    }

    ;

    @Override
    public boolean equals(Object temp0) {
        if (this == temp0) {
            return true;
        } else if (temp0 != null && this.getClass() == temp0.getClass()) {
            Shorty temp01 = (Shorty) temp0;
            if (!this.name.equals(temp01.name)) {
                return false;
            } else if (!this.planet.equals(temp01.planet)) {
                return false;
            } else {
                return this.count > 1 ? this.count != temp01.count : this.count == temp01.count;
            }
        } else {
            return false;
        }
    }


    @Override
    public int hashCode() {
        int temp0 = 13;
        temp0 = 17 * temp0 + this.name.hashCode();
        temp0 = 17 * temp0 + this.planet.hashCode();
        temp0 = 17 * temp0 + this.count;
        return temp0;
    }


    @Override
    public String toString() {
        if (this.count == 1) {
            return "Коротышка: имя: " + this.name + "\n" + "планета: " + this.planet + "\n";
        } else {
            return "Коротышки: планета: " + this.planet + "\n" + "количество: " + this.count + "\n";
        }
    }




    public void watchSky(Shorty temp1, Planet temp2) {
        System.out.println(temp1.watch(temp1.getName()) + temp2.sky() + " небо");
    }

    public void watchStars(Shorty temp1) {

        Star stars = new Star("звезд", "мириады");

        System.out.println(temp1.watch(temp1.getName()) + " " + stars.getReallyCount() + stars.getProperty() + " " + stars.getNamePlan());

    }

    public void watchEarth(Shorty temp1, Planet temp2) {

        Star SUN = new Star("Солнце", 1391016);
        System.out.print(temp1.watch(temp1.getName()));
        temp2.SideOf(temp2, Planet.Shape.DISK);
        System.out.println("\n");
        System.out.println(temp2.getNamePlan() + " имеет форму не" + Planet.Shape.SICKLE.getShape() + " или " + Planet.Shape.CRESCENT.getShape() + ", а" + Planet.Shape.DISK.getShape() + "а");
        System.out.print("Потому что, ");
        SUN.Beamed(SUN, Lumen.Ray.SLASH);
        System.out.println("\n");

    }

    public void watchRocket(Shorty temp1, Machine temp2){
        System.out.println(temp1.watch(temp1.getName()) + temp2.getDistMach() + temp2.getNamePlan());
        temp2.setNameMach("Ракета");
        temp2.Beamed(temp2, Lumen.Ray.NONE);
        temp2.HasColor(temp2);


    }

    public void goOut(Shorty temp1, Relief temp2) {
        System.out.println(temp1.getName() + " вышли из" + temp2.getType());
    }


    public String getName() {
        return this.name;
    }

    public void setName(String temp1) {
        this.name = temp1;
    }

    public CosmosObj getPlanet() {
        return planet;
    }
}

