package assignments.assignment2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Library {
    private Scanner input = new Scanner(System.in);

    private Member[] members = new Member[1];
    private Book[] books = new Book[1];
    private Category[] categories = new Category[1];

    public static void main(String[] args) {
        Library program = new Library();
        program.menu();
    }

    public void menu() {
        int command = 0;
        boolean hasChosenExit = false;
        System.out.println("Selamat Datang di Sistem Perpustakaan SistakaNG!");
        while (!hasChosenExit) {
            System.out.println("================ Menu Utama ================\n");
            System.out.println("1. Tambah Anggota");
            System.out.println("2. Tambah Kategori");
            System.out.println("3. Tambah Buku");
            System.out.println("4. Peminjaman");
            System.out.println("5. Pengembalian");
            System.out.println("6. Pembayaran Denda");
            System.out.println("7. Detail Anggota");
            System.out.println("8. 3 Peringkat Pertama");
            System.out.println("99. Keluar");
            System.out.print("Masukkan pilihan menu: ");
            command = Integer.parseInt(input.nextLine());
            System.out.println();
            if (command == 1) {
                // TODO
                // Print menu tambah anggota
                System.out.println("---------- Tambah Anggota ----------");
                System.out.print("Nama: ");
                String name = input.nextLine();
                System.out.print("Program Studi (SIK/SSI/MIK/MTI/DIK): ");
                String studyProgram = input.nextLine();
                System.out.print("Angkatan: ");
                String year = input.nextLine();
                System.out.print("Tanggal Lahir (dd/mm/yyyy): ");
                String birthDate = input.nextLine();
                // Mengupdate array members
                members = addMember(name, studyProgram, year, birthDate, members);

            } else if (command == 2) {
                // TODO
                // Print menu tambah kategori
                System.out.println("---------- Tambah Kategori ----------");
                System.out.print("Nama Kategori: ");
                String name = input.nextLine();
                System.out.print("Point: ");
                int point = Integer.parseInt(input.nextLine());
                // Mengupdate array categories
                categories = addCategory(name, point, categories);

            } else if (command == 3) {
                // TODO
                // Print menu tambah buku
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
                // Mengupdate array books
                books = addBook(title, author, publisher, category, stock, books);

            } else if (command == 4) {
                // TODO
                // Print menu peminjaman
                System.out.println("---------- Peminjaman Buku ----------");
                System.out.print("ID Anggota: ");
                String memberId = input.nextLine();
                System.out.print("Judul Buku: ");
                String title = input.nextLine();
                System.out.print("Penulis Buku: ");
                String author = input.nextLine();
                System.out.print("Tanggal Peminjaman: ");
                String borrowDate = input.nextLine();

                // Validator
                boolean isBorrowed = false;

                // Validasi apakah member sudah terdaftar
                Member member = null;
                for (Member m : members) {
                    if (m != null) {
                        if (m.getId().equals(memberId)) {
                            member = m;
                            isBorrowed = true;
                            break;
                        }
                    } else {
                        isBorrowed = false;
                        System.out.printf("Anggota dengan ID %s tidak ditemukan\n", memberId);
                        break;
                    }
                }

                // Validasi apakah buku sudah terdaftar
                Book book = null;
                if (isBorrowed) {
                    for (Book b : books) {
                        if (b != null) {
                            if (b.getTitle().equalsIgnoreCase(title) && b.getAuthor().equalsIgnoreCase(author)) {
                                isBorrowed = true;
                                book = b;
                                break;
                            }
                        } else {
                            isBorrowed = false;
                            System.out.printf("Buku %s oleh %s tidak ditemukan\n", title, author);
                            break;
                        }
                    }
                }

                // Validasi apakah stok buku tidak kosong
                if (isBorrowed) {
                    if (book != null){
                        if (book.getStok() > 0) {
                            isBorrowed = true;
                        } else {
                            isBorrowed = false;
                            System.out.printf("Buku %s oleh %s tidak tersedia\n", book.getTitle(), book.getAuthor());
                        }
                    }
                }


                // Validasi apakah buku yang dipinjam kurang dari 3
                if (isBorrowed) {
                    if (member != null){
                        if (member.getBookLoanCount() < 3) {
                            isBorrowed = true;
                        } else {
                            isBorrowed = false;
                            System.out.println("Jumlah buku yang sedang dipinjam sudah mencapai batas maksimal");
                        }
                    }
                }

                // Validasi apakah denda member sudah mencapai batas maksimal
                if (isBorrowed) {
                    if(member != null){
                        if (member.getFine() < 5000) {
                            isBorrowed = true;
                        } else {
                            isBorrowed = false;
                            System.out.println("Denda lebih dari Rp 5000");
                        }
                    }
                }

                // Validasi apakah member sudah meminjam buku
                if (isBorrowed) {
                    if (member != null && book != null) {
                        for (BookLoan bl : member.getBookLoans()) {
                            if (bl != null) {
                                String bookBorrowedTitle = bl.getBook().getTitle();
                                String bookSelectedTitle = book.getTitle();
                                String bookBorrowedAuthor = bl.getBook().getAuthor();
                                String bookSelectedAuthor = book.getAuthor();
                                if (bookBorrowedTitle.equalsIgnoreCase(bookSelectedTitle) && bookBorrowedAuthor.equalsIgnoreCase(bookSelectedAuthor) && bl.getStatus()) {
                                    isBorrowed = false;
                                    System.out.printf("Buku %s oleh %s sedang dipinjam", book.getTitle(), book.getAuthor());
                                    break;
                                }
                            }
                        }
                    }
                }


                // Member berhasil meminjam buku
                if (member != null && book != null) {
                    if (isBorrowed) {
                        System.out.printf("%s berhasil meminjam Buku %s!\n", member.getName(), book.getTitle());
                        member.pinjam(book, borrowDate);
                    }
                }

            } else if (command == 5) {
                // TODO
                // Print menu pengembalian
                System.out.println("---------- Pengembalian Buku ----------");
                System.out.print("ID Anggota: ");
                String memberId = input.nextLine();
                System.out.print("Judul Buku: ");
                String title = input.nextLine();
                System.out.print("Penulis Buku: ");
                String author = input.nextLine();
                System.out.print("Tanggal Pengembalian: ");
                String returnDate = input.nextLine();

                // Validator
                boolean isReturned = false;

                // Validasi apakah member terdaftar
                Member member = null;
                for (Member m : members) {
                    if (m != null) {
                        if (m.getId().equalsIgnoreCase(memberId)) {
                            isReturned = true;
                            member = m;
                            break;
                        }
                    } else {
                        isReturned = false;
                        System.out.printf("Anggota dengan ID %s tidak ditemukan\n", memberId);
                        break;
                    }
                }

                // Validasi apakah buku terdaftar
                Book book = null;
                if (isReturned) {
                    for (Book b : books) {
                        if (b != null) {
                            if (b.getTitle().equalsIgnoreCase(title) && b.getAuthor().equalsIgnoreCase(author)) {
                                isReturned = true;
                                book = b;
                                break;
                            }
                        } else {
                            isReturned = false;
                            System.out.printf("Buku %s oleh %s tidak ditemukan\n", title, author);
                        }
                    }
                }


                // Validasi apakah member sedang meminjam buku
                if (isReturned) {
                    if (member != null && book != null) {
                        for (BookLoan bl : member.getBookLoans()) {
                            if (bl != null) {
                                String bookBorrowedTitle = bl.getBook().getTitle();
                                String bookSelectedTitle = book.getTitle();
                                String bookBorrowedAuthor = bl.getBook().getAuthor();
                                String bookSelectedAuthor = book.getAuthor();
                                if (bookBorrowedTitle.equalsIgnoreCase(bookSelectedTitle) && bookBorrowedAuthor.equalsIgnoreCase(bookSelectedAuthor) && bl.getStatus()) {
                                    isReturned = true;
                                    break;
                                }
                            } else {
                                isReturned = false;
                                System.out.printf("Buku %s tidak sedang dipinjam\n", book.getTitle());
                            }
                        }
                    }
                }

                // Member berhasil mengembalikan buku
                if (member != null && book != null) {
                    if (isReturned) {
                        member.kembali(book, returnDate);
                        System.out.printf("Buku %s berhasil dikembalikan oleh %s dengan denda Rp %d!\n", book.getTitle(), member.getName(), member.getFineLastBook());
                    }
                }

            } else if (command == 6) {
                // TODO
                // Print menu pembayaran denda
                System.out.println("---------- Pembayaran Denda ----------");
                System.out.print("ID Anggota: ");
                String memberId = input.nextLine();
                System.out.print("Jumlah: ");
                long pay = Long.parseLong(input.nextLine());
//                long pay = input.nextLong();

                // Validator
                boolean isPaid = false;

                // Validasi apakah member terdaftar
                Member member = null;
                for (Member m : members) {
                    if (m != null) {
                        if (m.getId().equals(memberId)) {
                            isPaid = true;
                            member = m;
                            break;
                        }
                    } else {
                        isPaid = false;
                        System.out.printf("Anggota dengan ID %s tidak ditemukan\n", memberId);
                    }
                }

                // Validasi apakah member memiliki denda
                if (isPaid) {
                    if (member.getFine() <= 0) {
                        isPaid = false;
                        System.out.printf("%s tidak memiliki denda\n", member.getName());
                    }
                }

                // Berhasil membayar denda
                if (isPaid) {
                    if (pay >= member.getFine()) {
                        System.out.printf("%s berhasil membayar lunas denda\n", member.getName());
                        System.out.printf("Jumlah kembalian: Rp %d\n", pay - member.getFine());
                        member.bayarDenda(member.getFine());
                    } else {
                        member.bayarDenda(pay);
                        System.out.printf("%s berhasil membayar denda sebesar Rp %d\n", member.getName(), pay);
                        System.out.printf("Sisa denda saat ini: Rp %s\n", member.getFine());
                    }
                }

            } else if (command == 7) {
                // TODO
                // Print menu detail
                System.out.println("---------- Detail Anggota ----------");
                System.out.print("ID Anggota: ");
                String memberId = input.nextLine();

                // Validator
                boolean isMember = false;

                // Validasi apakah member terdaftar
                Member member = null;
                for (Member m : members) {
                    if (m != null) {
                        if (m.getId().equals(memberId)) {
                            isMember = true;
                            member = m;
                            break;
                        }
                    } else {
                        isMember = false;
                        System.out.printf("Anggota dengan ID %s tidak ditemukan\n", memberId);
                    }
                }

                // Jika member terdaftar
                if (isMember) {
                    member.detail();
                    System.out.println("Riwayat Peminjaman Buku :");
                    if (member.getTotalRiwayat() > 0) {
                        for (int i = 0; i < member.getTotalRiwayat(); i++) {
                            if (member.getBookLoans()[i] != null) {
                                Book book = member.getBookLoans()[i].getBook();
                                System.out.printf("----------------- %d -----------------\n", i + 1);
                                System.out.println(book);
                                System.out.printf("Tanggal Peminjaman: %s\n", member.getBookLoans()[i].getLoanDate());
                                System.out.printf("Tanggal Pengembalian: %s\n", member.getBookLoans()[i].getReturnDate());
                                System.out.printf("Denda: Rp %d\n", member.getBookLoans()[i].getFine());
                            }
                        }
                    }
                }
            } else if (command == 8) {
                // TODO
                // Print menu peringkat anggota
                System.out.println("---------- Peringkat Anggota ----------");
                int totalMember = 0;
                for (Member m : members) {
                    if (m != null) {
                        totalMember++;
                    }
                }

                // Jika ada anggota
                if (totalMember > 0) {
                    Member[] membersSorted = sortMembers(members);
                    for (int i = 0; i < membersSorted.length; i++) {
                        if (membersSorted[i] != null) {
                            System.out.printf("----------------- %d -----------------\n", i + 1);
                            System.out.printf("ID Anggota: %s\n", membersSorted[i].getId());
                            System.out.printf("Nama Anggota: %s\n", membersSorted[i].getName());
                            System.out.printf("Total Point: %d\n", membersSorted[i].getPoint());
                            System.out.printf("Denda: %d\n", membersSorted[i].getFine());
                        }
                        // only show top 3
                        if (i == 2) {
                            break;
                        }
                    }
                } else {
                    System.out.println("Belum ada anggota yang terdaftar pada sistem");
                }

            } else if (command == 99) {
                System.out.println("Terima kasih telah menggunakan SistakaNG!");
                hasChosenExit = true;
            } else {
                System.out.println("Menu tidak dikenal!");
            }
            System.out.println();
        }

        input.close();
    }

    /**
     * Method untuk membuat array of member dengan tambahan 1 anggota baru
     * @param nama nama anggota baru
     * @param programStudi program studi anggota baru
     * @param angkatan angkatan anggota baru
     * @param tanggalLahir tanggal lahir anggota baru
     * @param members array of member
     * @return array of member dengan tambahan 1 anggota baru
     */
    public static Member[] addMember(String nama, String programStudi, String angkatan, String tanggalLahir, Member[] members) {
        // Buat variable untuk menampung ID keanggotaan dan membuat Array yang berisi daftar prodi
        String id = "";

        // Handle jika program studi tidak ada di daftar prodi
        if (!programStudi.equals("SIK") &&
                !programStudi.equals("SSI") &&
                !programStudi.equals("MIK") &&
                !programStudi.equals("MTI") &&
                !programStudi.equals("DIK")){
            System.out.println("Tidak dapat menambahkan anggota silahkan periksa kembali input anda!");
        }
        // Handle jika angkatan bukan angka
        else if (!isNumeric(angkatan)){
            System.out.println("Tidak dapat menambahkan anggota silahkan periksa kembali input anda!");
        }
        // Handle jika angkatan tidak 4 digit dan tidak berada di rentang 2000 - 2021
        else if (angkatan.length() != 4 || !(Integer.parseInt(angkatan) >= 2000 && Integer.parseInt(angkatan) <= 2021)){
            System.out.println("Tidak dapat menambahkan anggota silahkan periksa kembali input anda!");
        }
        // Handle jika tanggal lahir tidak sesuai format
        else if (!validateJavaDate(tanggalLahir)){
            System.out.println("Tidak dapat menambahkan anggota silahkan periksa kembali input anda!");
        }
        else {
            // Menambahkan program studi dan 2 digit terakhir angkatan ke ID
            id += programStudi;
            id += angkatan.substring(angkatan.length() - 2);

            // Menghilangkan tanda "/" pada tanggal lahir
            tanggalLahir = tanggalLahir.replace("/", "");
            tanggalLahir = tanggalLahir.substring(0, 4) + tanggalLahir.substring(6);

            // Menambahkan tanggal lahir ke ID
            id += tanggalLahir;

            // Menambahkan Checksum C ke ID
            id += createChecksum(id);

            // Menambahkan Checksum K ke ID
            id += createChecksum(id);

            // Menambahkan member baru ke daftar anggota
            Member[] newMembers = new Member[members.length + 1];
            System.arraycopy(members, 0, newMembers, 0, members.length);
            newMembers[newMembers.length - 2] = new Member(id, nama, tanggalLahir, programStudi, angkatan);

            members = newMembers;

            System.out.println("Member " + nama +  " berhasil ditambahkan dengan data:\n" +
                    "ID Anggota: " + id + "\n" +
                    "Nama Anggota: " + nama + "\n" +
                    "Total Point: 0\n" +
                    "Denda: 0");
        }
        return members;
    }

    /**
     * Method untuk mengecek kevalidan format tanggal lahir
     * @param strDate string yang berisi tanggal lahir
     * @return true jika format tanggal lahir benar
     */
    public static boolean validateJavaDate(String strDate) {
        // Handling jika string kosong
        if (strDate.trim().equals("")) {
            return false;
        } else if (strDate.length() != 10) {
            return false;
        } else if (strDate.charAt(2) != '/') {
            return false;
        } else if (strDate.charAt(5) != '/') {
            return false;
        } else if (!isNumeric(strDate.substring(0, 2))) {
            return false;
        } else if (!isNumeric(strDate.substring(3, 5))) {
            return false;
        } else if (!isNumeric(strDate.substring(6, 10))) {
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
     * Method untuk mengecek apakah string yang diberikan merupakan angka
     * @param strNum string yang akan dicek
     * @return true jika string merupakan angka
     */
    public static boolean isNumeric(String strNum) {
        // Jika string null
        if (strNum == null) {
            return false;
        }

        // Merubah string menjadi double dan melempar exception jika error
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Method untuk membuat checksum berdasarkan ID
     * @param id ID yang akan dihitung checksumnya
     * @return checksum dari ID
     */
    public static char createChecksum(String id) {
        // Membuat variabel penampung checksum
        char checksumC;
        int checksumNumC = 0;

        // Menghitung value tiap karakter dan menjumlahkannya
        for (int i = 0; i < id.length(); i++) {
            int val = 0;

            // Mengubah karakter menjadi integer sesuai dengan aturan code 93
            if (isNumeric(Character.toString(id.charAt(i)))) {
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

    /**
     * Method untuk membuat array of category dengan tambahan 1 kategori baru
     * @param name nama kategori baru
     * @param point point kategori baru
     * @param categories array of category yang akan ditambahkan kategori baru
     * @return array of category dengan tambahan kategori baru
     */
    public Category[] addCategory(String name, int point, Category[] categories) {
        for (Category category : categories) {
            if (category != null) {
                // Handling jika kategori sudah terdaftar
                if (name.equalsIgnoreCase(category.getName())) {
                    System.out.printf("Kategori %s sudah pernah ditambahkan\n", category.getName());
                    break;
                }
            } else {
                // Membuat array of category baru
                Category[] newCategories = new Category[categories.length + 1];
                System.arraycopy(categories, 0, newCategories, 0, categories.length);
                newCategories[newCategories.length - 2] = new Category(name, point);

                categories = newCategories;

                System.out.printf("Kategori %s dengan %d point berhasil ditambahkan\n", name, point);
                break;
            }
        }
        return categories;
    }

    /**
     * Method untuk membuat array of book dengan tambahan 1 buku baru
     * @param title judul buku baru
     * @param author nama penulis buku baru
     * @param publisher nama penerbit buku baru
     * @param category kategori buku baru
     * @param stock stock buku baru
     * @param books array of book yang akan ditambahkan buku baru
     * @return array of book dengan tambahan buku baru
     */
    public Book[] addBook(String title, String author, String publisher, String category, int stock, Book[] books) {


        // Handling jika buku sudah pernah ditambahkan
        for (Book book : books) {
            if (book != null) {
                if (title.equalsIgnoreCase(book.getTitle()) && author.equalsIgnoreCase(book.getAuthor())) {
                    System.out.printf("Buku %s oleh %s sudah pernah ditambahkan\n", book.getTitle(), book.getAuthor());
                    return books;
                }
            } else {
                break;
            }
        }

        Category categoryObj = null;
        // Handling jika kategori tidak ditemukan
        for (Category c : categories) {
            if (c != null) {
                if (c.getName().equalsIgnoreCase(category)) {
                    categoryObj = c;
                    break;
                }
            } else {
                System.out.printf("Kategori %s tidak ditemukan\n", category);
                return books;
            }
        }

        // Handling jika stock buku kurang dari 1
        if (stock <= 0) {
            System.out.println("Stok harus lebih dari 0");
            return books;
        } else {
            // Membuat array of book baru
            Book[] newBooks = new Book[books.length + 1];
            System.arraycopy(books, 0, newBooks, 0, books.length);
            newBooks[newBooks.length - 2] = new Book(title, author, publisher, stock, categoryObj);

            books = newBooks;

            System.out.printf("Buku %s oleh %s berhasil ditambahkan \n", title, author);
        }

        return books;
    }

    /**
     * Method untuk mengurutkan array of member berdasarkan point dan nama
     * @param members array of member yang akan diurutkan
     * @return array of member yang sudah diurutkan
     */
    public Member[] sortMembers(Member[] members) {
        // Membuat array of member baru
        Member[] sortedMembers = new Member[members.length];
        // copy array of member ke array of member baru
        Member[] newMembers = new Member[members.length];
        System.arraycopy(members, 0, newMembers, 0, members.length);

        // create bubble sort algorithm
        for (int i = 0; i < newMembers.length - 1; i++) {
            for (int j = 0; j < newMembers.length - i - 1; j++) {
                if (newMembers[j] != null && newMembers[j + 1] != null) {
                    if (newMembers[j].getPoint() < newMembers[j + 1].getPoint()) {
                        Member temp = newMembers[j];
                        newMembers[j] = newMembers[j + 1];
                        newMembers[j + 1] = temp;
                    } else if (newMembers[j].getPoint() == newMembers[j + 1].getPoint()) {
                        if (newMembers[j].getName().compareTo(newMembers[j + 1].getName()) >= 0) {
                            Member temp = newMembers[j];
                            newMembers[j] = newMembers[j + 1];
                            newMembers[j + 1] = temp;
                        }
                    }
                }
            }
        }

        return newMembers;
    }
}
