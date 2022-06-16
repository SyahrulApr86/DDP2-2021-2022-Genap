package assignments.assignment3.buku;

import assignments.assignment3.pengguna.CanBorrow;

public class Buku {
    // TODO: Implementasi kelas ini sesuai dengan UML Diagram (attribute, method, inheritance, dll)
    // Deklarasi atribut
    private String judul;
    private String penulis;
    private String penerbit;
    private int stokAwal;
    private int stok;
    private Kategori kategori;
    private CanBorrow[] daftarPeminjam;

    // Constructor
    public Buku(String judul, String penulis, String penerbit, int stokAwal, Kategori kategori) {
        this.judul = judul;
        this.penulis = penulis;
        this.penerbit = penerbit;
        this.stokAwal = stokAwal;
        this.stok = stokAwal;
        this.kategori = kategori;
    }

    // Getter
    public String getJudul() {
        return judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public int getStokAwal() {
        return stokAwal;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public CanBorrow[] getDaftarPeminjam() {
        return daftarPeminjam;
    }

    public int getPoin() {
        return this.getKategori().getPoin();
    }

    /**
     * Method untuk menambahkan peminjam ke array daftarPeminjam yang dimiliki buku
     * @param peminjam objek yang mengimplementasi interface CanBorrow dan akan ditambahkan ke daftarPeminjam
     */
    public void addPeminjam(CanBorrow peminjam) {
        // Jika array daftarPeminjam belum dibuat, maka buat array baru
        if (this.daftarPeminjam == null) {
            this.daftarPeminjam = new CanBorrow[1];
            this.daftarPeminjam[0] = peminjam;
        } else { // Jika array daftarPeminjam sudah dibuat, maka tambahkan peminjam ke array
            CanBorrow[] temp = new CanBorrow[this.daftarPeminjam.length + 1];
            System.arraycopy(this.daftarPeminjam, 0, temp, 0, this.daftarPeminjam.length);
            temp[temp.length - 1] = peminjam;
            this.daftarPeminjam = temp;
        }
    }

    @Override
    public String toString() {
        return String.format("""
                            Judul Buku: %s
                            Penulis Buku: %s
                            Penerbit Buku: %s
                            %s""",
                this.getJudul(), this.getPenulis(), this.getPenerbit(), this.kategori);
    }

}
