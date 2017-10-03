package alisgroup.titanicmanipulator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Matrix {
    private double matrixX[][];
    private double matrixY[];

    public static Matrix EMPTY = new Matrix();

    private Matrix() {
    }

    private Matrix(double[][] matrixX, double[] matrixY) {
        this.matrixX = matrixX;
        this.matrixY = matrixY;
    }

    public static Matrix createMatrix(List<Person> persons, String... methodNames) {
        try {
            Method[] methods = new Method[methodNames.length];
            for (int i = 0; i < methodNames.length; i++) {
                String methodName = methodNames[i];
                methods[i] = Person.class.getMethod(methodName);
            }
            double[][] X = new double[persons.size()][methods.length];
            double[] Y = new double[persons.size()];
            for (int i = 0; i < persons.size(); i++) {
                Person person = persons.get(i);
                for (int j = 0; j < methods.length; j++) {
                    if (j == 0) {
                        X[i][j] = 1;
                    } else {
                        X[i][j] = (double) methods[j - 1].invoke(person);
                    }
                }
            }
            for (int i = 0; i < persons.size(); i++) {
                Person person = persons.get(i);
                Y[i] = (double) methods[methods.length - 1].invoke(person);
            }

            return new Matrix(X, Y);
        } catch (ReflectiveOperationException | SecurityException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return Matrix.EMPTY;
        }
    }

    public double[][] getMatrixX() {
        return matrixX;
    }

    public double[] getMatrixY() {
        return matrixY;
    }
}
