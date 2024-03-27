/*Unit - COS10033 - Advanced Programming
Assignment 1 Part C - 4th August 2023
Avery Porter - s104416957
This program is designed to create a booking system for a Cinema. 
It requires the user to enter the necessary details for the Cinema, and then will take them to a Menu.
In the Menu the user can display what seats are available, book tickets, refund tickets and 
display a statistic report until they decide to exit the program
*/

import java.util.InputMismatchException;
import java.util.Scanner;

// Defines the Cinema class
class Cinema {
    // Private attributes
    private int Rows;
    private String Date;
    private double SPrice;
    private double PPrice;
    private double FPrice;
    private int Seats;
    
    private char[] SeatStatus;
    private int totalPrice;
    private int booked;
    private int unbooked;

    // Constructor for the Cinema class
    public Cinema(int Rows, String Date, double SPrice, double PPrice, double FPrice) {
        // Initialise the variables
        this.Rows = Rows;
        this.Date = Date;
        this.SPrice = SPrice;
        this.PPrice = PPrice;
        this.FPrice = FPrice;
        this.Seats = Rows * 10;

        // Initialise the SeatStatus array with '-' representing unbooked seats
        this.SeatStatus = new char[Seats];
        for (int i = 0; i < Seats; i++) {
            SeatStatus[i] = '-';
        }

        this.booked = 0;
        this.unbooked = Seats;
        this.totalPrice = 0;
    }

    // Get methods for attributes of the Cinema class
    public String getDate() {
        return this.Date;
    }

    public double getS() {
        return this.SPrice;
    }

    public double getP() {
        return this.PPrice;
    }

    public double getF() {
        return this.FPrice;
    }

    public char[] getSeats() {
        return this.SeatStatus;
    }

    public int getBooked() {
        return this.booked;
    }

    public int getUnbooked() {
        return this.unbooked;
    }

    // This displays the available seats in the Cinema as a graphic
    public void displaySeats() {
        int k = 1;
        System.out.println("\t\tScreen");
        for (int i = 0; i < Rows; i++) {
            System.out.println("");
            for (int j = 0; j < 10; j++) {
                switch (SeatStatus[k - 1]) {
                    case 'S':
                        System.out.print(k + ":" + "B ");
                        break;
                    case 'P':
                        System.out.print(k + ":" + "B ");
                        break;
                    case 'F':
                        System.out.print(k + ":" + "B ");
                        break;
                    default:
                        System.out.print(k + ":- ");
                        break;
                }
                k++;
            }
        }
        System.out.println("\n\nNumber of available seats: " + unbooked);
    }

    // Stores the details when booking a ticket
    public void BookTicket(int SeatNum, char type) {
        this.SeatStatus[SeatNum] = type;
    }

    // Generates a receipt after the booking is successful
    public void ticketReceipt(int totalS, int totalP, int totalF, int ticket, String S, String P, String F,
            double total) {
        System.out.println("");
        System.out.println("\tReceipt");
        System.out.println("\t*******\n");
        System.out.println("Date: " + Date);
        System.out.println("Number of Tickets booked: " + ticket + "\n");
        System.out.println("\t" + totalS + " * Standard\t\t@ $" + SPrice + "0\t= $ " + SPrice * totalS + "0 seat(s) " + S);
        System.out.println("\t" + totalP + " * Pensioner\t\t@ $" + PPrice + "0\t= $ " + PPrice * totalP + "0 seat(s) " + P);
        System.out.println("\t" + totalF + " * Frequent Patron\t@ $" + FPrice + "0\t= $ " + FPrice * totalF + "0 seat(s) "
                + F);
        System.out.println("\t\t---------------");
        System.out.println("\t\tTotal : $" + total + "0\n");
        System.out.println("");
        totalPrice += total;
        booked += ticket;
        unbooked -= ticket;
    }

    // Stores details when refunding a ticket
    public void RefundTicket(int SeatNum) {
        this.SeatStatus[SeatNum] = '-';
    }

