/*
 * File: CMIS242PRJ3RenickC.java
 * Author: Chase Renick
 * Date: July 13, 2019
 * Purpose: This program is designed to do a calculation recursively and iteratively
 * based on what a user types into the program.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.stream.Collectors; 
import java.lang.Math;
import java.util.ArrayList;

public class PRJ3 {  
    
	private static ArrayList<String> nTimes = new ArrayList<>();
	private static ArrayList<String> results = new ArrayList<>();
	private static ArrayList<String> efficiencies = new ArrayList<>();
	
	public static void WriteFile (ArrayList<String> nTimes, ArrayList<String> results, ArrayList<String> efficiencies) {
		String fileName = "efficiency.csv";
		String fileName2 = "efficieny.txt";
		try {
			 // Assume default encoding.
			 FileWriter fileWriter = new FileWriter(fileName);
			 FileWriter fileWriter2 = new FileWriter(fileName2);
	
		     // Always wrap FileWriter in BufferedWriter.
		     BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		     BufferedWriter bufferedWriter2 = new BufferedWriter(fileWriter2);
		
		     // Note that write() does not automatically
		     // append a newline character.
		     
		     String line1 = nTimes.stream().collect(Collectors.joining(","));
		     String line3 = results.stream().collect(Collectors.joining(","));
		     String line5 = efficiencies.stream().collect(Collectors.joining(","));
		     
		     //Writing to File 1 (csv)
		     bufferedWriter.write(line1);
		     bufferedWriter.newLine();
		     bufferedWriter.write(line3);
		     bufferedWriter.newLine();
		     bufferedWriter.write(line5);
		     
		     //Writing to File 2 (txt)
		     bufferedWriter2.write(line1);
		     bufferedWriter2.newLine();
		     bufferedWriter2.write(line3);
		     bufferedWriter2.newLine();
		     bufferedWriter2.write(line5);
		     

		     //Closing Files
		     bufferedWriter.close();
		     bufferedWriter2.close();
		     }
		catch(IOException ex) {
			System.out.println("Error writing to file '"+ fileName + "'");
		     // ex.printStackTrace();
			}
		}// end WriteFile
	
    public static class Calculator extends JFrame {
        
        JTextField textField,textField_1,textField_2; //The values that are used to manipulate the data of the array
        JLabel entry1, entry2, entry3; //References to aid the user in the program
        private static JRadioButton rdbtnIterative, rdbtnRecursive; //JRadio buttons
        
        private static JFrame frame; //Creates the Frame for display messages
   
        public Calculator() {
            
            // Making layout GridBagLayout ()     
            setLayout(new GridBagLayout());
            frame = new JFrame();
            
            //Instance of the GUI
            GridBagConstraints window = new GridBagConstraints();
            
            window.insets = new Insets (5, 5, 5, 5);
            
            //Incorporate the Radio Buttons and bounds
			rdbtnIterative = new JRadioButton("Iterative");  //Setting the Jradio button iterative as final
			window.fill = GridBagConstraints.HORIZONTAL;
            window.gridx = 1;
            window.gridy = 0;
            window.gridwidth = 1;
			rdbtnIterative.setSelected(true); //Having the JRadio button of "iterative" selected upon opening the application
			add(rdbtnIterative, window);
			
			rdbtnRecursive = new JRadioButton("Recursive"); //Setting the JRadio button recursive
			window.fill = GridBagConstraints.HORIZONTAL;
            window.gridx = 1;
            window.gridy = 1;
            window.gridwidth = 1;
			add(rdbtnRecursive, window);
			
			// Group the radio buttons to toggle between two groups
			ButtonGroup group = new ButtonGroup();
			group.add(rdbtnIterative);
			group.add(rdbtnRecursive);
            
			// Entry N JLabel
            entry1 = new JLabel ("Enter N: ");
            entry1.setHorizontalAlignment(JLabel.LEFT);
            window.fill = GridBagConstraints.HORIZONTAL;
            window.gridx = 0;
            window.gridy = 2;
            window.gridwidth = 1;
            add (entry1, window);
            
            // Entry Two JLabel
            entry2 = new JLabel ("Results: ");
            entry2.setHorizontalAlignment(JLabel.LEFT);
            window.fill = GridBagConstraints.HORIZONTAL;
            window.gridx = 0;
            window.gridy = 3;
            window.gridwidth = 1;
            add (entry2, window);
            
            // Entry Three JLabel
            entry3 = new JLabel ("Efficiency: ");
            entry3.setHorizontalAlignment(JLabel.LEFT);
            window.fill = GridBagConstraints.HORIZONTAL;
            window.gridx = 0;
            window.gridy = 4;
            window.gridwidth = 1;
            add (entry3, window);
            
            // Textfield 1
            textField = new JTextField(8);
            window.fill = GridBagConstraints.HORIZONTAL;
            window.gridx = 1;
            window.gridy = 2;
            window.gridwidth = 1;
            add (textField, window);
            
            // Textfield 2
            textField_1 = new JTextField(4);
            window.fill = GridBagConstraints.HORIZONTAL;
            window.gridx = 1;
            window.gridy = 3;
            window.gridwidth = 1;
            textField_1.setEditable(false);
            add (textField_1, window);
            
            // Textfield 3
            textField_2 = new JTextField(4);
            window.fill = GridBagConstraints.HORIZONTAL;
            window.gridx = 1;
            window.gridy = 4;
            window.gridwidth = 1;
            textField_2.setEditable(false);
            add (textField_2, window);
            
            //JButton
			JButton btnCompute = new JButton("Compute");
            window.fill = GridBagConstraints.HORIZONTAL;
            window.gridx = 0;
            window.gridy = 5;
            window.gridwidth = 0;
            add (btnCompute, window);
            
            //Putting some initial labels to arrays to designate the responses for end user
            nTimes.add("Input Value");
			results.add("Result Value");
			efficiencies.add("Efficiency Value");
   
            //Make instances of events in the program
            Compute_Listener converter_listener = new Compute_Listener();
            //Each event attached to listener.
            btnCompute.addActionListener(converter_listener);
            } // end  Calculator()    

      /**
       *This class handles all the action listeners. Handles 
       */  
		public class Compute_Listener implements ActionListener  {
					
					//Function checks whether or not a user's input is an integer or not
					public boolean checkValue(String userInput) {
						try {
							Integer.parseInt(userInput); // Attempts to parse the Integer
							return true;
						  }
						   catch(Exception e){
						   return false;
						 }
					}
					
					// Actions performed 
			        @Override
			        public void actionPerformed(ActionEvent commands){
			            
			            String userInput = textField.getText();
			            String operators = commands.getActionCommand(); // Finding particular command
			            Boolean answer = rdbtnIterative.isSelected(); // Getting answer from whether 
			            
			            if(operators.equals("Compute")){ //
			            	try {
				            	if (checkValue(userInput)) {
				            		int intInput = Integer.parseInt(userInput);
				            		if (answer){ //if the iterative button is selected, do the following		
			    						Integer firstAnswer = Sequence.computerIterative(intInput); //call the computerRecursive method which will calculate fibonacci sequence iteratively
										String sAnswer = firstAnswer.toString();
										textField_1.setText(sAnswer);
										
										Integer efficiency = Sequence.getEfficiency(1,intInput);	
										textField_2.setText(efficiency.toString());
										
										nTimes.add(userInput);
										results.add(sAnswer);
										efficiencies.add(userInput);
									} else { //if the recursive button is selected do the following
										Integer secondAnswer = Sequence.computerRecursive(intInput); //call the computerRecursive method which will calculate fibonacci sequence recursively
										String s2Answer = secondAnswer.toString();
										textField_1.setText(s2Answer); //setting the answer from the computerRecursive function to the output of the text field
										textField_1.setEditable(false); //not allowing the user to edit this text field
				 
				    					Integer thirdAnswer = Sequence.getEfficiency(2,intInput);//Calling on getEfficiency method to show how many iterations came from the operation
										String s3Answer = thirdAnswer.toString();
										textField_2.setText(s3Answer); //setting the answer from the computerRecursive function to the output of the text field
										textField_2.setEditable(false); //not allowing the user to edit this text field
										
										nTimes.add(userInput);
										results.add(s2Answer);
										efficiencies.add(s3Answer);
										};
									} else {
										JOptionPane.showMessageDialog(null, "Invalid input, please enter correct input data", "Error", JOptionPane.ERROR_MESSAGE);
									}
		    					} catch (Exception ex) { //If the user types in something that is not valid then do the following
			                        JOptionPane.showMessageDialog(null, "Invalid input, please enter correct input data", "Error", JOptionPane.ERROR_MESSAGE);
			                    }
			            } // End Compute
		
			        }//End of public void actionPerformed
			        
		        }//End of public class Compute_Listener
		
    }//End of Calculator Class
    
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
	
	//Main Block
    public static void main(String[] args) {
    	Calculator converter = new Calculator(); //Create instance of converter 

         // Get basic parameters of a GUI 
         converter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         converter.addWindowListener(new WindowAdapter(){
				@Override
				public void windowClosing(WindowEvent event) {
					//Pops up a listener, and when the users select the listener it will write to the file and close
					if (JOptionPane.showConfirmDialog(converter,  "You want to close the program?","Please confirm", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) { 
						WriteFile(nTimes, results, efficiencies);
						System.exit(0);
					}
				}
			});
         converter.pack();
         converter.setTitle("Factorial Calculator");
         converter.setLocationRelativeTo(null);
         converter.setVisible(true);
    }//End main()
    
}//End CMISPRJ3RenickC
