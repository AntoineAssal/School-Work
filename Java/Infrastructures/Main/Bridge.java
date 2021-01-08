package FX_W20PKG;

public class Bridge extends Structure {
    private double length;
    private double height; // peak height above ground level

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Bridge(int yearOfCreation, double cost, double length, double height) {
        super(yearOfCreation, cost);
        this.length = length;
        this.height = height;
    }

    @Override
    public String toString() {
        return "This bridge was created in " + getYearOfCreation()  + " and it's cost is $" + getCost() + ".\nIt has a length of " +
                length + " meters and it's height is " + height + " meters.";
    }
}
