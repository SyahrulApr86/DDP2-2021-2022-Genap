public class MobilTruk extends Mobil{ //TODO: impelementasikan sesuai UML diagram

    // Constructor
    public MobilTruk(String nama, EngineBehaviour engineBehaviour, String bahanBakar) {
        // TODO: Lengkapi Constructor berikut
        super(nama, engineBehaviour, bahanBakar, "Truk");
    }

    // TODO: Lengkapi method ini
    // Mengimplementasikan method isiBahanBakar() dari class Mobil
    @Override
    public String isiBahanBakar(){
        if (this.getIsOn()){
            return "Mobil masih menyala, matikan terlebih dahulu agar tidak meledak";
        } else {
            this.setPersenFuel(100);
            return String.format("%s sekarang sudah penuh, mobil dapat digaskeun kembali!", this.getBahanBakar());
        }
    }

    // TODO: Lengkapi method ini
    // Mengimplementasikan method simulasi() dari class Mobil
    @Override
    public String[] simulasi(){
        String[] result = new String[7];
        result[0] = this.start();
        for (int i = 1; i < 5; i++){
            result[i] = this.gas();
        }
        result[5] = this.stop();
        result[6] = this.isiBahanBakar();
        return result;
    }
}