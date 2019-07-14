/*
 * File: CMIS242PRJ4RenickC.java
 * Author: Chase Renick
 * Date: July 10, 2019
 * Purpose: This program is designed to do a calculation recursively and iteratively
 * based on what a user types into the program.
 */

//Installing Necessary Packages

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

import java.io.*;
import java.awt.event.*;
import java.awt.SystemColor;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class CMIS242PRJ4RenickC {
	
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
			     
			            
			            if(operators.equals("Process Request")) { // Process Request
			            	if (selected == "Insert") {
			            		
			            	} else if (selected == "Insert") {
			            		map.(userId, Student(userName,userMajor));
//			            		map.put(intId, Student(userName, userMajor) );
			            	} else if (selected == "Delete") {
			            		if (map.remove(userId) != null) {
			            			JOptionPane.showMessageDialog(null, "Student ID"+id.getText()+"is deleted.", "Message", JOptionPane.INFORMATION_MESSAGE);
			            		} else {
			            			JOptionPane.showMessageDialog(null, "Student ID"+id.getText()+"cannot be found in the database", "Message", JOptionPane.INFORMATION_MESSAGE);
			            		}
			            	} else if (selected == "Find") {
			            		String studentId = map.get(userId);
			            		if (student != null) {
			            			userName.setText(student.getName());
			            			userMajor.setText(String.copyValueOf(student.getMajor()));
			            			JOptionPane.showMessageDialog(null, student.toString(), "Message", JOptionPane.INFORMATION_MESSAGE);
			            		} else {
			            			JOptionPane.showMessageDialog(null, "Could not find Student ID" +id.getText()+ " within databse.", "Message", JOptionPane.INFORMATION_MESSAGE);
			            		}
			            	} else if (selected == "Update") {
			            		System.out.println("Update");
			            	} else {
			            		System.out.println("Unrecognized");
			            	}
			          
			            }//End else
			            
			        }// End actionPerformed

		
						
			        
		} // End Compute Listener
		
	} // End GUI()
	
	public static class Study {
		
		private double grade;
		private int credit;
		
		public Study(double grade, int credit) {
			setGrade(grade);
			setCredit(credit);
		}
		
		public double getGrade() {
			return grade;
		}
		
		public void setGrade(double grade) {
			this.grade = grade;
		}
		
		public int getCredit() {
			return credit;
		}
		
		public void setCredit(int credit) {
			this.credit = credit;
		}
	} // end Study
	
	public static class Student {
		
		private String name;
		private String major;
		private List<Study> list;
		
		public Student (String name, String major) {
			this.name = name;
			this.major = major;	
		}
		
		public String getName() {
			return name;
		} //end getName
		
		public void setName() {
			this.name = name;
		} //end getMajor
		
		public String getMajor() {
			return major;
		}// end getMajor
		
		public void setMajor(String major) {
			this.major = major;
		} // end getMajor
		
		public Double calculateGPA(Double courseGrade, Double creditHours) {
			int totalCredits =0;
			double sum = 0;
			for (Study course: list) {
				totalCredits += course.getCredit();
				sum += course.getGrade() * course.getCredit();
			}
			if (totalCredits == 0) {
				return 0.0;
			} else {
				return (sum / totalCredits);
			}
		} // end calculateGPA
		
		public void increaseStudy(Double courseGrade, int creditHours) {
			list.add(new Study(courseGrade, creditHours));
		}// end increaseStudy
		
	}// End Student Class
	
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

