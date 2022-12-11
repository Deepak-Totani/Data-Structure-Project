package dataProject;
import java.util.Arrays;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
	 
	 public static void main(String[] args) throws ClassNotFoundException, IOException {	
		 boolean run = true; Scanner input = new Scanner(System.in);		
		 ContactMethods cm = new ContactMethods();			
		 Contacts contact  = cm.retreiveData();				 
		 String arr[] = new String[10];			
		 arr = contact.getNames(contact.root);  		
		 System.out.println(Arrays.toString(arr)); 
	 }
}
