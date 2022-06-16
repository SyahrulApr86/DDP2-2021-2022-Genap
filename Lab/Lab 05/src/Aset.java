
public class Aset {
	// TODO lengkapi visibility modifier atribut dan methods berikut
	// Deklarasikan atribut-atribut yang dibutuhkan
	private String nama;
	private int jumlah;
	private double harga;
	private int tahun = 0;

	// Constructor
	Aset(String nama, int jumlah, double harga) {
		// TODO lengkapi constructor ini
		this.nama = nama;
		this.jumlah = jumlah;
		this.harga = harga;
	}
	
	// Increment tahun
	void nextYear() {
		tahun++;
	}

	// TODO buat getter dan setter untuk fields pada class ini

	public String getNama() {
		return nama;
	}

	public int getJumlah() {
		return jumlah;
	}

	public double getHarga() {
		return harga;
	}

	public int getTahun() {
		return tahun;
	}

	public void setHarga(double harga) {
		this.harga = harga;
	}
}
