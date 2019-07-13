/*
 * File: CMIS242PRJ3RenickC.java
 * Author: Chase Renick
 * Date: July 13, 2019
 * Purpose: This program is designed to do a calculation recursively and iteratively
 * based on what a user types into the program.
 */

//Importing Necessary Packages
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.stream.Collectors; 

//Creating Class
public class CMIS242PRJ4RenickC {  
    
	public static Integer checkingAccount = 0;
	public static Integer savingsAccount = 0;
	
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
	
    public static class GUI extends JFrame {
        
        JTextField textField,textField_1,textField_2; //The values that are used to manipulate the data of the array
        JLabel entry1, entry2, entry3; //References to aid the user in the program
        private static JRadioButton checking, saving; //JRadio buttons
        
        private static JFrame frame; //Creates the Frame for display messages
   
        public GUI() {
            
            // Making layout GridBagLayout ()     
            setLayout(new GridBagLayout());
            frame = new JFrame();
            
            //Instance of the GUI
            GridBagConstraints window = new GridBagConstraints();
            
            window.insets = new Insets (5, 5, 5, 5);
            
            //Incorporate the Radio Buttons and bounds
			checking = new JRadioButton("Checking");  //Setting the Jradio button iterative as final
			window.fill = GridBagConstraints.HORIZONTAL;
            window.gridx = 0;
            window.gridy = 3;
            window.gridwidth = 1;
			checking.setSelected(true); //Having the JRadio button of "iterative" selected upon opening the application
			add(checking, window);
			
			saving = new JRadioButton("Savings"); //Setting the JRadio button recursive
			window.fill = GridBagConstraints.HORIZONTAL;
            window.gridx = 1;
            window.gridy = 3;
            window.gridwidth = 1;
			add(saving, window);
			
			// Group the radio buttons to toggle between two groups
			ButtonGroup group = new ButtonGroup();
			group.add(checking);
			group.add(saving);

            //JButton
			JButton withdraw = new JButton("Withdraw");
            window.fill = GridBagConstraints.HORIZONTAL;
            window.gridx = 0;
            window.gridy = 0;
            window.gridwidth = 1;
            add (withdraw, window);
            
            JButton deposit = new JButton("Deposit");
            window.fill = GridBagConstraints.HORIZONTAL;
            window.gridx = 1;
            window.gridy = 0;
            window.gridwidth = 1;
            add (deposit, window);
            
            JButton transfer = new JButton("Transfer to");
            window.fill = GridBagConstraints.HORIZONTAL;
            window.gridx = 0;
            window.gridy = 1;
            window.gridwidth = 1;
            add (transfer, window);
//            
            JButton balance = new JButton("Balance");
            window.fill = GridBagConstraints.HORIZONTAL;
            window.gridx = 1;
            window.gridy = 1;
            window.gridwidth = 1;
            add (balance, window);
            
            // Textfield 1
            textField = new JTextField(10);
            window.fill = GridBagConstraints.HORIZONTAL;
            window.gridx = 0;
            window.gridy = 4;
            window.gridwidth = 1;
            add (textField, window);
            
   
            //Make instances of events in the program
            Compute_Listener converter_listener = new Compute_Listener();
            //Each event attached to listener.
            deposit.addActionListener(converter_listener);
            withdraw.addActionListener(converter_listener);
            balance.addActionListener(converter_listener);
            transfer.addActionListener(converter_listener);
            } // end  Calculator()    

      /**
       *This class handles all the action listeners. Handles 
       */  
		public class Compute_Listener implements ActionListener  {

