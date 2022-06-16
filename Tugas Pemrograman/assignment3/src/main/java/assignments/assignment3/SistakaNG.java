package assignments.assignment3;

import assignments.assignment3.buku.*;
import assignments.assignment3.pengguna.*;

import java.util.Scanner;

public class SistakaNG {
    private static Scanner input = new Scanner(System.in);

    // Deklarasi variabel
    public static Staf[] daftarStaf;
    public static Anggota[] daftarAnggota;
    public static Buku[] daftarBuku;
    public static Kategori[] daftarKategori;
    public static Pengguna penggunaLoggedIn;

    // Main program
    public static void main(String[] args) {
        System.out.println("Start - Register Staf...");
        registerStaf();
        System.out.println("Done - Register Staf...\n");
        menu();
        input.close();
    }

    // Method ini digunakan untuk mendaftarkan staf-staf ketika program dijalankan
    private static void registerStaf() {
        String[] listNama = new String[]{"Dek Depe", "Dek DePram", "Dek Sofita", "Winter", "Boo"};

        for (String s : listNama) {
            // TODO: Buat objek Staf menggunakan listNama[i]
            Staf staf = new Staf(s);
            addDaftarStaf(staf);
            // TODO: Setelah objek Staf behasil dibuat, uncomment 2 baris kode di bawah ini
            System.out.println("Berhasil menambahkan staf dengan data:");
            System.out.println(staf);
        }
    }

    // Method ini digunakan untuk mencetak menu utama dari SistakaNG
    public static void menu() {
        boolean hasChosenExit = false;
        System.out.println("Selamat Datang di Sistem Perpustakaan SistakaNG!");
        while (!hasChosenExit) {
            int command = 0;
            System.out.println("================ Menu Utama ================\n");
            System.out.println("1. Login");
            System.out.println("2. Keluar");
            System.out.print("Masukkan pilihan menu: ");
            command = Integer.parseInt(input.nextLine());
            System.out.println();
            if (command == 1) {
                menuLogin();
            } else if (command == 2) {
                System.out.println("Terima kasih telah menggunakan SistakaNG. Sampai jumpa di lain kesempatan!");
                hasChosenExit = true;
            } else {
                System.out.println("Menu tidak dikenal!");
            }
            System.out.println();
        }
    }

    // Method ini digunakan untuk mencetak menu login
    public static void menuLogin() {
        boolean isLoginSuccess = false;
        while (!isLoginSuccess) {
            System.out.println("Masukkan ID Anda untuk login ke sistem");
            System.out.print("ID: ");
            String id = input.nextLine();

            // TODO: Implementasi login -> jika login berhasil, ubah nilai isLoginSuccess menjadi true
            penggunaLoggedIn = getPenggunaTerdaftar(id);
            if (penggunaLoggedIn != null){
                isLoginSuccess = true;
                System.out.printf("Halo, %s! Selamat datang di SistakaNG\n", penggunaLoggedIn.getNama());
            } else System.out.printf("Pengguna dengan ID %s tidak ditemukan\n", id);
        }

        showMenu();
    }

    // Method ini digunakan untuk mencetak menu yang dapat diakses berdasarkan jenis penggunaLoggedIn
    private static void showMenu() {
        if (penggunaLoggedIn instanceof Staf) {
            showMenuStaf();
        } else {
            showMenuAnggota();
        }
    }

    // Method ini digunakan untuk mencetak menu staf dari SistakaNG
    private static void showMenuStaf() {
        int command = 0;
        boolean hasChosenExit = false;
        while (!hasChosenExit) {
            System.out.println("================ Menu Staf ================\n");
            System.out.println("1. Tambah Anggota");
            System.out.println("2. Tambah Kategori");
            System.out.println("3. Tambah Buku");
            System.out.println("4. Hapus Buku");
            System.out.println("5. 3 Peringkat Pertama");
            System.out.println("6. Detail Anggota");
            System.out.println("7. Daftar Peminjam Buku");
            System.out.println("99. Logout");
            System.out.print("Masukkan pilihan menu: ");
            command = Integer.parseInt(input.nextLine());
            System.out.println();
            if (command == 1) {
                // TODO: Implementasikan menu-nya
                tambahAnggota();
            } else if (command == 2) {
                // TODO: Implementasikan menu-nya
                tambahKategori();
            } else if (command == 3) {
                // TODO: Implementasikan menu-nya
                tambahBuku();
            } else if (command == 4) {
                // TODO: Implementasikan menu-nya
                hapusBuku();
            } else if (command == 5) {
                // TODO: Implementasikan menu-nya
                peringkatAnggota();
            } else if (command == 6) {
                // TODO: Implementasikan menu-nya
                cekDetailAnggota();
            } else if (command == 7) {
                // TODO: Implementasikan menu-nya
                lihatDaftarPeminjamBuku();
            } else if (command == 99) {
                System.out.println("Terima kasih telah menggunakan SistakaNG!");
                hasChosenExit = true;
            } else {
                System.out.println("Menu tidak dikenal!");
            }
            System.out.println();
        }
    }

