package assignments.assignment3.pengguna;

public abstract class Pengguna {
    // TODO: Implementasi kelas ini sesuai dengan UML Diagram (attribute, method, inheritance, dll)
    // Deklarasi atribut
    private String nama;
    private String id;

    // Constructor
    public Pengguna(String nama) {
        this.nama = nama;
    }

    // Getter dan Setter
    public String getNama() {
        return nama;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Method untuk membuat ID dari pengguna
     * @return
     */
    abstract protected String generateId();
    abstract public String toString();
}
