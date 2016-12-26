class Teacher extends Customer{
  String subjectTeaching;
  String school;
  double spendingAllowance;
  
  public Teacher(String name, int customerNum, int curItems, int late, String subject, String school,
                double spendingAllowed){
    super (name, customerNum, curItems, late);
    subjectTeaching = subject;
    this.school = school;
    spendingAllowance = spendingAllowed;
   
  }
  public int compareToTeacherSpending (Teacher other){
    if (this.spendingAllowance > other.spendingAllowance){
      return 1;
    }
    else if (other.spendingAllowance > this.spendingAllowance){
      return -1;
    }
    return 0;
  }
  
  public String toString(){
    String s = "Subject: " + subject + "School: " + school + "Spending allowance: " + spendingAllowance;
    return s;
  }
  
}
