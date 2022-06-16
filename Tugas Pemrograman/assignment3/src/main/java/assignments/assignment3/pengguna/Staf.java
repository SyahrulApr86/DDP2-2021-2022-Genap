package assignments.assignment3.pengguna;

public class Staf extends Pengguna {
    // TODO: Implementasi kelas ini sesuai dengan UML Diagram (attribute, method, inheritance, dll)
    // Deklarasi atribut
    private static int jumlahStaf = 0;

    // Constructor
    public Staf(String nama) {
        super(nama);
        jumlahStaf++;
        this.setId(generateId());
    }

    @Override
    public String generateId() {
        return "STAF-" + jumlahStaf;
    }

    @Override
    public String toString() {
        return String.format("""
                               ID Staf: %s
                               Nama Staf: %s""",
                this.getId(), this.getNama());
    }

}
