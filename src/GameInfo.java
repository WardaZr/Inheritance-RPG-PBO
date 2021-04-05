//Nama  : Warda Azzahra
//NIM   : 1902810
//Kelas : Pendidikan Ilmu Komputer 4B (sesi 2)
//MOdifikasi game RPG dari code https://github.com/yudiwbs/rpgV3

public class GameInfo {
    private Boolean isGameOver = false;     //jika true, maka game berakhir
    private Player objPlayer;
    private Ruangan objRuangan; //ruangan aktif

    // =================== GETTER SETTER ===================
    public Ruangan getObjRuangan() {
        return objRuangan;
    }
    public void setObjRuangan(Ruangan objRuangan) {
        this.objRuangan = objRuangan;
    }

    public Player getObjPlayer() {
        return objPlayer;
    }
    public void setObjPlayer(Player objPlayer) {
        this.objPlayer = objPlayer;
    }

    public Boolean getGameOver() {
        return isGameOver;
    }
    public void setGameOver(Boolean gameOver) {
        isGameOver = gameOver;
    }
}
