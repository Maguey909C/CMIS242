
import java.awt.*;
import java.awt.event.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.*;
import java.lang.Math;

public class InvestmentGUI {  
    
    public static class Calculator extends JFrame {
        
        JButton calculate_button; 
        JTextField investment_amount, years, user_rate, roe; //The values that are used to manipulate the data of the array
        JLabel entry1, entry2, entry3; //References to aid the user in the program
        
        private JFrame frame;//Creates the Frame for display messages
   
        public Calculator() {
            
            // Making layout GridBagLayout ()     
            setLayout(new GridBagLayout());
            frame = new JFrame();
            
            //Instance of the GUI
            GridBagConstraints window = new GridBagConstraints();
            
            window.insets = new Insets (5, 5, 5, 5);
            
            entry1 = new JLabel ("Investment $: ");
            entry1.setHorizontalAlignment(JLabel.LEFT);
            window.fill = GridBagConstraints.HORIZONTAL;
            window.gridx = 0;
            window.gridy = 0;
            window.gridwidth = 1;
            add (entry1, window);
            
            entry2 = new JLabel ("Period Yr: ");
            entry2.setHorizontalAlignment(JLabel.LEFT);
            window.fill = GridBagConstraints.HORIZONTAL;
            window.gridx = 0;
            window.gridy = 1;
            window.gridwidth = 1;
            add (entry2, window);
            
            entry3 = new JLabel ("Rate %: ");
            entry3.setHorizontalAlignment(JLabel.LEFT);
            window.fill = GridBagConstraints.HORIZONTAL;
            window.gridx = 0;
            window.gridy = 2;
            window.gridwidth = 1;
            add (entry3, window);
            
            investment_amount = new JTextField(8);
            window.fill = GridBagConstraints.HORIZONTAL;
            window.gridx = 1;
            window.gridy = 0;
            window.gridwidth = 1;
            add (investment_amount, window);
            
            years = new JTextField(4);
            window.fill = GridBagConstraints.HORIZONTAL;
            window.gridx = 1;
            window.gridy = 1;
            window.gridwidth = 1;
            add (years, window);
            
            user_rate = new JTextField(4);
            window.fill = GridBagConstraints.HORIZONTAL;
            window.gridx = 1;
            window.gridy = 2;
            window.gridwidth = 1;
            add (user_rate, window);
            
            roe = new JTextField(10);
            window.fill = GridBagConstraints.HORIZONTAL;
            window.gridx = 2;
            window.gridy = 0;
            window.gridwidth = 1;
            add (roe, window);
            
            
            calculate_button = new JButton("Calculate ROE");
            window.fill = GridBagConstraints.HORIZONTAL;
            window.gridx = 3;
            window.gridy = 0;
            window.gridwidth = 0;
            add (calculate_button, window);
   
            //Make instances of events in the program
            Converter_Listener converter_listener = new Converter_Listener();
            //Each event attached to listener.
            calculate_button.addActionListener(converter_listener);
            roe.setEditable(false);
            } // end  Calculator()    

      /**
       *This class handles all the action listeners. Logic behind the Balance, 
       * Deposit, Transfer to, Exit, and withdraw buttons
       */  
       public class Converter_Listener implements ActionListener  {

        @Override
        public void actionPerformed(ActionEvent investment_listener){
            
            double i_amount = Double.parseDouble(investment_amount.getText());
            int yr = Integer.parseInt(years.getText());
            double rate = Double.parseDouble(user_rate.getText());
            String operators = investment_listener.getActionCommand();
            
            if(operators.equals("Calculate ROE")){
            	
            	for (int i=1; i <= yr; i++) {
            		System.out.println("Investment Amount Beginning of Year: " + i +" $"+ i_amount);
            		i_amount += (i_amount * rate);
            		System.out.println("Investment Amount End of Year: " + i +" $"+ i_amount);
            		
            	}
            	i_amount = Math.round(i_amount);
            	System.out.println(i_amount);
                roe.setText("$"+Double.toString(i_amount) );
            }

        }//End of public void actionPerformed

        }//End of public class Converter_Listener
       
    }//End of Calculator Class
    public static void main(String[] args) {
    	Calculator converter = new Calculator(); //Create instance of converter 

         // Get basic parameters of a GUI 
         converter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         converter.pack();
         converter.setTitle("Investment Calculator");
         converter.setLocationRelativeTo(null);
         converter.setVisible(true);
    }//End main()
    
}//End InvestmentGUI
