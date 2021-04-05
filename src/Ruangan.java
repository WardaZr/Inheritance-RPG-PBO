import java.util.ArrayList;
import java.util.Scanner;

public class Ruangan {
    private String deskripsi;
    private Pintu objPintu;
    private NPC objNPC;
    private ArrayList<Item> arrItem = new ArrayList<>();
    private GameInfo objGameInfo;
    private Scanner sc = new Scanner(System.in);

    // =================== CONSTRUCTOR===================
    public Ruangan() {
        // init ruangan
        // init pintu, kunci dan roti.
        objPintu = new Pintu("Pintu Keluar");
        objPintu.setDeskripsi("Pintu berwarna merah dengan tulisan 'EXIT' di atas ");
        objNPC = new NPC(); //jika seperti ini, maka setiap kali objRuangan di buat NPC selalu bernama Lily

        Makanan objRoti = new Makanan("Roti");
        objRoti.setDeskripsi("Roti rasa coklat dalam bungkusan plastik");
        objRoti.setObjRuangan(this);

        Makanan objSusuUHT = new Makanan("Susu Ultramilk");
        objSusuUHT.setBeracun(true);
        objSusuUHT.setDeskripsi("Susu UHT yang sudah kardaluasa");
        objSusuUHT.setObjRuangan(this);

        //makanannya baru bisa nambah 1 :(
        //tambah item ke array
        arrItem.add(objPintu);
        arrItem.add(objSusuUHT);
        arrItem.add(objRoti);
    }


    // =================== GETTER SETTER ===================
    //objgame juga diset pada pintu dan item2
    public void setObjGameInfo(GameInfo objGameInfo) {
        this.objGameInfo = objGameInfo;
        objNPC.setObjGameInfo(objGameInfo);
        for (Item objItem:arrItem) {
            objItem.setObjGameInfo(objGameInfo);
        }
    }

    public String getDeskripsi() {
        return deskripsi;
    }
    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public GameInfo getObjGameInfo () {
        return objGameInfo;
    }
    public ArrayList<Item> getArrItem(){return arrItem;}


    // =================== METHOD LAINNYA ===================
    //aksi yang dapat dilakukan di ruangan
    //agak kompleks tapi jadi fleksibel, logic aksi ada di masing2 item (bukan di game engine)
    //hardcode menu dikurangi
    public void pilihanAksi() {

        System.out.println("==== Pilihan Aksi pada Ruangan ===");
        int urutPil = 0;  //item, pintu
        int subPil = 0;   //aksinya

        //aksi2 item dan pintu
        System.out.println("Item di ruangan");
        urutPil = Aksi.printPilihanItem(arrItem, urutPil);  //ambil method untuk print pilihan dari class Aksi

        // aksi2 NPC
        System.out.println("NPC");
        urutPil++;
        int pilNPC  = urutPil; //catat untuk pintu
        Aksi.printPilihan(objNPC.getAksi(), subPil, urutPil);

        int[] arrPil = Aksi.Scanner();
        //int pil = arrPil[0], int subPil = arrPil[1]

        if (arrPil[0] == pilNPC) {
            objNPC.prosesAksi(arrPil[1]);
        } else {
            Item objItemPilih = arrItem.get(arrPil[0]-1);
            objItemPilih.prosesAksi(arrPil[1]); //aksi item
        }
    }

    // hapus item di ruangan berdasarkan namanya
    // digunakan saat suatu item diambil oleh player misalnya
    public void hapusItem(Item objItem) {
        arrItem.remove(objItem);  //buang item
    }

    public void addItem(Item objItem) {
        arrItem.add(objItem);
    }
}
