package assignments.assignment3.pengguna;

import assignments.assignment3.buku.Buku;
import assignments.assignment3.buku.Peminjaman;

public class Dosen extends Anggota{
    // TODO: Implementasi kelas ini sesuai dengan UML Diagram (attribute, method, inheritance, dll)
    // Deklarasi atribut
    private static int jumlahDosen = 0;
    public static final int BATAS_JUMLAH_PEMINJAMAN_BUKU = 5;
    public static final long BATAS_MAKSIMAL_DENDA = 10000;

    // Constructor
    public Dosen(String nama) {
        super(nama);
        jumlahDosen++;
        this.setId(generateId());
    }

    @Override
    public String pinjam(Buku buku, String tanggalPeminjaman) {
        // Pengecekan jumlah peminjaman buku
        if (this.getPeminjamanAktif() >= BATAS_JUMLAH_PEMINJAMAN_BUKU) return "Jumlah buku yang sedang dipinjam sudah mencapai batas maksimal";
        // Pengecekan jumlah denda Dosen
        if (this.getDenda() >= BATAS_MAKSIMAL_DENDA) return "Denda lebih dari Rp10000";
        // Pengecekan status peminjaman buku oleh Dosen
        if (isDiPinjam(buku)) return "Buku %s oleh %s sedang dipinjam".formatted(buku.getJudul(), buku.getPenulis());

        Peminjaman peminjamanBaru = new Peminjaman(this, buku, tanggalPeminjaman);
        this.addDaftarPeminjaman(peminjamanBaru);
        buku.setStok(buku.getStok() - 1);
        buku.addPeminjam(this);
        return "%s berhasil meminjam Buku %s!".formatted(this.getNama() , buku.getJudul());
    }

    @Override
    protected String generateId() {
        return "DOSEN-" + jumlahDosen;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
