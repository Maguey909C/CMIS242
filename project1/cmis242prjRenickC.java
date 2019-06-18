/*
 * File: cmis242prj1RenickC.java
 * Author: Chase Renick
 * Date: June 1, 2019
 * Purpose: This program is designed to read in a file
 * place data into array, perform operations, display basic analysis to the console.
 */

//Necessary Packages
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Arrays;

public class Cmis242Prj1RenickC {

  public static class Employee {
    private String firstName, lastName, monthlySalary;
  // [Constructor (), annual salary, and toString]

    public Employee() {
      firstName= "";
      lastName= "";
      monthlySalary="";
    }//end default Employee constructor

    public Employee(String firstName, String lastName, String monthlySalary){
      this.firstName = firstName;
      this.lastName = lastName;
      this.monthlySalary = monthlySalary;
    }// end Employee constructor

    //Function: Calculates the annual salary for an employee
    //Input: none
    //Output: Calculates the annual salary based on 12 months
    public String getAnnualSalary(){
      int annualSalary = Integer.parseInt(monthlySalary)*12;
      return Integer.toString(annualSalary).trim();
    }//end getAnnualSalary method

    //Function: Prints a row with Employee Features based on name, job title, monthly pay, and annual salary
    //Input: none
    //Output: Calculates the annual salary based on 12 months
    public String toString() {
      return firstName +" "+lastName+equiSpaces(firstName,lastName)+"Employee"+"         "+monthlySalary+"                                                           "+getAnnualSalary();
    }// end public class to String

    //Function: Helps line up output to console
    //Input: none
    //Output: returns how many spaces need to be between first, last name and type
    public String equiSpaces(String fn, String ln) {
      int n = 22 - (fn+ln).length();
      char [] spaces = new char[n];
      Arrays.fill(spaces, ' ');
      String filler = new String(spaces);
      return filler;
    } // end equispaces
  } // end public static class Employee


  public static class Salesman extends Employee {
    private String annualSales;

    public Salesman(String firstName, String lastName, String monthlySalary, String annualSales){
      super(firstName, lastName, monthlySalary);
      this.annualSales = annualSales;
    }//end Salesman constructor with superclass

    //Function: Calculates the commission of a Salesman
    //Input: String of Annual Sales from text file
    //Output: An integer of the commission a particular salesman earned
    public int commission(String annualSales) {
      double a = Integer.parseInt(annualSales) * .02;
      int sales = (int) a;
      if (sales > 20000){
        return 20000;
      } else {
        return sales;
      }
    } // end commission

    //Function: Calculates the annual salary for a Salesman
    //Input: None
    //Output: A String denoting the value of a specific Salesman's annual salary
    public String getAnnualSalary(){
      int b = commission(annualSales);
      int sAnnualSalary = (Integer.parseInt(super.monthlySalary)*12)+b;
      return Integer.toString(sAnnualSalary);
    } // end getAnnualSalary method which overrides one from Employee class

    //Function: Displays string of Salesman features from name, type, monthly salary, sales, bonus, and annual pay
    //Input: None
    //Output: A string of relevant Salesman Features
    public String toString(){
      return super.firstName +" "+ super.lastName+super.equiSpaces(super.firstName, super.lastName)+"Salesman"+"         "+super.monthlySalary+"             "+annualSales+"                         "+Integer.toString(commission(annualSales))+"           "+getAnnualSalary();
    }// end toString method which overrides one from Employee class
  } // end public static class Salesman extends Employee


  public static class Executive extends Employee {
    private String annualStock;

    public Executive(String firstName, String lastName, String monthlySalary, String annualStock){
      super(firstName,lastName,monthlySalary);
      this.annualStock = annualStock;
    }

    //Function: Calculates the bonus of an executive if stock is greater than 50, then 30k otherwise 0
    //Input: Annual stock for executive
    //Output: The bonus amount
    public int bonus(String annualStock) {
      int stock = Integer.parseInt(annualStock);
      if (stock > 50){
        return 30000;
      } else {
        return 0;
      }
    }

    //Function: Calculates annual salary for Executive based on bonus amount and monthly salary
    //Input: None
    //Output: String value for Executive's annual salary
    public String getAnnualSalary(){
      int adjustedSalary = (Integer.parseInt(super.monthlySalary)*12)+bonus(annualStock);
      return Integer.toString(adjustedSalary);
    }

    //Function: Displays string of Executive features from name, type, monthly salary, stock, bonus, and annual pay
    //Input: None
    //Output: A string of relevant Executive features
    public String toString(){
      return super.firstName +" "+super.lastName+super.equiSpaces(super.firstName,super.lastName)+"Executive"+"        "+super.monthlySalary+"                             "+annualStock+"             "+Integer.toString(bonus(annualStock))+"          "+getAnnualSalary();
    }
  } // end public static class Executive extends Employee

