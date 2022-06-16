
public class Obligasi extends Aset {
	// TODO lengkapi visibility modifier atribut dan methods berikut
	// Deklarasi atribut
	private double bunga;
	private int maturitas;
	private boolean jatuhTempo = false;

	// Constructor
	Obligasi(String nama, int jumlah, double harga, double bunga, int maturitas) {
		// TODO lengkapi constructor ini
		super(nama, jumlah, harga);
		this.bunga = bunga;
		this.maturitas = maturitas;
	}

	// Override method nextYear
	@Override
	void nextYear() {
		// TODO validasi jatuh tempo
		// Handle jatuh tempo
		if (this.getTahun() >= this.maturitas) {
			this.jatuhTempo = true;
		}

		super.nextYear();
		// TODO tambahkan bunga ke total pendapatan Pacilnomo
		if (!this.jatuhTempo) {
			Pacilnomo.addToEarnings(this.bunga * this.getJumlah() * this.getHarga());
		}

	}

	// TODO lengkapi method toString ini
	// Override method toString
	@Override
	public String toString() {
		return String.format("""
						%s
						Tipe: Obligasi
						Harga: %.2f
						Jumlah: %d
						Bunga: %.2f
						Jatuhtempo: %s""",
				this.getNama(), this.getHarga(), this.getJumlah(), this.bunga, this.jatuhTempo);
	}
}
