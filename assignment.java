import java.util.Scanner;

class WeatherAnalyzer {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        double[][] temperatures = {
            {18.5, 19.1, 20.3, 21.7, 22.4, 23.0, 23.6, 24.2, 24.8, 25.5, 26.1, 26.7, 27.3, 27.9, 28.5, 29.1, 29.7, 30.3, 30.9, 31.5, 32.1, 32.7, 33.3, 33.9}, // Monday
            {17.8, 18.3, 19.7, 20.9, 21.5, 22.0, 22.6, 23.2, 23.8, 24.4, 25.0, 25.6, 26.2, 26.8, 27.4, 28.0, 28.6, 29.2, 29.8, 30.4, 31.0, 31.6, 32.2, 32.8}, // Tuesday
            {16.9, 17.5, 18.1, 18.7, 19.3, 19.9, 20.5, 21.1, 21.7, 22.3, 22.9, 23.5, 24.1, 24.7, 25.3, 25.9, 26.5, 27.1, 27.7, 28.3, 28.9, 29.5, 30.1, 30.7}, // Wednesday
            {19.2, 20.1, 21.0, 21.8, 22.7, 23.5, 24.3, 25.2, 26.0, 26.8, 27.6, 28.4, 29.3, 30.1, 30.9, 31.7, 32.5, 33.3, 34.1, 34.9, 35.7, 36.5, 37.3, 38.1}, // Thursday
            {18.0, 18.9, 19.8, 20.6, 21.4, 22.3, 23.1, 24.0, 24.8, 25.6, 26.4, 27.3, 28.1, 28.9, 29.7, 30.6, 31.4, 32.2, 33.0, 33.8, 34.7, 35.5, 36.3, 37.1}, // Friday
            {20.1, 21.0, 21.9, 22.8, 23.7, 24.6, 25.5, 26.4, 27.3, 28.2, 29.1, 30.0, 30.9, 31.8, 32.7, 33.6, 34.5, 35.4, 36.3, 37.2, 38.1, 39.0, 39.9, 40.8}, // Saturday
            {21.3, 22.1, 22.9, 23.8, 24.7, 25.6, 26.5, 27.4, 28.3, 29.2, 30.1, 31.0, 31.9, 32.8, 33.7, 34.6, 35.5, 36.4, 37.3, 38.2, 39.1, 40.0, 40.9, 41.8}  // Sunday
        };

        char continueChoice;

        do {
            Menu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    displayDayTemperature(temperatures, "Minimum");
                    break;
                case 2:
                    displayDayTemperature(temperatures, "Maximum");
                    break;
                case 3:
                    displayDayTemperature(temperatures, "Average");
                    break;
                case 4:
                    displayWeekTemperature(temperatures, "Minimum");
                    break;
                case 5:
                    displayWeekTemperature(temperatures, "Maximum");
                    break;
                case 6:
                    displayWeekTemperature(temperatures, "Average");
                    break;
                case 7:
                    getTemperatureForGivenDay(temperatures, "Minimum", scanner);
                    break;
                case 8:
                    getTemperatureForGivenDay(temperatures, "Maximum", scanner);
                    break;
                case 9:
                    getTemperatureForGivenDay(temperatures, "Average", scanner);
                    break;
                case 0:
                    System.out.println("Exiting program. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 0 and 9.");
            }

