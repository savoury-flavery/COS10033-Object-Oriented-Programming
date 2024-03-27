/*Unit - COS10033 - Advanced Programming
Assignment 2 Part D - 6th September 2023
Avery Porter - s104416957
This program allows the user to create available hotel rooms as the RBS resort management, and then allows the user to 
book rooms out for guests as long as it is a suitable size, displays a receipt, shows all room details and allows guests to check-in and check-out. 
The program has a limit of 30 rooms total, 10 single, 10 double, 10 standard and 2 of those rooms are a special facilities double and triple.
*/

import java.util.*;
import java.io.*;

//interface for the Special Services within the Special Facilities Room
interface SpecialServices {
    int getRampLength();
    int getRampWidth();
    int getEmergency();
}

class Room implements Serializable { //so the data can be stored
	//room related variables
    protected String RoomName;
    protected String Description;
    protected static int nextRoomID = 101;
    protected int RoomID;
    protected char RoomType;
    protected char RoomSize;
    protected int maxGuests;
    protected int Price;
    protected int total;
    protected int totalprice;
    
    //guest related
    protected String guestID;
    protected int numDays;
    
    //room availability
	protected boolean checkin;
	protected boolean available;
    

	public Room(String RoomName, String Description, char RoomType, char RoomSize) {
	    this.RoomName = RoomName;
	    this.Description = Description;
	    
	    this.RoomType = RoomType;
	    this.RoomSize = RoomSize;
	    
	    this.available = false;
	    this.checkin = false;
	}
 
	
    public int getRoomID() {
        return this.RoomID;
    }
    
    public String getRoomName() {
        return this.RoomName;
    }

    public String getDescription() {
        return this.Description;
    }
    
    public char getRoomType() {
        return this.RoomType;
    }
    
    public char getRoomSize() {
        return this.RoomSize;
    }
    
    public String getguestID() {
    	return this.guestID;
    }
    
    public int getnumDays() {
    	return this.numDays;
    }

    //Sub-Class for Standard Rooms
    final public class Standard extends Room {

        public Standard(String RoomName, String Description, char RoomSize) {
            super(RoomName, Description, 'S', RoomSize); // Sets RoomType to 'S'
            this.RoomID = nextRoomID++; //increments room number
            setMaxGuests(RoomSize);
        }
        //price based on what Room Size is selected
        public double determinePrice() {
            double price = 0;
            switch (this.RoomSize) {
                case 'S':
                    price = 230;
                    break;
                case 'D':
                    price = 350;
                    break;
                case 'T':
                    price = 500;
                    break;
            }
            return price;
        }

        //max guest limit based on what Room Size is selected
        private void setMaxGuests(char RoomSize) {
            switch (RoomSize) {
                case 'S':
                    maxGuests = 2;
                    break;
                case 'D':
                    maxGuests = 4;
                    break;
                case 'T':
                    maxGuests = 6;
                    break;
            }
        }

        public int getMaxGuests() {
            return maxGuests;
        }
		
	}
   
    //Sub-Class for Deluxe Rooms
	final class Deluxe extends Room {
    	public int bath; 
    	
    	public Deluxe(String RoomName, String Description, char RoomSize, int bath) {
             super(RoomName, Description, 'D', RoomSize); // Sets RoomType to 'D'
             this.RoomID = nextRoomID++; //increments room number
             setMaxGuests(RoomSize);
             this.bath = bath;
         }

         public int getBath() {
             return bath;
         }
        //price based on what Room Size is selected
        public double determinePrice() {
            double price = 0;
            switch (this.RoomSize) {
                case 'S':
                    price = 280;
                    break;
                case 'D':
                    price = 430;
                    break;
                case 'T':
                    price = 600;
                    break;
            }
            return price;
        }

        //max guest limit based on what Room Size is selected
        private void setMaxGuests(char RoomSize) {
            switch (RoomSize) {
                case 'S':
                    maxGuests = 2;
                    break;
                case 'D':
                    maxGuests = 4;
                    break;
                case 'T':
                    maxGuests = 6;
                    break;
            }
        }