    // Method ini digunakan untuk mencetak menu anggota dari SistakaNG
    private static void showMenuAnggota() {
        int command = 0;
        boolean hasChosenExit = false;
        while (!hasChosenExit) {
            System.out.println("================ Menu Anggota ================\n");
            System.out.println("1. Peminjaman");
            System.out.println("2. Pengembalian");
            System.out.println("3. Pembayaran Denda");
            System.out.println("4. Detail Anggota");
            System.out.println("99. Logout");
            System.out.print("Masukkan pilihan menu: ");
            command = Integer.parseInt(input.nextLine());
            System.out.println();
            if (command == 1) {
                // TODO: Implementasikan menu-nya
                peminjamanBuku();
            } else if (command == 2) {
                // TODO: Implementasikan menu-nya
                pengembalianBuku();
            } else if (command == 3) {
                // TODO: Implementasikan menu-nya
                bayarDendaAnggota();
            } else if (command == 4) {
                // TODO: Implementasikan menu-nya
                detailAnggota();
            } else if (command == 99) {
                System.out.println("Terima kasih telah menggunakan SistakaNG!");
                hasChosenExit = true;
            } else {
                System.out.println("Menu tidak dikenal!");
            }
            System.out.println();
        }
    }

    // Method untuk menambahkan staf baru ke dalam dafar staf
    private static void addDaftarStaf(Staf staf) {
        if (daftarStaf == null) {
            daftarStaf = new Staf[1];
            daftarStaf[0] = staf;
        } else {
            Staf[] temp = new Staf[daftarStaf.length + 1];
            System.arraycopy(daftarStaf, 0, temp, 0, daftarStaf.length);
            temp[temp.length - 1] = staf;
            daftarStaf = temp;
        }
    }

    // Method untuk menambahkan anggota baru ke dalam daftar anggota
    private static void addDaftarAnggota(Anggota anggota) {
        if (daftarAnggota == null) {
            daftarAnggota = new Anggota[1];
            daftarAnggota[0] = anggota;
        } else {
            Anggota[] temp = new Anggota[daftarAnggota.length + 1];
            System.arraycopy(daftarAnggota, 0, temp, 0, daftarAnggota.length);
            temp[temp.length - 1] = anggota;
            daftarAnggota = temp;
        }
    }

    // Method untuk menambahkan kategori baru ke dalam daftar kategori
    public static void addDaftarKategori(Kategori kategori) {
        if (daftarKategori == null) {
            daftarKategori = new Kategori[1];
            daftarKategori[0] = kategori;
        } else {
            Kategori[] temp = new Kategori[daftarKategori.length + 1];
            System.arraycopy(daftarKategori, 0, temp, 0, daftarKategori.length);
            temp[temp.length - 1] = kategori;
            daftarKategori = temp;
        }
    }

    // Method untuk menambahkan buku baru ke dalam daftar buku
    public static void addDaftarBuku(Buku buku) {
        if (daftarBuku == null) {
            daftarBuku = new Buku[1];
            daftarBuku[0] = buku;
        } else {
            Buku[] temp = new Buku[daftarBuku.length + 1];
            System.arraycopy(daftarBuku, 0, temp, 0, daftarBuku.length);
            temp[temp.length - 1] = buku;
            daftarBuku = temp;
        }
    }

