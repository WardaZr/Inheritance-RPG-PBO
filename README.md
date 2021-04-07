# Inheritance-RPG-PBO
- Nama  : Warda Azzahra
- NIM   : 1902810
- Kelas : Pendidikan Ilmu Komputer 4B (sesi 2)
# Modifikasi
- beberapa fungsi di method pilihanAksi() milik Ruangan dan Player dijadikan method static
- menggunakan polymorpism pada saat mengeksekusi prosesAksi() untuk objItem
- Class pintu dijadikan subclass dari class Item
- objek pintu dalam ruangan diinput ke arrItem
- item roti pada ruangan dijadikan objek dari class makanan 
- menggunakan override method pada class pintu dan makanan yang merupakan subclass dari class Item
- sedikit merapihkan dengan mengelompokkan method berdasarkan fungsinya
- keluar ruangan menjadi misi utama player
# Tambahan
- menambahkan Class Makanan inheritance dari class Item
- menambahkan sistem penambahan dan pengurangan kesehatan player
    - makanan beracun (-)10 poin kesehatan Player
    - makanan sehat (+)10 poin kesehatan Player
    - sekali gagal menebak angka pada quest (-)20 poin kesehatan Player
- menambahkan class Quest
- menambahkan Quest/miniGame saat ingin mengambil kunci dari NPC
- menambahkan kondisi gameover yaitu saat player kehabisan tenaga untuk menyelesaikan quest
# Penjelasan Perubahan yang sudah dilakukan
- class Pintu dijadikan subclass dari class Item agar dapat memudahkan implementasi polymorphism pada method pilihanAksi() di class Ruangan.
- Membuat class Aksi() yang berfungsi menyimpan method-method yang digunakan untuk menampilkan dan meproses aksi di class Ruangan dan class Player. method aksinya dijadikan    static agar bisa langsung dipakai tanpa harus membuat objeknya.
```
    import java.util.ArrayList;
    import java.util.Scanner;
    
    public class Aksi {
       static Scanner sc = new Scanner(System.in);
       
       static void printPilihan(ArrayList<String> arrPil, int subPil, int urutPil){
           for (String strPil : arrPil) {
               subPil++;
               System.out.printf("%d%d. %s %n", urutPil, subPil, strPil);
           }
       }

        //method untuk print pilihan dari semua objek dalam 1 array
       static int printPilihanItem (ArrayList<Item> arrItem, int urutPil){
           for (Item objItem:arrItem) {
               urutPil++;
               int subPil = 0;   //sistem penomorannya 11  12  13 dst
               System.out.println(objItem.getNama());
               //ambil pilihannya
               ArrayList <String> arrPil = objItem.getAksi();
               //print pilihan
               Aksi.printPilihan(arrPil, subPil, urutPil);
           }
           return urutPil;          //nilai urutan pilihannya dikembalikan
       }

       //scanner (inputan user)
       static int[] Scanner (){
           //masukkan pilihan
           System.out.print("Pilihan anda?");
           String strPil = sc.next();
           System.out.println("--");

           //split pilihan dan subpilihan
           int pil    =  Integer.parseInt(strPil.substring(0,1)); //ambil digit pertama, asumsikan jumlah tidak lebih dari 10
           int subPil = Integer.parseInt(strPil.substring(1, 2)); //ambil digit kedua, asumsikan jumlah tidak lebih dari 10
           int[] arrIndexPil = new int[2];
           arrIndexPil[0] = pil;
           arrIndexPil[1] = subPil;

           return arrIndexPil;
           //nilai yang dikembalikan adalah nilai array yang berisi angka hasil split pilihan
       }
    }
```
- contoh implementasi pemakaian method printAksi(), printAksiItem(), dan Scanner() pada method pilihanAksi() di class Ruangan. Adapun pilihanAksi() pada class Player kurang lebih sama seperti ini pengaplikasiannya
```
    int urutPil = 0;  //item, pintu
        int subPil = 0;   //aksinya

        //aksi2 item dan pintu
        System.out.println("Item di ruangan");
        urutPil = Aksi.printPilihanItem(arrItem, urutPil);      //method dari class Aksi
```
pada penampilan pilihan aksi NPC:
```
        System.out.println("NPC");
        urutPil++;
        int pilNPC  = urutPil; //catat untuk pintu
        Aksi.printPilihan(objNPC.getAksi(), subPil, urutPil);       //method dari class Aksi
```
dan pada saat melakukan inputan pilihan user:
```
    int[] arrPil = Aksi.Scanner();          //method dari class Aksi
    //int pil = arrPil[0], int subPil = arrPil[1]       
```
- mekanisme mencari proses akasi dari pintu yang semulanya dipisahkan dengan statement if diubah dengan memakai polymorphism pada saat menampilkan pilihan aksi dan pada saat mencari proses untuk aksinya di method pilihanAksi() pada class Ruangan. 
```
        } else {
            //polymorphism
            Item objItemPilih = arrItem.get(arrPil[0]-1);
            objItemPilih.prosesAksi(arrPil[1]); //aksi item
        }
```
- mengubah akses modifier method dibuang() dan diambil() pada class item yang semulanya private menjadi proctected agar dapat diakses oleh subclassnya. Subclass dari class Item yang memakai method ini hanya class Makanan sedangkan class Pintu tidak memakainya.
```
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
```
- membuat class Makanan yang merupakan turunan dari class Item. Pada class Makanan ditambahkan method dimakan() dan atribut boolean beracun yang mana jika beracun = true maka makanan tersebut akan mengurangi kesehatan player pada saat dimakan, sedangkan jika beracun = false maka akan menambah kesehatan player jika dimakan.
- method dimakan():
```
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
```
- menambahkan pilihan aksi dimakan untuk class Makanan pada method getAksi()
```
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
```
- objRoti pada init Ruangan (pada constructor class Ruangan) dijadikan sebagai objek dari class Makanan. Objek Makanannya ditambah juga objSusuUHT yang atribut beracun = true.
```
        Makanan objRoti = new Makanan("Roti");
        objRoti.setDeskripsi("Roti rasa coklat dalam bungkusan plastik");
        objRoti.setObjRuangan(this);

        Makanan objSusuUHT = new Makanan("Susu Ultramilk");
        objSusuUHT.setBeracun(true);
        objSusuUHT.setDeskripsi("Susu UHT yang sudah kardaluasa");
        objSusuUHT.setObjRuangan(this);
```
lalu objek makanan dan pintu dimasukkan ke arrItem
```
        arrItem.add(objPintu);
        arrItem.add(objSusuUHT);
        arrItem.add(objRoti);
```
- menambahkan class Quest untuk menampung quest-quest/minigame dalam game yang akan digunakan oleh class NPC saat Player ingin mengambil kunci
method QuestTebakAngka:
```
    static boolean QuestTebakAngka(){
        boolean tertebak = false;
        int angkaDitebak = (int)(Math.random()*10); //angka random

        System.out.println("Coba tebak angka yang sedang NPC pikirkan");
        System.out.println("(batas menebak 5 kali)\n");

        //batas menebak 5x
        for (int i=0; i<5; i++){
            System.out.print("Tebakan Anda : ");    //input tebakan
            int tebak = sc.nextInt();

            if(angkaDitebak == tebak){
                tertebak = true;
                break;
            } else {
                if(tebak > angkaDitebak) {
                    System.out.println("Terlalu Besar");
                }
                else {
                    System.out.println("Terlalu kecil");
                }
            }
        }
        if (tertebak) {
            System.out.println("YEAYY! Anda berhasil menebaknya");
            return true;
        } else {
            System.out.println("Yah, kesempatan menebakmu telah habis");
            return false;
        }
    }
```
- minigame dimulai dari method prosesAksi() pada class NPC pada saat player meminta kunci
```
                } else {
                    //player harus menyelesaikan quest yang diberikan NPC
                    System.out.println("****** Selesaikan quest ini! ******\n");
                    boolean questWin = Quest.QuestTebakAngka();
                    System.out.println("***********************************");
                    ....
```
- lalu menambahkan sistem pengurangan kesehatan Player jika gagal menebak angka dalam 5 kesempatan sekali quest.
```
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
```
# Catatan
- mohon maaf untuk di class Ruangan ada commentline yang bertuliskan **//jika seperti ini, maka setiap kali objRuangan di buat NPC selalu bernama Lily**, jadi rencana awalnya NPC ingin ditambahkan atribut nama, hanya saja tidak jadi dan lupa untuk menghapus keterangannya 
