public class Main {
    public static void main(String[] args) {
        try {
            Game game = new Game();
            game.start();
        } catch (Exception e) {
            System.out.println("\nOops! something went wrong");
            System.out.println("Error: " + e.getMessage());
        }
    }
}
