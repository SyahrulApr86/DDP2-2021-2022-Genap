package assignments.assignment3.buku;

public class Kategori {
    // TODO: Implementasi kelas ini sesuai dengan UML Diagram (attribute, method, inheritance, dll)
    // Deklarasi atribut
    private String nama;
    private int poin;

    // Constructor
    public Kategori(String nama, int poin) {
        this.nama = nama;
        this.poin = poin;
    }

    // Getter
    public String getNama() {
        return nama;
    }

    public int getPoin() {
        return poin;
    }

    @Override
    public String toString() {
        return String.format("""
                            Kategori: %s
                            Poin: %d""",
                this.getNama(), this.getPoin());
    }
}
