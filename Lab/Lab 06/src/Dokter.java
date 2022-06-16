public class Dokter extends Warga {
	// TODO: Ubah modifier atribut pada class Dokter agar code menjadi lebih aman
	private int jumlahPasienDitemui;
	private String penyakitKeahlian;
	private boolean dokterRamah;
	
	// TODO: Lengkapi constructor
	Dokter(String nama, String penyakitKeahlian, boolean dokterRamah){
		super(nama); // memanggil constructor dari superclass
		this.jumlahPasienDitemui = 0;
		this.penyakitKeahlian = penyakitKeahlian;
		this.dokterRamah = dokterRamah;
	}

	// TODO: Lengkapi method berinteraksi untuk dokter
	@Override
	public void berinteraksi(Warga X){
		// Jika X adalah pasien, maka jumlahPasienDitemui bertambah 1
		if (X instanceof Pasien){
			this.jumlahPasienDitemui++;
		}
		// Menambah daftar warga yang berinteraksi dengan dokter
		addLogInteraksi(X);
	}

	// TODO: Lengkapi toString dengan memanggil method toString milik superclass
	@Override
	public String toString() {
		return super.toString(); // memanggil method toString milik superclass
	}

	public int getJumlahPasienDitemui(){
		return this.jumlahPasienDitemui;
	}

	public String getPenyakitKeahlian(){
		return this.penyakitKeahlian;
	}

	public boolean getDokterRamah(){
		return this.dokterRamah;
	}

}
