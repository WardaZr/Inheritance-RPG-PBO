//Nama  : Warda Azzahra
//NIM   : 1902810
//Kelas : Pendidikan Ilmu Komputer 4B (sesi 2)
//MOdifikasi game RPG dari code https://github.com/yudiwbs/rpgV3

import java.util.ArrayList;

public class Player {
    //item defulat yang dimiliki player
    private String nama;
    private int kesehatan = 100;
    private Item objCincin;
    private ArrayList<Item> arrItem = new ArrayList<>();
    private Ruangan ruanganAktif;  //ruangan tempat player saat ini berada
    private GameInfo objGameInfo;

    // =================== CONSTRUCTOR ===================
    public Player(String nama) {
        this.nama=nama;
        objCincin = new Item("Cincin Emas");
        objCincin.setDeskripsi("Cincin emas bertuliskan suatu kalimat..");
        arrItem.add(objCincin);
    }


    // =================== GETTER SETTER ===================
    public int getKesehatan() {
        return kesehatan;
    }
    public void setKesehatan(int kesehatan) {
        this.kesehatan = kesehatan;
    }

    public GameInfo getObjGameInfo() {
        return objGameInfo;
    }
    public void setObjGameInfo(GameInfo objGameInfo) {
        this.objGameInfo = objGameInfo;
        objCincin.setObjGameInfo(objGameInfo);
    }

    public void setRuanganAktif(Ruangan ruanganAktif) {
        this.ruanganAktif = ruanganAktif;
    }
    public Ruangan getRuanganAktif() {
        return ruanganAktif;
    }


    // =================== METHOD LAINNYA ===================
    public void printPlayer() {
        System.out.println("Nama Player      :"+nama);
        System.out.println("Kesehatan Player :"+kesehatan);
    }

    public void printItem() {
        System.out.println("Item milik player");
        int cc = 0;
        for (Item objItem:arrItem) {
            cc++;
            System.out.printf("%d. %s%n",cc,objItem.getNama());
            System.out.println(objItem.getDeskripsi());
        }
    }

    public void addItem(Item objItem) {
        arrItem.add(objItem);
    }

    // hapus item di ruangan berdasarkan namanya
    // digunakan saat suatu item diambil oleh player misalnya
    public void hapusItem(Item objItem) {
        arrItem.remove(objItem);  //buang item
    }

    //cari item yang dimiliki oleh player, return TRUE jika ada
    //salah satu yg menggunakan: pintu untuk mengecek apakah player sudah punya kunci
    public boolean cariItem(String namaItem) {
        for (Item objItem:arrItem) {
            if (namaItem.equals(objItem.getNama())) {
                return (true);      //jika ditemukan
            }
        }
        return(false); //tidak ketemu
    }

    public void pilihanAksi() {
        System.out.println("**** Pilihan Aksi pada Player *** ");
        //disamakan dengan  ruangan
        //bisa direfactoring nanti supaya tdk duplikasi

        //aksi2 item yang dimiliki player
        int urutPil = 0;  //item
        int subPil;   //aksinya
        System.out.println("Item yang dimiliki player");
        urutPil = Aksi.printPilihanItem(arrItem, urutPil);      //dari class Aksi

        //info seputar player
        urutPil++;
        subPil = 0;
        int pilInfoPlayer  = urutPil; //catat untuk pintu
        System.out.println("Info Player");
        //hanya satu
        subPil++;
        System.out.printf("%d%d. info player%n", urutPil, subPil);

        int[] arrPil = Aksi.Scanner();  //dari class Aksi

        if (arrPil[0] == pilInfoPlayer) {
            printPlayer();
        } else {
            Item objItemPilih = arrItem.get(arrPil[0]-1);
            objItemPilih.prosesAksi(arrPil[1]); //aksi item
        }
    }
}
