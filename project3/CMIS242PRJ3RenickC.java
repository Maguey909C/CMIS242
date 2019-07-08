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
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.JOptionPane;
import java.awt.SystemColor;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Arrays;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

public class CMIS242PRJ3RenickC {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	public static void WriteFile () {

	// [Code to open, write, and close file]
	} // end WriteFile

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
			public static Integer rc ++;
//		    getEfficiency(1);
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
	  public static Integer getEfficiency () {
//
	     return recursiveCounter;
	    }// end static Integer getEfficiency

	} // public static class Sequence

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame window = new Frame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Frame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		final JRadioButton rdbtnIterative = new JRadioButton("Iterative");  //Setting the Jradio button iterative as final
		rdbtnIterative.setSelected(true); //Having the JRadio button of "iterative" selected upon opening the application
		rdbtnIterative.setBounds(238, 30, 141, 23);
		frame.getContentPane().add(rdbtnIterative);

		JRadioButton rdbtnRecursive = new JRadioButton("Recursive"); //Setting the Jradio button recursive
		rdbtnRecursive.setBounds(238, 73, 141, 23);
		frame.getContentPane().add(rdbtnRecursive);

		textField = new JTextField();  //Creating the first user input text field
		textField.setBounds(235, 119, 190, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField(); //creating the second text field
		textField_1.setBounds(235, 198, 190, 26);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField(); //creating the third text field
		textField_2.setBounds(235, 236, 190, 26);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);

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
						textField_1.setEditable(false);
						textField_2.setText(userInput);
						textField_2.setEditable(false);
					} else { //if the recursive button is selected do the following
						Integer secondAnswer = Sequence.computerRecursive(intInput); //call the computerRecursive method which will calculate fibonacci sequence recursively
						String s2Answer = secondAnswer.toString();
						textField_1.setText(s2Answer); //setting the answer from the computerRecursive function to the output of the text field
						textField_1.setEditable(false); //not allowing the user to edit this text field

						Integer thirdAnswer = Sequence.getEfficiency();//Calling on getEfficiency method to show how many iterations came from the operation
						String s3Answer = thirdAnswer.toString();
						textField_2.setText(s3Answer); //setting the answer from the computerRecursive function to the output of the text field
						textField_2.setEditable(false); //not allowing the user to edit this text field
					}

				} catch (Exception ex) { //If the user types in something that is not valid then do the following
                    JOptionPane.showMessageDialog(null, "Invalid input, please enter correct input data", "Error", JOptionPane.ERROR_MESSAGE);
                }

			}
		});

		btnCompute.setBackground(SystemColor.textHighlight);
		btnCompute.setBounds(235, 157, 190, 29);
		frame.getContentPane().add(btnCompute);

		JTextPane txtpnEnterN = new JTextPane();
		txtpnEnterN.setBackground(SystemColor.window);
		txtpnEnterN.setText("Enter n:");
		txtpnEnterN.setBounds(20, 129, 59, 16);
		frame.getContentPane().add(txtpnEnterN);

		JTextPane txtpnResults = new JTextPane();
		txtpnResults.setBackground(SystemColor.window);
		txtpnResults.setText("Results:");
		txtpnResults.setBounds(20, 203, 59, 16);
		frame.getContentPane().add(txtpnResults);

		JTextPane txtpnEfficiency = new JTextPane();
		txtpnEfficiency.setBackground(SystemColor.window);
		txtpnEfficiency.setText("Efficiency:");
		txtpnEfficiency.setBounds(20, 246, 77, 16);
		frame.getContentPane().add(txtpnEfficiency);
	}
}
