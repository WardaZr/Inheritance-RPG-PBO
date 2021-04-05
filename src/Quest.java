//Nama  : Warda Azzahra
//NIM   : 1902810
//Kelas : Pendidikan Ilmu Komputer 4B (sesi 2)
//MOdifikasi game RPG dari code https://github.com/yudiwbs/rpgV3

import java.util.Scanner;

//class ini berfungsi menyimpan berbagai quest
public class Quest {
    static Scanner sc = new Scanner(System.in);

    //game menebak angka
    //dibuat static agar tidak perlu membuat objeknya
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
}