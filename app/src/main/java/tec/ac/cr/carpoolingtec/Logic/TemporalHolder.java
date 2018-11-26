package tec.ac.cr.carpoolingtec.Logic;

public class TemporalHolder {

    private int matrixEnableRoads[][];
    private int matrixLenghtRoads[][];
    private int roadMatrix[][];
    private int matrixLenghtRoads2[][];
    private List route;
    private List list;

    /**
     * Constructor of the holder class
     */
    public TemporalHolder(){
    }

    public int[][] getMatrixEnableRoads() {
        return matrixEnableRoads;
    }

    public void setMatrixEnableRoads(int[][] matrixEnableRoads) {
        this.matrixEnableRoads = matrixEnableRoads;
    }

    public int[][] getMatrixLenghtRoads() {
        return matrixLenghtRoads;
    }

    public void setMatrixLenghtRoads(int[][] matrixLenghtRoads) {
        this.matrixLenghtRoads = matrixLenghtRoads;
    }

    public int[][] getRoadMatrix() {
        return roadMatrix;
    }

    public void setRoadMatrix(int[][] roadMatrix) {
        this.roadMatrix = roadMatrix;
    }

    public int[][] getMatrixLenghtRoads2() {
        return matrixLenghtRoads2;
    }

    public void setMatrixLenghtRoads2(int[][] matrixLenghtRoads2) {
        this.matrixLenghtRoads2 = matrixLenghtRoads2;
    }

    public List getRoute() {
        return route;
    }

    public void setRoute(List route) {
        this.route = route;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

}
