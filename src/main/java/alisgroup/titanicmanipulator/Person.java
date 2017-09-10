//*************************************************//
//          INTHER LOGISTICS ENGINEERING           //
//*************************************************//
package alisgroup.titanicmanipulator;

/**
 *
 * @author agore
 */
public class Person {

  int id;
  int survived; //1 - survived, 0 - died
  int pClass;
  String Name;
  String title;
  String ticket;
  int sex; //female = 1, male = 0
  String age;
  int sibSp;
  int parCh;
  long fare;
  int embarked; // C = 1 Q = 2 S = 3

  public Person() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getSurvived() {
    return survived;
  }

  public void setSurvived(int survived) {
    this.survived = survived;
  }

  public int getpClass() {
    return pClass;
  }

  public void setpClass(int pClass) {
    this.pClass = pClass;
  }

  public String getName() {
    return Name;
  }

  public void setName(String Name) {
    this.Name = Name;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTicket() {
    return ticket;
  }

  public void setTicket(String ticket) {
    this.ticket = ticket;
  }

  public int getSex() {
    return sex;
  }

  public void setSex(int sex) {
    this.sex = sex;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public int getSibSp() {
    return sibSp;
  }

  public void setSibSp(int sibSp) {
    this.sibSp = sibSp;
  }

  public int getParCh() {
    return parCh;
  }

  public void setParCh(int parCh) {
    this.parCh = parCh;
  }

  public int getParChSibSp() {
    return parCh + sibSp;
  }
  public long getFare() {
    return fare;
  }

  public void setFare(long fare) {
    this.fare = fare;
  }

  public int getEmbarked() {
    return embarked;
  }

  public void setEmbarked(int embarked) {
    this.embarked = embarked;
  }

}
