public class MobilAir extends Mobil { //TODO: impelementasikan sesuai UML diagram

    // Constructor
    public MobilAir(String nama, EngineBehaviour engineBehaviour, String bahanBakar){
        // TODO: Lengkapi constructor berikut
        super(nama, engineBehaviour, bahanBakar, "Air");
    }

    // TODO: Lengkapi method ini
    // Mengimplementasikan method isiBahanBakar() dari class Mobil
    @Override
    public String isiBahanBakar(){
        if (this.getIsOn()){
            return "Mobil masih menyala, matikan terlebih dahulu agar tidak tenggelam.";
        } else {
            this.setPersenFuel(100);
            return String.format("%s sekarang sudah penuh, mobil dapat digunakan kembali!", this.getBahanBakar());

        }
    }

    // TODO: Lengkapi method ini
    // Mengimplementasikan method simulasi() dari class Mobil
    @Override
    public String[] simulasi(){
        String[] result = new String[8];
        result[0] = this.start();
        for (int i = 1; i < 6; i++){
            result[i] = this.gas();
        }
        result[6] = this.stop();
        result[7] = this.isiBahanBakar();
        return result;
    }   
}