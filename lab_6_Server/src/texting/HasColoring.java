package texting;

public interface HasColoring{
    void HasColor(Thing temp);
    default void WarColor(Color temp1, Color temp2, Shorty temp3){
        temp1.setColor("красного");
        temp2.setColor("зеленого");
        System.out.println(temp3.getName() + " наблюдади везде борьбу двух цветов: " + temp1.getColor() + " и " + temp2.getColor() + "\n");
    }
    enum Color {
        GREEN(" изумрудно-зеленый "),
        BLUE(" голубой "),
        BRIGHT_BLUE("ярко-голубой"),
        BRIGHT_RED("светло-вишневого"),
        WINE_RED("пурпурного"),
        DARK_RED("темно-багрового"),
        LIGHT_BlUE(" светло-голубого"),
        RED(" красный "),
        BLACK(" черное "),
        WHITE(" ярко-белого" ),
        NONE(" ");


        private String description;


        Color(String color){
            this.description = color;
        }

        public String getColor() {
            return description;
        }

        public void setColor(String color) {
            this.description = color;
        }
    }
}
