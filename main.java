public class Gauss {

    public static void main(String[] args) {
        double[][] matrix = {
                {2, 3, 1, 5},
                {6, 13, 5, 19},
                {2, 19, 10, 23}
        };
        double[] solution = gauss(matrix);
        for (int i = 0; i < solution.length; i++) {
            System.out.println("x" + (i + 1) + " = " + solution[i]);
        }
    }

    public static double[] gauss(double[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            // Поиск максимального элемента в i-ом столбце
            double maxElement = Math.abs(matrix[i][i]);
            int maxRow = i;
            for (int k = i + 1; k < n; k++) {
                if (Math.abs(matrix[k][i]) > maxElement) {
                    maxElement = Math.abs(matrix[k][i]);
                    maxRow = k;
                }
            }
            // Перестановка строки с максимальным элементом с текущей строкой
            for (int k = i; k < n + 1; k++) {
                double temp = matrix[maxRow][k];
                matrix[maxRow][k] = matrix[i][k];
                matrix[i][k] = temp;
            }
            // Приведение всех элементов в i-ом столбце к нулю, кроме i-го элемента
            for (int k = i + 1; k < n; k++) {
                double factor = -matrix[k][i] / matrix[i][i];
                for (int j = i; j < n + 1; j++) {
                    if (i == j) {
                        matrix[k][j] = 0;
                    } else {
                        matrix[k][j] += factor * matrix[i][j];
                    }
                }
            }
        }
        // Обратный ход
        double[] solution = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            solution[i] = matrix[i][n] / matrix[i][i];
            for (int k = i - 1; k >= 0; k--) {
                matrix[k][n] -= matrix[k][i] * solution[i];
            }
        }
        return solution;
    }
}
