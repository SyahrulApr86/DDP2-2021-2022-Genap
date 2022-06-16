package assignments.assignment3.pengguna;

import assignments.assignment3.buku.Buku;
import assignments.assignment3.buku.Peminjaman;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Mahasiswa extends Anggota{
    // TODO: Implementasi kelas ini sesuai dengan UML Diagram (attribute, method, inheritance, dll)
    public static final int BATAS_JUMLAH_PEMINJAMAN_BUKU = 3;
    public static final long BATAS_MAKSIMAL_DENDA = 5000;
    private String tanggalLahir;
    private String programStudi;
    private String angkatan;

    public Mahasiswa(String nama, String tanggalLahir, String programStudi, String angkatan) {
        super(nama);
        this.tanggalLahir = tanggalLahir;
        this.programStudi = programStudi;
        this.angkatan = angkatan;
        this.setId(generateId());
    }

    @Override
    protected String generateId() {
        // Variabel untuk menyimpan id
        String id = "";

        // Handle program studi
        if (!isValidProgramStudi(this.programStudi)){
            return null;
        }

        // Handle angkatan
        if (!isNumerik(this.angkatan)) {
            return null;
        }

        if (this.angkatan.length() != 4 || !(Integer.parseInt(this.angkatan) >= 2000 && Integer.parseInt(this.angkatan) <= 2021)){
            return null;
        }

        // Handle jika tanggal lahir tidak sesuai format
        if (!validateJavaDate(this.tanggalLahir)) {
            return null;
        }


        // Menambahkan program studi dan 2 digit terakhir angkatan ke ID
        id += programStudi;
        id += angkatan.substring(angkatan.length() - 2);

        // Menghilangkan tanda "/" pada tanggal lahir
        tanggalLahir = tanggalLahir.replace("/", "");
        tanggalLahir = tanggalLahir.substring(0, 4) + tanggalLahir.substring(6);

        id += tanggalLahir; // Menambahkan tanggal lahir ke ID
        id += createChecksum(id); // Menambahkan Checksum C ke ID
        id += createChecksum(id); // Menambahkan Checksum K ke ID

        return id;
    }

    @Override
    public String pinjam(Buku buku, String tanggalPeminjaman) {
        if (this.getPeminjamanAktif() >= BATAS_JUMLAH_PEMINJAMAN_BUKU) return "Jumlah buku yang sedang dipinjam sudah mencapai batas maksimal";
        if (this.getDenda() >= BATAS_MAKSIMAL_DENDA) return "Denda lebih dari Rp5000";
        if (isDiPinjam(buku)) return "Buku %s oleh %s sedang dipinjam".formatted(buku.getJudul(), buku.getPenulis());

        Peminjaman peminjamanBaru = new Peminjaman(this, buku, tanggalPeminjaman);
        this.addDaftarPeminjaman(peminjamanBaru);
        buku.setStok(buku.getStok() - 1);
        buku.addPeminjam(this);
        return "%s berhasil meminjam Buku %s!".formatted(this.getNama() , buku.getJudul());
    }

    /**
     * Method untuk mengecek apakah program studi yang dimasukkan adalah salah satu dari program studi yang ada
     * @param programStudi Program studi mahasiswa
     * @return True jika program studi yang dimasukkan adalah salah satu dari program studi yang ada
     */
    private boolean isValidProgramStudi(String programStudi) {
        if (programStudi.length() != 3) return false;
        if (programStudi.equals("SIK")) return true;
        if (programStudi.equals("SSI")) return true;
        if (programStudi.equals("MIK")) return true;
        if (programStudi.equals("MTI")) return true;
        if (programStudi.equals("DIK")) return true;

        return false;
    }

    /**
     * Method untuk mengecek apakah sebuah String merupakan angka
     * @param strNum String yang akan dicek
     * @return True jika string merupakan angka
     */
    public boolean isNumerik(String strNum) {
        if (strNum == null || strNum.isEmpty()) return false; // Jika null atau kosong

        // Merubah string menjadi double dan melempar exception jika error
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Method untuk mengecek apakah tanggal yang dimasukkan adalah tanggal yang valid
     * @param strDate Tanggal yang akan dicek
     * @return True jika tanggal yang dimasukkan adalah tanggal yang valid
     */
    public boolean validateJavaDate(String strDate) {

        if (strDate.trim().equals("")) {
            return false;
        } else if (strDate.length() != 10) {
            return false;
        } else if (strDate.charAt(2) != '/') {
            return false;
        } else if (strDate.charAt(5) != '/') {
            return false;
        } else if (!isNumerik(strDate.substring(0, 2))) {
            return false;
        } else if (!isNumerik(strDate.substring(3, 5))) {
            return false;
        } else if (!isNumerik(strDate.substring(6, 10))) {
            return false;
        } else if (Integer.parseInt(strDate.substring(0, 2)) > 31) {
            return false;
        } else if (Integer.parseInt(strDate.substring(3, 5)) > 12) {
            return false;
        } else {
            // Memilih format tanggal yang akan digunakan
            SimpleDateFormat sdfrmt = new SimpleDateFormat("dd/MM/yyyy");
            sdfrmt.setLenient(false);
            // Membuat objek date sesuai dengan format
            try {
                Date javaDate = sdfrmt.parse(strDate);
            } catch (ParseException e) {
                return false;
            }
            return true;
        }
    }

    /**
     * Method untuk membuat karakter check digit dari id mahasiswa
     * @param id ID mahasiswa
     * @return Karakter check digit
     */
    public char createChecksum(String id) {
        // Membuat variabel penampung checksum
        char checksumC;
        int checksumNumC = 0;

        // Menghitung value tiap karakter dan menjumlahkannya
        for (int i = 0; i < id.length(); i++) {
            int val = 0;

            // Mengubah karakter menjadi integer sesuai dengan aturan code 93
            if (isNumerik(Character.toString(id.charAt(i)))) {
                val = (id.charAt(i) - '0');
            } else if (Character.isUpperCase(id.charAt(i))) {
                val = (id.charAt(i) - 'A' + 10);
            }
            val *= id.length() - i;
            checksumNumC += val;
        }
        checksumNumC %= 36;

        // Mengubah checksum dari integer ke char sesuai aturan Code 93
        if (checksumNumC < 10) {
            checksumC = (char) (checksumNumC + '0');
        } else {
            checksumC = (char) (checksumNumC - 10 + 'A');
        }

        return checksumC;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
