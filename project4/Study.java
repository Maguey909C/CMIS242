public class Study {
		
		private double grade;
		private int credit;
		
		public Study(double grade, int credit) {
			setGrade(grade);
			setCredit(credit);
		}
		
		public double getGrade() {
			return grade;
		}
		
		public void setGrade(double grade) {
			this.grade = grade;
		}
		
		public int getCredit() {
			return credit;
		}
		
		public void setCredit(int credit) {
			this.credit = credit;
		}
	}
