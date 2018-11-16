package main.java.tec.ac.cr.carpoolingtec;

public class Main {

    public static void main(String[] args) {
        int matrix[][] = new int[30][30];
        createGraph(matrix);
        printGraph(matrix);
    }

    public static void createGraph(int matrix[][]) {
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                if (i == 0 || j == 0 || i == 29 || j == 29){
                    matrix[i][j] = 1;
                } else {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public static void printGraph(int[][] matrix) {
        System.out.println("Matriz miedo terror:");
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println(" ");
        }
    }
}
