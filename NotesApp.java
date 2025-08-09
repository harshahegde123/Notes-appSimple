import java.io.*;
import java.util.Scanner;

public class NotesApp {
    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Notes App =====");
            System.out.println("1. Add Note");
            System.out.println("2. View Notes");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addNote(sc);
                    break;
                case 2:
                    viewNotes();
                    break;
                case 3:
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }

        } while (choice != 3);

        sc.close();
    }

    // Add a note to the file
    private static void addNote(Scanner sc) {
        System.out.print("Enter your note: ");
        String note = sc.nextLine();

        try (FileWriter fw = new FileWriter(FILE_NAME, true)) { // append mode
            fw.write(note + "\n");
            System.out.println("Note saved successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // View all notes from the file
    private static void viewNotes() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("\n--- Your Notes ---");
            boolean hasNotes = false;
            while ((line = br.readLine()) != null) {
                hasNotes = true;
                System.out.println("- " + line);
            }
            if (!hasNotes) {
                System.out.println("No notes found.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("No notes found. Start adding some!");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
