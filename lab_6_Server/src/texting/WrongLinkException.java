package texting;

public class WrongLinkException extends NullPointerException{
    private String exc;

    public WrongLinkException(String s){
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
