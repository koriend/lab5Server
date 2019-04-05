package texting;

public class BadNameException extends Exception {
    private String exc;

    public BadNameException(String s){
        super(s);
        exc = s;
    }

    public String getExc() {
        return exc;
    }

    @Override
    public String toString() {
        return "Еггог: " + getExc();
    }
}