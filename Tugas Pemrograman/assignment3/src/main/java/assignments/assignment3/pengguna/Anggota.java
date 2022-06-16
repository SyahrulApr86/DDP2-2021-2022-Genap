package assignments.assignment3.pengguna;

import assignments.assignment3.buku.Buku;
import assignments.assignment3.buku.Peminjaman;

public abstract class Anggota extends Pengguna implements Comparable<Anggota>, CanBorrow  {
    // TODO: Implementasi kelas ini sesuai dengan UML Diagram (attribute, method, inheritance, dll)
    // Deklarasi atribut
    protected long denda;
    protected int poin;
    protected Peminjaman[] daftarPeminjaman;

    // Constructor
    public Anggota(String nama) {
        super(nama);
    }

    @Override
    public abstract String pinjam(Buku buku, String tanggalPeminjaman);

    // Getter
    public int getPoin() {
        return poin;
    }

    public long getDenda() {
        return denda;
    }

    public Peminjaman[] getDaftarPeminjaman() {
        return daftarPeminjaman;
    }

    /**
     * Method untuk mencari jumlah buku yang masih dipinjam oleh anggota
     * @return jumlah buku yang masih dipinjam oleh anggota
     */
    public long getPeminjamanAktif() {
        if (this.getDaftarPeminjaman() == null) return 0;

        long bukuDipinjam = 0;
        for (Peminjaman p : this.getDaftarPeminjaman()) {
            if (p != null && p.isDipinjam()) {
                bukuDipinjam++;
            }
        }
        return bukuDipinjam;
    }

    /**
     * Method untuk menghitung total peminjaman buku yang dilakukan oleh anggota
     * @return total peminjaman buku yang dilakukan oleh anggota
     */
    public int getTotalPeminjaman() {
        int total = 0;

        // Jika daftar peminjaman belum dibuat maka tidak ada buku yang dipinjam
        if (this.getDaftarPeminjaman() == null) return 0;

        // Menghitung total peminjaman buku yang dilakukan oleh anggota
        for (Peminjaman peminjaman : daftarPeminjaman) {
            if (peminjaman != null) {
                total++;
            }
        }
        return total;
    }

    /**
     * Method untuk mengecek apakah anggota sedang meminjam buku atau tidak
     * @param buku buku yang akan dicek
     * @return true jika sedang meminjam buku, false jika tidak
     */
    public boolean isDiPinjam(Buku buku) {
        // Jika daftar peminjaman belum dibuat maka tidak ada buku yang dipinjam
        if (this.getDaftarPeminjaman() == null) return false;

        // mencari buku yang sedang dipinjam dalam daftar peminjaman anggota
        for (Peminjaman p : this.getDaftarPeminjaman()) {
            if (p != null && p.getBuku().equals(buku) && p.isDipinjam()) return true;
        }
        return false;
    }

    // Setter
    public void setDaftarPeminjaman(Peminjaman[] daftarPeminjaman) {
        this.daftarPeminjaman = daftarPeminjaman;
    }

    public void setDenda(long denda) {
        this.denda = denda;
    }

    /**
     * Method untuk menambahkan peminjaman buku ke daftar peminjaman yang dimiliki anggota
     * @param peminjamanBaru peminjaman buku yang akan ditambahkan ke daftar peminjaman
     */
    public void addDaftarPeminjaman(Peminjaman peminjamanBaru) {
        // Jika daftar peminjaman belum dibuat, buat daftar peminjaman baru
        if (this.getDaftarPeminjaman() == null) {
            this.setDaftarPeminjaman(new Peminjaman[]{peminjamanBaru});
        } else { // Jika daftar peminjaman sudah dibuat, tambahkan peminjaman baru ke daftar peminjaman
            Peminjaman[] temp = new Peminjaman[this.getDaftarPeminjaman().length + 1];
            for (int i = 0; i < this.getDaftarPeminjaman().length; i++) {
                temp[i] = this.getDaftarPeminjaman()[i];
            }
            temp[temp.length - 1] = peminjamanBaru;
            this.setDaftarPeminjaman(temp);
        }
    }

    /**
     * Method untuk mencetak detail dari anggota. Isinya adalah ID, nama, total poin, denda, dan daftar peminjaman
     */
    public void detail() {
        System.out.println(this);
        System.out.println("Riwayat Peminjaman Buku:");
        if (this.getDaftarPeminjaman() == null || this.getTotalPeminjaman() <= 0) { // Jika tidak ada peminjaman buku
            System.out.println("Belum pernah meminjam buku");
        } else {
            int nomor = 1;
            for (Peminjaman peminjaman : daftarPeminjaman) {
                if (peminjaman != null) {
                    System.out.printf("----------------- %d -----------------\n", nomor);
                    System.out.println(peminjaman);
                    nomor++;
                }
            }
        }
    }

    /**
     * Method untuk melakukan apa saja yang harus dilakukan ketika anggota membayar denda
     * @param jumlahBayar jumlah uang yang dibayar untuk membayar denda
     * @return Kalimat konformasi pembayaran denda
     */
    public String bayarDenda(long jumlahBayar) {
        if (this.getDenda() == 0) {
            return String.format("%s tidak memiliki denda", this.getNama());
        } else if (jumlahBayar < this.getDenda()) {
            this.denda -= jumlahBayar;
            return String.format("""
                    %s berhasil membayar denda sebesar Rp%d
                    Sisa denda saat ini: Rp%d""", this.getNama(), jumlahBayar, this.getDenda());
        } else {
            long kembalian = jumlahBayar - this.getDenda();
            this.denda = 0;
            return String.format("""
                            %s berhasil membayar lunas denda
                            Jumlah kembalian: Rp%d""", this.getNama(), kembalian);
        }
    }

    /**
     * Method untuk melakukan apa saja yang harus dilakukan ketika anggota mengembalikan buku
     * @param buku buku yang akan dikembalikan
     * @param tanggalPengembalian tanggal pengembalian buku
     * @return Kalimat konformasi pengembalian buku
     */
    public String kembali(Buku buku, String tanggalPengembalian) {
        // Jika dafar peminjaman belum dibuat
        if (this.getDaftarPeminjaman() == null) {
            return String.format("Buku %s tidak sedang dipinjam oleh %s", buku.getJudul(), this.getNama());
        }

        // pengecekan apakah buku yang dikembalikan ada di daftar peminjaman
        boolean sedangMeminjam = false;
        Peminjaman peminjamanAnggota = null;
        for (Peminjaman peminjaman : daftarPeminjaman) {
            if (peminjaman != null && peminjaman.getBuku().equals(buku) && peminjaman.isDipinjam()) {
                sedangMeminjam = true;
                peminjamanAnggota = peminjaman;
                break;
            }
        }

        if (!sedangMeminjam) {
            return String.format("Buku %s tidak sedang dipinjam oleh %s", buku.getJudul(), this.getNama());
        } else {
            peminjamanAnggota.kembalikanBuku(tanggalPengembalian);
            this.poin += buku.getPoin();
            return String.format("Buku %s berhasil dikembalikan oleh %s dengan denda Rp%d!", buku.getJudul(), this.getNama(), peminjamanAnggota.getDenda());
        }
    }

    @Override
    public int compareTo(Anggota other) {
        return this.getNama().compareTo(other.getNama());
    }

    @Override
    public String toString() {
        return String.format("""
                ID Anggota: %s
                Nama Anggota: %s
                Total Poin: %d
                Denda: Rp%d""",
                this.getId(), this.getNama(), this.getPoin(), this.getDenda());
    }
}
