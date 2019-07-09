/*
 * File: CMIS242PRJ3RenickC.java
 * Author: Chase Renick
 * Date: July 1, 2019
 * Purpose: This program is designed to do a calculation recursively and iteratively
 * based on what a user types into the program.
 */

//Installing Necessary Packages
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.io.*;
import java.awt.SystemColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Arrays;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

public class CMIS242PRJ3RenickC {

	private static JFrame frame;
	private static JTextField textField;
	private static JTextField instruction1;
	private static JTextField textField_1;
	private static JTextField instruction2;
	private static JTextField textField_2;
	private static JTextField instruction3;
	
	private static ArrayList<Integer> nTimes = new ArrayList<>();
	private static ArrayList<Integer> results = new ArrayList<>();
	private static ArrayList<Integer> efficiencies = new ArrayList<>();

	public static void WriteFile (ArrayList<Integer> nTimes, ArrayList<Integer> results, ArrayList<Integer> efficiencies) {
		String fileName = "results.csv";
		try {
			 // Assume default encoding.
			 FileWriter fileWriter = new FileWriter(fileName);
	
		     // Always wrap FileWriter in BufferedWriter.
		     BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		
		     // Note that write() does not automatically
		     // append a newline character.
		     bufferedWriter.write("Hello there,");
		     bufferedWriter.write(" here is some text.");
		     bufferedWriter.newLine();
		     bufferedWriter.write("We are writing");
		     bufferedWriter.write(" the text to the file.");
		
		     // Always close files.
		     bufferedWriter.close();
		     }
		catch(IOException ex) {
			System.out.println("Error writing to file '"+ fileName + "'");
		     // ex.printStackTrace();
			}
		}// end WriteFile
	
	
	public static class GUI {
		
	}
	
	//Sequence Class
	public static class Sequence {

		public static Integer recursiveCounter = 0;

		//Function: Calculates the fibonacci sequence in an iterative fashion
	    //Input: An integer specified by the user
	    //Output: An integer based on each term of the sequence is twice the previous term plus the second
		//previous term
		static Integer computerIterative(Integer val) {
			Integer counter = 0;
	        int[] smf = new int[val + 1];
	        smf[1] = 1;
			smf[0] = 0;

	        for (int i = 2; i <= val; i++) {
	        	smf[i] = 2 * smf[i - 1] + smf[i - 2];
	            counter++;
	        }
	        return smf[val];
	    } // end static Integer computerIterative

		//Function: Calculates the fibonacci sequence in a recursive fashion
	    //Input: An integer specified by the user
	    //Output: An integer based on each term of the sequence is twice the previous term plus the second
		//previous term calculated recursively
		static Integer computerRecursive (Integer val) {
			recursiveCounter ++;
	        if (val <= 0) {
	            return 0;
	        } else {
	            if (val == 1) {
	                return 1;
	            } else {
	            	return computerRecursive(val - 2) + computerRecursive(val - 1) * 2; 
	            }
	        }
	    } // end static Integer computerRecursive
		
		//Function: Shows how many times the recursive function was called
	    //Input: An integer based on the counter
	    //Output: An integer showing how often it was called
	     public static Integer getEfficiency (Integer code, Integer userInput) {
	    	 if (code == 1) {
	    		 return userInput;
	    	 } else {
	    		 Integer answer = recursiveCounter;
	    		 recursiveCounter = 0;
	    		 return answer;
	    	 }
	    } // end static Integer getEfficiency

	} // public static class Sequence

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Build Basic JFrame
					frame = new JFrame("CMIS242PRJ3RenickC");
					frame.setBounds(100, 100, 450, 300);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.getContentPane().setLayout(null);
					frame.setVisible(true);
					
					//Incorporate the Radio Buttons and bounds
					final JRadioButton rdbtnIterative = new JRadioButton("Iterative");  //Setting the Jradio button iterative as final
					rdbtnIterative.setSelected(true); //Having the JRadio button of "iterative" selected upon opening the application
					rdbtnIterative.setBounds(238, 30, 141, 23);
					frame.getContentPane().add(rdbtnIterative);
					
