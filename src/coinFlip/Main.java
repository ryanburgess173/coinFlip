package coinFlip;

import java.io.*;// Library used for File Input and Output
import java.util.Formatter;
import java.util.Random; // library used for generating random numbers
import java.util.Scanner; // library used for creating Scanner objects, used to collect user input from the keyboard

// class Main
public class Main {
	
    static int number;
	
    // read file method
    public void readFile(String filename) throws IOException {
    	try(DataInputStream din = new DataInputStream(new FileInputStream(filename)) ){
    		number = din.readInt();
    	}catch(FileNotFoundException e) {
    		System.out.println("File not found exception.");
    		return;
    	}catch(EOFException e) {
    		System.out.println("end of file.");
    	}
    	catch(IOException e) {
    		System.out.println(e);
    	}
    }
    
    //writes to file method
    // I use a method for this that can be used to write to either heads.txt OR tails.txt. I did this for efficiency.
    public void write2File(int number, String filename) throws IOException{
    	try(DataOutputStream y = 
    			new DataOutputStream(new FileOutputStream(filename))){
			y.writeInt(number);
		}catch(FileNotFoundException e) {
			System.out.println("you have an error: "+e);
			return;
		}catch(IOException e) {
			System.out.println("Issue with writing");
		}
    }
	// main method
	// main function which will automatically execute when the program is run
	public static void main(String[] args) throws IOException {
		
		// next instance of Main
		Main program = new Main();
		
		// initializes random
		Random random = new Random();
		
		// initializes scanner
		Scanner scanner = new Scanner(System.in);
		
		// initializes the int variable that user input will be assigned to
		int input = 0;
		
		// try to get int input from the user
		try {
			System.out.print("How Many Times Do You Want to Flip the Coin? ");
			input = scanner.nextInt();
		}
		// if exception is throw execute this:
		catch(Exception e) {
			// print statement explaining what the user did wrong.
			System.out.println("Error: Make sure you enter a whole number next time!");
		}
		// defines coin variable.
		boolean coin;
		// initializes the variable that holds the consecutive number of heads.
		int consecutiveHeads = 0;
		// initializes the variable that holds the consecutive number of tails.
		int consecutiveTails = 0;
		//count of heads in a row at the moment
		int headCount = 0;
		//count of tails in a row at the moment
		int tailCount = 0;
		
		// if heads is on a streak or not
		boolean headStreak=false;
		// if tails is on a streak or not
		boolean tailStreak=false;
		
		// if its been heads before
		boolean hasBeenHeads = false;
		// if its been tails before
		boolean hasBeenTails = false;
		// loop to flip the coin
		for(int i=0; i<input; i++) {
			// randomly assigns either true or false to the boolean variable coin
			coin = random.nextBoolean();
			if(coin==true) {
				hasBeenHeads = true;
				System.out.println("H");
				headStreak = true;
				tailStreak = false;
				if(headStreak==true) {
					headCount++;
				}
				if(headCount>consecutiveHeads) {
					consecutiveHeads = headCount;
				}
				tailCount = 0;
			}else {
				//assigns hasBeenTails a value of true
				hasBeenTails = true;
				//prints the tails symbol to console
				System.out.println("T");
				//says that tails is on a streak
				tailStreak = true;
				// says that heads lost its streak
				headStreak = false;
				// if tails is on a streak
				if(tailStreak==true) {
					//then add 1 to tail count
					tailCount++;
				}
				// if tailCount is bigger than consecutive number of tails
				if(tailCount>consecutiveTails) {
					// then assign consecutiveTails the new record
					consecutiveTails = tailCount;
				}
				headCount = 0;
			}
		}
		// if its been heads
		if(hasBeenHeads==true) {
			//and if it doesn't have a streak
			if(consecutiveHeads<1) {
				// give it a streak of 1
				consecutiveHeads=1;
			}
		}
		// if its been tails
		if(hasBeenTails==true) {
			// and if it doesn't have a streak
			if(consecutiveTails<1) {
				// give it a streak of 1
				consecutiveTails=1;
			}
		}
		System.out.println("The Highest Number of Consecutive Heads: "+consecutiveHeads);
		System.out.println("The Highest Number of Consecutive Tails: "+consecutiveTails);
		System.out.println();
		try {
			program.readFile("heads.dat");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// if the number is less than consecutive ones
		if(number<consecutiveHeads) {
			System.out.println("You set a new record for consecutive heads: "+consecutiveHeads);
			System.out.println("The old record for consecutive heads was: "+number);
			program.write2File(consecutiveHeads, "heads.dat");
		}
		try {
			program.readFile("tails.dat");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
		if(number<consecutiveTails) {
			System.out.println("You set a new record for consecutive tails: "+consecutiveTails);
			System.out.println("The old record for consecutive tails was: "+number);
			program.write2File(consecutiveTails, "tails.dat");
		}
		
	}
}
