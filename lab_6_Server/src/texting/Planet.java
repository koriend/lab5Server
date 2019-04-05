package texting;

import java.util.Date;

public class Planet extends CosmosObj implements HasColoring {
    private String namePlan;
    private int diametrPlan;
    private int distToSun;
    private Color colorMach;
    private Planet.TypeOfPlanet typePlan;
    private Property property;
    private ToShine toShine;
    private int idOfPlan;
    //private SimpleDateFormat dateInitPlan;
    private String datePlanet;
    private int[] coord = new int[3];

    /**
     * for colection
     * for text
     *
     *
     * @param temp1 namePlan
     * @param temp2 weight
     * @param temp4 type
     * @param id = 2
     */
    public Planet(String temp1, int temp2, Planet.TypeOfPlanet temp4, int id) {
        this.namePlan = temp1;
        this.diametrPlan = temp2;
        this.distToSun = (int)(Math.random() * 1999.0D);
        this.typePlan = temp4;
        this.idOfPlan = 2;
        Date d = new Date();
        this.datePlanet = d.toString();
        for(int i = 0; i <= 2; i++) coord[i] = (int)(Math.random() * 2000 + 234);
    }

    /*public Planet(String namePlan, int value, int id) {
        this.namePlan = namePlan;
        this.diametrPlan = value;
        this.distToSun = (int)(Math.random() * 1999.0D);
        this.idOfPlan = 2;
    } */

    public void Beamed(Thing temp, Ray temp1) {
    }

    public void HasColor(Thing temp) {
        Planet temp1 = (Planet)temp;
        System.out.println(temp1);
    }

     String sky() {
        this.typePlan.HasColor(this);
        return this.colorMach.getColor() + this.property.getPropertion();
    }

     void SideOf(Planet temp1, Planet.Shape temp2) {
        if (temp2 == Planet.Shape.DISK) {
            temp1.property = Property.GREAT;
            temp1.toShine = ToShine.LUMINOUS;
            System.out.print(temp1.property.getPropertion() + temp1.toShine.getName() + temp2.getShape());
            temp1.typePlan.HasColor(temp1);
        }

    }

    @Override
    public int getValueThing() {
        return this.diametrPlan;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Planet obj = (Planet)o;
            return obj.getValueThing() == this.getValueThing() && obj.getNamePlan().equals(this.getNamePlan());
        } else {
            return false;
        }
    }

    public int hashCode() {
        int temp0 = 19;
        temp0 = temp0 * 13 + this.namePlan.hashCode();
        temp0 = temp0 * 13 + this.distToSun;
        temp0 = temp0 * 13 + this.diametrPlan;
        return temp0;
    }

    public String getNamePlan() {
        return this.namePlan;
    }

    public String toString() {
        String ofClass = "Planet";
        String valueStr = "diametr: " + this.getDiametrPlan();
        String types = this.getTypePlanet().getTypeOfPlan();
        return "Name: " + this.namePlan + "  -------  class: " + ofClass + "  ------  type: " + types + "  ------  " + valueStr;
    }

    public int getDiametrPlan() {
        return this.diametrPlan;
    }

    public Planet.TypeOfPlanet getTypePlanet() {
        return this.typePlan;
    }

    public static class MoonProperty implements Lumen {
        private int lay;
        private ToShine toShine;
        private String name;
        private Planet temp3;

        public MoonProperty() {
        }

        public void Beamed(Thing temp, Ray temp1) {
            Planet temp3 = (Planet)temp;
            temp3.property = Property.INVISIBLE;
            this.toShine = ToShine.GLOW;
            System.out.println(this.toShine.getName() + " из-за" + temp3.property.getPropertion() + temp1.getName() + " лучей");
        }

        public void PropertyOfLayer(int layer) {
            this.lay = layer;
            System.out.print(this.lay + " поверхность (Луна) ");
        }

        public void Answer(Planet temp1) {
            System.out.print("Это объяется тем, что ");
            (new Planet.MoonProperty()).PropertyOfLayer(410);
            (new Planet.MoonProperty()).Beamed(temp1, Ray.COSMIC);
            System.out.println("\n");
        }
    }

    public String getDatePlanet() {
        return datePlanet;
    }

    public static enum TypeOfPlanet implements HasColoring {
        IS_PLANET {
            public void HasColor(Thing temp) {
                Planet temp1 = (Planet)temp;
                temp1.colorMach = Color.BLUE;
            }

            public String getTypeOfPlan() {
                return "IS_PLANET";
            }
        },
        IS_SATELLITE {
            public void HasColor(Thing temp) {
                Planet temp1 = (Planet)temp;
                temp1.colorMach = Color.BLACK;
                temp1.property = Property.BOTOMLESS;
            }

            public String getTypeOfPlan() {
                return "IS_SATELLITE";
            }
        },
        NONE {
            public void HasColor(Thing temp) {
                Planet temp1 = (Planet)temp;
                temp1.colorMach = Color.NONE;
                temp1.property = Property.NONE;
            }

            public String getTypeOfPlan() {
                return "NONE";
            }
        };

        private TypeOfPlanet() {
        }

        public abstract String getTypeOfPlan();
    }

    public static enum Shape implements HasColoring {
        DISK(" диск") {
            public void HasColor(Thing temp) {
                Planet temp1 = (Planet)temp;
                temp1.colorMach = Color.WHITE;
                String s = temp1.colorMach.getColor();
                temp1.colorMach = Color.BLUE;
                (new StringBuilder()).append(s).append(temp1.colorMach.getColor()).toString();
            }
        },
        SICKLE(" серпа") {
            public void HasColor(Thing temp) {
            }
        },
        CRESCENT("полумесяца") {
            public void HasColor(Thing temp) {
            }
        },
        CIRCLE(" круга") {
            public void HasColor(Thing temp) {
            }
        };

        private String shape;


        private Shape(String shape) {
            this.shape = shape;
        }

        public String getShape() {
            return this.shape;
        }

        public void setShape(String shape) {
            this.shape = shape;
        }
    }

    public int[] getCoord() {
        return coord;
    }
}
