package tec.ac.cr.carpoolingtec.Logic;

public class Node {

    public Node next;
    public int id;
    private int posx;
    private int posy;
    private int hasPerson;

    /**
     * Constructor of the nodes class
     * @param posx x position of the node
     * @param posy y position of the node
     */
    public Node(int posx, int posy, int id) {
        this.next = null;
        this.id = id;
        this.posx = posx;
        this.posy = posy;
        this.hasPerson = 0;
    }

    public Node(){}

    public int getPosx() {
        return this.posx;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public int getPosy() {
        return this.posy;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHasPerson() {
        return hasPerson;
    }

    public void setHasPerson(int hasPerson) {
        this.hasPerson = hasPerson;
    }
}
