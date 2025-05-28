import java.util.Scanner;
abstract class Timetable {
    public abstract void displayTimetable();
    public abstract void setSubject(int dayIndex, int periodIndex, String subject);
}
class TimetableManager extends Timetable {
    private String[] days;
    private String[] periods;
    private String[][] timetable;

    public TimetableManager() {
        this.days = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        this.periods = new String[]{"class 1", "class 2", "class 3", "class 4", "class 5", "class 6"};
        this.timetable = new String[5][6];
        initializeTimetable();
    }
    private void initializeTimetable() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                timetable[i][j] = "Add Subject";
            }
        }
    }
    @Override
    public void displayTimetable() {
        System.out.printf("%-10s", "Day");
        for (String period : periods) {
            System.out.printf("%-15s", period);
        }
        System.out.println();

        for (int i = 0; i < days.length; i++) {
            System.out.printf("%-10s", days[i]);
            for (int j = 0; j < periods.length; j++) {
                System.out.printf("%-15s", timetable[i][j]);
            }
            System.out.println();
        }
    }
    @Override
    public void setSubject(int dayIndex, int periodIndex, String subject) {
        if (isValidIndex(dayIndex, periodIndex)) {
            timetable[dayIndex][periodIndex] = subject;
        } else {
            System.out.println("Invalid day or period index!");
        }
    }
    public String getSubject(int dayIndex, int periodIndex) {
        if (isValidIndex(dayIndex, periodIndex)) {
            return timetable[dayIndex][periodIndex];
        } else {
            return "Invalid";
        }
    }
    private boolean isValidIndex(int dayIndex, int periodIndex) {
        return dayIndex >= 0 && dayIndex < days.length && periodIndex >= 0 && periodIndex < periods.length;
    }
    public String[] getDays() {
        return days;
    }
    public String[] getPeriods() {
        return periods;
    }
}
public class SchoolTimeTable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TimetableManager timetable = new TimetableManager();
        while (true) {
            System.out.println("\n--- School Timetable Menu ---");
            System.out.println("1. Display Timetable");
            System.out.println("2. Add/Edit Subject");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
                case 1:
                    timetable.displayTimetable();
                    break;
                case 2:
                    System.out.println("Select Day:");
                    String[] days = timetable.getDays();
                    for (int i = 0; i < days.length; i++) {
                        System.out.println((i + 1) + ". " + days[i]);
                    }
                    int dayIndex = scanner.nextInt() - 1;

                    System.out.println("Select Period:");
                    String[] periods = timetable.getPeriods();
                    for (int i = 0; i < periods.length; i++) {
                        System.out.println((i + 1) + ". " + periods[i]);
                    }
                    int periodIndex = scanner.nextInt() - 1;

                    scanner.nextLine(); 
                    System.out.print("Enter Subject Name: ");
                    String subject = scanner.nextLine();

                    timetable.setSubject(dayIndex, periodIndex, subject);
                    break;
                case 3:
                    System.out.println("Exiting Program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
