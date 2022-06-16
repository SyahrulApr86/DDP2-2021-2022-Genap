package assignments.assignment2;

// TODO
public class Category {
    // Deklarasi properties
    private String name;
    private int point;

    // Constructor
    public Category(String name, int point) {
        this.name = name;
        this.point = point;
    }

    // Getter dan setter
    public String getName() {
        return name;
    }

    public int getPoint() {
        return point;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
