//*************************************************//
//          INTHER LOGISTICS ENGINEERING           //
//*************************************************//
package alisgroup.titanicmanipulator;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author agore
 */
public class Main {

    public static void main(String[] args) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Person> persons = Csv.readPersons("src\\main\\resources\\total.csv");

        persons.forEach(Person::adjustFare);
        populateFare(persons, 0.015f, 1000);

        persons.forEach(Person::adjustTitle);
        persons.forEach(Person::reduceTitle);

        List<Person> personsMr = persons.stream().filter(Person::isMr).collect(Collectors.toList());
        List<Person> personsMrs = persons.stream().filter(Person::isMrs).collect(Collectors.toList());
        List<Person> personsMiss = persons.stream().filter(Person::isMiss).collect(Collectors.toList());
        List<Person> personsMaster = persons.stream().filter(Person::isMaster).collect(Collectors.toList());

//     To be more precise, these titles should be treated each.
        populateAge(personsMr, 0.0015f, 1500);
        populateAge(personsMrs, 0.001f, 1500);
        populateAge(personsMiss, 0.0004f, 1500);
        populateAge(personsMaster, 0.01f, 1500);

        populateSurvivance(personsMr, 0.0015f, 1500);
        populateSurvivance(personsMrs, 0.001f, 1500);
        populateSurvivance(personsMiss, 0.0004f, 500);
        populateSurvivance(personsMaster, 0.01f, 1500);

        Csv.exportCSV(persons, "src\\main\\resources\\results.csv");

//Just a test data
//    float x[][] = {{1, 3, 4}, {1, 5, 7}, {1, 7, 6}, {1, 4, 8}, {1, 5, 9}};
//    float y[] = {3, 4, 5, 3, 7};
//    Matrix m = new Matrix(x, y);
//    float theta[] = Gradient.calculateTheta(m, 0.01f, 50);
//    for(int i = 0; i < theta.length; i++){
//      System.out.println(theta[i]);
//    }
//Should obtain : 0.04705481; 0.3663616; 0.38562405
    }

    private static void populateFare(List<Person> persons, float alpha, int iterations) {
        List<Person> personsWithFare = persons.stream().filter(p -> !p.isWithoutFare()).collect(Collectors.toList());
        List<Person> personsWithoutFare = persons.stream().filter(Person::isWithoutFare).collect(Collectors.toList());

        Matrix m = Matrix.createMatrix(personsWithFare, "getpClass", "getParChSibSp", "getEmbarked", "getFare");
        double[] theta = Gradient.calculateTheta(m, alpha, iterations);

        personsWithoutFare.forEach(p -> {
            double fare = Gradient.calculateCost(theta, p, "getpClass", "getParChSibSp", "getEmbarked");
            p.setFare(fare);
        });
    }

    private static void populateAge(List<Person> persons, float alpha, int iterations) {
        List<Person> personsWithAge = persons.stream().filter(p -> !p.isWithoutAge()).collect(Collectors.toList());
        List<Person> personsWithoutAge = persons.stream().filter(Person::isWithoutAge).collect(Collectors.toList());

        Matrix m = Matrix.createMatrix(personsWithAge,
                "getpClass", "getFare", "getpClass",
                "getSibSp", "getParCh", "getSibSp", "getEmbarked", "getAge");
        double[] theta = Gradient.calculateTheta(m, alpha, iterations);

        personsWithoutAge.forEach(p -> {
            double age = Gradient.calculateCost(theta, p,
                    "getpClass", "getFare", "getpClass",
                    "getSibSp", "getParCh", "getSibSp", "getEmbarked");
            p.setAge(age);
        });
    }

    private static void populateSurvivance(List<Person> persons, float f, int i) {
        List<Person> personsWithSurvivance = persons.stream().filter(p -> !p.isWithoutSurvivance()).collect(Collectors.toList());
        List<Person> personsWithoutSurvivance = persons.stream().filter(Person::isWithoutSurvivance).collect(Collectors.toList());

        Matrix m = Matrix.createMatrix(personsWithSurvivance,
                "getParChSibSpProduce", "getFairPClass", "getEmbarked", "getAgeIndex", "getSurvived");
        double[] theta = Gradient.calculateLogisticTheta(m, f, i);

        personsWithoutSurvivance.forEach(p -> {
            double survivance = Math.round(Gradient.sigmoid(Gradient.calculateCost(theta, p,
                    "getParChSibSpProduce", "getFairPClass", "getEmbarked", "getAgeIndex"))); //"getAgeIndexSQRPClass"
            p.setSurvived(survivance);
        });
    }
}
