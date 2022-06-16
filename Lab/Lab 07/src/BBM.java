public class BBM implements EngineBehaviour {
    // TODO: implementasikan sesuai UML diagram
    // TODO: tambahkan method-method yang diperlukan

    // Mendefinisikan method-method yang perlu dioverride\

    // Method ketika mobil menyalakan mesin
    @Override
    public String start(Mobil mobil) {
        return String.format("%s menyalakan mesin, NGENG!" , mobil.getNama());
    }

    // Method ketika mobil ngegas
    @Override
    public int gas(int persenFuel) {
        return persenFuel -25;
    }

    // Method ketika mobil berhenti
    @Override
    public String stop(Mobil mobil) {
        return String.format("%s mesin mati, mobil istirahat dulu.", mobil.getNama());
    }
  
}