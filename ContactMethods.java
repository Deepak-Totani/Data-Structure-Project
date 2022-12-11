package dataProject;
import java.io.File;
import java.io.FileInputStream; 

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ContactMethods implements Serializable{

	/**
	 * @return 
	 *  
	 */
	String callLogs;
	private static final long serialVersionUID = 1L;
	
	void addContact(Contacts contact) throws IOException {
	
		Scanner input = new Scanner(System.in);
		boolean okay = true;
		//String name;
    	String firstName;
    	String lastName;
    	String phoneNumber; 
    	while(okay) {
    	System.out.print("Enter first name: ");
    	firstName = input.nextLine();
    	System.out.print("Enter last name: ");
    	lastName = input.nextLine();
    	System.out.print("Enter phone number: ");
    	phoneNumber = input.nextLine(); 
    	if(contact.searchContact(contact.root, firstName + " " + lastName)) {
    		System.out.println("Number already exist.");
    		System.out.print("Enter another number? ");
    		//input.nextLine();
    		if((input.next().charAt(0)) == 'y') {
    			okay = true;
    			input.nextLine();
    		} else {
    			okay = false;
    		}
    		
    	} else {
    		saveData(contact,firstName, lastName, phoneNumber);
    		System.out.println("Number saved!");
    		okay = false;
    	}
    	}
    	//contact.insert(firstName+" "+lastName, firstName, lastName, phoneNumber);
    	//contact.root.setContact(firstName, lastName, phoneNumber);
    	System.out.println("Contact Saved!");
    	
	}
	void saveData(Contacts tree,String firstName, String lastName, String phoneNumber) throws IOException {
		  tree.insert(firstName+" " + lastName,firstName, lastName, phoneNumber);
		  FileOutputStream fileOut = new FileOutputStream("H:\\Project\\File.txt");
		  ObjectOutputStream ou = new ObjectOutputStream(fileOut);
		  ou.writeObject(tree); 
		  ou.close();			
	}
	void saveData(Contacts tree) throws IOException {
		  FileOutputStream fileOut = new FileOutputStream("H:\\Project\\File.txt");
		  ObjectOutputStream ou = new ObjectOutputStream(fileOut);
		  ou.writeObject(tree); 
		  ou.close();			
	}
	void delContact(Contacts tree, String name) throws IOException {
		
			tree.deleteKey(name);
			saveData(tree);
	}
	
	Contacts retreiveData() throws IOException, ClassNotFoundException {
		  Contacts tree;
		  FileInputStream fileIn = new FileInputStream("H:\\Project\\File.txt");
		  ObjectInputStream in = new ObjectInputStream(fileIn);
		  tree = (Contacts) in.readObject();
		//  tree.inorderRec(tree.root);
		  in.close();
		  return tree;
		 // tree.inorderRec(tree.root);
	}
	
	public String getDate() {		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  	
		LocalDateTime now = LocalDateTime.now();  											
	    return dtf.format(now);  									
    	}					
		public void addCallLogs(String name, String phoneNumber) throws IOException {
			FileWriter myWriter = new FileWriter("H:\\Project\\callLogs.txt",true);
			
			myWriter.append(getDate() + ": " + name + " " + phoneNumber + "\n");					
			myWriter.close();	    
								
		}
		public String bringCallLogs() throws IOException{
			File file = new File("H:\\Project\\callLogs.txt");
			Scanner input = new Scanner(file);
			String save = "";
			while(input.hasNextLine()) {
				save+=input.nextLine()+"\n";
				
			}
			input.close();
			return save;
		}
		public void makeCall() {
			
		}
		
}

