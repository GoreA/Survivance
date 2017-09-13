package alisgroup.titanicmanipulator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Matrix {
    int matrixX[][];
    long matrixY[];

    public Matrix (){}
    public Matrix(int[][] matrixX, long[] matrixY) {
        this.matrixX = matrixX;
        this.matrixY = matrixY;
    }

    public <Person> Matrix createMatrix(List<Person> persons, Method... methods) throws InvocationTargetException, IllegalAccessException {
        int[][] X = new int[persons.size()][methods.length];
        long[] Y = new long[persons.size()];
        for(int i = 0; i < persons.size(); i++){
            Person person = persons.get(i);
            for (int j = 0; j < methods.length; j++) {
                if(j == 0) {X[i][j] = 0;}
                else {X[i][j] = (int) methods[j-1].invoke(person);}
            }
        }
        for(int i = 0; i < persons.size(); i++){
            Person person = persons.get(i);
            Y[i] = (long) methods[methods.length-1].invoke(person);
        }

        return new Matrix(X, Y);
    }

    public int[][] getMatrixX() {
        return matrixX;
    }

    public long[] getMatrixY() {
        return matrixY;
    }
}