					// Actions performed 
			        @Override
			        public void actionPerformed(ActionEvent commands){
			            
			            String userInput = textField.getText();
			            String operators = commands.getActionCommand(); // Finding particular command
			            Boolean answer = checking.isSelected(); // Getting answer from whether 
			            
			            if(operators.equals("Deposit")){ //
			            	Account.despositIt(userInput, answer);   
			            	System.out.println("Entered Deposit");       
			            } else if (operators.equals("Withdraw")) {
			            	Account.withdrawIt(userInput, answer);
			            } else if (operators.equals("Balance")) {
			            	Account.showBalance(answer); 	
			            } else {
			            	Account.transferTo(userInput, answer);
			            }
		
			        }//End of public void actionPerformed
			        
		        }//End of public class Compute_Listener
		
    }//End of Calculator Class
    
	public static class Account {
		
		private static JFrame frame;
		
		//Function: checks whether or not a user's input is an integer or not
		//Input: The users input 
		//Output: Checks to see if the userinput can be parsed, so either an integer if it can or an error
		static boolean checkValue(String userInput) {
			try {
				Integer.parseInt(userInput); // Attempts to parse the Integer
				return true;
			  }
			   catch(Exception e){
			   return false;
			 }
		} // end CheckValue
		
		//Function:
		//Input:
		//Output:
		public static void showBalance(Boolean answer) {
			if (answer) {
				JOptionPane.showMessageDialog(frame, "Checking Balance: " + checkingAccount.toString());
			} else {
			JOptionPane.showMessageDialog(frame, "Savings Balance: " + savingsAccount.toString());
			}
		}
		
		//Function: 
	    //Input: 
	    //Output: 
		static void despositIt(String userInput, Boolean answer) {
			try {
            	if (checkValue(userInput)) {
            		int intInput = Integer.parseInt(userInput);
            		if (answer){ //if the iterative button is selected, do the following		
						checkingAccount += intInput; //call the computerRecursive method which will calculate fibonacci sequence iteratively
						System.out.println(checkingAccount);
					} else { //if the recursive button is selected do the following
						savingsAccount += intInput;	
						System.out.println(savingsAccount);
						};
					} else {
						JOptionPane.showMessageDialog(null, "Invalid input, please enter correct input data", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception ex) { //If the user types in something that is not valid then do the following
                    JOptionPane.showMessageDialog(null, "Invalid input, please enter correct input data", "Error", JOptionPane.ERROR_MESSAGE);
                }
	    } // end static deposit it

		//Function: 
	    //Input: 
	    //Output:
		static void withdrawIt (String userInput, Boolean answer) {
			try {
            	if (checkValue(userInput)) {
            		int intInput = Integer.parseInt(userInput);
            		if (answer){ //		
						checkingAccount -= intInput; //
					} else { //
						savingsAccount -= intInput;		
						};
					} else {
						JOptionPane.showMessageDialog(null, "Invalid input, please enter correct input data", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception ex) { //If the user types in something that is not valid then do the following
                    JOptionPane.showMessageDialog(null, "Invalid input, please enter correct input data", "Error", JOptionPane.ERROR_MESSAGE);
                }
		}// End withdrawIt
		
		//Function: 
	    //Input: 
	    //Output:
		static void transferTo (String userInput, Boolean answer) {
			try {
            	if (checkValue(userInput)) {
            		int intInput = Integer.parseInt(userInput);
            		if (answer){ //		
            			checkingAccount = checkingAccount - intInput;
						savingsAccount += intInput;
					} else { //
						savingsAccount = savingsAccount - intInput;
						checkingAccount += intInput;
						};
					} else {
						JOptionPane.showMessageDialog(null, "Invalid input, please enter correct input data", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception ex) { //
                    JOptionPane.showMessageDialog(null, "Invalid input, please enter correct input data", "Error", JOptionPane.ERROR_MESSAGE);
                }
		}// End withdrawIt
	} // public static class Account
	
	public static class insufficientFunds {
		
	}
	
	//Main Block
    public static void main(String[] args) {
    	GUI converter = new GUI(); //Create instance of converter 

         // Get basic parameters of a GUI 
         converter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         converter.pack();
         converter.setTitle("ATM Machine");
         converter.setLocationRelativeTo(null);
         converter.setVisible(true);
    }//End main()
    
}//End CMISPRJ3RenickC
