public class MobilTerbang extends Mobil{ //TODO: impelementasikan sesuai UML diagram

    // Constructor
    public MobilTerbang(String nama, EngineBehaviour engineBehaviour, String bahanBakar) {
        // TODO: Lengkapi Constructor berikut
        super(nama, engineBehaviour, bahanBakar, "Terbang");
    }

    // TODO: Lengkapi method ini
    // Mengimplementasikan method isiBahanBakar() dari class Mobil
    @Override
    public String isiBahanBakar(){
        if (this.getIsOn()){
            return "Mobil masih terbang, matikan terlebih dahulu agar tidak jatuh.";
        } else {
            this.setPersenFuel(100);
            return String.format("%s sekarang sudah penuh, mobil dapat terbang kembali!", this.getBahanBakar());
        }
    }

    // TODO: Lengkapi method
    // Mengimplementasikan method simulasi() dari class Mobil
    @Override
    public String[] simulasi(){
        String[] result = new String[5];
        result[0] = this.start();
        result[1] = this.gas();
        result[2] = this.gas();
        result[3] = this.stop();
        result[4] = this.isiBahanBakar();
        return result;
    }
  
}