/*
 * File: CMIS242PRJ4RenickC.java
 * Author: Chase Renick
 * Date: July 14, 2019
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
import java.util.ArrayList;

public class CMISPRJ4RenickC {

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
	            //Each event attached to listener
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
			            String userName = name.getText();
			            String userMajor = major.getText();
			            String selected = box.getSelectedItem().toString();
			            String operators = commands.getActionCommand(); // Finding particular command
			            String[] gradeOptions = {"A","B","C","D"};
			            String[] numberOfCredits = {"1","2","4","6"};

			            if(operators.equals("Process Request")) {
				            //Execution if user clicks Process Request Button
				            try {
				            	Integer intId = Integer.parseInt(userId);
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
				            			JOptionPane.showMessageDialog(null, "Found!\n"+existingStudent.summary() +" found. See text box for details", "Message", JOptionPane.INFORMATION_MESSAGE);
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
				            			newStudent.addClass(gradeNumber, numofCreds); //adds grade and credits to the object
				            			map.put(userId, newStudent); //puts in the upadted version back into the hashmap
				            			JOptionPane.showMessageDialog(null, "Updated Database for \n"+ newStudent.summary(), "Message", JOptionPane.INFORMATION_MESSAGE); //relays that information to user
				            		} else {
				            			JOptionPane.showMessageDialog(null, "Unable to update. Please try again", "Error", JOptionPane.ERROR_MESSAGE);
				            		}
				            	} else {
				            		System.out.println("Unrecognized");
				            	}//End else
				            } catch (Exception ex) { //If the user types in something that is not valid then do the following
			                    JOptionPane.showMessageDialog(null, "Invalid input, please enter correct input data", "Error", JOptionPane.ERROR_MESSAGE);
			                } // end Exception
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
			} else if (gradeString.equals("D")) {
				return 1;
			} else {
				return 0;
			}
		}

	} // End GUI()

	static class Study {

			private Integer grade;
			private int credit;

			//Constructing the study
			public Study(Integer grade, Integer credit) {
				setGrade(grade);
				setCredit(credit);
			}

			//getting the grade
			public int getGrade() {
				return grade;
			}

			//setting the grade
			public void setGrade(Integer grade) {
				this.grade = grade;
			}

			//retrieving the credit
			public int getCredit() {
				return credit;
			}
			//Setting the credit
			public void setCredit(int credit) {
				this.credit = credit;
			}
		} // end of Study

	//Class Student
	static class Student {

		private String name;
		private String major;
		private List<Study> list = new ArrayList<Study>();

		//Constructor for Student name and major
		public Student (String name, String major) {
			this.name = name;
			this.major = major;
		}

		//retrieving name from object
		public String getName() {
			return name;
		} //end getName

		//setting name for the object
		public void setName() {
			this.name = name;
		} //end getMajor

		//getting major
		public String getMajor() {
			return major;
		}// end getMajor

		//setting major
		public void setMajor(String major) {
			this.major = major;
		} // end getMajor

		//Function: Takes the course grade and credit hours, creates a study object and puts it in a list
		//Input: Course grade integer and credit hours integer
		//Output: Nothing, but it will add the object to a list
		public void addClass(Integer courseGrade, Integer creditHours) {
			Study n = new Study(courseGrade, creditHours);
			list.add(n);
		}

		//Function: Designed to calculate the total GPA based on the course credit and grade
			//Input: Nothing
			//Output: A string representing a student's GPA
		public String calculateGPA() {
			int totalCredits =0;
			double sum = 0;
			for (Study course: list) {
				totalCredits += course.getCredit();
				sum += course.getGrade() * course.getCredit();
			}
			if (totalCredits == 0) {
				return "0";
			} else {
				double value = (sum / totalCredits);
				System.out.println(value);
				return Double.toString(value);
			}
		} // end calculateGPA

		//Function: To summarize the results from the query or update
		//Input: nothing
		//Output: An summary of the major information related to user
		public String summary() {
			StringBuilder data = new StringBuilder();
			data.append("Name of student: " + name+"\n");
			data.append("Student Major: " + major+"\n");
			String response = calculateGPA();
			data.append("Student GPA: " +response+ "\n");
			return data.toString();
		}
	}// End Student Class

	public static void main(String[] args) {
		GUI database = new GUI(); //Create instance of converter

        // Get basic parameters of a GUI
		database.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		database.pack();
		database.setTitle("Database");
		database.setLocationRelativeTo(null);
		database.setVisible(true);

	} // end main

} //end CMIS242PRJ4RenickC
