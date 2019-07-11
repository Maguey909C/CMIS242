/*
 * AUTHOR: Chase Renick
 * Date: July 12, 2019
 * PURPOSE: The 
 * */

import java.util.ArrayList;
import java.util.LinkedList;

public class InvestmentGUI2 {
	
	public static void main(String[] args) {
		
		ArrayList<String> a = new ArrayList<String>(); 
        a.add("Dog"); 
        a.add("Cat"); 
        a.add("Bird"); 
        a.remove(2);  // Remove a value at index 3
        System.out.println("ArrayList object output : " +  a); 
  
        // Checking if an element is present. 
        if (a.contains("Dog")) 
            System.out.println("Found"); 
        else
            System.out.println("Not found"); 
  
  
        LinkedList b = new LinkedList(); 
        b.add("Lizard"); 
        b.add("Cow"); 
        b.add("Animal"); 
        b.remove("Animal"); //remove element at index 3
        System.out.println("LinkedList object output : " + b); 
  
        // Checking if an element is present. 
        if (b.contains("Lizard")) 
            System.out.println("Found"); 
        else
            System.out.println("Not found"); 
		
	}

}