					JRadioButton rdbtnRecursive = new JRadioButton("Recursive"); //Setting the Jradio button recursive
					rdbtnRecursive.setBounds(238, 73, 141, 23);
					frame.getContentPane().add(rdbtnRecursive);
					
					//Group the radio buttons to toggle between two groups
					ButtonGroup group = new ButtonGroup();
					group.add(rdbtnIterative);
					group.add(rdbtnRecursive);
					
					//Create the Text Fields within the content pane
					textField = new JTextField();  //Creating the first user input text field
					textField.setBounds(235, 119, 190, 26);
					frame.getContentPane().add(textField);
					textField.setColumns(10);
					
					instruction1 = new JTextField("Enter n:");
					instruction1.setBounds(20, 129, 59, 16);
					frame.getContentPane().add(instruction1);
					instruction1.setEditable(false);
					instruction1.setColumns(4);

					textField_1 = new JTextField(); //creating the second text field
					textField_1.setBounds(235, 198, 190, 26);
					frame.getContentPane().add(textField_1);
					textField_1.setColumns(10);
					textField_1.setEditable(false);
					
					instruction2 = new JTextField("Results:");
					instruction2.setBounds(20, 203, 59, 16);
					frame.getContentPane().add(instruction2);
					instruction2.setEditable(false);
					instruction2.setColumns(4);

					textField_2 = new JTextField(); //creating the third text field
					textField_2.setBounds(235, 236, 190, 26);
					frame.getContentPane().add(textField_2);
					textField_2.setColumns(10);
					textField_2.setEditable(false);
					
					instruction3 = new JTextField("Efficiency:");
					instruction3.setBounds(20, 246, 77, 16);
					frame.getContentPane().add(instruction3);
					instruction3.setEditable(false);
					instruction3.setColumns(4);
					
					JButton btnCompute = new JButton("Compute");
					btnCompute.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								String userInput = textField.getText();
								int intInput = Integer.parseInt(userInput);

								if (rdbtnIterative.isSelected()){ //if the iterative button is selected, do the following
									
									Integer firstAnswer = Sequence.computerIterative(intInput); //call the computerRecursive method which will calculate fibonacci sequence iteratively
									String sAnswer = firstAnswer.toString();
									textField_1.setText(sAnswer);
									
									Integer efficiency = Sequence.getEfficiency(1,intInput);	
									textField_2.setText(efficiency.toString());
									
									nTimes.add(intInput);
									results.add(firstAnswer);
									efficiencies.add(intInput);
									
								} else { //if the recursive button is selected do the following
									
									Integer secondAnswer = Sequence.computerRecursive(intInput); //call the computerRecursive method which will calculate fibonacci sequence recursively
									String s2Answer = secondAnswer.toString();
									textField_1.setText(s2Answer); //setting the answer from the computerRecursive function to the output of the text field
									textField_1.setEditable(false); //not allowing the user to edit this text field

									Integer thirdAnswer = Sequence.getEfficiency(2,intInput);//Calling on getEfficiency method to show how many iterations came from the operation
									String s3Answer = thirdAnswer.toString();
									textField_2.setText(s3Answer); //setting the answer from the computerRecursive function to the output of the text field
									textField_2.setEditable(false); //not allowing the user to edit this text field
									
									nTimes.add(intInput);
									results.add(secondAnswer);
									efficiencies.add(thirdAnswer);
								}

							} catch (Exception ex) { //If the user types in something that is not valid then do the following
			                    JOptionPane.showMessageDialog(null, "Invalid input, please enter correct input data", "Error", JOptionPane.ERROR_MESSAGE);
			                }

						}
					});
					
					btnCompute.setBackground(SystemColor.textHighlight);
					btnCompute.setBounds(235, 157, 190, 29);
					frame.getContentPane().add(btnCompute);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
