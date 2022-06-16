public class Listrik implements EngineBehaviour {
    // TODO: implementasikan sesuai UML diagram
    // TODO: tambahkan method-method yang diperlukan

    // Mendefinisikan method-method yang perlu dioverride

    // Method ketika mobil menyalakan mesin
    @Override
    public String start(Mobil mobil) {
        return String.format("%s menyalakan listrik, SIAP DIGAS!" , mobil.getNama());
    }

    // Method ketika mobil ngegas
    @Override
    public int gas(int persenFuel) {
        return persenFuel - 20;
    }

    // Method ketika mobil berhenti
    @Override
    public String stop(Mobil mobil) {
        return String.format("%s listrik dimatikan, mobil telah berhenti.", mobil.getNama());
    }
}