public class Pasien extends Warga {
	// TODO: Ubah modifier atribut pada class Pasien agar code menjadi lebih aman
	private int happiness;
	private String penyakit;
	private boolean pasienSembuh;

	// TODO: Lengkapi constructor
	Pasien(String nama, String penyakit) {
		super(nama); // Memanggil constructor dari superclass
		this.happiness = 0;
		this.penyakit = penyakit;
		this.pasienSembuh = false;
	}

	// TODO: Lengkapi method berinteraksi untuk pasien
	@Override
	public void berinteraksi(Warga X) {
		if (X instanceof Dokter dokter) { // Jika X adalah Dokter
			// menyembuhkan penyakit pasien
			if (dokter.getPenyakitKeahlian().equalsIgnoreCase(this.penyakit) && !this.pasienSembuh) {
				this.happiness += 20;
				this.pasienSembuh = true;

				// Handle jika happiness melebihi batas minimum dan maksimum
				if (this.happiness > 100) {
					this.happiness = 100;
				} else if (this.happiness < 0) {
					this.happiness = 0;
				}
			}

			// mengubah happiness pasien berdasarkan keramahan dokter
			if (dokter.getDokterRamah()){
				this.happiness += 10;

				// Handle jika happiness melebihi batas minimum dan maksimum
				if (this.happiness > 100) {
					this.happiness = 100;
				} else if (this.happiness < 0) {
					this.happiness = 0;
				}
			} else {
				this.happiness -= 5;

				// Handle jika happiness melebihi batas minimum dan maksimum
				if (this.happiness > 100) {
					this.happiness = 100;
				} else if (this.happiness < 0) {
					this.happiness = 0;
				}
			}

			addLogInteraksi(dokter);
		} else if (X instanceof Pasien) { // Jika X adalah Pasien
			this.happiness += 5;
			addLogInteraksi(X);

			// Handle jika happiness melebihi batas minimum dan maksimum
			if (this.happiness > 100) {
				this.happiness = 100;
			} else if (this.happiness < 0) {
				this.happiness = 0;
			}
		}


	}



	// TODO: Lengkapi toString dengan memanggil method toString milik superclass
	@Override
	public String toString() {
		return super.toString(); // Memanggil method toString milik superclass
	}

	public int getHappiness() {
		return this.happiness;
	}

	public boolean getStatusSembuh() {
		return this.pasienSembuh;
	}

	public String getPenyakit() {
		return this.penyakit;
	}

}
