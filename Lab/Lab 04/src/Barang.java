public class Barang {
  
    //TODO: Tambahkan modifier
    String nama;
    private int harga;
    private int beratBarang;
    private int stock;

    //TODO: Buat Constructor
    public Barang(String nama, int harga, int beratBarang, int stock) {
        this.nama = nama;
        this.harga = harga;
        this.beratBarang = beratBarang;
        this.stock = stock;
    }
      
    //TODO: Silakan cek stock
    boolean cekStock(int stock){
        return stock <= this.stock;
    }
    
    String getNama() {
        return nama;
    }
    
    int getStock(){
        return stock;
    }
  
    void setStock(int kuantitas){
        this.stock = kuantitas;
    }
    
    int getBeratBarang(){
        return beratBarang;
    }

    int getHarga(){
        return harga;
    }
    
}
