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

    public float[] calculateTheta(Matrix matrix, float alpha, int iterations){
        float[] theta = new float[matrix.getMatrixX()[1].length];
        for (int i = 0; i < theta.length; i++) {
            theta[0] = 0;
        }
        
//        function [theta, J_history] = gradientDescentMulti(X, y, theta, alpha, num_iters)
//        GRADIENTDESCENTMULTI Performs gradient descent to learn theta \
//        theta = GRADIENTDESCENTMULTI(x, y, theta, alpha, num_iters) updates theta by
//        taking num_iters gradient steps with learning rate alpha
//        Initialize some useful values
//        m = length(y); % number of training examples
//        J_history = zeros(num_iters, 1);
//
//        for iter = 1:num_iters
//        ====================== YOUR CODE HERE ======================
//        Instructions: Perform a single gradient step on the parameter vector theta.
//
//        H = sum((X.*theta')')';
//
//        it seems to be a huge difference between theta = theta - sum(alpha*(H-y).*X/m)'; and
//
//        theta = theta - alpha/m*sum((H-y).*X)';
//        ============================================================
//         Save the cost J in every iteration
//        J_history(iter) = computeCostMulti(X, y, theta);
//
//        end

        return theta;
    }

    public int[][] getMatrixX() {
        return matrixX;
    }

    public long[] getMatrixY() {
        return matrixY;
    }
}
