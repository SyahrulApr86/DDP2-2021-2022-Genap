public class Mahasiswa {
    // Deklarasi atribut
    private static int MINIMUM_TINGKAT_KESEHATAN = 70;
    private String nama;
    private int tingkatKesehatan;

    // Konstruktor
    public Mahasiswa(String nama, int tingkatKesehatan) {
        this.nama = nama;
        this.tingkatKesehatan = tingkatKesehatan;
    }

    // Getter
    public String getNama() {
        return nama;
    }

    // Pengecekan status kelayakan
    public String getStatus() {
        // TODO: Implementasi method untuk mencetak status tingkat kesehatan mahasiswa
        if (this.tingkatKesehatan >= MINIMUM_TINGKAT_KESEHATAN) {
            return "Layak mengikuti program";
        }
        return "Tidak layak mengikuti program";
    }

    // Untuk mencetak sebagai output di file
    @Override
    public String toString() {
        return String.format("%-24s| %s\n", this.nama, this.getStatus());
    }
}
