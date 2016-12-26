class Student extends Customer{
  int grade;
  double allowance;
  
  public Student(int stuGrade, double stuAllowance,
                 String name, int studentNum, int curItems, int lateItems){
    super (name, studentNum, curItems, lateItems);
    grade = stuGrade;
    allowance = stuAllowance;
    subjects = sujectsLearning;
  }
  public int compareToStudent(Student other){
    if (this.id > other.id)
      return 1;
    else if (other.id > this.id)
      return -1;
    else
      return 0;
  }
  public String toString(){
    String s = "Grade: " + grade + "\nAllowance: " + allowance ;
    return s;
  }
}
