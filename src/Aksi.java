//Nama  : Warda Azzahra
//NIM   : 1902810
//Kelas : Pendidikan Ilmu Komputer 4B (sesi 2)
//MOdifikasi game RPG dari code https://github.com/yudiwbs/rpgV3

import java.util.ArrayList;
import java.util.Scanner;

//class ini berisi method untuk mempersingkat source code pada method pilihanAksi() di ruangan dan player
public class Aksi {
   static Scanner sc = new Scanner(System.in);

   //method untuk print pilihan dalam 1 objek
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
