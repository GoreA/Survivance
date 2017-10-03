//*************************************************//
//          INTHER LOGISTICS ENGINEERING           //
//*************************************************//
package alisgroup.titanicmanipulator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author agore
 */
public class Csv {

    public static List<Person> readPersons(String path) throws IOException {
        List<Person> persons = new ArrayList<>();
        CSVParser parser = new CSVParser(new FileReader(path), CSVFormat.DEFAULT.withHeader());
        for (CSVRecord record : parser) {
            Person person = new Person();
            person.setId(Integer.parseInt(record.get("PassengerId")));
            person.setSurvived(Double.parseDouble(record.get("Survived")));
            person.setpClass(Double.parseDouble(record.get("Pclass")));
            person.setName(record.get("Name"));
            person.setSex(record.get("Sex").equals("male") ? 0 : 1);
            person.setAge(record.get("Age").equals("") ? 1000 : Double.parseDouble(record.get("Age")));
            person.setSibSp(Double.parseDouble(record.get("SibSp")));
            person.setParCh(Double.parseDouble(record.get("Parch")));
            person.setTicket(record.get("Ticket"));
            person.setFare(record.get("Fare").equals("") ? 1000 : Double.parseDouble(record.get("Fare")));
            switch (record.get("Embarked")) {
                case "C":
                    person.setEmbarked(1);
                    break;
                case "Q":
                    person.setEmbarked(2);
                    break;
                case "S":
                    person.setEmbarked(3);
                    break;
                default:
                    person.setEmbarked(0);
            }
            persons.add(person);
        }
        return persons;
    }

    public static void exportCSV(List<Person> persons, String path) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));
            for (Person person : persons) {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(person.getId());
                oneLine.append(",");
                oneLine.append(person.getSurvived());
                oneLine.append(",");
                oneLine.append(person.getpClass());
                oneLine.append(",");
                oneLine.append(person.getTitle());
                oneLine.append(",");
                oneLine.append(person.getName());
                oneLine.append(",");
                oneLine.append(person.getParCh());
                oneLine.append(",");
                oneLine.append(person.getSibSp());
                oneLine.append(",");
                oneLine.append(person.getEmbarked());
                oneLine.append(",");
                oneLine.append(person.getSex());
                oneLine.append(",");
                oneLine.append(person.getFare());
                oneLine.append(",");
                oneLine.append(person.getTicket());
                oneLine.append(",");
                oneLine.append(person.getAge());
                bw.write(oneLine.toString());
                bw.newLine();
                bw.flush();
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