  //Function: Organizes output to the console as well as calculates the average salaries per year based on the array list
  //Input: An array list based on employee, salesman, or executives in a given year, a string for the year
  //Output: A summary of each object's toString method based on a given year, as well as the average salary for that year among all objects
  public static String summarizeYear(ArrayList<Employee> yearArray, String theYear){
    List<Integer> yearList = new ArrayList<Integer>();

    System.out.println("Employee Name" + "          "+"Type"+"          "+"Monthly Pay"+"          "+"Sales"+"          "+"Stock"+"          "+"Bonus"+"          "+"Annual Pay");
    System.out.println("-----------------------------------------------------------------------------------------------------------------\n");

    yearArray.forEach(obj -> {
      System.out.println(obj.toString());
      String final_number = obj.getAnnualSalary().trim();
      Integer c = Integer.parseInt(final_number);
      yearList.add(c);
    });

    int yearTotal= yearList.stream().mapToInt(Integer::intValue).sum();
    double avg = yearTotal / (yearList.size());
    String avgSalary = Double.toString(avg);

    System.out.println("");
    System.out.println("Report for " +theYear+ " Employee: Average Salary is " + avgSalary);
    System.out.print("\n");
    return avgSalary;
  }// end summarizeYear

  //Function: Calculates the Salary difference between two years
  //Input: Avg Salry for Year 1, Avg Salary of Year 2
  //Output: Absolute value of difference between two yearly salaries
  public static void showDiff(String y1, String y2){
    double yr1 = Double.parseDouble(y1);
    double yr2 = Double.parseDouble(y2);
    double diff = (yr2-yr1);
    System.out.println("Average Salary differences between 2015 and 2014: " + Double.toString(diff)+"\n");
  }

  /////////////////////////////////////////// THE MAIN BLOCK//////////////////////////////////////////////
  public static void main (String args[]) {
    BufferedReader inputStream = null;
    int rows = 17;
    int columns = 6;

    String [ ][ ] aryNewFile  = new String [rows][columns];
    int fieldTotal = 0;
    int lineCount = 0;
    int fieldCount = 0;
    String fileLine;

    ArrayList<Employee> year2014 = new ArrayList<Employee>();
    ArrayList<Employee> year2015 = new ArrayList<Employee>();

    try {
        // inputStream = new BufferedReader(new FileReader("Employees.txt"));
        inputStream = new BufferedReader(new FileReader(args[0]));

        System.out.println("Employee Summary Data:");
        // Read one Line using BufferedReader
        while ((fileLine = inputStream.readLine()) != null) {
            String[] values = fileLine.split(",");
            for (String str : values) {
                          aryNewFile [lineCount][fieldCount] = str.trim();
                          fieldCount++;
                          fieldTotal++;
                      } // end for (String str : values)
                      lineCount++;
                      fieldCount=0;
        }

        System.out.println("\nFile record lines: "+ lineCount);
        System.out.println("\nTotal fields read : "+ fieldTotal +"\n");
        int fieldNumber = fieldTotal / lineCount;

        for (int i=0; i < lineCount; i++) {
          int yearNum = Integer.parseInt(aryNewFile[i][0]);
          String profession = aryNewFile[i][1].trim().toLowerCase();

          if (yearNum == 2014){  //Using year to determine what array list the following objects should be passed to
            if (profession.equals("employee")){  //Examining each row to determine what class to invoke
              Employee emp_2014 = new Employee(aryNewFile[i][3], aryNewFile[i][2], aryNewFile[i][4]);
              year2014.add(emp_2014);
            } else if (profession.equals("salesman")) {
              Salesman s_2014 = new Salesman(aryNewFile[i][3], aryNewFile[i][2], aryNewFile[i][4], aryNewFile[i][5]);
              year2014.add(s_2014);
            } else {
              Executive exe_2014 = new Executive(aryNewFile[i][3], aryNewFile[i][2], aryNewFile[i][4],aryNewFile[i][5]);
              year2014.add(exe_2014);
            }
          } else { //This is for the 2015 block
            if (profession.equals("employee")){  //if the row has employee as the deliniator
              Employee emp_2015 = new Employee(aryNewFile[i][3], aryNewFile[i][2], aryNewFile[i][4]);
              year2015.add(emp_2015);
            } else if (profession.equals("salesman")) {
              Salesman s_2015 = new Salesman(aryNewFile[i][3], aryNewFile[i][2], aryNewFile[i][4], aryNewFile[i][5]);
              year2015.add(s_2015);
            } else {
              Executive exe_2015 = new Executive(aryNewFile[i][3], aryNewFile[i][2], aryNewFile[i][4],aryNewFile[i][5]);
              year2015.add(exe_2015);
            }
          }//End of else for 2015
        }// End of for loop creating respecitive objects based on the year and type of employee

        //Calls Summary Report
        String y1 = summarizeYear(year2014, "2014");
        String y2 = summarizeYear(year2015, "2015");

        //Prints the difference in salaries between two year
        showDiff(y1,y2);

    } catch (IOException io) {
        System.out.println("File IO exception" + io.getMessage());
    }finally {
        // Need another catch for closing the streams
        try {
            if (inputStream != null) {
            inputStream.close();
        }
        } catch (IOException io) {
            System.out.println("Issue closing the Files" + io.getMessage());
        }
      }
    }// end public static void main (String args []) {

} // end public class cmis242prj1RenickC
