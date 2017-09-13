//*************************************************//
//          INTHER LOGISTICS ENGINEERING           //
//*************************************************//
package alisgroup.titanicmanipulator;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
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
              if(mTitle.find()){
                p.setTitle(mTitle.group());
              }
              Matcher mName = paternName.matcher(name);
              if(mName.find()){
                p.setName(mName.group(0));
              }
              if(p.getFare() != 1000){
                  p.setFare(p.getFare()/(p.getSibSp() + p.getParCh() + 1));
                }
            });
    
    populateFare(persons);
    
//    populateAge(persons);


    csv.exportCSV(persons, "src\\main\\resources\\results.csv");
  }

  private static void populateFare(List<Person> persons) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

      List<Person> personsWithFair = new ArrayList<>();
      List<Person> personsWithoutFair = new ArrayList<>();
      persons.stream().forEach(p -> {
          if(p.getFare() == 1000){
              personsWithoutFair.add(p);
          }
          else{personsWithFair.add(p);}
      });
      Matrix m = new Matrix();
      m = m.createMatrix(personsWithFair, Person.class.getMethod("getpClass"),
              Person.class.getMethod("getParChSibSp"), Person.class.getMethod("getEmbarked")
              , Person.class.getMethod("getFare"));

  }

  private static void populateAge(List<Person> persons) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
}
