/*
 * File: CMIS242PRJ3RenickC.java
 * Author: Chase Renick
 * Date: July 13, 2019
 * Purpose: This program is designed to act like an ATM on multiple accounts. However it should be rewritten to become more modular, 
 *although this code works
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
public class CMIS242PRJ2RenickC {  
    
	
	public static Integer checkingAccount9812 = 0;
	public static Integer savingsAccount9812 = 0;
	public static Integer checkingAccount7622 = 0;
	public static Integer savingsAccount7622 = 0;
	public static Integer checkingAccount7623 = 1200;
	public static Integer savingsAccount7623 = 2000;
	
    public static class GUI extends JFrame {
        
        JTextField textField,textField_1,textField_2; //The values that are used to manipulate the data of the array
        JLabel entry1, entry2, entry3; //References to aid the user in the program
        private static JRadioButton checking, saving, acct9812, acct7622, acct7623; //JRadio buttons
        
        private static JFrame frame; //Creates the Frame for display messages
   
        public GUI() {
            
            // Making layout GridBagLayout ()     
            setLayout(new GridBagLayout());
            frame = new JFrame();
            
            //Instance of the GUI
            GridBagConstraints window = new GridBagConstraints();
            
            //Sets up a 5x5 gird
            window.insets = new Insets (6, 6, 6, 6);
            
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
			
			acct9812 = new JRadioButton("Acct#: 9812");
			window.fill = GridBagConstraints.HORIZONTAL;
            window.gridx = 0;
            window.gridy = 5;
            window.gridwidth = 1;
			add(acct9812, window);
			
			acct7622 = new JRadioButton("Acct#: 7622");
			window.fill = GridBagConstraints.HORIZONTAL;
            window.gridx = 1;
            window.gridy = 5;
            window.gridwidth = 1;
			add(acct7622, window);
			
			acct7623 = new JRadioButton("Acct#: 7623");
			window.fill = GridBagConstraints.HORIZONTAL;
            window.gridx = 0;
            window.gridy = 6;
            window.gridwidth = 1;
			add(acct7623, window);
			
			// Group the radio buttons to toggle between two groups
			ButtonGroup group = new ButtonGroup();
			group.add(checking);
			group.add(saving);
			
			ButtonGroup group2 = new ButtonGroup();
			group2.add(acct9812);
			group2.add(acct7622);
			group2.add(acct7623);

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
			            Boolean answer = checking.isSelected(); // Check if checking account is selected
			            Boolean acct98 = acct9812.isSelected();
						Boolean acct76 = acct7622.isSelected();
						Boolean acct762 = acct7623.isSelected();
			            
			            if(operators.equals("Deposit")){ // Looks for whether user clicked on the deposit button and then performs necessary operations
			            	
			            	if (acct98) {
			            		Account.despositIt(userInput, answer, 9812); 
			            	} else if (acct76) {
			            		Account.despositIt(userInput, answer, 7622); 
			            	} else if (acct762) {
			            		Account.despositIt(userInput, answer, 7623); 
			            	} else {
			            		System.out.println("Unknown Account Deposit");
			            	}
			            		
			            } else if (operators.equals("Withdraw")) { //Looks for whether user clicked on the withdraw button and then performs operations
			            	if (acct98) {
			            		Account.withdrawIt(userInput, answer, 9812); 
			            	} else if (acct76) {
			            		Account.withdrawIt(userInput, answer, 7622); 
			            	} else if (acct762) {
			            		Account.withdrawIt(userInput, answer, 7623); 
			            	} else {
			            		System.out.println("Unknown Account Balance");
			            	}
			            	
			            } else if (operators.equals("Balance")) {
			            	
			            	if (acct98) {
			            		Account.showBalance(answer, 9812); 
			            	} else if (acct76) {
			            		Account.showBalance(answer, 7622); 
			            	} else if (acct762) {
			            		Account.showBalance(answer, 7623); 
			            	} else {
			            		System.out.println("Unknown Account Balance");
			            	}
			            		
			            } else {
			            	if (acct98) {
			            		Account.transferTo(userInput, answer, 9812); 
			            	} else if (acct76) {
			            		Account.transferTo(userInput, answer, 7622); 
			            	} else if (acct762) {
			            		Account.transferTo(userInput, answer, 7623); 
			            	} else {
			            		System.out.println("Unknown Account Balance");
			            	}
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
		public static void showBalance(Boolean answer, Integer accountNumber) {
			if (accountNumber == 9812) {
				if (answer) {
					JOptionPane.showMessageDialog(frame, "Checking Account (9812) Balance: \n $" + checkingAccount9812.toString());
				} else {
					JOptionPane.showMessageDialog(frame, "Savings Account (9812) Balance: \n $" + savingsAccount9812.toString());
				}
			} else if (accountNumber == 7622) {
				if (answer) {
					JOptionPane.showMessageDialog(frame, "Checking Account (7622) Balance: \n $" + checkingAccount7622.toString());
				} else {
					JOptionPane.showMessageDialog(frame, "Savings Account (7622) Balance: \n $" + savingsAccount7622.toString());
				}
			} else {
				if (answer) {
					JOptionPane.showMessageDialog(frame, "Checking Account (7623) Balance: \n $" + checkingAccount7623.toString());
				} else {
					JOptionPane.showMessageDialog(frame, "Savings Account (7623) Balance: \n $" + savingsAccount7623.toString());
				}
			}
			
		} //End showBalance
		
		//Function: To deposit funds into the appropriate account
	    //Input: The amount to be deposited, and whether or not the amount is going into checking
	    //Output: The amount deposited into the account
		static void despositIt(String userInput, Boolean answer, Integer accountNumber) {
			try {
            	if (checkValue(userInput)) {
            		int intInput = Integer.parseInt(userInput);
            			if (accountNumber == 9812) {
            				if (answer) {
            					checkingAccount9812 += intInput; //
            				} else {
            					savingsAccount9812 += intInput;
            				}
            			} else if (accountNumber == 7622) { //  
            				if (answer) {
                				checkingAccount7622 += intInput; //
                			} else {
                				savingsAccount7622 += intInput;
                			}
            			} else {
            				if (answer) {
                				checkingAccount7623 += intInput; //
                			} else {
                				savingsAccount7623 += intInput;
                			}
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
		static void withdrawIt (String userInput, Boolean answer, Integer accountNumber) {
			try {
            	if (checkValue(userInput)) {
            		int intInput = Integer.parseInt(userInput);
            		if (accountNumber == 9812) {
        				insufficientFunds.fundStatus(intInput, answer, accountNumber);
					} else if (accountNumber == 7622) {
							insufficientFunds.fundStatus(intInput, answer, accountNumber);
					} else if (accountNumber == 7623) {
							insufficientFunds.fundStatus(intInput, answer, accountNumber);
					} else {
						  JOptionPane.showMessageDialog(null, "Invalid input, please enter correct input data", "Error", JOptionPane.ERROR_MESSAGE);	
					}
            	}
			} catch (Exception ex) { //If the user types in something that is not valid then do the following
                JOptionPane.showMessageDialog(null, "Invalid input, please enter correct input data", "Error", JOptionPane.ERROR_MESSAGE);
            }
		}// End withdrawIt
		
		//Function: Function to transfer money from one account to another
	    //Input: UserInput which will be parsed, and whether or not it is going into checking or savings
	    //Output: Transfers an amount to a particular account 
		static void transferTo (String userInput, Boolean answer, Integer accountNumber) {
			try {
            	if (checkValue(userInput)) {
            		int intInput = Integer.parseInt(userInput);
            		if (accountNumber==9812) {
            			if (answer){ //		
//                		
                			insufficientFunds.fundStatus(intInput, answer, 9812);
    						savingsAccount9812 += intInput;
    					} else { //
//    					
    						insufficientFunds.fundStatus(intInput, answer, 9812);
    						checkingAccount9812 += intInput;
    						}
            		} else if (accountNumber==7622) {
            			if (answer){ //		
//                			
                			insufficientFunds.fundStatus(intInput, answer, 9812);
    						savingsAccount7622 += intInput;
    					} else { //
//    						
    						insufficientFunds.fundStatus(intInput, answer, 9812);
    						checkingAccount7622 += intInput;
    					}
            		} else if (accountNumber == 7623) {
            			if (answer){ //		
//                		
                			insufficientFunds.fundStatus(intInput, answer, 9812);
    						savingsAccount7623 += intInput;
    					} else { //
//    						
    						insufficientFunds.fundStatus(intInput, answer, 9812);
    						checkingAccount7623 += intInput;
    					}
					} else {
						JOptionPane.showMessageDialog(null, "Invalid input, please enter correct input data", "Error", JOptionPane.ERROR_MESSAGE);
					}
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
		static void fundStatus (Integer intInput, Boolean answer, Integer accountNumber) {
			if (accountNumber==9812) {
				if (answer) {
					int newBalance = checkingAccount9812 -intInput; // Checks to see if checking account balance will be negative 
					if (newBalance < 0){ //if it is negative then alert the user there are insufficient funds
						JOptionPane.showMessageDialog(frame, "Insufficient Funds to withdraw from checking account!");
					} else { //Otherwise deduct the amount
						checkingAccount9812 -= intInput;
					}
				} else {
					int newBalance = savingsAccount9812 -intInput; //Checks to see if savings account balance will be negative
					if (newBalance < 0){ //if it is negative then alert the user there insufficient funds
						JOptionPane.showMessageDialog(frame, "Insufficient Funds to withdraw from savings account!");
					} else { //Otherwise deduct the amount 
						savingsAccount9812 -=  intInput;
						}
				}
			} else if (accountNumber == 7622) {
				if (answer) {
					int newBalance = checkingAccount7622 -intInput; // Checks to see if checking account balance will be negative 
					if (newBalance < 0){ //if it is negative then alert the user there are insufficient funds
						JOptionPane.showMessageDialog(frame, "Insufficient Funds to withdraw from checking account!");
					} else { //Otherwise deduct the amount
						checkingAccount7622 -= intInput;
					}
				} else {
					int newBalance = savingsAccount7622 -intInput; //Checks to see if savings account balance will be negative
					if (newBalance < 0){ //if it is negative then alert the user there insufficient funds
						JOptionPane.showMessageDialog(frame, "Insufficient Funds to withdraw from savings account!");
					} else { //Otherwise deduct the amount 
						savingsAccount7622 -=  intInput;
						}
				}
			} else if (accountNumber == 7623) {
				if (answer) {
					int newBalance = checkingAccount7623 -intInput; // Checks to see if checking account balance will be negative 
					if (newBalance < 0){ //if it is negative then alert the user there are insufficient funds
						JOptionPane.showMessageDialog(frame, "Insufficient Funds to withdraw from checking account!");
					} else { //Otherwise deduct the amount
						checkingAccount7623 -= intInput;
					}
				} else {
					int newBalance = savingsAccount7623 -intInput; //Checks to see if savings account balance will be negative
					if (newBalance < 0){ //if it is negative then alert the user there insufficient funds
						JOptionPane.showMessageDialog(frame, "Insufficient Funds to withdraw from savings account!");
					} else { //Otherwise deduct the amount 
						savingsAccount7623 -=  intInput;
					}
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
    
}//End CMISPRJ2RenickC
