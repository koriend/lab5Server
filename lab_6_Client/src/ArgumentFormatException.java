//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

public class ArgumentFormatException extends Exception {
    private static final String COLOR_STANDART = "\u001b[0m";
    private static final String COLOR_RED = "\u001b[31m";
    private String exc;

    public ArgumentFormatException(String s) {
        super(s);
        this.exc = s;
    }

    public String getExc() {
        return this.exc;
    }

    public String toString() {
        return COLOR_RED + "Еггог: " + this.getExc() + COLOR_STANDART;
    }
}
