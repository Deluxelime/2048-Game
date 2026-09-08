import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Board b = new Board();
        b.spawnTile();
        b.spawnTile();
        Scanner scan = new Scanner(System.in);
        while (true) {
            b.printBoard();
            System.out.println("Enter a move (w/a/s/d): ");
            String input = scan.nextLine();
            // Process the user input and call the appropriate move method
            switch (input) {
                case "w":
                    b.moveUp();
                    b.spawnTile();
                    break;
                case "a":
                    b.moveLeft();
                    b.spawnTile();
                    break;
                case "s":
                    b.moveDown();
                    b.spawnTile();
                    break;
                case "d":
                    b.moveRight();
                    b.spawnTile();
                    break;
                default:
                    System.out.println("Invalid input!");
            }
            if (b.hasWon()) {
                System.out.println("Congratulations! You've reached 2048!");
                break;
            }
            if (!b.canMove()) {
                System.out.println("Game over!");
                break;
            }
        }
    }
}
