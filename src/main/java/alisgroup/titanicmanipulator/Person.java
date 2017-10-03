//*************************************************//
//          INTHER LOGISTICS ENGINEERING           //
//*************************************************//
package alisgroup.titanicmanipulator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author agore
 */
public class Person {

    private static final Pattern PATTERN_TITLE = Pattern.compile("\\b[A-Za-z]*\\.");
    private static final Pattern PATTERN_NAME = Pattern.compile("^[\\p{L} '-]+");

    private int id;
    private double survived; //1 - survived, 0 - died
    private double pClass;
    private String name;
    private String title;
    private String ticket;
    private double sex; //female = 1, male = 0
    private double age;
    private double sibSp;
    private double parCh;
    private double fare;
    private double embarked; // C = 1 Q = 2 S = 3

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
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public boolean isMr() {
        return "Mr.".equals(title);
    }

    public boolean isMrs() {
        return "Mrs.".equals(title);
    }

    public boolean isMiss() {
        return "Miss.".equals(title);
    }

    public boolean isMaster() {
        return "Master.".equals(title);
    }

    public boolean isWithoutSurvivance() {
        return Math.round(survived) == 2;
    }

    public boolean isWithoutAge() {
        return Math.round(age) == 1000f;
    }

    public boolean isWithoutFare() {
        return Math.round(fare) == 1000d;
    }

    public void adjustTitle() {
        Matcher mTitle = PATTERN_TITLE.matcher(name);
        if (mTitle.find()) {
            title = mTitle.group();
        }

        Matcher mName = PATTERN_NAME.matcher(name);
        if (mName.find()) {
            name = mName.group(0);
        }
    }

    public void adjustFare() {
        if (fare != 1000) {
            fare = fare / (sibSp + parCh + 1);
        }
    }

    //Capt.         1
//Col.          4
//Countess.	1
//Don.          1
//Dona.         1
//Dr.           8
//Jonkheer.	1
//Lady.         1
//Major.	2
//Master.	61
//Miss.         260
//Mlle.         2
//Mme.          1
//Mr.           757
//Mrs.          197
//Ms.           2
//Rev.          8
//Sir.          1
// We have to reduce all these classes just in some general that really can help us in determining the age and maybe in future for survivance
// Finally we should have: Mr. Mrs. Miss. Master.
    public void reduceTitle() {
        switch (title) {
            case "Capt.":
            case "Col.":
            case "Don.":
            case "Jonkheer.":
            case "Major.":
            case "Rev.":
            case "Sir.":
                title = "Mr.";
                break;
            case "Countess.":
            case "Dona.":
            case "Lady.":
            case "Mme.":
                title = "Mrs.";
                break;
            case "Ms.":
            case "Mlle.":
                title = "Miss.";
                break;
            case "Dr.": {
                if (Math.round(sex) == 0f) {
                    title = "Mr.";
                } else {
                    title = "Mrs.";
                }
                break;
            }
            default:
                break;
        }
    }
}
