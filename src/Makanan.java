//Nama  : Warda Azzahra
//NIM   : 1902810
//Kelas : Pendidikan Ilmu Komputer 4B (sesi 2)
//MOdifikasi game RPG dari code https://github.com/yudiwbs/rpgV3

import java.util.ArrayList;

public class Makanan extends Item {
    private boolean beracun = false;     //mendeskripsikan apakah makanan tersebut berancun atau aman dikonsumsi
    private ArrayList<String> arrAksi = new ArrayList<>();

    // =================== CONSTRUCTOR===================
    public Makanan (String nama) {
        super(nama);
    }


    // =================== GETTER SETTER ===================
    public boolean isBeracun () {
        return beracun;
    }
    public void setBeracun (boolean beracun) {
        this.beracun = beracun;
    }

    @Override       //override dari method superclass
    public ArrayList<String> getAksi () {
        ArrayList<String> arrOut = new ArrayList<>();
        if (getObjRuangan()==null) {
            //ada di player, ada opsi buang
            arrOut.add("Deskripsi Item");
            arrOut.add("Buang item");
            arrOut.add("makan item");
        } else {
            //ada di ruangan ada opsi ambil
            arrOut.add("Deskripsi Item");
            arrOut.add("Ambil item");
        }
        return(arrOut);
    }


    // =================== METHOD LAINNYA ===================
    private void dimakan(){
        int hp;
        if(beracun){
            //makanan beracun akan mengurangi kesehatan player
            hp = getObjGameInfo().getObjPlayer().getKesehatan() - 10;
            System.out.println("Kamu memakan makanan beracun, kesehatan dikurangi 10 poin");
        }
        else{
            //makanan sehat akan menambah stamina player
            hp = getObjGameInfo().getObjPlayer().getKesehatan() + 10;
            System.out.println("Makanan sehat, stamina mu bertambah 10 poin");
        }

        getObjGameInfo().getObjPlayer().setKesehatan(hp);
        getObjGameInfo().getObjPlayer().hapusItem(this);     //jika sudah dimakan maka item akan hilang
    }

    @Override           //override dari method superclass
    public void prosesAksi(int pil) {
        //pilihan user untuk aksi yang akan diambil
        //urutan harus sama dengan isi arrAksi
        if (pil==1) {
            System.out.println(getDeskripsi());
        }
        else  if (pil==2 || pil == 3) {  //bisa ambil atau buang
            if (getObjRuangan()==null) {
                //dipegang player
                if (pil==3){
                    dimakan();      //makan item
                } else {
                    dibuang();      //buang item
                }
            } else {
                //ada di ruangan, diambil player
                diambil();
            }
        }
    }
}
