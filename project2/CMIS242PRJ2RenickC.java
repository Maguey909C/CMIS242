/*
 * File: CMIS242PRJ3RenickC.java
 * Author: Chase Renick
 * Date: July 10, 2019
 * Purpose: This program is designed to do a calculation recursively and iteratively
 * based on what a user types into the program.
 */

//Installing Necessary Packages
import java.awt.EventQueue;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class CMIS242PRJ2RenickC {
	
	private static JFrame frame;
	private static JTextField textField;
	
	public static Integer checkingAccount = 0;
	public static Integer savingsAccount = 0;
	
	public static void confirmAndExit() {
		if (JOptionPane.showConfirmDialog(frame,  "You want to close the program?","Please confirm", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	
	public static void showBalance(String accountName, Integer balance) {
		JOptionPane.showMessageDialog(frame, accountName+" Balance: " + balance.toString());
	}
	
	public static class GUI {
		
	}
	
	public static class Account {
		
	}
	
	public static class InsufficientFunds {
		
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					//Build Basic JFrame
					frame = new JFrame("ATM Machine");
					frame.setBounds(100, 100, 450, 300);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.getContentPane().setLayout(null);
					frame.setVisible(true);
					frame.addWindowListener(new WindowAdapter(){
						@Override
						public void windowClosing(WindowEvent event) {
							confirmAndExit();
						}
					});
					
					//Incorporate the Radio Buttons and bounds
					final JRadioButton checking = new JRadioButton("Checking");  //Setting the Jradio button iterative as final
					checking.setSelected(true); //Having the JRadio button of "iterative" selected upon opening the application
					checking.setBounds(235, 119, 100, 29);
					frame.getContentPane().add(checking);
					
					JRadioButton savings = new JRadioButton("Savings"); //Setting the Jradio button recursive
					savings.setBounds(100, 119, 100, 29);
					frame.getContentPane().add(savings);
					
					//Group the radio buttons to toggle between two groups
					ButtonGroup group = new ButtonGroup();
					group.add(checking);
					group.add(savings);
					
					//Create the Text Fields within the content pane
					textField = new JTextField();  //Creating the first user input text field
					textField.setBounds(90, 157, 250, 26);
					frame.getContentPane().add(textField);
					textField.setColumns(10);
					
					//Deposit Button
					JButton btnCompute = new JButton("Deposit");
					btnCompute.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								String userInput = textField.getText();
								int intInput = Integer.parseInt(userInput);

								if (checking.isSelected()){ //if the iterative button is selected, do the following
									
									checkingAccount += (intInput);
									
								} else { //if the recursive button is selected do the following
									
									savingsAccount += (intInput);
								}

							} catch (Exception ex) { //If the user types in something that is not valid then do the following
			                    JOptionPane.showMessageDialog(null, "Invalid input, please enter correct input data", "Error", JOptionPane.ERROR_MESSAGE);
			                }

						}
					});
					btnCompute.setBackground(SystemColor.textHighlight);
					btnCompute.setBounds(238, 30, 100, 30);
					frame.getContentPane().add(btnCompute);
					
					//Balance Button
					JButton btnCompute2 = new JButton("Balance");
					btnCompute2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								if (checking.isSelected()){ //if the iterative button is selected, do the following
									
									showBalance("Checking", checkingAccount);
//									textField.setText(checkingAccount.toString());
									System.out.println(checkingAccount);
						
								} else { //if the recursive button is selected do the following
									
									showBalance("Savings", savingsAccount);
//									textField.setText(savingsAccount.toString());
									System.out.println(savingsAccount);
								}

							} catch (Exception ex) { //If the user types in something that is not valid then do the following
			                    JOptionPane.showMessageDialog(null, "Invalid input, please enter correct input data", "Error", JOptionPane.ERROR_MESSAGE);
			                }

						}
					});
					btnCompute2.setBackground(SystemColor.textHighlight);
					btnCompute2.setBounds(238, 73, 100, 30);
					frame.getContentPane().add(btnCompute2);
					
					//Withdraw Button
					JButton btnCompute3 = new JButton("Withdraw");
					btnCompute3.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								String userInput = textField.getText();
								int intInput = Integer.parseInt(userInput);

								if (checking.isSelected()){ //if the checking button is selected, do the following
									
									checkingAccount = checkingAccount - intInput;
									
								} else { //
									
									savingsAccount = savingsAccount - intInput;
								}

							} catch (Exception ex) { //If the user types in something that is not valid then do the following
			                    JOptionPane.showMessageDialog(null, "Invalid input, please enter correct input data", "Error", JOptionPane.ERROR_MESSAGE);
			                }

						}
					});
					btnCompute3.setBackground(SystemColor.textHighlight);
					btnCompute3.setBounds(90, 30, 100, 30);
					frame.getContentPane().add(btnCompute3);
					
					
					//Withdraw Button
					JButton btnCompute4 = new JButton("Transfer to");
					btnCompute4.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								String userInput = textField.getText();
								int intInput = Integer.parseInt(userInput);

								if (checking.isSelected()){ //if the iterative button is selected, do the following
									
									checkingAccount = checkingAccount - intInput;
									savingsAccount += intInput;
									
								} else { //if the recursive button is selected do the following
									
									savingsAccount = savingsAccount - intInput;
									savingsAccount = 0;
								}

							} catch (Exception ex) { //If the user types in something that is not valid then do the following
			                    JOptionPane.showMessageDialog(null, "Invalid input, please enter correct input data", "Error", JOptionPane.ERROR_MESSAGE);
			                }

						}
					});
					btnCompute4.setBackground(SystemColor.textHighlight);
					btnCompute4.setBounds(90, 73, 100, 30);
					frame.getContentPane().add(btnCompute4);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
