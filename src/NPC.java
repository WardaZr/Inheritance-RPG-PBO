//Nama  : Warda Azzahra
//NIM   : 1902810
//Kelas : Pendidikan Ilmu Komputer 4B (sesi 2)
//MOdifikasi game RPG dari code https://github.com/yudiwbs/rpgV3

import java.util.ArrayList;

public class NPC{
    private Item objKunci;
    private GameInfo objGameInfo;
    private final ArrayList<String> arrAksi = new ArrayList<>();
    private boolean isKenal = false;

    // =================== CONSTRUCTOR===================
    public NPC() {
        //init kunci
        objKunci = new Item("Kunci");
        objKunci.setDeskripsi("Sebuah kunci kecil yang sudah agak berkarat");

        //aksi npc
        arrAksi.add("Perkenalan dgn NPC");
        arrAksi.add("Minta kunci");
    }


    // =================== GETTER SETTER ===================
    public ArrayList<String> getAksi() {
        return arrAksi;
    }

    public GameInfo getObjGameInfo () {
        return objGameInfo;
    }
    public void setObjGameInfo (GameInfo objGameInfo) {
        this.objGameInfo = objGameInfo;
        objKunci.setObjGameInfo(objGameInfo);
    }

    public Item getObjKunci () {
        return objKunci;
    }
    public void setObjKunci (Item objKunci) {
        this.objKunci = objKunci;
    }


    // =================== METHOD LAINNYA ===================
    public void prosesAksi(int subPil) {
        //1: perkenalan dengan npc
        //2: buka pintu
        if (subPil == 1) {
            System.out.println("Halo saya penjaga pintu ini");
            isKenal = true;
        } else if (subPil == 2) {
            if (isKenal) {
                //berikan kunci pada player
                if (objKunci==null) {
                    System.out.println("Masa lupa, kunci kan sudah saya berikan!");
                } else {
                    //player harus menyelesaikan quest yang diberikan NPC
                    System.out.println("****** Selesaikan quest ini! ******\n");
                    boolean questWin = Quest.QuestTebakAngka();
                    System.out.println("***********************************");

                    //jika menang, player mendapatkan kunci
                    if (questWin){
                        System.out.println("\nKunci diberikan pada player");
                        objGameInfo.getObjPlayer().addItem(objKunci);     //tambahkan  objek ini pada player
                        objKunci = null;

                    } else {
                        //jika Player kalah, staminanya akan dikurangi
                        int hp = objGameInfo.getObjPlayer().getKesehatan()-50;
                        objGameInfo.getObjPlayer().setKesehatan(hp);
                        System.out.println("Kesehatan Player saat ini : " + hp);

                        //jika staminanya habis, game over
                        if(objGameInfo.getObjPlayer().getKesehatan()<1){
                            System.out.println("Poin kesehatan Player habis\n\nGAME OVER!!!");
                            objGameInfo.setGameOver(true); //game over
                        }
                    }
                }
            } else {
                System.out.println("Siapa anda? kenalan dulu dong");
            }
        }
    }
}
