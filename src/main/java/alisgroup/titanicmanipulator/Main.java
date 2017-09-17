//*************************************************//
//          INTHER LOGISTICS ENGINEERING           //
//*************************************************//
package alisgroup.titanicmanipulator;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author agore
 */
public class Main {

  private static final String REGEX_TITLE = "\\b[A-Za-z]*\\.";
  private static final String REGEX_NAME = "^[\\p{L} '-]+";

  public static void main(String[] args) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    CSV csv = new CSV();
    List<Person> persons = csv.readPersons("src\\main\\resources\\total.csv");

    Pattern paternTitle = Pattern.compile(REGEX_TITLE);
    Pattern paternName = Pattern.compile(REGEX_NAME);
    persons.stream().forEach(
            p -> {
              String name = p.getName();
              Matcher mTitle = paternTitle.matcher(name);
              if (mTitle.find()) {
                p.setTitle(mTitle.group());
              }
              Matcher mName = paternName.matcher(name);
              if (mName.find()) {
                p.setName(mName.group(0));
              }
              if (p.getFare() != 1000) {
                p.setFare(p.getFare() / (p.getSibSp() + p.getParCh() + 1));
              }
            });

    populateFare(persons);
    reduceTitles(persons);
    List<Person> personsMr = new ArrayList<>();
    List<Person> personsMrs = new ArrayList<>();
    List<Person> personsMiss = new ArrayList<>();
    List<Person> personsMaster = new ArrayList<>();
    persons.stream().forEach(p -> {
      switch (p.getTitle()) {
        case "Mr.":
          personsMr.add(p);
          break;
        case "Mrs.":
          personsMrs.add(p);
          break;
        case "Miss.":
          personsMiss.add(p);
          break;
        case "Master.":
          personsMaster.add(p);
          break;
      }
    });
    // To be more precise, these titles should be treated each.
//    populateAge(personsMr);
//    populateAge(personsMrs);
//    populateAge(personsMiss);

    populateAge(personsMaster);
    
    csv.exportCSV(personsMaster, "src\\main\\resources\\results.csv");
  }

  private static void populateFare(List<Person> persons) throws NoSuchMethodException {

    List<Person> personsWithFair = new ArrayList<>();
    List<Person> personsWithoutFair = new ArrayList<>();
    persons.stream().forEach(p -> {
      if (Math.round(p.getFare()) == 1000f) {
        personsWithoutFair.add(p);
      } else {
        personsWithFair.add(p);
      }
    });
    Matrix m = new Matrix();
    try {
      m = m.createMatrix(personsWithFair, Person.class.getMethod("getpClass"),
              Person.class.getMethod("getParChSibSp"), Person.class.getMethod("getEmbarked"),
              Person.class.getMethod("getFare"));
    } catch (InvocationTargetException ex) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }
    float[] theta = Gradient.calculateTheta(m, 0.015f, 2000);

    personsWithoutFair.stream().forEach(p -> {
      float fare = 0;
      try {
        fare = Gradient.calculateCost(theta, p, Person.class.getMethod("getpClass"),
                Person.class.getMethod("getParChSibSp"), Person.class.getMethod("getEmbarked"));
      } catch (NoSuchMethodException ex) {
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
      } catch (SecurityException ex) {
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
      }
      p.setFare(fare);

    });
  }

  private static void populateAge(List<Person> persons) throws NoSuchMethodException {
    List<Person> personsWithAge = new ArrayList<>();
    List<Person> personsWithoutAge = new ArrayList<>();
    persons.stream().forEach(p -> {
      if (Math.round(p.getAge()) == 1000f) {
        personsWithoutAge.add(p);
      } else {
        personsWithAge.add(p);
      }
    });
 Matrix m = new Matrix();
    try {
      m = m.createMatrix(personsWithAge, Person.class.getMethod("getpClass"), Person.class.getMethod("getFare"),
              Person.class.getMethod("getParCh"), Person.class.getMethod("getSibSp"), Person.class.getMethod("getEmbarked"),
              Person.class.getMethod("getAge"));
    } catch (InvocationTargetException ex) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }
    float[] theta = Gradient.calculateTheta(m, 0.01f, 1000);

    personsWithoutAge.stream().forEach(p -> {
      float age = 0;
      try {
        age = Gradient.calculateCost(theta, p, Person.class.getMethod("getpClass"), Person.class.getMethod("getFare"),
                Person.class.getMethod("getParCh"), Person.class.getMethod("getSibSp"), Person.class.getMethod("getEmbarked"),
                Person.class.getMethod("getAge"));
      } catch (NoSuchMethodException ex) {
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
      } catch (SecurityException ex) {
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
      }
      p.setAge(age);

    });
  }

  private static void reduceTitles(List<Person> persons) {
    persons.stream().forEach(p -> {
      switch (p.getTitle()) {
        case "Capt.":
          p.setTitle("Mr.");
          break;
        case "Col.":
          p.setTitle("Mr.");
          break;
        case "Countess.":
          p.setTitle("Mrs.");
          break;
        case "Don.":
          p.setTitle("Mr.");
          break;
        case "Dona.":
          p.setTitle("Mrs.");
          break;
        case "Jonkheer.":
          p.setTitle("Mr.");
          break;
        case "Major.":
          p.setTitle("Mr.");
          break;
        case "Lady.":
          p.setTitle("Mrs.");
          break;
        case "Ms.":
          p.setTitle("Miss.");
          break;
        case "Mlle.":
          p.setTitle("Miss.");
          break;
        case "Mme.":
          p.setTitle("Mrs.");
          break;
        case "Rev.":
          p.setTitle("Mr.");
          break;
        case "Sir.":
          p.setTitle("Mr.");
          break;
        case "Dr.": {
          if (Math.round(p.getSex()) == 0f) {
            p.setTitle("Mr.");
            break;
          } else {
            p.setTitle("Mrs.");
          }
        }
        default:
          break;
      }
    });
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
  }
}
