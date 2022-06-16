public class Warga {
	// TODO: Ubah modifier atribut pada class Warga agar hanya dapat diakses oleh subclass-subclassnya
	protected String nama;
	protected Warga[] logInteraksi = new Warga[0];

	// TODO: Lengkapi constructor
	Warga(String nama) {
		// inisialisasi atribut nama
		this.nama = nama;
	}

	public String getNama() {
		return nama;
	}
	// Method sengaja dikosongkan
	public void berinteraksi(Warga X){
	}

	// Method untuk menambah log interaksi
	public void addLogInteraksi(Warga warga){
		Warga[] newLog = new Warga[this.logInteraksi.length+1];

        for(int i = 0; i < this.logInteraksi.length; i++){
            newLog[i] = this.logInteraksi[i];
        }
        this.logInteraksi = newLog;

        newLog[this.logInteraksi.length -1] = warga;
	}
	
	// TODO: Lengkapi toString() dengan mengembalikan nama warga
	@Override
	public String toString() {
		return this.nama;
	}

	// TODO: Lengkapi getLogInteraksi() dengan mengembalikan log interaksi warga
	public Warga[] getLogInteraksi(){
		return this.logInteraksi;
	}
}
