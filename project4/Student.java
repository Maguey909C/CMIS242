/*
 * File: CMIS242PRJ4RenickC.java
 * Author: Chase Renick
 * Date: July 14, 2019
 * Purpose: This program is used to build out the type of student.
 */

import java.util.List;
import java.util.ArrayList;

//Class Student
public class Student {
	
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
