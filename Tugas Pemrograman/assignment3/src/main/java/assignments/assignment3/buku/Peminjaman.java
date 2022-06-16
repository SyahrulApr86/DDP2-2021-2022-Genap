package assignments.assignment3.buku;

import assignments.assignment3.pengguna.Anggota;
import java.util.Date;
import java.util.Calendar;


public class Peminjaman {
    // TODO: Implementasi kelas ini sesuai dengan UML Diagram (attribute, method, inheritance, dll)
    // Deklarasi atribut
    private static final long DENDA_PER_HARI = 3000;
    private Anggota anggota;
    private Buku buku;
    private String tanggalPeminjaman;
    private String tanggalPengembalian = "-";
    private long denda;
    private boolean dipinjam;

    // Constructor
    public Peminjaman(Anggota anggota, Buku buku, String tanggalPeminjaman){
        this.anggota = anggota;
        this.buku = buku;
        this.tanggalPeminjaman = tanggalPeminjaman;
        this.dipinjam = true;
        this.denda = 0;
    }

    // Getter
    public Buku getBuku() {
        return buku;
    }

    public long getDenda() {
        return denda;
    }

    public boolean isDipinjam() {
        return dipinjam;
    }

    /**
     * Method untuk mengubah status peminjaman dan menghitung denda saat pengembalian buku
     * @param tanggalPengembalian Tanggal pengembalian buku
     */
    public void kembalikanBuku(String tanggalPengembalian) {
        this.tanggalPengembalian = tanggalPengembalian; // set tanggal pengembalian
        this.dipinjam = false; // set status peminjaman
        buku.setStok(buku.getStok() + 1); // menambahkan stok buku
        this.denda = hitungDenda(); // menghitung denda
        anggota.setDenda(anggota.getDenda() + this.denda); // menambahkan denda anggota
    }

    /**
     * Method untuk mengubah string menjadi tipe data Date
     * @param tanggal string tanggal
     * @return tanggal dalam bentuk Date
     */
    public Date buatTanggalDariString(String tanggal) {
        int intHari = Integer.parseInt(tanggal.substring(0, 2));
        int intBulan = Integer.parseInt(tanggal.substring(3, 5));
        int intTahun = Integer.parseInt(tanggal.substring(6));

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, intTahun);
        cal.set(Calendar.MONTH, intBulan - 1);
        cal.set(Calendar.DATE, intHari);

        return cal.getTime();
    }

    /**
     * Method untuk menghitung denda
     * @return denda yang harus dibayar
     */
    public long hitungDenda() {
        Date datePinjam = buatTanggalDariString(this.tanggalPeminjaman);
        Date dateKembali = buatTanggalDariString(this.tanggalPengembalian);

        long bedaHari = bedaHari(datePinjam, dateKembali); // menghitung beda hari

        denda = bedaHari > 7 ? (bedaHari - 7) * DENDA_PER_HARI : 0; // menghitung denda berdasarkan beda hari
        return denda;
    }

    /**
     *  Method untuk menghitung selisih hari antara 2 tanggal
     * @param tanggal1 tanggal pertama
     * @param tanggal2 tanggal kedua, yang lebih besar
     * @return selisih hari antara 2 tanggal
     */
    private long bedaHari(Date tanggal1, Date tanggal2) {
        return (tanggal2.getTime() - tanggal1.getTime()) / (24 * 60 * 60 * 1000);
    }

    @Override
    public String toString() {
        return String.format("""
                            %s
                            Tanggal Peminjaman: %s
                            Tanggal Pengembalian: %s
                            Denda: Rp%s""",
                this.buku, this.tanggalPeminjaman, this.tanggalPengembalian, this.denda);
    }
}