        public int getMaxGuests() {
            return maxGuests;
        }
    }

    //Sub-Class for Premium Rooms
	final class Premium extends Room {
    	public int spa;
    	public int kitchenette;
    	
        public Premium(String RoomName, String Description, char RoomSize, int spa, int kitchenette) {
            super(RoomName, Description, 'P', RoomSize); // Set RoomType to 'P'
            this.RoomID = nextRoomID++; //increments room number
            setMaxGuests(RoomSize);
            this.spa = spa;
            this.kitchenette = kitchenette; 
        }
        
        public int getSpa() {
            return spa;
        }
        public int getKitchenette() {
            return kitchenette;
        }
        
        //price based on what Room Size is selected
        public double determinePrice() {
            double price = 0;
            switch (this.RoomSize) {
                case 'S':
                    price = 350;
                    break;
                case 'D':
                    price = 500;
                    break;
                case 'T':
                    price = 690;
                    break;
            }
            return price;
        }

        //max guest limit based on what Room Size is selected
        private void setMaxGuests(char RoomSize) {
            switch (RoomSize) {
                case 'S':
                    maxGuests = 2;
                    break;
                case 'D':
                    maxGuests = 4;
                    break;
                case 'T':
                    maxGuests = 6;
            }
        }

        public int getMaxGuests() {
            return maxGuests;
        }
    }

    //Sub-Class for Special Facility Double Rooms
	final class SpecialDouble extends Room implements SpecialServices {
        private int rampLength;
        private int rampWidth;
        private int emergency;
        

        public SpecialDouble(String RoomName, String Description, char RoomSize, int rampLength, int rampWidth, int emergency) {
            super(RoomName, Description, 'X', RoomSize);
            this.RoomID = nextRoomID++; //increments room number
            setMaxGuests(RoomSize);
            if (RoomSize != 'D' || (rampLength <= 0 || rampWidth <= 0 || emergency <= 0)) {
                throw new IllegalArgumentException("Invalid information entered for Special Facilities room.\n");
            }
            this.rampLength = rampLength;
            this.rampWidth = rampWidth;
            this.emergency = emergency;
        }

        public int getRampLength() {
            return rampLength;
        }

        public int getRampWidth() {
            return rampWidth;
        }

        public int getEmergency() {
            return emergency;
        }
        
        public double determinePrice() {
            double price = 0;
            switch (this.RoomSize) {
                case 'D':
                    price = 350;
                    break;
            }
            return price;
        }


        private void setMaxGuests(char RoomSize) {
            switch (RoomSize) {
                case 'D':
                    maxGuests = 4;
                    break;
            }
        }

        public int getMaxGuests() {
            return maxGuests;
        }

    }

    //Sub-Class for Special Facility Triple Rooms
	final class SpecialTriple extends Room implements SpecialServices {
         private int rampLength;
         private int rampWidth;
         private int emergency;

         public SpecialTriple(String RoomName, String Description, char RoomSize, int rampLength, int rampWidth, int emergency) {
             super(RoomName, Description, 'X', RoomSize);
             this.RoomID = nextRoomID++; //increments room number
             setMaxGuests(RoomSize);
             if (RoomSize != 'T' || (rampLength <= 0 || rampWidth <= 0 || emergency <= 0)) {
                 throw new IllegalArgumentException("Invalid information entered for Special Facilities room.\n");
             }
             this.rampLength = rampLength;
             this.rampWidth = rampWidth;
             this.emergency = emergency;
         }

        
         public int getRampLength() {
             return rampLength;
         }
         
         public int getRampWidth() {
             return rampWidth;
         }
       
         public int getEmergency() {
             return emergency;
         }
         
         public double determinePrice() {
             double price = 0;
             switch (this.RoomSize) {
                 case 'T':
                     price = 500;
                     break;
             }
             return price;
         }

         private void setMaxGuests(char RoomSize) {
             switch (RoomSize) {
                 case 'T':
                     maxGuests = 6;
                     break;
             }
         }

         public int getMaxGuests() {
             return maxGuests;
         }
     }
 
    
	public int getTotal() {
		return this.total;
	}
	