    // Generates a receipt after the refund is successful
    public void refundReceipt(int totalS, int totalP, int totalF, int refund, String S, String P, String F,
            double total) {
        System.out.println("");
        System.out.println("\t\tReceipt");
        System.out.println("\t\t*******\n");
        System.out.println("Date: " + Date);
        System.out.println("Number of Tickets refunded: " + refund + "\n");
        System.out.println("\t" + totalS + " * Standard\t\t@ $" + SPrice + "0\t= $ " + SPrice * totalS + "0 seat(s) " + S);
        System.out.println("\t" + totalP + " * Pensioner\t\t@ $" + PPrice + "0\t= $ " + PPrice * totalP + "0 seat(s) " + P);
        System.out.println("\t" + totalF + " * Frequent Patron\t@ $" + FPrice + "0\t= $ " + FPrice * totalF + "0 seat(s) "
                + F);
        System.out.println("\t\t---------------");
        System.out.println("\t\tTotal : $" + total + "0\n");
        System.out.println("");
        totalPrice -= total;
        booked -= refund;
        unbooked += refund;
    }

    // Displays the Sales report
    public void displayReport() {
        double TotalBooked = booked;
        double TotalSeats = Seats;
        double percentage = (TotalBooked / TotalSeats) * 100;
        double avgPrice;
        if (booked == 0)
            avgPrice = 0;
        else
            avgPrice = totalPrice / booked;

        System.out.println("Number of sales:\t\t" + booked);
        System.out.println("Percentage of tickets sold:\t" + percentage + "0%");
        System.out.println("Average price:\t\t\t$" + avgPrice + "0\n");
    }
}

