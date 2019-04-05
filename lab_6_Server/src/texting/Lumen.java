package texting;

public interface Lumen {
    enum ToShine {
        SELF(" освещенна"),
        TO_OTHER(" освещает"),
        SHADOW(" в тени"),
        LUMINOUS(" светящийся"),
        GLOW(" светиться"),
        NONE(" ");

        private String name;

        public String getName(){
            return name;
        }

        ToShine(String descr){
            this.name = descr;
        }
    }

    public enum Ray {
        STRAIGHT("косыми"),
        SLASH("прямыми"),
        COSMIC("космических"),
        NONE(" ");

        private String name;

        public String getName() {
            return name;
        }

        Ray(String descr) {
            this.name = descr;
        }
    }

    void Beamed(Thing temp, Ray temp1);
}
