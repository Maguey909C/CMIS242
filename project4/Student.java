import java.util.List;

public class Student {
	
	private String name;
	private String major;
	private List<Study> list;
	
	public Student (String name, String major) {
		this.name = name;
		this.major = major;	
	}
	
	public String getName() {
		return name;
	} //end getName
	
	public void setName() {
		this.name = name;
	} //end getMajor
	
	public String getMajor() {
		return major;
	}// end getMajor
	
	public void setMajor(String major) {
		this.major = major;
	} // end getMajor
	
	public Double calculateGPA(Double courseGrade, Double creditHours) {
		int totalCredits =0;
		double sum = 0;
		for (Study course: list) {
			totalCredits += course.getCredit();
			sum += course.getGrade() * course.getCredit();
		}
		if (totalCredits == 0) {
			return 0.0;
		} else {
			return (sum / totalCredits);
		}
	} // end calculateGPA
	
	public void increaseStudy(Double courseGrade, int creditHours) {
		list.add(new Study(courseGrade, creditHours));
	}
	
}// End Student Class
