//Nama  : Warda Azzahra
//NIM   : 1902810
//Kelas : Pendidikan Ilmu Komputer 4B (sesi 2)
//MOdifikasi game RPG dari code https://github.com/yudiwbs/rpgV3

import java.util.ArrayList;

public class Item {
    private String deskripsi;
    private String nama;
    private ArrayList<String> arrAksi = new ArrayList<>(); //pilihan aksi untuk item
    private Ruangan objRuangan;  //ruangan tempat item, jika null artinya item dipegang npc atau plyaer
    private GameInfo objGameInfo;

    // =================== CONSTRUCTOR ===================
    public Item(String nama) {
        this.nama = nama;
    }

    // =================== GETTER SETTER ===================
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setObjGameInfo(GameInfo objGameInfo) {
        this.objGameInfo = objGameInfo;
    }
    public GameInfo getObjGameInfo () {
        return objGameInfo;
    }

    public void setObjRuangan(Ruangan objRuangan) {
        this.objRuangan = objRuangan;
    }
    public Ruangan getObjRuangan() {
        return objRuangan;
    }

    public String getDeskripsi() {
        return deskripsi;
    }
    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public ArrayList<String> getAksi() {
        //aksi dinamik tergantung ada di ruangan atau dipegang player/npc
        ArrayList<String> arrOut = new ArrayList<>();
        if (objRuangan==null) {
            //ada di player, ada opsi buang
            arrOut.add("Deskripsi Item");
            arrOut.add("Buang item");
        } else {
            //ada di ruangan ada opsi ambil
            arrOut.add("Deskripsi Item");
            arrOut.add("Ambil item");
        }
        return(arrOut);
    }

    // =================== METHOD LAINNYA ===================
    public void print() {
        //print deskripsi item
        System.out.println(nama);
        System.out.println(deskripsi);
    }

    protected void dibuang () {
        System.out.println("Item dibuang player ke ruangan");
        objGameInfo.getObjRuangan().addItem(this);  //tambah ke ruangan
        objGameInfo.getObjPlayer().hapusItem(this); //hapus dari player
        objRuangan = objGameInfo.getObjRuangan(); // set ruangan
    }

    //pindahkan item dari ruangan ke player
    protected void diambil () {
        System.out.println("Item diambil player");
        objGameInfo.getObjPlayer().addItem(this);     //tambahkan  objek ini (this) pada player
        objRuangan.hapusItem(this);                    //hapus dari ruangan
        objRuangan = null;
    }

    public void prosesAksi(int pil) {
        //pilihan user untuk aksi yang akan diambil
        //urutan harus sama dengan isi arrAksi
        if (pil == 1) {
            System.out.println(deskripsi);
        } else if (pil == 2) {  //bisa ambil atau buang
            if (objRuangan == null) {
                //dipegang player, buang ke ruangan
                dibuang();
            } else {
                //ada di ruangan, diambil player
                diambil();
            }
        }
    }
}
