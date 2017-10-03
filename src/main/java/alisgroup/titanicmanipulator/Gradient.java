package alisgroup.titanicmanipulator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gradient {

    public static double[] calculateTheta(Matrix matrix, float alpha, int iterations) {
        //initialise theta. All values should be equal to 0 as starters. The length of Theta should be equal to the number
        //of parameters from one matrixX row
        double[] theta = new double[matrix.getMatrixX()[1].length];
        double[][] theta_hist = new double[iterations][matrix.getMatrixX()[1].length];
        int m = matrix.getMatrixY().length;
//        initialise cost
        double[] cost = new double[m];
//        initialise J
        double[] J_history = new double[iterations];
        for (int i = 0; i < J_history.length; i++) {
            J_history[i] = 1000;
        }

//        here the iterations begin
        for (int i = 0; i < iterations; i++) {

//          update theta
            for (int l = 0; l < theta.length; l++) {
                double div = 0;
                for (int ll = 0; ll < m; ll++) {
                    div = div + (cost[ll] - matrix.getMatrixY()[ll]) * matrix.getMatrixX()[ll][l];
                }
                theta[l] = theta[l] - (alpha / m) * div;
            }
//        calculate the cost with obtained theta
            for (int j = 0; j < m; j++) {
                double[] vectorX = matrix.getMatrixX()[j];
                cost[j] = 0;
                for (int k = 0; k < theta.length; k++) {
                    cost[j] = cost[j] + theta[k] * vectorX[k];
                }
            }

            // update J after each iteration.
            double J_part = 0;
            for (int t = 0; t < m; t++) {
                J_part = J_part + Math.pow((cost[t] - matrix.getMatrixY()[t]), 2);
            }
            // if we put 1 / (2 * m) instead of just /(2*m) JVM returns us 0. In this case the J will always be 0
            J_history[i] = J_part / (2 * m);
            theta_hist[i] = theta;
        }
        double J_min = J_history[0];
        int best_iterration = 0;
        for (int i = 1; i < J_history.length; i++) {
            if (Math.abs(J_history[i]) < Math.abs(J_min)) {
                J_min = J_history[i];
                best_iterration = i;
            }
        }
        System.out.println(best_iterration);
        return theta_hist[best_iterration];
    }

    public static double[] calculateLogisticTheta(Matrix matrix, float alpha, int iterations) {
        //initialise theta. All values should be equal to 0 as starters. The length of Theta should be equal to the number
        //of parameters from one matrixX row
        double[] theta = new double[matrix.getMatrixX()[1].length];
        double[][] theta_hist = new double[iterations][matrix.getMatrixX()[1].length];
        int m = matrix.getMatrixY().length;
//        initialise cost
        double[] cost = new double[m];
//        initialise J
        double[] J_history = new double[iterations];
        for (int i = 0; i < J_history.length; i++) {
            J_history[i] = 1000;
        }

//        here the iterations begin
        for (int i = 0; i < iterations; i++) {
//          update theta
            for (int l = 0; l < theta.length; l++) {
                double div = 0;
                for (int ll = 0; ll < m; ll++) {
                    div += (cost[ll] - matrix.getMatrixY()[ll]) * matrix.getMatrixX()[ll][l];
                }
                theta[l] = theta[l] - (alpha / m) * div;
            }
//        calculate the cost with obtained theta
            for (int j = 0; j < m; j++) {
                double[] vectorX = matrix.getMatrixX()[j];
                cost[j] = 0;
                for (int k = 0; k < theta.length; k++) {
                    cost[j] += theta[k] * vectorX[k];
                }
                //sigmoid function
                cost[j] = sigmoid(cost[j]);
            }

            // TODO need to add regularisation.
            double J_part = 0;
            for (int t = 0; t < m; t++) {
                J_part += matrix.getMatrixY()[t] * Math.log(cost[t]) + (1 - matrix.getMatrixY()[t]) * Math.log(1 - cost[t]);
            }

            J_history[i] = J_part / m;
            theta_hist[i] = theta;
        }
        double J_min = J_history[0];
        int best_iterration = 0;
        for (int i = 1; i < J_history.length; i++) {
            if (Math.abs(J_history[i]) < Math.abs(J_min)) {
                J_min = J_history[i];
                best_iterration = i;
            }
        }
        System.out.println(best_iterration);
        return theta_hist[best_iterration];
    }

    public static double calculateCost(double[] theta, Person p, Method... methods) {
        double cost = theta[0];
        for (int i = 1; i < theta.length; i++) {
            try {
                cost = cost + ((double) (methods[i - 1].invoke(p)) * theta[i]);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Gradient.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(Gradient.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(Gradient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cost;
    }

    public static double sigmoid(double cost) {
        return 1.0 / (1.0 + Math.exp(-cost));
    }

    private static float computeJ(Matrix matrix, float[] theta) {
//    function J = computeCostMulti(X, y, theta)
//    COMPUTECOSTMULTI Compute cost for linear regression with multiple variables
//    J = COMPUTECOSTMULTI(X, y, theta) computes the cost of using theta as the
//    parameter for linear regression to fit the data points in X and y
//
//    Initialize some useful values
//    m = length(y); % number of training examples
//    You need to return the following variables correctly
//            J = 0;
//
// ====================== YOUR CODE HERE ======================
// Instructions: Compute the cost of a particular choice of theta
//                           You should set J to the cost.
//
//    H = X.*theta';
//    J = (sum(H')' - y)'*(sum(H')' - y)/(2*m);
// =========================================================================
//
//    end

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
        return 0;
    }
}
