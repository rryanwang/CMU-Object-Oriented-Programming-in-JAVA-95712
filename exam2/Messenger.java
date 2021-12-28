package exam2;

import java.util.Scanner;

public class Messenger {

	Message[] messages = {new Caesar(), new Keyword()}; //these objects must be used for encryption / decryption. No new instances of Caesar or Keywold should be created.
	Scanner input = new Scanner(System.in); //to take user inputs

	public static void main(String[] args) {
		Messenger messenger = new Messenger();
		messenger.runMessenger();
	}

	/** runMessenger() prints the menu options, takes user input,
	 * invokes methods from appropriate classes to encrypt or decrypt
	 * messages, and then prints the output
	 */
	void runMessenger() {
		Message m;

		System.out.println("Choose ...");
		System.out.println("1. Caesar encryption");
		System.out.println("2. Keyword encryption");
		System.out.println("3. Caesar decryption");
		System.out.println("4. Keyword decryption");
		int choice = input.nextInt();
		input.nextLine(); //to clear buffer
		//write rest of your code here
		if (choice == 1){
			System.out.println("Enter message to encrypt");
		} else if (choice == 2){
			System.out.println("Enter message to encrypt");
		} else if (choice == 3){
			System.out.println("Enter message to decrypt");
		} else{
			System.out.println("Enter message to decrypt");
		} 

	    String message = input.nextLine().strip();
		System.out.println("Enter key");
		String key = input.nextLine().strip();

		if (choice == 1){
			m = messages[0];
			m.setMessage(message, key);
			System.out.printf("Encrypted message: %s\n", m.encrypt());	
		} else if (choice == 2){
			m = messages[1];
			m.setMessage(message, key);
			System.out.printf("Encrypted message: %s\n", m.encrypt());
		} else if (choice == 3){
			m = messages[0];
			m.setMessage(message, key);
			System.out.printf("Decrypted message: %s\n", m.decrypt());
		} else{
			m = messages[1];
			m.setMessage(message, key);
			System.out.printf("Decrypted message: %s\n", m.decrypt());	
		} 	
	}
}
