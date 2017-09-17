package alisgroup.titanicmanipulator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Matrix {
    float matrixX[][];
    float matrixY[];

    public Matrix (){}
    public Matrix(float[][] matrixX, float[] matrixY) {
        this.matrixX = matrixX;
        this.matrixY = matrixY;
    }

    public <Person> Matrix createMatrix(List<Person> persons, Method... methods) throws InvocationTargetException, IllegalAccessException {
        float[][] X = new float[persons.size()][methods.length];
        float[] Y = new float[persons.size()];
        for(int i = 0; i < persons.size(); i++){
            Person person = persons.get(i);
            for (int j = 0; j < methods.length; j++) {
                if(j == 0) {X[i][j] = 1;}
                else {X[i][j] = (float) methods[j-1].invoke(person);}
            }
        }
        for(int i = 0; i < persons.size(); i++){
            Person person = persons.get(i);
            Y[i] = (float) methods[methods.length-1].invoke(person);
        }

        return new Matrix(X, Y);
    }

    public float[][] getMatrixX() {
        return matrixX;
    }

    public float[] getMatrixY() {
        return matrixY;
    }
}
