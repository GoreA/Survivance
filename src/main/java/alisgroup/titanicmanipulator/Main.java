//*************************************************//
//          INTHER LOGISTICS ENGINEERING           //
//*************************************************//
package alisgroup.titanicmanipulator;

import java.io.IOException;
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

  public static void main(String[] args) throws IOException {
    CSV csv = new CSV();
    List<Person> persons = csv.readPersons("C:\\train.csv");

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
            });
    
    populateFare(persons);
    
    populateAge(persons);

    csv.exportCSV(persons, "C:\\results.csv");
  }

  private static void populateFare(List<Person> persons) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  private static void populateAge(List<Person> persons) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
}
