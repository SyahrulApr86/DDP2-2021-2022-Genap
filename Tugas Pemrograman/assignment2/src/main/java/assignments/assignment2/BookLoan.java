package assignments.assignment2;
import java.util.Calendar;
import java.util.Date;

// TODO
public class BookLoan {
    // Deklarasi properties
    private static final long DENDA_PER_HARI = 3000;
    private Member member;
    private Book book;
    private String loanDate;
    private String returnDate;
    private long fine;
    private boolean status;

    // TODO
    // Constructor
    public BookLoan(Member member, Book book, String loanDate) {
        this.member = member;
        this.book = book;
        this.loanDate = loanDate;
        this.status = true;
        this.fine = 0;
    }

    // Getter dan setter
    public String getLoanDate() {
        return loanDate;
    }

    public String getReturnDate() {
        if (this.status) {
            return "-";
        }
        return returnDate;
    }

    public long getFine() {
        return this.fine;
    }

    public Book getBook() {
        return book;
    }

    public boolean getStatus() {
        return status;
    }

    /**
     *  Method untuk menghitung selisih hari antara 2 tanggal
     * @param date1 tanggal pertama
     * @param date2 tanggal kedua, yang lebih besar
     * @return selisih hari antara 2 tanggal
     */
    public int diffDays(Date date1, Date date2) {
        long diff = date2.getTime() - date1.getTime();
        return (int) (diff / (24 * 60 * 60 * 1000));
    }

    /**
     * Method untuk mengubah status peminjaman dan menghitung denda saat pengembalian buku
     * @param returnDate return date
     */
    public void pengembalian(String returnDate) {
        this.returnDate = returnDate;
        this.status = false;

        // Definisikan objek Date
        // tanggal peminjaman
        Calendar calendar1 = Calendar.getInstance();
        int returnDay = Integer.parseInt(returnDate.substring(0, 2));
        int returnMonth = Integer.parseInt(returnDate.substring(3, 5));
        int returnYear = Integer.parseInt(returnDate.substring(6));
        calendar1.set(Calendar.YEAR, returnYear);
        calendar1.set(Calendar.MONTH, returnMonth - 1);
        calendar1.set(Calendar.DATE, returnDay);
        Date date2 = calendar1.getTime();

        // tanggal pengembalian
        Calendar calendar2 = Calendar.getInstance();
        int loanDay = Integer.parseInt(loanDate.substring(0, 2));
        int loanMonth = Integer.parseInt(loanDate.substring(3, 5));
        int loanYear = Integer.parseInt(loanDate.substring(6));
        calendar2.set(Calendar.YEAR, loanYear);
        calendar2.set(Calendar.MONTH, loanMonth - 1);
        calendar2.set(Calendar.DATE, loanDay);
        Date date1 = calendar2.getTime();

        // menghitung selisih hari
        int diff = diffDays(date1, date2);
        
        // menghitung denda
        if (diff > 7) {
            this.fine = (diff-7) * DENDA_PER_HARI;
        } else {
            this.fine = 0;
        }
    }

}
