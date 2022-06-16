package assignments.assignment2;

// TODO
public class Member {
    // Deklarasi properties
    private String id;
    private String name;
    private String dateOfBirth;
    private String studyProgram;
    private String angkatan;
    private long fine;
    private int point;
    private BookLoan[] bookLoans;
    private long fineLastBook;

    // Constructor
    public Member(String id, String name, String dateOfBirth, String studyProgram, String angkatan) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.studyProgram = studyProgram;
        this.angkatan = angkatan;
        this.fine = 0;
        this.point = 0;
        this.bookLoans = new BookLoan[1];
    }

    // Getters
    public int getPoint() {
        return point;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBookLoanCount() {
        int count = 0;
        for (BookLoan bookLoan : bookLoans) {
            if (bookLoan != null) {
                if (bookLoan.getStatus()) {
                    count++;
                }
            }
        }
        return count;
    }

    public BookLoan[] getBookLoans() {
        return bookLoans;
    }

    public long getFine() {
        return fine;
    }

    /**
     * Method untuk meminjam buku
     * @param book buku yang akan dipinjam
     * @param loanDate tanggal peminjaman
     */
    public void pinjam(Book book, String loanDate) {
        // TODO: method untuk melakukan peminjaman oleh member
        // Membuat temporary array untuk menampung buku yang dipinjam
        BookLoan[] temp = new BookLoan[bookLoans.length + 1];
        System.arraycopy(bookLoans, 0, temp, 0, bookLoans.length);
        temp[bookLoans.length-1] = new BookLoan(this, book, loanDate);
        bookLoans = temp;

        // Mengupdate stok buku
        book.setStok(book.getStok() - 1);
    }

    /**
     * Method untuk mengembalikan buku
     * @param book buku yang aka dikembalikan
     * @param returnDate tanggal pengembalian
     */
    public void kembali(Book book, String returnDate) {
        // TODO: method untuk melakukan pengembalian oleh member
        // mengupdate stok buku dan menambah point member
        book.setStok(book.getStok() + 1);
        this.point += book.getPoint();

        // Mengupdate status peminjaman
        for (BookLoan bookLoan : bookLoans) {
            if (bookLoan != null) {
                if (bookLoan.getBook().equals(book) && bookLoan.getStatus()) {
                    bookLoan.pengembalian(returnDate);
                    this.fine += bookLoan.getFine();
                    this.fineLastBook = bookLoan.getFine();
                    break;
                }
            }
        }

    }

    public long getFineLastBook() {
        return fineLastBook;
    }

    public void detail() {
        // TODO: method untuk menampilkan detail anggota
        // Menampilkan detail anggota
        System.out.printf("ID Anggota: %s\n", this.getId());
        System.out.printf("Nama Anggota: %s\n", this.getName());
        System.out.printf("Total Point: %d\n", this.getPoint());
        System.out.printf("Denda: %d\n", this.getFine());
    }

    // Method untuk mengurangi denda sesuai dengan pembayaran
    public void bayarDenda(long amount) {
        // TODO: method untuk melakukan pembayaran denda
        this.fine -= amount;
    }

    // Method untuk menghitung banyak peminjaman
    public int getTotalRiwayat() {
        int count = 0;
        for (BookLoan bookLoan : bookLoans) {
            if (bookLoan != null) {
                count++;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
