package dataProject;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Scanner;

public class Contacts implements Serializable{
  /**
	 * The Code is modified version of Binary Search Tree of Integer (programmiz.com)
 	 * The code is modified to be able to process String data type. 
	 */
	
	private static final long serialVersionUID = 1L;
class Node implements Serializable{
	//Node next; 
	String name;
	String firstName;
	String lastName;
	String phoneNumber;   
	void setContact(String firstName, String lastName, String phoneNumber) {
		
		this.firstName = firstName;             
		this.lastName = lastName;         
 		this.phoneNumber = phoneNumber;             
		this.name = firstName + " " + lastName;    
		
	}
    private Node left, right;
    
    public Node(String item) {							
      name = item;								
      left = right = null; 				
    }		
    public Node(String key, String firstName, String lastName, String phoneNumber) {
    	this.name = key;
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.phoneNumber = phoneNumber;
    
    }
    
  }													
															
  Node root;
  
  Contacts() {
    root = null;
  }
  boolean check = false;
  boolean searchContact(Node root, String delete) {
	  if (root != null) {   
	      searchContact(root.left, delete);     
	      if(root.name.equals(delete))
	    	  check = true;
	      searchContact(root.right, delete);  
	      }
		  return check;
	  
	  
  }
  
  void insert(String key, String firstName, String lastName, String phoneNumber) {
    root = insertKey(root, key, firstName, lastName, phoneNumber);
  }


  Node insertKey(Node root, String key, String firstName, String lastName, String phoneNumber) {
	  
	    if (root == null) {
	      root = new Node(key,firstName,lastName,phoneNumber);
	      return root;
	    }

	
	    if (key.compareTo(root.name) < 0)
	      root.left = insertKey(root.left, key, firstName, lastName, phoneNumber);
	    else if (key.compareTo(root.name) > 0)
	      root.right = insertKey(root.right, key, firstName, lastName, phoneNumber);

	    return root;
	  }

  void inorder() {
    inorderRec(root);
  }

  
    String info ="";
    
    int count = -1;
    int count2 = -1;
    class Names{
    	String name;
    	String number;
    	Node next;
    }
    static LinkedList<Node> linkedlist = new LinkedList<Node>();
     LinkedList getNumNames(Node root) {
    	if (root != null) {   
    	  getNumNames(root.left); 
    	  linkedlist.add(root);
    	  getNumNames(root.right); 
    	} 
       return linkedlist;  	  
    }
     String[] getNames(Node root) {  	 
    	 LinkedList<Node> list = getNumNames(root);
    	 String names[] = new String[list.size()];
    	 Node y;
    	 for(int x = 0 ; x < list.size() ; x++) {
    		 y = list.get(x);
    		 names[x] = y.name;
    	 }
    	 return names;
     }
     String phoneNumber2 = "";
     String getContactNumber(Node root, String name) { 	 
    	 if(root != null) {   		
    		 if(root.name.indexOf(name) >= 0) {
    			 phoneNumber2 = root.phoneNumber;
    			 return phoneNumber2;
    		 }
    		 getContactNumber(root.left, name);
    		 getContactNumber(root.right,name);
    	 }
    	return phoneNumber2;
     }
    String getcontactInformation(Node root) {
	    
	  if (root != null) {   
      getcontactInformation(root.left);     
      info+="Name: " + root.name + "\n";  
      info+="Number: " + root.phoneNumber + "\n\n";  
      getcontactInformation(root.right);  
      }
	  return info;
  }
  void inorderRec(Node root) {
    
	  if (root != null) {
      inorderRec(root.left); 
      System.out.println("\nContact Name: " + root.name);
      System.out.println("Contact Number: " + root.phoneNumber + "\n");
      inorderRec(root.right); 
    } 
  }

  void deleteKey(String key) {
    root = deleteRec(root, key);
  }

  Node deleteRec(Node root, String key) {

    // Return if the tree is empty
    if (root == null)
      return root;

    // Find the node to be deleted
    if (key.compareTo(root.name) < 0)
      root.left = deleteRec(root.left, key);
    else if (key.compareTo(root.name) > 0)
      root.right = deleteRec(root.right, key);
    else {
      // If the node is with only one child or no child
      if (root.left == null)
        return root.right;
      else if (root.right == null)
        return root.left;

      // If the node has two children
      // Place the inorder successor in position of the node to be deleted
      root.name = minValue(root.right);

      // Delete the inorder successor
      root.right = deleteRec(root.right, root.name);
    }

    return root;
  }

  // Find the inorder successor
  String minValue(Node root) {
    String minv = root.name;
    while (root.left != null) {
      minv = root.left.name;
      root = root.left;
    }
    return minv;
  }
 
}
