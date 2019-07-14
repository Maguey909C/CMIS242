/*
 * File: CMIS242PRJ3RenickC.java
 * Author: Chase Renick
 * Date: July 2019
 * Purpose: This program is designed to act as an ATM machine on one account.
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
            
            //Sets up a 5x5 gird
            window.insets = new Insets (5, 5, 5, 5);
            
            //Incorporate the Radio Buttons and bounds
			checking = new JRadioButton("Checking"); 
			window.fill = GridBagConstraints.HORIZONTAL;
            window.gridx = 0;
            window.gridy = 3;
            window.gridwidth = 1;
			checking.setSelected(true); //Having the JRadio button of "iterative" selected upon opening the application
			add(checking, window);
			
			saving = new JRadioButton("Savings");
			window.fill = GridBagConstraints.HORIZONTAL;
            window.gridx = 1;
            window.gridy = 3;
            window.gridwidth = 1;
			add(saving, window);
			
			// Group the radio buttons to toggle between two groups
			ButtonGroup group = new ButtonGroup();
			group.add(checking);
			group.add(saving);

            //Various buttons for user to click on to later perform some action on the account
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
            } // end  GUI()    

      /**
       *This class handles all the action listeners. Takes care of the logic for each of the buttons when a user action clicks 
       */  
		public class Compute_Listener implements ActionListener  {

					// Actions performed 
			        @Override
			        public void actionPerformed(ActionEvent commands){
			            
			            String userInput = textField.getText();
			            String operators = commands.getActionCommand(); // Finding particular command
			            Boolean answer = checking.isSelected(); // Getting answer from whether 
			            
			            if(operators.equals("Deposit")){ // Looks for whether user clicked on the deposit button and then performs necessary operations
			            	Account.despositIt(userInput, answer);        
			            } else if (operators.equals("Withdraw")) { //Looks for whether user clicked on the withdraw button and then performs operations
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
		
		//Function: To show the current balance of the appropriate account 
		//Input: Boolean of whether or not the account is checking
		//Output: A pop-up window specifying the current balance in the appropriate account
		public static void showBalance(Boolean answer) {
			if (answer) {
				JOptionPane.showMessageDialog(frame, "Checking Balance: " + checkingAccount.toString());
			} else {
			JOptionPane.showMessageDialog(frame, "Savings Balance: " + savingsAccount.toString());
			}
		} //End showBalance
		
		//Function: To deposit funds into the appropriate account
	    //Input: The amount to be deposited, and whether or not the amount is going into checking
	    //Output: The amount deposited into the account
		static void despositIt(String userInput, Boolean answer) {
			try {
            	if (checkValue(userInput)) {
            		int intInput = Integer.parseInt(userInput);
            		if (answer){ //if it is checking, then do the following		
						checkingAccount += intInput; //
					} else { // do it to the savings 
						savingsAccount += intInput;
						};
					} else {
						JOptionPane.showMessageDialog(null, "Invalid input, please enter correct input data", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception ex) { //If the user types in something that is not valid then do the following
                    JOptionPane.showMessageDialog(null, "Invalid input, please enter correct input data", "Error", JOptionPane.ERROR_MESSAGE);
                }
	    } // end static depositIt

		//Function: Checks whether appropriate funds based on the user can be withdrawn from the appropriate account
	    //Input: The users specified amount, and whether or not it will be in checking or savings
	    //Output: The amount withdrawn from the appropriate account
		static void withdrawIt (String userInput, Boolean answer) {
			try {
            	if (checkValue(userInput)) {
            		int intInput = Integer.parseInt(userInput);
            		if (answer){ //		
						insufficientFunds.fundStatus(intInput, answer);
					} else { //
						insufficientFunds.fundStatus(intInput, answer);
						};
					} else {
						JOptionPane.showMessageDialog(null, "Invalid input, please enter correct input data", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception ex) { //If the user types in something that is not valid then do the following
                    JOptionPane.showMessageDialog(null, "Invalid input, please enter correct input data", "Error", JOptionPane.ERROR_MESSAGE);
                }
		}// End withdrawIt
		
		//Function: Function to transfer money from one account to another
	    //Input: UserInput which will be parsed, and whether or not it is going into checking or savings
	    //Output: Transfers an amount to a particular account 
		static void transferTo (String userInput, Boolean answer) {
			try {
            	if (checkValue(userInput)) {
            		int intInput = Integer.parseInt(userInput);
            		if (answer){ //		
//            			checkingAccount = checkingAccount - intInput;
            			insufficientFunds.fundStatus(intInput, answer);
						savingsAccount += intInput;
					} else { //
//						savingsAccount = savingsAccount - intInput;
						insufficientFunds.fundStatus(intInput, answer);
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
	
	
	//InsufficientFunds Class
	public static class insufficientFunds {
		
		private static JFrame frame;
		
		//Function: Checks the status of the account
		//Input: Integer version of user input, as well as whether or not to deduct from checking account or savings
		//Output: Either a window saying appropriate account is insufficient, or a deduction from that account based on the intInput
		static void fundStatus (Integer intInput, Boolean answer) {
			
			if (answer) {
				int newBalance = checkingAccount -intInput; // Checks to see if checking account balance will be negative 
				if (newBalance < 0){ //if it is negative then alert the user there are insufficient funds
					JOptionPane.showMessageDialog(frame, "Insufficient Funds to withdraw from checking account!");
				} else { //Otherwise deduct the amount
					checkingAccount -= intInput;
				}
			} else {
				int newBalance = savingsAccount -intInput; //Checks to see if savings account balance will be negative
				if (newBalance < 0){ //if it is negative then alert the user there insufficient funds
					JOptionPane.showMessageDialog(frame, "Insufficient Funds to withdraw from savings account!");
				} else { //Otherwise deduct the amount 
					savingsAccount -=  intInput;
				}
			}
		}
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
