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
