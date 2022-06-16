package assignments.assignment2;

// TODO
public class Book {
    // Deklarasi properties
    private String title;
    private String author;
    private String publisher;
    private int stok;
    private Category category;

    // Constructor
    public Book(String title, String author, String publisher, int stok, Category category) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.stok = stok;
        this.category = category;
    }

    // Getter dan setter
    public void setStok(int stok) {
        this.stok = stok;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getStok() {
        return stok;
    }

    public int getPoint(){
        return category.getPoint();
    }

    public String getPublisher() {
        return publisher;
    }

    public String getCategoryName(){
        return category.getName();
    }

    @Override
    public String toString() {
        return String.format("""
                            Judul Buku: %s
                            Penulis Buku: %s
                            Penerbit Buku: %s
                            Kategori: %s
                            Point: %d""",
                this.getTitle(), this.getAuthor(), this.getPublisher(), this.getCategoryName(), this.getPoint());
    }
}
