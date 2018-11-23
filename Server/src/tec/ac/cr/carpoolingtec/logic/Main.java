package tec.ac.cr.carpoolingtec.logic;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        List list = new List();
        createNodes(list);
        list.printList();

        int matrixEnableRoads[][] = new int[30][30];
        int matrixLenghtRoads[][] = new int[30][30];

        createRoads(matrixEnableRoads);
        createLenghts(matrixLenghtRoads, matrixEnableRoads, list);

        int roadMatrix[][] = createRoadsMatrix(30,30);

        printGraph(matrixEnableRoads);
        printGraph(matrixLenghtRoads);

        setMinRoad(matrixLenghtRoads, roadMatrix);

        transformArrayToList(roadMatrix, list);

        Holder.matrixEnableRoads = matrixEnableRoads;
        Holder.matrixLenghtRoads = matrixLenghtRoads;

        Holder.route.printList();
    }

    public static void createNodes(List list) {
        for (int i = 0; i < 30; i++) {
            list.addElement(randomWithRangeForPosition(1080, 100), randomWithRangeForPosition(1700, 400), i);
        }
    }

    public static void createRoads(int matrixEnableRoads[][]) {
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                if (i == j) {
                    matrixEnableRoads[i][j] = 0;
                } else {
                    int road = randomWithRangeForRoad();
                    matrixEnableRoads[i][j] = road;
                }
            }
        }
    }

    public static void createLenghts(int matrixLengthRoads[][], int matrixEnableRoads[][], List list) {
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                if (matrixEnableRoads[i][j] == 0) {
                    if (i == j){
                        matrixLengthRoads[i][j] = 0;
                    }else {
                        matrixLengthRoads[i][j] = 10000;
                    }
                }
                else{
                    matrixLengthRoads[i][j] = (int) getRoadsLenght(list.searchElement(i), list.searchElement(j));
                }
            }
        }
    }

    public static void printGraph(int[][] matrix) {
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                System.out.print(matrix[i][j] + "    ");
            }
            System.out.println("    ");
        }
    }

    public static int randomWithRangeForRoad() {
        double road = Math.random();
        if (road < 0.8) {
            return 0;
        } else {
            return 1;
        }
    }

    public static int randomWithRangeForPosition(int max, int min) {
        int range = (max - min) + 1;
        int number = (int) (Math.random() * range) + min;
        if (number == 0) {
            randomWithRangeForPosition(max, min);
        }
        return number;
    }

    public static double getRoadsLenght(Node node1, Node node2) {
        int posX1 = node1.getPosx();
        int posY1 = node1.getPosy();
        int posX2 = node2.getPosx();
        int posY2 = node2.getPosy();
        double length = Math.sqrt(Math.abs(((posX2 - posX1) * (posX2 - posX1))) + ((posY2 - posY1) * (posY2 - posY1)));
        return (int) length;
    }

    public static int[][] createRoadsMatrix(int a, int b) {
        int roadsMatrix[][] = new int[30][30];
        for (int j = 0; j < b; j++) {
            for (int i = 0; i < a; i++) {
                roadsMatrix[i][j] = j;
            }
        }
        return roadsMatrix;
    }

    public static int[][] setMinRoad(int[][] lengthMatrix, int[][] roadMatrix) {
        for (int i_j = 0; i_j < 30; i_j++) {
            for (int tmp_i = 0; tmp_i < 30; tmp_i++) {
                if (tmp_i != i_j) {
                    for (int tmp_j = 0; tmp_j < 30; tmp_j++) {
                        if (tmp_j != i_j) {
                            if (lengthMatrix[i_j][tmp_j] + lengthMatrix[tmp_i][i_j] < lengthMatrix[tmp_i][tmp_j]) {
                                roadMatrix[tmp_i][tmp_j] = i_j;
                                lengthMatrix[tmp_i][tmp_j] = lengthMatrix[i_j][tmp_j] + lengthMatrix[tmp_i][i_j];
                            }
                        }
                    }
                }
            }
        }
        return roadMatrix;
    }

    public static ArrayList createRoute(int pointA, int pointB, int[][] roadMatrix){
        ArrayList<Integer> route = new ArrayList<Integer>(); //Cambiar a lista enlazada xd
        route.add(pointA);
        while(roadMatrix[pointA][pointB] != pointB){
            route.add(roadMatrix[pointA][pointB]);
            pointA = roadMatrix[pointA][pointB];
        }
        route.add(roadMatrix[pointA][pointB]);
        return route;
    }

    public static void transformArrayToList(int roadMatrix[][], List list){
        List route = new List();
        ArrayList<Integer> arrayRoute = createRoute(0,23, roadMatrix);
        for (int i = 0; i < arrayRoute.size(); i++){
            Node tmp = list.head;
            while (tmp != null){
                if (arrayRoute.get(i) == tmp.id){
                    int posx = tmp.getPosx();
                    int posy = tmp.getPosy();
                    int id = tmp.getId();
                    route.addElement(posx, posy, id);
                    tmp = tmp.next;
                }else{
                    tmp = tmp.next;
                }
            }
        }
        Holder.list = list;
        Holder.route = route;
    }
}
