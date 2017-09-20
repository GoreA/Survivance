package alisgroup.titanicmanipulator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Matrix {
    double matrixX[][];
    double matrixY[];

    public Matrix (){}
    public Matrix(double[][] matrixX, double[] matrixY) {
        this.matrixX = matrixX;
        this.matrixY = matrixY;
    }

    public <Person> Matrix createMatrix(List<Person> persons, Method... methods) throws InvocationTargetException, IllegalAccessException {
        double[][] X = new double[persons.size()][methods.length];
        double[] Y = new double[persons.size()];
        for(int i = 0; i < persons.size(); i++){
            Person person = persons.get(i);
            for (int j = 0; j < methods.length; j++) {
                if(j == 0) {X[i][j] = 1;}
                else {X[i][j] = (double) methods[j-1].invoke(person);}
            }
        }
        for(int i = 0; i < persons.size(); i++){
            Person person = persons.get(i);
            Y[i] = (double) methods[methods.length-1].invoke(person);
        }

        return new Matrix(X, Y);
    }

    public double[][] getMatrixX() {
        return matrixX;
    }

    public double[] getMatrixY() {
        return matrixY;
    }
}