    // Method untuk menghapus buku yang ada di daftar buku
    public static void removeFromDaftarBuku(Buku buku) {
        if (daftarBuku == null) {
            return;
        } else {
            Buku[] temp = new Buku[daftarBuku.length - 1];
            int index = 0;
            for (Buku value : daftarBuku) {
                if (!value.getJudul().equalsIgnoreCase(buku.getJudul()) &&
                        !value.getPenulis().equalsIgnoreCase(buku.getPenulis())) {
                    temp[index] = value;
                    index++;
                }
            }
            daftarBuku = temp;
        }
    }

    // Method untuk mengurutkan anggota berdasarkan poin dan nama
    public static Anggota[] urutkanAnggota() {

        Anggota[] newAnggota = new Anggota[daftarAnggota.length];
        System.arraycopy(daftarAnggota, 0, newAnggota, 0, daftarAnggota.length);

        // bubble sort
        for (int i = 0; i < newAnggota.length - 1; i++) {
            for (int j = 0; j < newAnggota.length - i - 1; j++) {
                if (newAnggota[j] != null && newAnggota[j + 1] != null) {
                    if (newAnggota[j].getPoin() < newAnggota[j + 1].getPoin()) {
                        Anggota temp = newAnggota[j];
                        newAnggota[j] = newAnggota[j + 1];
                        newAnggota[j + 1] = temp;
                    } else if (newAnggota[j].getPoin() == newAnggota[j + 1].getPoin()) {
                        if (newAnggota[j].compareTo(newAnggota[j + 1]) >= 0) {
                            Anggota temp = newAnggota[j];
                            newAnggota[j] = newAnggota[j + 1];
                            newAnggota[j + 1] = temp;
                        }
                    }
                }
            }
        }
        return newAnggota;
    }

    // Method untuk mengecek apakah buku sedang dipinjam oleh anggota
    public static boolean isBukuSedangDipinjam(Buku buku){
        return buku.getStok() != buku.getStokAwal();
    }

    // Method untuk mencari pengguna berdasarkan ID
    public static Pengguna getPenggunaTerdaftar(String id){
        if (daftarStaf != null) {
            for (Staf staf : daftarStaf) {
                if (staf.getId().equals(id)) {
                    return staf;
                }
            }
        }

        if (daftarAnggota != null) {
            for (Anggota anggota : daftarAnggota) {
                if (anggota.getId().equals(id)) {
                    return anggota;
                }
            }
        }
        return null;
    }

    // Method untuk mencetak menu tambah anggota
    public static void tambahAnggota(){
        System.out.println("---------- Tambah Anggota ----------");
        System.out.print("Tipe Anggota: ");
        String tipe = input.nextLine();

        // Mengecek tipe anggota
        if (!tipe.equals("Mahasiswa") && !tipe.equals("Dosen")) {
            System.out.println("Tipe Anggota mahasiswa tidak valid!");
            return;
        } else if (tipe.equals("Mahasiswa")) {
            System.out.print("Nama: ");
            String nama = input.nextLine();
            System.out.print("Program Studi (SIK/SSI/MIK/MTI/DIK): ");
            String programStudi = input.nextLine();
            System.out.print("Angkatan: ");
            String angkatan = input.nextLine();
            System.out.print("Tanggal Lahir (dd/mm/yyyy): ");
            String tanggalLahir = input.nextLine();

            Mahasiswa mahasiswa = new Mahasiswa(nama, tanggalLahir, programStudi, angkatan );

            // Mengecek kevalidan id mahasiswa
            if (mahasiswa.getId() == null) {
                System.out.println("Tidak dapat menambahkan anggota silahkan periksa kembali input anda!");
                mahasiswa = null;
                return;
            } else {
                addDaftarAnggota(mahasiswa);
                System.out.println("Berhasil menambahkan mahasiswa dengan data:");
                System.out.println(mahasiswa);
                return;
            }
        } else if (tipe.equals("Dosen")) {
            System.out.print("Nama: ");
            String nama = input.nextLine();
            Dosen dosen = new Dosen(nama);
            addDaftarAnggota(dosen);
            System.out.println("Berhasil menambahkan dosen dengan data:");
            System.out.println(dosen);
            return;
        }
    }

