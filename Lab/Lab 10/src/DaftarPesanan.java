import java.util.ArrayList;
import java.util.Collections;

public class DaftarPesanan<T extends Pesanan> {
    //attributes
    private ArrayList<T> pesanans;

    public DaftarPesanan() {
        //Constructor
        pesanans = new ArrayList<>();
    }

    public void tambahPesanan(T pesanan) {
        // menambahkan pesanan ke dalam daftar pesanan
        this.pesanans.add(pesanan);
    }

    public T nextPesanan() {
        // return sesuai dengan urutan prioritas
        T temp;
        try {
            temp = this.pesanans.get(0);
        } catch (IndexOutOfBoundsException e) {
            return null; // return null jika pesanan sudah tidak ada
        }
        this.pesanans.remove(0);
        return temp;
    }

    public void urutkan() {
        // mengurutkan pesanan berdasarkan prioritas
        Collections.sort(pesanans);
    }

}
