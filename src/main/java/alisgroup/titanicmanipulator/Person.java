//*************************************************//
//          INTHER LOGISTICS ENGINEERING           //
//*************************************************//
package alisgroup.titanicmanipulator;

/**
 * @author agore
 */
public class Person {

    int id;
    double survived; //1 - survived, 0 - died
    double pClass;
    String Name;
    String title;
    String ticket;
    double sex; //female = 1, male = 0
    double age;
    double sibSp;
    double parCh;
    double fare;
    double embarked; // C = 1 Q = 2 S = 3

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public double getParChSibSp() {
        return parCh + sibSp;
    }

    public double getSurvived() {
        return survived;
    }

    public void setSurvived(double survived) {
        this.survived = survived;
    }

    public double getpClass() {
        return pClass;
    }

    public void setpClass(double pClass) {
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

    public double getSex() {
        return sex;
    }

    public void setSex(double sex) {
        this.sex = sex;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public double getSibSp() {
        return sibSp;
    }

    public void setSibSp(double sibSp) {
        this.sibSp = sibSp;
    }

    public double getParCh() {
        return parCh;
    }

    public void setParCh(double parCh) {
        this.parCh = parCh;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public double getEmbarked() {
        return embarked;
    }

    public void setEmbarked(double embarked) {
        this.embarked = embarked;
    }

    public double getFairPClass() {
        return (1.0 / pClass) * fare;
    }

    public double getParChSibSpProduce() {
        return (parCh + 1) * (sibSp + 1);
    }

}
