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
  float survived; //1 - survived, 0 - died
  float pClass;
  String Name;
  String title;
  String ticket;
  float sex; //female = 1, male = 0
  float age;
  float sibSp;
  float parCh;
  float fare;
  float embarked; // C = 1 Q = 2 S = 3

  public Person() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public float getSurvived() {
    return survived;
  }

  public void setSurvived(float survived) {
    this.survived = survived;
  }

  public float getpClass() {
    return pClass;
  }

  public void setpClass(float pClass) {
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

  public float getSex() {
    return sex;
  }

  public void setSex(float sex) {
    this.sex = sex;
  }

  public float getAge() {
    return age;
  }

  public void setAge(float age) {
    this.age = age;
  }

  public float getSibSp() {
    return sibSp;
  }

  public void setSibSp(float sibSp) {
    this.sibSp = sibSp;
  }

  public float getParCh() {
    return parCh;
  }

  public void setParCh(float parCh) {
    this.parCh = parCh;
  }

  public float getParChSibSp() {
    return parCh + sibSp;
  }
  public float getFare() {
    return fare;
  }

  public void setFare(float fare) {
    this.fare = fare;
  }

  public float getEmbarked() {
    return embarked;
  }

  public void setEmbarked(float embarked) {
    this.embarked = embarked;
  }

}