    // Method untuk mencari buku berdasarkan judul dan penulis
    public static Buku cariBuku(String title, String author) {
        if (daftarBuku == null) {
            return null;
        }

        for (Buku buku : daftarBuku) {
            if (buku != null &&
                    buku.getJudul().equalsIgnoreCase(title) &&
                    buku.getPenulis().equalsIgnoreCase(author)) {
                return buku;
            }
        }
        return null;
    }

    // Method untuk mencari Anggota berdasarkan ID
    public static Anggota cariAnggota(String id) {
        if (daftarAnggota == null) {
            return null;
        }

        for (Anggota anggota : daftarAnggota) {
            if (anggota != null && anggota.getId().equals(id)) {
                return anggota;
            }
        }
        return null;
    }

    // Method untuk mencari kategori berdasarkan nama
    public static Kategori cariKategori(String nama) {
        if (daftarKategori == null) {
            return null;
        }

        for (Kategori kategori : daftarKategori) {
            if (kategori != null && kategori.getNama().equalsIgnoreCase(nama)) {
                return kategori;
            }
        }
        return null;
    }

    // Method untuk mencetak menu tambah kategori
    public static void tambahKategori(){
        System.out.println("---------- Tambah Kategori ----------");
        System.out.print("Nama Kategori: ");
        String name = input.nextLine();
        System.out.print("Poin: ");
        int poin = Integer.parseInt(input.nextLine());

        if (daftarKategori != null) {
            for (Kategori kategori : daftarKategori) {
                if (kategori != null && kategori.getNama().equalsIgnoreCase(name)) {
                    System.out.printf("Kategori %s sudah pernah ditambahkan\n", kategori.getNama());
                    return;
                }
            }
        }

        Kategori kategori = new Kategori(name, poin);
        addDaftarKategori(kategori);
        System.out.printf("Kategori %s dengan poin %d berhasil ditambahkan\n", name, poin);
        return;
    }

    // Method untuk mencetak menu tambah buku
    public static void tambahBuku(){
        System.out.println("---------- Tambah Buku ----------");
        System.out.print("Judul: ");
        String title = input.nextLine();
        System.out.print("Penulis: ");
        String author = input.nextLine();
        System.out.print("Penerbit: ");
        String publisher = input.nextLine();
        System.out.print("Kategori: ");
        String category = input.nextLine();
        System.out.print("Stok: ");
        int stock = Integer.parseInt(input.nextLine());

        Kategori kategori = cariKategori(category);
        if (kategori == null) {
            System.out.printf("Kategori %s tidak ditemukan\n", category);
            return;
        }

        if (stock <= 0) {
            System.out.println("Stok harus lebih dari 0");
            return;
        }

        Buku buku = cariBuku(title, author);
        if (buku != null) {
            System.out.printf("Buku %s oleh %s sudah pernah ditambahkan\n", buku.getJudul(), buku.getPenulis());
            return;
        } else {
            buku = new Buku(title, author, publisher, stock, kategori);
            addDaftarBuku(buku);
            System.out.printf("Buku %s oleh %s berhasil ditambahkan\n", title, author);
            return;
        }
    }

    // Method untuk mencetak menu hapus buku
    public static void hapusBuku(){
        System.out.println("---------- Hapus Buku ----------");
        System.out.print("Judul: ");
        String title = input.nextLine();
        System.out.print("Penulis: ");
        String author = input.nextLine();

        Buku buku = cariBuku(title, author);
        if (buku == null) {
            System.out.printf("Buku %s oleh %s tidak ditemukan\n", title, author);
            return;
        } else {
            if (isBukuSedangDipinjam(buku)) {
                System.out.printf("Buku %s oleh %s tidak dapat dihapus karena sedang dipinjam\n", buku.getJudul(), buku.getPenulis());
                return;
            } else {
                removeFromDaftarBuku(buku);
                System.out.printf("Buku %s oleh %s berhasil dihapus\n", buku.getJudul(), buku.getPenulis());
            }
        }
    }

    // Method untuk mencetak menu peringkat anggota
    public static void peringkatAnggota(){
        System.out.println("---------- Peringkat Anggota ----------");
        if (daftarAnggota == null) {
            System.out.println("Belum ada anggota yang terdaftar pada sistem");
            return;
        }

        Anggota[] daftarAnggotaSorted = urutkanAnggota();
        for (int i = 0; i < daftarAnggotaSorted.length; i++) {
            System.out.printf("----------------- %d -----------------\n", i + 1);
            System.out.println(daftarAnggotaSorted[i]);

            // hanya tampilkan 3 anggota teratas
            if (i == 2) {
                break;
            }
        }
    }