	//Check-in the guest
    public void checkinRoom(String guestID, int numDays) throws AccommodationException {
        if (!this.checkin) {
            this.guestID = guestID;
            this.numDays = numDays;
            this.checkin = true;
            this.available = false;
            this.totalprice = (int) (getPrice() * numDays); // Store the price when checked in
        } else {
            throw new AccommodationException("Unable to Check-In Room");
        }
    }

	//Check-out the guest
    public void checkoutRoom() throws AccommodationException {
        if (this.checkin) {
            this.checkin = false;
        } else {
            throw new AccommodationException("Unable to Check-Out Room");
        }
    }

	 
	public String toString() {
		return "Room ID: " + RoomID + " Room Name: " + RoomName +  " Room Type: " + RoomType + " Room Size: " + RoomSize + " Description: " + Description + "\n";
	}


	//Gets the maximum number of guests based on all the sub-classes
	public int getMaxGuests() {
        if (this instanceof Standard) {
            return ((Standard) this).getMaxGuests();
        } else if (this instanceof Deluxe) {
            return ((Deluxe) this).getMaxGuests();
        } else if (this instanceof Premium) {
            return ((Premium) this).getMaxGuests();
        } else if (this instanceof SpecialDouble) {
            return ((SpecialDouble) this).getMaxGuests();
        } else if (this instanceof SpecialTriple) {
            return ((SpecialTriple) this).getMaxGuests();
        } else {
            return 0; 
        }
	}
	
	//Gets the price based on all the sub-classes
    public double getPrice() {
        if (this instanceof Standard) {
            return ((Standard) this).determinePrice();
        } else if (this instanceof Deluxe) {
            return ((Deluxe) this).determinePrice();
        } else if (this instanceof Premium) {
            return ((Premium) this).determinePrice();
        } else if (this instanceof SpecialDouble) {
            return ((SpecialDouble) this).determinePrice();
        } else if (this instanceof SpecialTriple) {
            return ((SpecialTriple) this).determinePrice();
        } else {
            return 0; 
        }
    }
    
    //calculates the total revenue for Case 3 
    public static int TotalRevenue(ArrayList<Room> accommodation) {
        int totalRevenue = 0;
        for (Room room : accommodation) {
            if (room != null && room.checkin) {
                totalRevenue += room.totalprice;
            }
        }
        return totalRevenue;
    }
}


public class Part_D {
	private static final String Room_File = "rooms.txt"; // Name of the data file

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Room> Accommodation = new ArrayList<>();

        //Checks if a data file already exists
        File dataFile = new File(Room_File);

