package tec.ac.cr.carpoolingtec.logic;

import java.util.ArrayList;

public class MainBrain {

    public static boolean mapCreated = false;

    public static Holder preparation() {
        Holder holder = new Holder();

        List list = new List();
        createNodes(list);

        int matrixEnableRoads[][] = new int[15][15];
        int matrixLenghtRoads[][] = new int[15][15];

        createRoads(matrixEnableRoads);
        createLenghts(matrixLenghtRoads, matrixEnableRoads, list);

        int roadMatrix[][] = createRoadsMatrix(15,15);
        int matrixtmp[][] = new int[15][15];
        copy(matrixLenghtRoads, matrixtmp);

        setMinRoad(matrixtmp, roadMatrix);

        transformArrayToList(roadMatrix, list, holder);

        holder.setMatrixLenghtRoads(matrixLenghtRoads);
        holder.setMatrixEnableRoads(matrixEnableRoads);
        holder.setRoadMatrix(roadMatrix);
        holder.setMatrixLenghtRoads2(matrixtmp);

        mapCreated = true;

        return holder;
    }

    private static void copy(int[][] from, int[][] to){
        for(int i=0; i < from.length; i++){
            for(int j=0; j < from[i].length; j++)
                to[i][j] = from[i][j];
        }
    }

    public static boolean checkRoad(int pointA, int pointB, int[][] roads){
        if (roads[pointA][pointB] == 1){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Method that creates nodes for the simple list from 0 to 29
     * @param list simple list containing nodes
     */
    public static void createNodes(List list) {
        for (int i = 0; i < 15; i++) {
            list.addElement(randomWithRangeForPosition(700, 80), randomWithRangeForPosition(1200, 400), i);
        }
    }

    /**
     * Method that randomly enable or disable a road between nodes
     * @param matrixEnableRoads Matrix with enable roads between nodes
     */
    public static void createRoads(int matrixEnableRoads[][]) {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (i == j) {
                    matrixEnableRoads[i][j] = 0;
                } else {
                    int road = randomWithRangeForRoad();
                    matrixEnableRoads[i][j] = road;
                }
            }
        }
    }

    /**
     * Method that takes the length between nodes and add it to the matrixLengthRoads
     * @param matrixLengthRoads Matrix with length between nodes
     * @param matrixEnableRoads Matrix with enable roads between nodes
     * @param list
     */
    public static void createLenghts(int matrixLengthRoads[][], int matrixEnableRoads[][], List list) {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
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

    /**
     * Method that prints graphs or matrix
     * @param matrix matrix to be shown
     */
    public static void printGraph(int[][] matrix) {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                System.out.print(matrix[i][j] + "    ");
            }
            System.out.println("    ");
        }
    }

    /**
     * Method that gives random binary value for enable roads in matrixEnableRoads
     * @return int 1 or 0
     */
    public static int randomWithRangeForRoad() {
        double road = Math.random();
        if (road < 0.9) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * Method that gives random x and y positions for nodes
     * @param max maximum limit
     * @param min minimum limit
     * @return int number for the position
     */
    public static int randomWithRangeForPosition(int max, int min) {
        int range = (max - min) + 1;
        int number = (int) (Math.random() * range) + min;
        if (number == 0) {
            randomWithRangeForPosition(max, min);
        }
        return number;
    }

    /**
     * Method that gives the length between nodes
     * @param node1 first node chosen
     * @param node2 second node chosen
     * @return length between node1 and node2
     */
    public static double getRoadsLenght(Node node1, Node node2) {
        int posX1 = node1.getPosx();
        int posY1 = node1.getPosy();
        int posX2 = node2.getPosx();
        int posY2 = node2.getPosy();
        double length = Math.sqrt(Math.abs(((posX2 - posX1) * (posX2 - posX1))) + ((posY2 - posY1) * (posY2 - posY1)));
        return (int) length;
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static int[][] createRoadsMatrix(int a, int b) {
        int roadsMatrix[][] = new int[15][15];
        for (int j = 0; j < b; j++) {
            for (int i = 0; i < a; i++) {
                roadsMatrix[i][j] = j;
            }
        }
        return roadsMatrix;
    }

    /**
     *
     * @param lengthMatrix
     * @param roadMatrix
     * @return
     */
    public static int[][] setMinRoad(int[][] lengthMatrix, int[][] roadMatrix) {
        for (int i_j = 0; i_j < 15; i_j++) {
            for (int tmp_i = 0; tmp_i < 15; tmp_i++) {
                if (tmp_i != i_j) {
                    for (int tmp_j = 0; tmp_j < 15; tmp_j++) {
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

    /**
     *
     * @param pointA
     * @param pointB
     * @param roadMatrix
     * @return
     */
    public static ArrayList createRoute(int pointA, int pointB, int[][] roadMatrix){
        ArrayList<Integer> route = new ArrayList<Integer>();
        route.add(pointA);
        while(roadMatrix[pointA][pointB] != pointB){
            route.add(roadMatrix[pointA][pointB]);
            pointA = roadMatrix[pointA][pointB];
        }
        route.add(roadMatrix[pointA][pointB]);
        return route;
    }

    /**
     * Method that transforms an ArrayList into a simple list
     * @param roadMatrix matrix with roads
     * @param list simple list containing all nodes
     * @param holder class containing data
     */
    public static void transformArrayToList(int roadMatrix[][], List list, Holder holder){
        List route = new List();
        ArrayList<Integer> arrayRoute = createRoute(0,14, roadMatrix);
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
        holder.setList(list);
        holder.setRoute(route);
    }
}
