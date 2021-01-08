package FX_W20PKG;

public class Airport extends Structure {
    private int numOfRunways;
    private String code; // international airport code

    public int getNumOfRunways() {
        return numOfRunways;
    }

    public void setNumOfRunways(int numOfRunways) {
        this.numOfRunways = numOfRunways;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Airport(int yearOfCreation, double cost, int numOfRunways, String code) {
        super(yearOfCreation, cost);
        this.numOfRunways = numOfRunways;
        this.code = code;
    }

    @Override
    public String toString() {
        return "This airport was created in " + getYearOfCreation() + " and it costs $" + getCost() + ".\nIt has "+
                numOfRunways + " runways and its code is: " + code;
    }
}
