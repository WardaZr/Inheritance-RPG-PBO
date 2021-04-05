//Nama  : Warda Azzahra
//NIM   : 1902810
//Kelas : Pendidikan Ilmu Komputer 4B (sesi 2)
//MOdifikasi game RPG dari code https://github.com/yudiwbs/rpgV3

import java.util.ArrayList;

public class Pintu extends Item{
    private final ArrayList<String> arrAksi = new ArrayList<>();

    // =================== CONSTRUCTOR ===================
    public Pintu(String nama) {
        super(nama);
        //init pilihan
        arrAksi.add("Deskripsikan pintu");
        arrAksi.add("Coba buka pintu");
    }


    // =================== GETTER SETTER ===================
    @Override       //override dari method superclass
    public ArrayList<String> getAksi() {
        return arrAksi;
    }


    // =================== METHOD LAINNYA ===================
    @Override //override dari method superclass
    public void prosesAksi(int subPil) {
        //1: deskripsikan
        //2: buka pintu
        if (subPil==1) {
            print();        //print deskripsi pintu
        }
        else if (subPil==2) {
            //cek apakah mempunyai kunci
            if (getObjGameInfo().getObjPlayer().cariItem("Kunci")) {
                //kunci ada, pintu terbuka
                System.out.println("Player menggunakan kunci untuk membuka pintu dan pintu terbuka!");
                getObjGameInfo().setGameOver(true); //game selesai
            }
            else {
                //kunci tidak ada
                System.out.println("Player mencoba membuka pintu. TERKUNCI!");
            }
        }
    }
}
