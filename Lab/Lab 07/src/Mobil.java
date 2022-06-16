abstract class Mobil {
    // Deklarasi variabel
    private String nama;
    private int persenFuel;
    private String bahanBakar;
    private String jenis;
    private EngineBehaviour engineBehaviour;
    private boolean isOn;

    // Constructor
    protected Mobil (String nama, EngineBehaviour engineBehaviour, String bahanBakar, String jenis){
        // TODO: Lengkapi constructor berikut
        this.nama = nama;
        this.engineBehaviour = engineBehaviour;
        this.bahanBakar = bahanBakar;
        this.jenis = jenis;
        this.persenFuel = 100;
        this.isOn = false;
    }

    // TODO: Lengkapi method ini
    // implementasi method start dari interface EngineBehaviour
    public String start(){
        this.isOn = true;
        return this.engineBehaviour.start(this);
    }

    // TODO: Lengkapi method ini
    // implementasi method gas dari interface EngineBehaviour
    public String gas(){
        if (this.isOn){
            String tempat;
            if ("Air".equals(this.jenis)) {
                tempat = "Laut";
            } else if ("Terbang".equals(this.jenis)) {
                tempat = "Langit";
            } else {
                tempat = "Jalan Raya";
            }

            if (this.persenFuel <= 0){
                return "Bensin habis!";
            } else {
                this.persenFuel = this.engineBehaviour.gas(persenFuel);
                return String.format("%s digas dengan cepat di %s! Bahan bakar mobil %s sekarang %d%%.", this.nama, tempat, this.bahanBakar, this.persenFuel);
            }

        } else {
            return "Nyalakan mobil dulu!";
        }
    }
  
    // TODO: Lengkapi method ini
    // implementasi method stop dari interface EngineBehaviour
    public String stop(){
        this.isOn = false;
        return this.engineBehaviour.stop(this);
    }
  
    public abstract String isiBahanBakar();
    public abstract String[] simulasi();

    // Getter & Setter
    public String getNama() {
        return nama; 
    }

    public int getPersenFuel() {
        return persenFuel; 
    }

    public void setPersenFuel(int persenFuel) {
        this.persenFuel = persenFuel;
    }

    public String getBahanBakar() {
        return bahanBakar; 
    }
  
    public boolean getIsOn() {
        return isOn; 
    }

}