            System.out.print("Continue (y/n)? ");
            continueChoice = scanner.next().charAt(0);
        } while (continueChoice == 'y' || continueChoice == 'Y');

        printEndOfProgram();
    }

    static void Menu() {
        starLine(45, '*');
        System.out.println("Choose Temperature Menu of Weather Station : ");
        starLine(45, '*');
        System.out.println("1. Minimum temperature for each day");
        System.out.println("2. Maximum temperature for each day");
        System.out.println("3. Average temperature for each day");
        System.out.println("4. Minimum temperature for the week");
        System.out.println("5. Maximum temperature for the week");
        System.out.println("6. Average temperature for the week");
        System.out.println("7. Minimum temperature for a given day");
        System.out.println("8. Maximum temperature for a given day");
        System.out.println("9. Average temperature for a given day");
        System.out.println("0. Exit");
        starLine(45, '*');
        System.out.print("Enter your choice: ");
    }

    static void starLine(int n, char ch) {
        for (int i = 1; i <= n; i++) 
            System.out.print(ch);
        System.out.println();
    }

    static int getUserChoice() {
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    static void displayDayTemperature(double[][] temperatures, String resultType) {
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        for (int i = 0; i < days.length; i++) {
            double result = calculateResult(temperatures[i], resultType);
            System.out.println(days[i] + " - " + resultType + " temperature: " + result + "\u00B0C");
        }
    }

    static void displayWeekTemperature(double[][] temperatures, String resultType) {
        double result = calculateResult(temperatures, resultType);
        System.out.println(resultType + " temperature for the week: " + result + "\u00B0C");
    }

    static double calculateResult(double[][] temperatures, String resultType) {
        double result = -1.0; // Sentinel value indicating an error
        switch (resultType) {
            case "Minimum":
                result = findOverallMin(temperatures); 
                break;
            case "Maximum":
                result = findOverallMax(temperatures); 
                break;
            case "Average":
                result = findOverallAvg(temperatures); 
                break;
        }
        return result; // Return the calculated result or the sentinel value
    }

    static double calculateResult(double[] temperatures, String resultType) {
        double result = -1.0; // Sentinel value indicating an error
        switch (resultType) {
            case "Minimum":
                result = findMin(temperatures);
                break;
            case "Maximum":
                result = findMax(temperatures);
                break;
            case "Average":
                result = findDayAvg(temperatures);
                break;
        }
        return result; // Return the calculated result or the sentinel value
    }

    // Calculate minimum temperature for the entire week
    static double findOverallMin(double[][] temperatures) {
        double min = temperatures[0][0];
        for (int i = 0; i < temperatures.length; i++) {
            for (int j = 0; j < temperatures[i].length; j++) {
                if (temperatures[i][j] < min) {
                    min = temperatures[i][j];
                }
            }
        }
        return min;
    }

    // Calculate maximum temperature for the entire week
    static double findOverallMax(double[][] temperatures) {
        double max = temperatures[0][0];
        for (int i = 0; i < temperatures.length; i++) {
            for (int j = 0; j < temperatures[i].length; j++) {
                if (temperatures[i][j] > max) {
                    max = temperatures[i][j];
                }
            }
        }
        return max;
    }

    // Calculate average temperature for the entire week
    static double findOverallAvg(double[][] temperatures) {
        double sum = 0;
        int count = 0;
        for (int i = 0; i < temperatures.length; i++) {
            for (int j = 0; j < temperatures[i].length; j++) {
                sum += temperatures[i][j];
                count++;
            }
        }
        return sum / count;
    }

    // Calculate minimum temperature for a given day
    static double findMin(double[] temperatures) {
        double min = temperatures[0];
        for (int i = 1; i < temperatures.length; i++) {
            if (temperatures[i] < min) {
                min = temperatures[i];
            }
        }
        return min;
    }

    // Calculate maximum temperature for a given day
    static double findMax(double[] temperatures) {
        double max = temperatures[0];
        for (int i = 1; i < temperatures.length; i++) {
            if (temperatures[i] > max) {
                max = temperatures[i];
            }
        }
        return max;
    }

     // Calculate average temperature for a given day
    static double findDayAvg(double[] temperatures) {
        double sum = 0;
        for (int i = 0; i < temperatures.length; i++) {
            sum += temperatures[i];
        }
        return sum / temperatures.length;
    }

    // Calculate minimum temperature for a given day
    static double findDayMin(double[][] temperatures, int dayIndex) {
        double min = temperatures[0][dayIndex];
        for (int i = 1; i < temperatures.length; i++) {
            if (temperatures[i][dayIndex] < min) {
                min = temperatures[i][dayIndex];
            }
        }
        return min;
    }

    // Calculate maximum temperature for a given day
    static double findDayMax(double[][] temperatures, int dayIndex) {
        double max = temperatures[0][dayIndex];
        for (int i = 1; i < temperatures.length; i++) {
            if (temperatures[i][dayIndex] > max) {
                max = temperatures[i][dayIndex];
            }
        }
        return max;
    }


    // Get user input for day and display corresponding result
    static void getTemperatureForGivenDay(double[][] temperatures, String resultType, Scanner scanner) {
        System.out.print("Enter the day (0 for Monday, 1 for Tuesday, ..., 6 for Sunday) : ");
        int dayIndex = scanner.nextInt();
        if (dayIndex < 0 || dayIndex > 6) {
            System.out.println("There Is Only 7Days So, Please Enter a Number Between 0 and 6.");
            return;
        }
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        double result = calculateResult(temperatures[dayIndex], resultType);
        System.out.println("For " + days[dayIndex] + ", " + resultType + " temperature: " + result + "\u00B0C");
    }

    static void printEndOfProgram() {
        System.out.println("End of program.");
    }
}
