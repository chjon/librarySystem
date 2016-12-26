class Teacher extends Customer{
  String subjectTeaching;
  String school;
  
  public Teacher(String name, int customerNum, int curItems, int late, String subject, String school){
    super (name, customerNum, curItems, late);
    subjectTeaching = subject;
    this.school = school;
   
  }
  public int compareToTeacher (Teacher other){
    if (this.id > other.id){
      return 1;
    }
    else if (other.id > this.id){
      return -1;
    }
    return 0;
  }
  
}
