
public class Pelanggan {
  
    //TODO: Tambahkan modifier
    // Modifier Private agar tidak dapat langsung diakses oleh class lain
    private String nama;
    private int uang;
    private Order[] keranjang;
    private int kapasitasKeranjang = 5000;

    //TODO: Buat Constructor
    public Pelanggan(String nama, int uang, int kapasitas) {
        this.nama = nama;
        this.uang = uang;
        this.keranjang = new Order[kapasitas];
    }
    
    // TODO: lengkapi method di bawah ini
    String addBarang(Barang barang, int banyakBarang){
        // Handling jika stock barang kurang dari banyak barang yang dipesan
        if (!barang.cekStock(banyakBarang)) {
            return String.format("Stock %s kurang \n", barang.getNama());
        }

        // Iterasi untuk menambahkan barang ke keranjang
        for (int i = 0; i < keranjang.length; i++) {

            // Menambahkan barang jika sudah pernah ditambahkan sebelumnya
            if (keranjang[i] != null) {
                if (keranjang[i].getBarang().getNama().equals(barang.getNama())) {

                    for (int j = 0; j < banyakBarang; j++) {
                        // Handling jika keranjang penuh
                        if ((kapasitasKeranjang - barang.getBeratBarang()) < 0) {
                            return String.format("Maaf %d %s terlalu berat, tetapi %d %s berhasil ditambahkan \n", banyakBarang, barang.getNama(), j, barang.getNama());
                        } else {
                            keranjang[i].setBanyakBarang(keranjang[i].getBanyakBarang());
                            kapasitasKeranjang -= barang.getBeratBarang();
                            barang.setStock(barang.getStock() - 1);
                        }
                    }
                    break;
                }

            } else { // Menambahkan order baru jika belum pernah ditambahkan sebelumnya
                for (int j = 0; j < banyakBarang; j++) {

                    // Handling jika kapasitas keranjang sudah penuh
                    if ((kapasitasKeranjang - barang.getBeratBarang()) < 0) {
                        keranjang[i] = new Order(barang, j);
                        return String.format("Maaf %d %s terlalu berat, tetapi %d %s berhasil ditambahkan \n", banyakBarang, barang.getNama(), j, barang.getNama());
                    } else {
                        kapasitasKeranjang -= barang.getBeratBarang();
                        barang.setStock(barang.getStock() - 1);
                    } keranjang[i] = new Order(barang, banyakBarang);
                }
                break;
            }
        }
        return String.format("%s berhasil menambahkan %d %s \n", nama, banyakBarang, barang.getNama());
    }
    
    // TODO: lengkapi method di bawah ini
    int totalHargaBarang(){
        int totalHarga = 0;

        // Iterasi untuk menghitung total harga barang
        for (Order order : keranjang) {
            if (order != null) {
                totalHarga += order.getBarang().getHarga() * order.getBanyakBarang();
            }
        }
        return totalHarga;
    }
    
    // TODO: lengkapi method di bawah ini
    String cekUang(){
        return String.format("Uang %s sekarang %d \n", this.nama, this.uang);
    }

    // Setter and Getter dan lengkapi modifier
    String getNama() {
        return this.nama;
    }

    void setNama(String nama) {
        this.nama = nama;
    }

    int getUang() {
        return this.uang;
    }

    void clearKeranjang() {
        this.keranjang = new Order[keranjang.length];
    }

    void setUang(int uang) {
        this.uang = uang;
    }

    Order[] getKeranjang() {
        return keranjang;
    }

    void setKapasitasKeranjang(int kapasitasKeranjang) {
        this.kapasitasKeranjang = kapasitasKeranjang;
    }

}