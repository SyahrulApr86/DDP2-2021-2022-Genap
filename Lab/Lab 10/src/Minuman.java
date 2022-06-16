public class Minuman extends Pesanan {
    // Inisialisasi atribut
    private boolean isPakeEs;

    // Constructor
    public Minuman(String nama, int harga, int prioritas, boolean isPakeEs) {
        super(nama, harga, prioritas);
        this.isPakeEs = isPakeEs;
    }

    @Override
    public String toString() {
        // return deskripsi sesuai dengan ketentuan soal
        if (isPakeEs) return getName() + " dingin";
        else return getName() + " hangat";
    }

}
