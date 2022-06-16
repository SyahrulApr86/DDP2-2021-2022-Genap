package assignments.assignment3.pengguna;

import assignments.assignment3.buku.Buku;

public interface CanBorrow {
    // TODO: Implementasi kelas ini sesuai dengan UML Diagram (attribute, method, inheritance, dll)
    // Objek yang mengimplementasikan kelas ini harus memiliki method pinjam dan kembali

    /**
     * Method untuk melakukan hal yang harus dilakukan oleh objek yang mengimplemtasi interface CanBorrow saat melakukan peminjaman buku
     * @param buku Buku yang akan dipinjam
     * @param tanggalPeminjaman Tanggal peminjaman buku
     * @return Kalimat konfirmasi peminjaman buku
     */
    String pinjam(Buku buku, String tanggalPeminjaman);
    String kembali(Buku buku, String tanggalPengembalian);
}
