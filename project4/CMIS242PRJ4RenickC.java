/*
 * File: CMIS242PRJ4RenickC.java
 * Author: Chase Renick
 * Date: July 10, 2019
 * Purpose: This program is designed to act as a database allowing a user to insert, delete, find, and update records.
 */

//Installing Necessary Packages

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

import java.io.*;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class InvestmentGUI2 {
	
	public static Map<String, Student> map = new HashMap<String, Student>();
	
	public static class GUI extends JFrame {
	        
	        JButton calculate_button; 
	        JTextField id, name, major; //The values that are used to manipulate the data of the array
	        JLabel entry1, entry2, entry3, entry4; //References to aid the user in the program
	        
	        private JFrame frame;//Creates the Frame for display messages
	        private JComboBox box;
	        
	        public GUI() {
	            
	            // Making layout GridBagLayout ()     
	            setLayout(new GridBagLayout());
	            frame = new JFrame();
	            box = new JComboBox();
	           
	            //Instance of the GUI
	            GridBagConstraints window = new GridBagConstraints();
	            
	            window.insets = new Insets (5, 5, 5, 5);
	            
	            entry1 = new JLabel ("ID: ");
	            entry1.setHorizontalAlignment(JLabel.LEFT);
	            window.fill = GridBagConstraints.HORIZONTAL;
	            window.gridx = 0;
	            window.gridy = 0;
	            window.gridwidth = 1;
	            add (entry1, window);
	            
	            entry2 = new JLabel ("Name: ");
	            entry2.setHorizontalAlignment(JLabel.LEFT);
	            window.fill = GridBagConstraints.HORIZONTAL;
	            window.gridx = 0;
	            window.gridy = 1;
	            window.gridwidth = 1;
	            add (entry2, window);
	            
	            entry3 = new JLabel ("Major: ");
	            entry3.setHorizontalAlignment(JLabel.LEFT);
	            window.fill = GridBagConstraints.HORIZONTAL;
	            window.gridx = 0;
	            window.gridy = 2;
	            window.gridwidth = 1;
	            add (entry3, window);
	            
	            entry4 = new JLabel ("Choose Selection: ");
	            entry4.setHorizontalAlignment(JLabel.LEFT);
	            window.fill = GridBagConstraints.HORIZONTAL;
	            window.gridx = 0;
	            window.gridy = 3;
	            window.gridwidth = 1;
	            add (entry4, window);
	            
	            id = new JTextField(8);
	            window.fill = GridBagConstraints.HORIZONTAL;
	            window.gridx = 1;
	            window.gridy = 0;
	            window.gridwidth = 1;
	            add (id, window);
	            
	            name = new JTextField(8);
	            window.fill = GridBagConstraints.HORIZONTAL;
	            window.gridx = 1;
	            window.gridy = 1;
	            window.gridwidth = 1;
	            add (name, window);
	            
	            major = new JTextField(8);
	            window.fill = GridBagConstraints.HORIZONTAL;
	            window.gridx = 1;
	            window.gridy = 2;
	            window.gridwidth = 1;
	            add (major, window);
	            
	            String s1[] = {"Insert", "Delete", "Find", "Update"};
	            box = new JComboBox(s1);
	            window.fill = GridBagConstraints.HORIZONTAL;
	            window.gridx = 1;
	            window.gridy = 3;
	            window.gridwidth = 1;
	            add (box, window);
	            
	            calculate_button = new JButton("Process Request");
	            window.fill = GridBagConstraints.HORIZONTAL;
	            window.gridx = 0;
	            window.gridy = 4;
	            window.gridwidth = 0;
	            add (calculate_button, window);

		        //Make instances of events in the program
	            Compute_Listener converter_listener = new Compute_Listener();
	            //Each event attached to listener.
	            calculate_button.addActionListener(converter_listener);
	            } // end  GUI()    

      /**
       *This class handles all the action listeners. Takes care of the logic for each of the buttons when a user action clicks 
       */  
		public class Compute_Listener implements ActionListener  {

					// Actions performed 
			        @Override
			        public void actionPerformed(ActionEvent commands){
			            
			            String userId = id.getText();
			            Integer intId = Integer.parseInt(userId);
			            String userName = name.getText();
			            String userMajor = major.getText();
			            String selected = box.getSelectedItem().toString();
			            String operators = commands.getActionCommand(); // Finding particular command
			            String[] gradeOptions = {"A","B","C","D"};
			            String[] numberOfCredits = {"1","2","4","6"};
			            
			            if(operators.equals("Process Request")) { 
				            //Execution if user clicks Process Request Button
				            try {
				            	if (selected == "Insert") { // Looks to see in combo box if insertion was highlighted
				            		System.out.println("Inserting");
				            		Student newStudent = new Student(userName, userMajor);
				            		map.put(userId, newStudent);
				            		JOptionPane.showMessageDialog(null, "Student with ID " +userId+" has been inserted into the database. ", "Message", JOptionPane.INFORMATION_MESSAGE);
				            	} else if (selected == "Delete") { // Looks to see in combo box if deletion was highlighted
				            		System.out.println("Deleting");
				            		if (map.remove(userId) != null) {
				            			JOptionPane.showMessageDialog(null, "Student with ID "+id.getText()+"has been deleted from the database.", "Message", JOptionPane.INFORMATION_MESSAGE);
				            		} else {
				            			JOptionPane.showMessageDialog(null, "Student with ID "+id.getText()+"cannot be found in the database", "Message", JOptionPane.INFORMATION_MESSAGE);
				            		}
				            	} else if (selected == "Find") { // Looks to see in combo box if find was highlighted
				            		System.out.println("Searching");
				            		Student existingStudent = map.get(userId);
				            		if (existingStudent != null) {
				            			name.setText(existingStudent.getName()); //sets the name of the student in jtextbook and searches for existing student in hashmap
				            			major.setText(String.valueOf(existingStudent.getMajor()));
				            			JOptionPane.showMessageDialog(null, existingStudent.summary() +" found. See text box for details", "Message", JOptionPane.INFORMATION_MESSAGE);
				            		} else {
				            			JOptionPane.showMessageDialog(null, "Cannot not find the student with ID" +id.getText()+ " within the database.", "Message", JOptionPane.INFORMATION_MESSAGE);
				            		}
				            	} else if (selected == "Update") { // Looks to see in combo box if update was highlighted
				            		System.out.println("Updating");
				            		
				            		//Takes the user's selection for the update
				            		String gradeString = (String) JOptionPane.showInputDialog(null,"Select Grade: ","",JOptionPane.QUESTION_MESSAGE,null,gradeOptions,gradeOptions[0]);
				            		
				            		//Calculates an integer version of the string
				            		Integer gradeNumber = getGradeNumber(gradeString);
				            		
				            		String creds = (String) JOptionPane.showInputDialog(null,"Select Credit #: ","",JOptionPane.QUESTION_MESSAGE,null,numberOfCredits,numberOfCredits[0]);
				            		Integer numofCreds = Integer.parseInt(creds);
				            		if (map.containsKey(userId)) { //Looks to see if the userId is found in the hashmap
				            			Student newStudent = map.get(userId); //gets out the object from the hashmap containing student data
				            			newStudent.supplementalClass(gradeNumber, numofCreds); //adds grade and credits to the object
				            			map.put(userId, newStudent); //puts in the upadted versio back into the hashmap
				            			JOptionPane.showMessageDialog(null, newStudent.summary(), "Message", JOptionPane.INFORMATION_MESSAGE); //relays that information to user
				            		}
				            	} else {
				            		System.out.println("Unrecognized");
				            	}//End else
				            } catch (Exception ex) { //If the user types in something that is not valid then do the following
			                    JOptionPane.showMessageDialog(null, "Invalid input, please enter correct input data", "Error", JOptionPane.ERROR_MESSAGE);
			                }
			            }
			        }// End actionPerformed

		} // End Compute Listener
		
		
		//Function: Returns a grade number based on the letter grade a student receives
		//Input: A letter grade number
		//Output: An integer representing the letter grade
		public static Integer getGradeNumber(String gradeString) {
			if (gradeString.equals("A")) {
				return 4;
			} else if (gradeString.equals("B")){
				return 3;
			} else if (gradeString.equals("C")) {
				return 2;
			} else if (gradeString.equals("C")) {
				return 1;	
			} else {
				return 0;
			}
		}
		
	} // End GUI()
	
	
	public static void main(String[] args) {
		GUI database = new GUI(); //Create instance of converter 

        // Get basic parameters of a GUI 
		database.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		database.pack();
		database.setTitle("Database");
		database.setLocationRelativeTo(null);
		database.setVisible(true);

	}

}

