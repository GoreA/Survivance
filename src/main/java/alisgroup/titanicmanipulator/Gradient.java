package alisgroup.titanicmanipulator;

public class Gradient {

  public static float[] calculateTheta(Matrix matrix, float alpha, int iterations) {
    //initialise theta. All values should be equal to 0 as starters. The length of Theta should be exual to the number
    //of parameters from one matrixX row
    float[] theta = new float[matrix.getMatrixX()[1].length];
    for (int i = 0; i < theta.length; i++) {
      theta[i] = 0;
    }

    int m = matrix.getMatrixY().length;

//        initialise cost
    float[] cost = new float[m];
//        initialise J
    float[] J_history = new float[iterations];

//        here the iterations begin
    for (int i = 0; i < iterations; i++) {

//        calculate the cost with obtained theta
      for (int j = 0; j < m; j++) {
        int[] vectorX = matrix.getMatrixX()[j];
        for (int k = 0; k < theta.length; k++) {
          cost[j] = cost[j] + theta[k] * vectorX[k];
        }
      }

//          update theta
      for (int l = 0; l < theta.length; l++) {
        float div = 0;
        for(int ii = 0; ii < m; ii++){
          div = div + (cost[ii] - matrix.getMatrixY()[ii]) * matrix.getMatrixX()[ii][l];
        }
        theta[l] = theta[l] - ( alpha/m ) * div;
      }
      
      // TODO need to update J after each iteration.
      J_history[i] = 0;

    }
    return theta;
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
}
