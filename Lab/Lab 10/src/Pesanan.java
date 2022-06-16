public class Pesanan implements Comparable<Pesanan> {
    // Inisialisasi atribut
    private String name;
    private int harga;
    private int prioritas;

    // Konstruktor
    public Pesanan(String nama, int harga, int prioritas) {
        // TODO: Lengkapi Constructor berikut
        this.name = nama;
        this.harga = harga;
        this.prioritas = prioritas;
    }

    @Override
    public int compareTo(Pesanan o) {
        // TODO: Lengkapi method ini
        return this.prioritas - o.prioritas;
    }

    // Tambahkan getter-setter bila diperlukan
    public String getName() {
        return name;
    }
}