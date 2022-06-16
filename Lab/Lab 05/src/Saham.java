
public class Saham extends Aset{
	// TODO lengkapi visibility modifier atribut dan methods berikut
	// Deklarasi atribut
	private double dividen;
	private double pertumbuhan;

	// Constructor
	Saham(String nama, int jumlah, double harga, double pertumbuhan, double dividen) {
		super(nama, jumlah, harga);
		this.pertumbuhan = pertumbuhan;
		this.dividen = dividen;
	}

	// Override method nextYear
	@Override
	void nextYear() {
		super.nextYear();
		grow();

		// TODO modifikasi harga sesuai dengan pertumbuhan sekarang dan tambahkan dividen ke earnings
		this.setHarga(this.getHarga() + this.getHarga() * pertumbuhan);
		Pacilnomo.addToEarnings(this.dividen * this.getJumlah() * this.getHarga());
	}

	// Linear congruential generator for subsequent growth
	void grow() {
		int a = 0x4b;
		int c = 0x4a;
		int m = 2;
		pertumbuhan = ((a * pertumbuhan + c) % m) - 1;
		pertumbuhan = pertumbuhan < 0 ? pertumbuhan % -m : pertumbuhan;
	}

	// TODO lengkapi method toString ini
	// Override method toString
	@Override
	public String toString() {
		return String.format("""
						%s
						Tipe: Saham
						Harga: %.2f
						Jumlah: %d
						Dividen: %.2f
						Pertumbuhan: %.2f""",
						this.getNama(), this.getHarga(), this.getJumlah(), this.dividen, this.pertumbuhan);
	}
}
