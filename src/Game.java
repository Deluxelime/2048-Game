
public class Game {
    public static void main(String[] args) {
        Board b = new Board();
        b.spawnTile();
        b.spawnTile();
        Gui gui = new Gui(b);
        gui.updateBoard(b);
    }
}