// Defines the Demo class
public class Demo {
    // Main method of the program
    public static void main(String[] args) {
        // An object that will scan user input
        Scanner input = new Scanner(System.in);
        int Rows, option;
        double SPrice, PPrice, FPrice;
        String Date;

        // Prompt the user to enter information for the Cinema
        System.out.print("Please enter the number of Rows in the Cinema: ");
        Rows = input.nextInt();
        input.nextLine();
        System.out.print("Please enter the Date: ");
        Date = input.nextLine();
        System.out.print("Please enter the price of a Standard Ticket: $");
        SPrice = input.nextDouble();
        System.out.print("Please enter the price of a Pensioner Ticket: $");
        PPrice = input.nextDouble();
        System.out.print("Please enter the price of a Frequent Patron Ticket: $");
        FPrice = input.nextDouble();

        // Create an instance of the Cinema class
        Cinema cinema = new Cinema(Rows, Date, SPrice, PPrice, FPrice);

        // Menu options
        do {
            System.out.println("");
            System.out.println("*** Cinema Booking Menu ***");
            System.out.println("1: Display Available Seats");
            System.out.println("2: Book Ticket");
            System.out.println("3: Refund Ticket");
            System.out.println("4: Display Report");
            System.out.println("5: Exit");
            System.out.print("Please select an option (1-5): ");
            option = input.nextInt();
            System.out.println("");

            // Once option is selected it will take user to their choice
            switch (option) {
                case 1:
                    cinema.displaySeats();
                    break;

                case 2:
                    int SeatNum, ticket, STotal = 0, PTotal = 0, FTotal = 0;
                    double ticketTotal = 0;
                    char type;
                    String S = "", P = "", F = "";

                    System.out.print("Please enter the number of Tickets to purchase: ");
                    ticket = input.nextInt();
                    // Checks if there are enough available seats to book
                    if (ticket > cinema.getUnbooked()) {
                        System.out.println("Unable to book " + ticket + " seat(s). Only " + cinema.getUnbooked()
                                + " seats available.");
                        break;
                    }

                    // Loop if user decides to book multiple tickets
                    for (int i = 0; i < ticket; i++) {
                        System.out.println("Tickets: " + i + "/" + ticket + " booked.");
                        System.out.print("Please enter which Seat Number you'd like to book: ");
                        SeatNum = input.nextInt();
                        // if (input.hasNextInt()) {
                        //     in
                        // }
                        System.out.println("S: Standard\t\tseat\t$" + cinema.getS() + "0");
                        System.out.println("P: Pensioner\t\tseat\t$" + cinema.getP() + "0");
                        System.out.println("F: Frequent Patrons\tseat\t$" + cinema.getF() + "0");
                        System.out.print("Please select a Ticket type: ");
                        type = input.next().charAt(0);
                        // Checks if the selected seat is available
                        if (SeatNum > Rows * 10) {
                            System.out.println("Seat " + SeatNum + " isn't available. Please select another.");
                            i--;
                            continue;
                        }

                        // Checks if the seat isn't booked yet
                        if (cinema.getSeats()[SeatNum - 1] == '-') {
                            // Book the ticket and updates the counter
                            switch (type) {
                                case 'S':
                                    cinema.BookTicket(SeatNum - 1, type);
                                    STotal++;
                                    ticketTotal += cinema.getS();
                                    if (S.equals(""))
                                        S += SeatNum;
                                    else
                                        S += ", " + SeatNum;
                                    break;

                                case 'P':
                                    cinema.BookTicket(SeatNum - 1, type);
                                    PTotal++;
                                    ticketTotal += cinema.getP();
                                    if (P.equals(""))
                                        P += SeatNum;
                                    else
                                        P += ", " + SeatNum;
                                    break;

                                case 'F':
                                    cinema.BookTicket(SeatNum - 1, type);
                                    FTotal++;
                                    ticketTotal += cinema.getF();
                                    if (F.equals(""))
                                        F += SeatNum;
                                    else
                                        F += ", " + SeatNum;
                                    break;

                                default:
                                    System.out.println("Invalid input. Please enter S/P/F.");
                                    i--;
                                    break;
                            }
                        } else {
                            System.out.println("Seat " + SeatNum + " isn't available. Please select another.");
                            i--;
                        }
                    }
                    // Generates and displays the receipt
                    cinema.ticketReceipt(STotal, PTotal, FTotal, ticket, S, P, F, ticketTotal);
                    break;

                case 3:
                    int refund;
                    double refundTotal = 0;
                    STotal = 0;
                    PTotal = 0;
                    FTotal = 0;
                    S = "";
                    P = "";
                    F = "";

                    System.out.print("Please enter the number of Tickets to refund: ");
                    refund = input.nextInt();
                    // Checks if there's enough booked seats to refund
                    if (refund > cinema.getBooked()) {
                        System.out.println("Unable to refund " + refund + " seat(s). Only " + cinema.getBooked()
                                + " seats booked.");
                        break;
                    }

                    // Loop to refund multiple tickets
                    for (int i = 0; i < refund; i++) {
                        System.out.println("Tickets: " + i + "/" + refund + " refunded.");
                        System.out.print("Please enter the Seat Number you'd like to refund: ");
                        SeatNum = input.nextInt();
                        // Checks if the selected seat number is booked and refundable
                        if (SeatNum > Rows * 10) {
                            System.out.println("Seat " + SeatNum + " isn't available. Please select another.");
                            i--;
                            continue;
                        }

                        // Checks if the selected seat is booked
                        if (cinema.getSeats()[SeatNum - 1] != '-') {
                            // Refunds the ticket and updates the counter
                            switch (cinema.getSeats()[SeatNum - 1]) {
                                case 'S':
                                    cinema.RefundTicket(SeatNum - 1);
                                    STotal++;
                                    refundTotal += cinema.getS();
                                    if (S.equals(""))
                                        S += SeatNum;
                                    else
                                        S += ", " + SeatNum;
                                    break;
                                case 'P':
                                    cinema.RefundTicket(SeatNum - 1);
                                    PTotal++;
                                    refundTotal += cinema.getP();
                                    if (P.equals(""))
                                        P += SeatNum;
                                    else
                                        P += ", " + SeatNum;
                                    break;
                                case 'F':
                                    cinema.RefundTicket(SeatNum - 1);
                                    FTotal++;
                                    refundTotal += cinema.getF();
                                    if (F.equals(""))
                                        F += SeatNum;
                                    else
                                        F += ", " + SeatNum;
                                    break;
                                default:
                                    break;
                            }
                        } else if (cinema.getSeats()[SeatNum - 1] == '-') {
                            System.out.println("Seat " + SeatNum + " isn't booked. Please select another.");
                            i--;
                        }
                    }
                    // Generate and displays the refund receipt
                    cinema.refundReceipt(STotal, PTotal, FTotal, refund, S, P, F, refundTotal);
                    break;
                case 4:
                    cinema.displayReport();
                    break;
                case 5:
                    System.out.println("Exiting Program");
                    System.out.println("...");
                    System.out.println("Program Ended");
                    break;
            }

        } while (option != 5); // Continues the loop until the user decides to exit
    }
}