    // Method untuk mencetak menu detail anggota
    public static void cekDetailAnggota(){
        System.out.println("---------- Detail Anggota ----------");
        System.out.print("ID Anggota: ");
        String memberId = input.nextLine();

        Anggota anggota = cariAnggota(memberId);
        if (anggota == null) {
            System.out.printf("Anggota dengan ID %s tidak ditemukan\n", memberId);
            return;
        } else {
            anggota.detail();
            return;
        }
    }

    // Method untuk mencetak menu daftar peminjaman buku
    public static void lihatDaftarPeminjamBuku(){
        System.out.println("---------- Daftar Peminjam Buku ----------");
        System.out.print("Judul: ");
        String title = input.nextLine();
        System.out.print("Penulis: ");
        String author = input.nextLine();

        Buku buku = cariBuku(title, author);
        if (buku == null) {
            System.out.printf("Buku %s oleh %s tidak ditemukan\n", title, author);
            return;
        }

        if (buku.getDaftarPeminjam() == null  || buku.getDaftarPeminjam().length == 0) {
            System.out.println(buku);
            System.out.println("---------- Daftar Peminjam ----------");
            System.out.printf("Belum ada anggota yang meminjam buku %s\n", buku.getJudul());
            return;
        }

        System.out.println(buku);
        System.out.println("---------- Daftar Peminjam ----------");
        for (int i = 0; i < buku.getDaftarPeminjam().length; i++) {
            System.out.printf("----------------- %s -----------------\n", i + 1);
            System.out.println(buku.getDaftarPeminjam()[i]);
        }
    }

    // Method untuk mencetak menu peminjaman buku
    public static void peminjamanBuku(){
        System.out.println("---------- Peminjaman Buku ----------");
        System.out.print("Judul Buku: ");
        String title = input.nextLine();
        System.out.print("Penulis Buku: ");
        String author = input.nextLine();
        System.out.print("Tanggal Peminjaman: ");
        String borrowDate = input.nextLine();

        Buku buku = cariBuku(title, author);
        if (buku == null) {
            System.out.printf("Buku %s oleh %s tidak ditemukan\n", title, author);
            return;
        }

        if (buku.getStok() <= 0) {
            System.out.printf("Buku %s oleh %s tidak tersedia\n", title, author);
            return;
        }

        Anggota penggunaAnggota = (Anggota) penggunaLoggedIn;
        String statusPeminjaman = penggunaAnggota.pinjam(buku, borrowDate);
        System.out.println(statusPeminjaman);
    }

    // Method untuk mencetak menu pengembalian buku
    public static void pengembalianBuku(){
        System.out.println("---------- Pengembalian Buku ----------");
        System.out.print("Judul Buku: ");
        String title = input.nextLine();
        System.out.print("Penulis Buku: ");
        String author = input.nextLine();
        System.out.print("Tanggal Pengembalian: ");
        String returnDate = input.nextLine();

        Buku buku = cariBuku(title, author);
        if (buku == null) {
            System.out.printf("Buku %s oleh %s tidak ditemukan\n", title, author);
            return;
        }

        Anggota penggunaAnggota = (Anggota) penggunaLoggedIn;
        String statusPengembalian = penggunaAnggota.kembali(buku, returnDate);
        System.out.println(statusPengembalian);
    }

    // Method untuk mencetak menu pembayaran dengan
    public static void bayarDendaAnggota(){
        System.out.println("---------- Pembayaran Denda ----------");
        System.out.print("Jumlah: ");
        long pay = Long.parseLong(input.nextLine());

        Anggota penggunaAnggota = (Anggota) penggunaLoggedIn;
        String statusPembayaran = penggunaAnggota.bayarDenda(pay);
        System.out.println(statusPembayaran);
    }

    // Method untuk mencetak menu detail anggota
    public static void detailAnggota(){
        Anggota penggunaAnggota = (Anggota) penggunaLoggedIn;
        penggunaAnggota.detail();
    }

}
