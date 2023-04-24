package Exercise;

import java.time.DayOfWeek;
import java.time.LocalDate;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Exercise.Booking.BookingStatus;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public class FitnessCenterBookingSystem {

    private static ArrayList<Booking> bookings = new ArrayList<>();
    private static int capacityPerSlot = 5;
    private static Scanner sc = new Scanner(System.in);
    static Map<String, Map<String, Double>> timetable = new HashMap<>();

    public static void genTimetable() {
        String[] fitnessTypes = { "yoga", "pilates", "cardio", "zumba", "yoga", "yoga", "cardio", "cardio", "yoga",
                "pilates", "cardio", "zumba", "yoga", "pilates", "cardio", "zumba" };
        String[] days = { "Saturday", "Sunday", "Saturday", "Sunday", "Saturday", "Sunday", "Saturday", "Sunday",
                "Saturday", "Sunday", "Saturday", "Sunday", "Saturday", "Sunday", "Saturday", "Sunday" };
        String[] timeslots = { "morning", "evening", "afternoon", "morning", "evening", "afternoon", "morning",
                "evening", "afternoon", "morning", "evening", "afternoon", "morning", "evening", "afternoon",
                "morning" };
        double[] prices = { 10.0, 12.0, 8.0, 15.0, 10.0, 10.0, 8.0, 8.0, 10.0, 12.0, 8.0, 15.0, 10.0, 12.0, 8.0,
                15.0 };
    
        for (int i = 0; i < fitnessTypes.length; i++) {
            String fitness = fitnessTypes[i];
            String day = days[i];
            String timeslot = timeslots[i];
            double price = prices[i];
    
            if (!timetable.containsKey(fitness)) {
                timetable.put(fitness, new HashMap<>());
            }
    
            Map<String, Double> pricesMap = timetable.get(fitness);
    
            String dayTimeslot = day + " " + timeslot;
            pricesMap.put(dayTimeslot, price);
        }
    }
    

    public static void displayTimetableForDay(String day) {
        Map<String, Double> pricesMap;
        Double price;
    
        for (String fitness : timetable.keySet()) {
            pricesMap = timetable.get(fitness);
    
            price = pricesMap.get(day + " morning");
            if (price != null) {
                System.out.println(fitness + " class on " + day + " in the morning at " + price + " dollars");
            }
    
            price = pricesMap.get(day + " afternoon");
            if (price != null) {
                System.out.println(fitness + " class on " + day + " in the afternoon at " + price + " dollars");
            }
    
            price = pricesMap.get(day + " evening");
            if (price != null) {
                System.out.println(fitness + " class on " + day + " in the evening at " + price + " dollars");
            }
        }
    }
    
    
    public static Map<String, Double> getTimetableForFitnessType(String fitnessType) {
        if (fitnessType == null) {
            return new HashMap<>();
        } else {
            Map<String, Double> pricesMap = timetable.get(fitnessType);
            if (pricesMap == null) {
                return new HashMap<>();
            } else {
                return new HashMap<>(pricesMap);
            }
        }
    }
    

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the Fitness Center!");
            System.out.println("1. Timetable");
            System.out.println("2. Book a fitness lesson");
            System.out.println("3. View bookings");
            System.out.println("4. update or cancel booking");
            System.out.println("5. Give a review");
            System.out.println("6. Overall bookings by fitnesstype");
            System.out.println("7. Monthly lesson report");
            System.out.println("8. Generate Income By Month");
            System.out.println("9. Exit");
            System.out.print("Please enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume the extra newline character

            switch (choice) {

                case 1:

                    String[][] timetable1 = {
                            { "Feb (Weekend 1)", "Sunday morning - Yoga", "Sunday evening - Cardio",
                                    "Saturday afternoon - Pilates", "Saturday evening - Zumba" },
                            { "Feb (Weekend 2)", "Saturday morning - Pilates", "Sunday afternoon - Zumba",
                                    "Saturday afternoon - Yoga", "Sunday evening - Cardio" },
                            { "Feb (Weekend 3)", "Saturday afternoon - Yoga", "Sunday morning - Zumba",
                                    "Saturday afternoon - Pilates", "Sunday evening - Zumba" },
                            { "Feb (Weekend 4)", "Saturday evening - Cardio", "Sunday morning - Pilates",
                                    "Saturday afternoon - Yoga", "Sunday evening - Zumba" },
                            { "Mar (Weekend 5)", "Saturday morning - Yoga", "Sunday afternoon  - Pilates",
                                    "Sunday morning - Zumba", "Saturday evening - Cardio" },
                            { "Mar (Weekend 6)", "Saturday evening - Zumba", "Sunday morning - Cardio",
                                    "Sunday morning - Yoga", "Saturday morning - Pilates" },
                            { "Mar (Weekend 7)", "Saturday morning - Yoga", "Sunday morning - Zumba",
                                    "Saturday morning - Pilates", "Sunday evening - Cardio" },
                            { "Mar (Weekend 8)", "Saturday afternoon- Pilates", "Sunday afternoon - Cardio",
                                    "Saturday morning - Yoga", "Sunday morning - Zumba" }
                    };

                    // Print the timetable
                    System.out.println("Fitness Timetable for February and March:");
                    for (String[] row : timetable1) {
                        System.out
                                .println(row[0] + ": " + "\n" + row[1] + "\n" + row[2] + "\n" + row[3] + "\n" + row[4]);
                    }

                    genTimetable(); // Initialize the timetableList
                    // System.out.println("The Timetable is for February and March");
                    System.out.println("1. Specify day\n2. Specify fitness type");
                    String input = sc.next();

                    if (input.equals("1")) {
                        System.out.print("Enter the day you want to view the timetable for (Saturday or Sunday): ");
                        String day = sc.next();

                        displayTimetableForDay(day); // Display the timetable for the user-specified day
                    } else if (input.equals("2")) {
                         System.out.print("Enter the fitness type you want to view the timetable for (yoga, pilates, cardio, or zumba) for upcoming class: ");
                         String fitnessType = sc.next();
                      Map<String, Double> timetableForFitnessType = getTimetableForFitnessType(fitnessType);
                       if (timetableForFitnessType.isEmpty()) {
                         System.out.println("No classes available for the specified fitness type.");
                    } else {
                     System.out.println("Timetable for " + fitnessType + " classes:");
                     for (String lesson : timetableForFitnessType.keySet()) {
                     double price = timetableForFitnessType.get(lesson);
                    System.out.println(lesson + " on " + price);
        }
    }
}
                    
                    break;

                case 2:
                    Booking(sc);
                    break;
                case 3:
                    viewBookingsofthelesson();
                    break;
                case 4:
                    updateOrCancelBooking(sc);
                    break;
                case 5:
                    GiveReview(sc);
                    break;

                case 6:
                    BookingsAndAverageRating();
                    break;
                case 7:
                    AverageRatingByMonth(sc, bookings);

                    break;
                case 8:
                    IncomeByMonth(sc);
                    break;

                case 9:

                    while (true) {
                        System.out.println("Do you want to continue? (Y/N)");
                        String input1 = sc.nextLine();
                        if (input1.equalsIgnoreCase("N")) {
                            System.exit(0);
                        } else if (input1.equalsIgnoreCase("Y")) {
                            break;
                        } else {
                            System.out.println("Invalid input. Please enter Y or N.");
                        }
                    }
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;

            }
        }
    }

    private static void Booking(Scanner sc) {
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.print("Enter fitness type (yoga, pilates,zumba or cardio): ");
        String fitnessType = sc.nextLine();// Check if there is an existing booking for the same person and fitness type
        boolean hasExistingBooking = false;
for (Booking booking : bookings) {
    if (booking.getName().equals(name) && booking.getFitnessType().equals(fitnessType)) {
        hasExistingBooking = true;
        break;
    }
}
if (hasExistingBooking) {
    System.out.println("You have already booked for this fitness type.");
    return;
}
        System.out.print("Enter booking date is for the Month( Febraury-02 and March-03 )(YYYY-MM-DD): ");

        LocalDate date = LocalDate.parse(sc.nextLine());
        System.out.print("Enter booking weekend day(Sunday or Saturday): ");

        String day = sc.nextLine();

        System.out.print("Enter time slot (morning, afternoon, or evening): ");
        String timeSlot = sc.nextLine();

        if (!checkCapacity(fitnessType, timeSlot)) {
            System.out.println("The fitness center is fully booked for this time slot.");
            return;
        }

        int id = generateBookingId();
        Booking newBooking = new Booking(id, name, date, day, fitnessType, timeSlot);
        bookings.add(newBooking);

        System.out.println("Booking made successfully. Your booking ID is " + String.valueOf(id) + ".");
    }

    private static void viewBookingsofthelesson() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
        } else {
            System.out.printf(" %-20s %-20s %-15s %-15s %n", "Booking ID", "Name", "Fitness Type", "Weekend",
                    "Time Slot");
            for (Booking booking : bookings) {
                System.out.printf(" %-20s %-20s %-15s %-15s %n", booking.getId(), booking.getName(),
                        booking.getFitnessType(), booking.getDay(), booking.getStatus());
            }
        }
    }

    private static void updateOrCancelBooking(Scanner sc) {
        System.out.print("Enter booking ID: ");
        int bookingId = sc.nextInt();
        sc.nextLine(); // consume the extra newline character

        Booking bookingToModify = null;

        // Find the booking to modify based on the booking ID entered by the user
        for (Booking booking : bookings) {
            if (booking.getId() == bookingId) {
                bookingToModify = booking;
                break;
            }
        }

        if (bookingToModify == null) {
            System.out.println("Booking ID not found.");
            return;
        }

        System.out.println("Enter 1 to modify the booking or 2 to cancel the booking: ");
        int userChoice = sc.nextInt();
        sc.nextLine(); // consume the extra newline character

        if (userChoice == 1) { // Modify booking
            System.out.print("Enter new fitness type (yoga, pilates, or cardio): ");
            String newFitnessType = sc.nextLine();

            System.out.print("Enter new time slot (morning, afternoon, or evening): ");
            String newTimeSlot = sc.nextLine();

            // Check if the fitness center is fully booked for the new time slot
            if (!checkCapacity(newFitnessType, newTimeSlot)) {
                System.out.println("The fitness center is fully booked for this time slot.");
                return;
            }

            // Check if the booking has already been cancelled or attended and cannot be
            // modified
            if (bookingToModify.getStatus() != null && bookingToModify.getStatus().equals("cancelled")) {
                System.out.println("This booking has been cancelled and cannot be modified.");
                return;
            }

            if (bookingToModify.getStatus() != null && bookingToModify.getStatus().equals("attended")) {
                System.out.println("This booking has been attended and cannot be modified.");
                return;
            }

            // release the capacity of the original booking before modifying it
            releaseCapacity(bookingToModify.getFitnessType(), bookingToModify.getTimeSlot());

            // modify the booking with the new fitness type, time slot, and set the status
            // to "changed"
            bookingToModify.setFitnessType(newFitnessType);
            bookingToModify.setTimeSlot(newTimeSlot);
            bookingToModify.setStatus(Booking.BookingStatus.CHANGED);

            System.out.println("Booking modified successfully.");
        } else if (userChoice == 2) { // Cancel booking
            // Check if the booking has already been cancelled or attended and cannot be
            // cancelled
            if (bookingToModify.getStatus() != null && (bookingToModify.getStatus().equals("cancelled")
                    || bookingToModify.getStatus().equals("attended"))) {
                System.out
                        .println("This booking has already been cancelled or attended and cannot be cancelled again.");
                return;
            }

            // release the capacity of the booking to be cancelled
            releaseCapacity(bookingToModify.getFitnessType(), bookingToModify.getTimeSlot());

            // remove the booking from the list of bookings
            bookings.remove(bookingToModify);

            System.out.println("Booking cancelled successfully.");
        } else {
            System.out.println("Invalid choice.");
            return;
        }
    }

    private static void GiveReview(Scanner sc) {
        System.out.print("Enter booking ID: ");// Prompt user to enter booking ID and validate booking
        int bookingId = sc.nextInt();
        sc.nextLine(); // consume the extra newline character

        Booking bookingToReview = null;

        for (Booking booking : bookings) {
            if (booking.getId() == bookingId) {
                bookingToReview = booking;
                break;
            }
        }

        if (bookingToReview == null) {
            System.out.println("Booking ID not found.");
            return;
        }
        System.out.print("Did you attend the Fitnesstype? Type (y/n): ");
        String attendance = sc.nextLine();
        if (!attendance.toLowerCase().equals("y")) {
            System.out.println("You cannot leave a review for a fitnesstype you have not attended.");
            return;
        }

        if (bookingToReview.getStatus() == BookingStatus.CANCELLED) {// Check if booking was cancelled and cannot be
                                                                     // reviewed
            System.out.println("This booking has been cancelled and cannot be reviewed.");
            return;
        }
        System.out.print("Enter your rating (1-5): ");
        int rating = sc.nextInt();
        sc.nextLine(); // consume the extra newline character

        if (rating < 1 || rating > 5) {
            System.out.println("Invalid rating! Please enter a rating between 1 and 5.");
            return;
        }

        System.out.print("Type Very dissatisfied, Dissatisfied, Ok,  Satisfied, Very Satisfied\n");
        System.out.print("Enter your comments: ");
        String review = sc.nextLine();// Update booking's rating and review

        bookingToReview.setRating(rating);
        bookingToReview.setReview(review);

        System.out.println("Thank you for attending the class and leaving a review.");
    }

    private static boolean checkCapacity(String fitnessType, String timeSlot) {
        int count = 0;
        for (Booking booking : bookings) {
            if (booking.getFitnessType().equals(fitnessType) && booking.getTimeSlot().equals(timeSlot)
                    && booking.getStatus() == BookingStatus.BOOKED) {
                count++;
            }
        }
        return count < 5; // maximum capacity per slot is 5
    }

    private static void releaseCapacity(String fitnessType, String timeSlot) {
        for (Booking booking : bookings) {
            if (booking.getFitnessType().equals(fitnessType) && booking.getTimeSlot().equals(timeSlot)
                    && booking.getStatus() == BookingStatus.CHANGED) {
                booking.setStatus(BookingStatus.BOOKED); // set status to "booked" again
                break;
            }
        }
    }

    private static int generateBookingId() {
        if (bookings.isEmpty()) {
            return 1;
        } else {
            // find the largest existing ID and increment by 1
            int maxId = bookings.get(0).getId();
            for (Booking booking : bookings) {
                if (booking.getId() > maxId) {
                    maxId = booking.getId();
                }
            }
            return maxId + 1;
        }
    }

    private static void BookingsAndAverageRating() {

        // Add static data of fitness types
        String[] fitnessTypes = { "yoga", "pilates", "cardio", "Zumba" };

        // Create a Map to store the number of bookings for each fitness type
        Map<String, Integer> numBookingsByFitnessType = new HashMap<>();

        // Initialize the Map with the static data
        for (String fitnessType : fitnessTypes) {
            numBookingsByFitnessType.put(fitnessType, 0);
        }

        // Create a Map to store the total rating for each fitness type
        Map<String, Double> totalRatingByFitnessType = new HashMap<>();
        // Loop through the bookings and update the Maps
        for (Booking booking : bookings) {
            String fitnessType = booking.getFitnessType();
            String day = booking.getDay(); // get the day of the current booking
            numBookingsByFitnessType.put(fitnessType, numBookingsByFitnessType.getOrDefault(fitnessType, 0) + 1);
            if (booking.getRating() != 0) {
                double totalRating = totalRatingByFitnessType.getOrDefault(fitnessType, 0.0);
                totalRatingByFitnessType.put(fitnessType, totalRating + booking.getRating());
            }
            // Print the average rating for the current fitness type
            int numBookings = numBookingsByFitnessType.get(fitnessType);
            double averageRating = 0.0;
            if (totalRatingByFitnessType.containsKey(fitnessType)) {
                double totalRating = totalRatingByFitnessType.get(fitnessType);
                averageRating = totalRating / numBookings;
            }
            System.out.println(fitnessType + ": " + numBookings + " bookings, " + day + " "
                    + String.format("%.2f", averageRating) + " average rating");
        }
    }
   private static void AverageRatingByMonth(Scanner sc, List<Booking> bookings) {
    System.out.print("Enter month (in numeric format, e.g. 01 for January): ");
    String monthInput = sc.nextLine();
    int month = Integer.parseInt(monthInput);

    Map<String, List<Integer>> ratingsByType = new HashMap<>();
    Map<String, Integer> bookingsByType = new HashMap<>();
    for (Booking booking : bookings) {
        if (getMonthValue(booking.getDate()) == month) {
            String type = booking.getFitnessType();
            int rating = booking.getRating();
            if (rating != 0) {
                ratingsByType.computeIfAbsent(type, new Function<String, List<Integer>>() {
                    @Override
                    public List<Integer> apply(String k) {
                        return new ArrayList<>();
                    }
                }).add(rating);
            }
            bookingsByType.put(type, bookingsByType.getOrDefault(type, 0) + 1);
        }
    }

    if (bookingsByType.isEmpty()) {
        System.out.println("No bookings in month " + monthInput + ".");
    } else {
        System.out.printf("Number of bookings and average rating by fitness type:%n");
        for (String type : bookingsByType.keySet()) {
            List<Integer> ratings = ratingsByType.getOrDefault(type, new ArrayList<>());
            double averageRating = ratings.stream().mapToInt(new ToIntFunction<Integer>() {
                @Override
                public int applyAsInt(Integer value) {
                    return value.intValue();
                }
            }).average().orElse(0);
            int numBookings = bookingsByType.getOrDefault(type, 0);
            String dayOfWeek = null;
            for (Booking booking : bookings) {
                if (getMonthValue(booking.getDate()) == month && booking.getFitnessType().equals(type)) {
                    dayOfWeek = booking.getDay();
                    break;
                }
            }
            System.out.printf("%s: %d bookings, %s %d average rating%n", type, numBookings, dayOfWeek,
                    Math.round(averageRating));
        }
    }
}

    
    private static int getMonthValue(LocalDate localDate) {
        return localDate.getMonthValue();
    }

    private static void IncomeByMonth(Scanner sc) {
        System.out.print("Enter month (in numeric format, e.g. 01 for January): ");
        String monthInput = sc.nextLine();
        int month = Integer.parseInt(monthInput);
    
        Map<String, Double> incomeByType = new HashMap<>();
        for (Booking booking : bookings) {
            if (getMonthValue(booking.getDate()) == month) {
                String type = booking.getFitnessType();
                double price = booking.getPrice();
                incomeByType.merge(type, price, new BiFunction<Double, Double, Double>() {
                    @Override
                    public Double apply(Double oldValue, Double newValue) {
                        return oldValue + newValue;
                    }
                });
            }
        }
    
        if (incomeByType.isEmpty()) {
            System.out.println("No bookings in month " + monthInput + ".");
        } else {
            System.out.printf("Income by fitness type in month %s:%n", monthInput);
            double highestIncome = 0;
            String highestType = "";
            for (String type : incomeByType.keySet()) {
                double income = incomeByType.get(type);
                if (income > highestIncome) {
                    highestIncome = income;
                    highestType = type;
                }
                System.out.printf("%s: %.2f%n", type, income);
            }
            System.out.printf("The highest income was generated by %s fitnesstype: %.2f%n", highestType, highestIncome);
        }
    }
    

}