        if (dataFile.exists()) {
            //Reads the data from the file to populate the Array list
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(Room_File))) {
                Accommodation = (ArrayList<Room>) inputStream.readObject();
                System.out.println("Data loaded successfully.");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading data: " + e.getMessage());
            }
        } else {
            System.out.println("Data file not found. Starting with an empty system.");
        }
        
        //variables
        int option;
	    int roomCount = 0;
	    char RoomSize = 0;
	    int total = 0;
	    int SCount = 0;
	    int DCount = 0;
	    int TCount = 0;

		
		do {

			System.out.println("Resort by the Sea Management");
			System.out.println("Select an Option (1 - 6)");
			System.out.println("1. Add Room Availibility");
			System.out.println("2. Display Room Details");
			System.out.println("3. Display All Room Details");
			System.out.println("4. Check-in Customer");
			System.out.println("5. Check-out Customer");
			System.out.println("6. Exit");
			
			option = input.nextInt();
			switch (option) {
			case 1:
			    if (roomCount <= 30) { // allows only 30 rooms to be created
			        char RoomType = ' ';
			        int bath = 0;
			        int spa = 0;
			        int kitchenette = 0;
			        int rampLength = 0;
			        int rampWidth = 0;
			        int emergency = 0;

			        boolean specialDoubled = false; //to show if 1 of these rooms have already been booked
			        boolean specialTripled = false; //used to set a limit

			        do {
			        	 System.out.println("Enter Room Name: ");
				            String RoomName = input.next();
				            System.out.println("Enter Room Description: ");
				            String Description = input.next();
				            System.out.println("Enter Which Type of Room to Add: ");
				            System.out.println("S: Standard Room\nD: Deluxe Room\nP: Premium Room\nX: Speciality Room");
				            RoomType = input.next().charAt(0);
				            RoomType = Character.toUpperCase(RoomType);
				            input.nextLine();

			            if (RoomType != 'S' && RoomType != 'D' && RoomType != 'P' && RoomType != 'X') {
			                System.out.println("Invalid Room Type.");
			            } else {

			                switch (RoomType) {
		                    case 'S':
		                        System.out.println("Enter Room Size (S/D/T): ");
		                        RoomSize = input.next().charAt(0);
		                        RoomSize = Character.toUpperCase(RoomSize);
		                        if (RoomSize != 'S' && RoomSize != 'D' && RoomSize != 'T') {
		                            System.out.println("Invalid Room Size.");
		                            break;
		                        }
		                        if (RoomSize == 'S' && SCount < 10) { 
	                                Room room = new Room(RoomName, Description, 'S', RoomSize);
	                                Room.Standard stand = room.new Standard(RoomName, Description, RoomSize);
	                                Accommodation.add(stand); 
	                                roomCount++;
	                                SCount++;
	                                System.out.println("Standard Single Room " + stand.getRoomID() + " added. Maximum Guests: " + stand.getMaxGuests() + ". Price: $" + stand.determinePrice());
	                            } else if (RoomSize == 'D' && DCount < 10) {
	                                Room room = new Room(RoomName, Description, 'S', RoomSize);
	                                Room.Standard stand = room.new Standard(RoomName, Description, RoomSize);
	                                Accommodation.add(stand); 
	                                roomCount++;
	                                DCount++;
	                                System.out.println("Standard Double Room " + stand.getRoomID() + " added. Maximum Guests: " + stand.getMaxGuests() + ". Price: $" + stand.determinePrice());
	                            } else if (RoomSize == 'T' && TCount < 10) {
	                                Room room = new Room(RoomName, Description, 'S', RoomSize);
	                                Room.Standard stand = room.new Standard(RoomName, Description, RoomSize);
	                                Accommodation.add(stand); 
	                                roomCount++;
	                                TCount++;
	                                System.out.println("Standard Triple Room " + stand.getRoomID() + " added. Maximum Guests: " + stand.getMaxGuests() + ". Price: $" + stand.determinePrice());
	                            } else {
	                                System.out.println("You have reached the limit of " + RoomSize + " Rooms.");
	                            }
	                            break;

		                    case 'D':
		                    	//Requires use to enter Bath details
                                System.out.println("Enter the bath space available: ");
                                bath = input.nextInt();
		                        System.out.println("Enter Room Size (S/D/T): ");
		                        RoomSize = input.next().charAt(0);
		                        RoomSize = Character.toUpperCase(RoomSize);
		                        if (RoomSize != 'S' && RoomSize != 'D' && RoomSize != 'T') {
		                            System.out.println("Invalid Room Size.");
		                            break;
		                        }
		                        if (RoomSize == 'S' && SCount < 10) {
		                            Room room = new Room(RoomName, Description, 'S', RoomSize);
		                            Room.Deluxe delu = room.new Deluxe(RoomName, Description, RoomSize, bath);
	                                Accommodation.add(delu); 
		                            roomCount++;
		                            SCount++;
		                            System.out.println("Deluxe Single Room " + delu.getRoomID() + " added. Maximum Guests: " + delu.getMaxGuests() + ". Price: $" + delu.determinePrice() 
		                            + ". Baths: " + delu.getBath());
		                        } else if (RoomSize == 'D' && DCount < 10) {
		                            Room room = new Room(RoomName, Description, 'S', RoomSize);
		                            Room.Deluxe delu = room.new Deluxe(RoomName, Description, RoomSize, bath);
	                                Accommodation.add(delu); 
		                            roomCount++;
		                            DCount++;
		                            System.out.println("Deluxe Double Room " + delu.getRoomID() + " added. Maximum Guests: " + delu.getMaxGuests() + ". Price: $" + delu.determinePrice() 
		                            + ". Baths: " + delu.getBath());
		                        } else if (RoomSize == 'T' && TCount < 10) {
		                            Room room = new Room(RoomName, Description, 'S', RoomSize);
		                            Room.Deluxe delu = room.new Deluxe(RoomName, Description, RoomSize, bath);
	                                Accommodation.add(delu); 
		                            roomCount++;
		                            TCount++;
		                            System.out.println("Deluxe Triple Room " + delu.getRoomID() + " added. Maximum Guests: " + delu.getMaxGuests() + ". Price: $" + delu.determinePrice() 
		                            + ". Baths: " + delu.getBath());
		                        } else {
		                            System.out.println("You have reached the limit of " + RoomSize + " Rooms.");
		                        }
		                        break;

		                    case 'P':
		                    	//requires user to enter Spa and Kitchenette details
                                System.out.println("Enter the spa space available: ");
                                spa = input.nextInt();
                                System.out.println("Enter the kitchenette space available: ");
		                    	kitchenette = input.nextInt();
		                    	System.out.println("Enter Room Size (S/D/T): ");
			                        RoomSize = input.next().charAt(0);
			                        RoomSize = Character.toUpperCase(RoomSize);
			                        if (RoomSize != 'S' && RoomSize != 'D' && RoomSize != 'T') {
			                            System.out.println("Invalid Room Size.");
			                            break;
			                        }
			                        if (RoomSize == 'S' && SCount < 10) {
			                            Room room = new Room(RoomName, Description, 'S', RoomSize);
			                            Room.Premium premi = room.new Premium(RoomName, Description, RoomSize, spa, kitchenette);
		                                Accommodation.add(premi); 
			                            roomCount++;
			                            SCount++;
			                            System.out.println("Premium Single Room " + premi.getRoomID() + " added. Maximum Guests: " + premi.getMaxGuests() + ". Price: $" + premi.determinePrice() 
			                            + ". Spa Space: " + premi.getSpa() + ". Kitchenette: " +premi.getKitchenette());
			                        } else if (RoomSize == 'D' && DCount < 10) {
			                            Room room = new Room(RoomName, Description, 'S', RoomSize);
			                            Room.Premium premi = room.new Premium(RoomName, Description, RoomSize, spa, kitchenette);
		                                Accommodation.add(premi); 
			                            roomCount++;
			                            DCount++;
			                            System.out.println("Premium Double Room " + premi.getRoomID() + " added. Maximum Guests: " + premi.getMaxGuests() + ". Price: $" + premi.determinePrice()
			                            + ". Spa Space: " + premi.getSpa() + ". Kitchenette: " +premi.getKitchenette());
			                        } else if (RoomSize == 'T' && TCount < 10) {
			                            Room room = new Room(RoomName, Description, 'S', RoomSize);
			                            Room.Premium premi = room.new Premium(RoomName, Description, RoomSize, spa, kitchenette);
		                                Accommodation.add(premi); 
			                            roomCount++;
			                            TCount++;
			                            System.out.println("Premium Triple Room " + premi.getRoomID() + " added. Maximum Guests: " + premi.getMaxGuests() + ". Price: $" + premi.determinePrice()
			                            + ". Spa Space: " + premi.getSpa() + ". Kitchenette: " +premi.getKitchenette());
			                        } else {
			                            System.out.println("You have reached the limit of " + RoomSize + " Rooms.");
			                        }
			                        break;
			                 
		                    case 'X':
		                        if (!specialDoubled || !specialTripled) {
		                            System.out.println("Enter Room Size (D/T): ");
		                            RoomSize = input.next().charAt(0);
		                            RoomSize = Character.toUpperCase(RoomSize);
		                           
		                            if (RoomSize == 'D' || RoomSize == 'T') {
		                            	//requires user to enter in Special Facility details
		                                System.out.println("Enter Ramp Length: ");
		                                rampLength = input.nextInt();
		                                System.out.println("Enter Ramp Width: ");
		                                rampWidth = input.nextInt();
		                                System.out.println("Enter Number of Emergency Calling Facilities: ");
		                                emergency = input.nextInt();

		                                try {
		                                    Room room = new Room(RoomName, Description, 'X', RoomSize);
		                                    if (RoomSize == 'D') {
		                                        Room.SpecialDouble specialDouble = room.new SpecialDouble(RoomName, Description, RoomSize, rampLength, rampWidth, emergency);
		                                        Accommodation.add(specialDouble);
		                                        specialDoubled = true; // Mark that a Special Facilities Double Room has been added
		                                        System.out.println("Special Double Room " + specialDouble.getRoomID() + " added. Maximum Guests: " + specialDouble.getMaxGuests() + ". Price: $" + specialDouble.determinePrice());
		                                    } else { 
		                                        Room.SpecialTriple specialTriple = room.new SpecialTriple(RoomName, Description, RoomSize, rampLength, rampWidth, emergency);
		                                        Accommodation.add(specialTriple);
		                                        specialTripled = true; // Mark that a Special Facilities Triple Room has been added
		                                        System.out.println("Special Triple Room " + specialTriple.getRoomID() + " added. Maximum Guests: " + specialTriple.getMaxGuests() + ". Price: $" + specialTriple.determinePrice());
		                                    }
		                                    roomCount++;
		                                } catch (IllegalArgumentException e) {
		                                    System.out.println("Invalid room category or parameters for Special Room: " + e.getMessage());
		                                }
		                            } else {
		                                System.out.println("Invalid Room Size for Special Room.");
		                            }
		                        } else {
		                            System.out.println("You have reached the limit of Special Facility Rooms.");
		                        }
		                        break;
		                }

		                if (roomCount < 30) {
		                	//allows user to enter 0 to return to main menu or hit any other key to continue adding rooms
		                    System.out.println("Add More Rooms? (0 to return to Main Menu, any other key to Continue): ");
		                    String exit = input.next();
		                    if (exit.equals("0")) {
		                        break;
		                    }
		                } else {
		                    System.out.println("Maximum Number of Rooms Registered.\n");
		                    break;
		                }
		            }
		        } while (true); //keeps the loop running until max number or rooms is hit or user exits
		    } else {
		        System.out.println("Maximum Number of Rooms Registered.\n");
		    }
		    break;
			    
            case 2:
                System.out.print("Enter Room ID: ");
                int roomNumber = input.nextInt();
                input.nextLine();

                Room foundRoom = null;
                
                //has the user enter a room ID and if it matches a roomID it will then output the room details
                for (Room room : Accommodation) {
                    if (room.getRoomID() == roomNumber) {
                        foundRoom = room;
                        break;
                    }
                }

                if (foundRoom != null) {
                    System.out.println("Room ID: " + foundRoom.getRoomID() + "\nRoom Name: " + foundRoom.getRoomName() + "\nRoom Type: " + foundRoom.getRoomType() + "\nRoom Size: " + foundRoom.getRoomSize() + "\nDescription: " + foundRoom.getDescription()
                            + "\nPrice: $" + foundRoom.getPrice() + "\n");
                } else {
                    System.out.println("Error 404. Room not Found.\n");
                }
                break;

            case 3:
                System.out.println("Details of Booked Rooms:\n");
                for (Room room : Accommodation) {
                    if (room.checkin) { //outputs the details for all checked-in rooms
                        System.out.println(room.toString());
                        System.out.println("Guest ID:\t" + room.getguestID());
                        System.out.println("Number of Days:\t" + room.getnumDays());
                        System.out.println("Total Price:\t$" + room.totalprice);
                        System.out.println("***********************");
                    }
                }
                total = Room.TotalRevenue(Accommodation); 
                System.out.println("Total Revenue:\t$" + total + "\n");
                break;

            case 4:
                System.out.println("Enter Number of Guests that will be Staying: ");
                int guests = input.nextInt();
                input.nextLine();

                boolean suitableRoom = false;
                System.out.println("Available Rooms for " + guests + " guests:");

                // Search for suitable rooms based on the number of guests
                for (Room room : Accommodation) {
                    if (!room.checkin && room.getMaxGuests() >= guests && room.getMaxGuests() <= guests + 1) {
                        System.out.println(room.toString() + " Price: " + room.getPrice());
                        suitableRoom = true;
                    }
                }

                if (!suitableRoom) {
                    System.out.println("No Rooms available for " + guests + " guest(s). Offering larger rooms:");
                    // If no suitable rooms are found, offer larger rooms
                    for (Room room : Accommodation) {
                    	if (guests == 1 || room.getRoomType() == 'X') {
                           System.out.println("");
                    	}
                    	else if (!room.checkin && room.getMaxGuests() >= guests) {
                            System.out.println(room.toString() + " Price: $" + room.getPrice());
                            suitableRoom = true;
                        }
                    }
                }

                if (suitableRoom) {
                	//if a suitable room is found it allows the user to book, or hit 0 to return to main menue
                    System.out.println("Enter the Room ID to Book (0 to return to Main Menu): ");
                    int bookID = input.nextInt();
                    input.nextLine();

                    if (bookID == 0) {
                        break;
                    }

                    Room bookedRoom = null;

                    for (Room room : Accommodation) {
                        if (room.getRoomID() == bookID && !room.checkin) {
                            bookedRoom = room;
                            break;
                        }
                    }
                    //if its successfully been booked it will ask for guest ID and how many days stay
                    if (bookedRoom != null) {
                        System.out.print("Enter Guest ID: ");
                        String guestID = input.nextLine();
                        System.out.print("Enter Number of Days to Book: ");
                        int numDays = input.nextInt();

                        try {
                        	//if it checks-in correctly it outputs a receipt
                            bookedRoom.checkinRoom(guestID, numDays);
                            double totalCost = bookedRoom.getPrice() * numDays;
                            System.out.println("\tReceipt");
                            System.out.println("***********************");
                            System.out.println("Customer:\t" + guestID);
                            System.out.println("Room ID:\t" + bookedRoom.getRoomID() + "\nRoom Name:\t" + bookedRoom.getRoomName() +
                                    "\nRoom Type:\t" + bookedRoom.getRoomType() + "\nDays Booked:\t" + numDays + "\nTotal Cost:\t$" + totalCost + "\n");
                            System.out.println("Check-in Successful.\n");
                        } catch (AccommodationException e) {
                            System.out.println("Check-in Failed: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Invalid Room ID. Booking Failed.\n");
                    }
                } else {
                    System.out.println("Sorry. There are No Suitable Rooms Available for " + guests + " Guests.\n");
                }
                break;


            case 5:
            	//input room that needs to be Checked-out
                System.out.println("Enter Room ID to Check-Out: ");
                int Checkout = input.nextInt();
                input.nextLine();

                boolean CheckedOut = false;

                for (Room room : Accommodation) {
                    try {
                    	//sees if the room is checked-in and available for check-out
                        if (room.getRoomID() == Checkout && room.checkin) {
                            room.checkoutRoom(); 
                            System.out.println("Check-out Successful.\n");
                            CheckedOut = true;
                        }
                    } catch (AccommodationException e) {
                        System.out.println("Check-out Failed: " + e.getMessage());
                    }
                }
                //if unable to be checked out error message
                if (!CheckedOut) {
                    System.out.println("Room Unavailable for Check-out.\n");
                }
                break;

				case 6:
					//saves the accommodation data
			        saveDataToFile(Accommodation);
					System.out.println("Exiting Program...\nThank you for Choosing Resort by the Sea. ㅇㅅㅇ");
					break;
					
				default:
					System.out.println("Invalid Option. Please select (1 - 6)");
			}
			
			
		} while(option != 6);
	}
    //saves any data entered into a new file
    private static void saveDataToFile(ArrayList<Room> Accommodation) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(Room_File))) {
            outputStream.writeObject(Accommodation);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
        	//outputs if unable to save data
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
}

	
