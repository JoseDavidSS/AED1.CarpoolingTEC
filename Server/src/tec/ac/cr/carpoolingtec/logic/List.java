package tec.ac.cr.carpoolingtec.logic;

public class List {
    public Node head = null;
    public int length = 0;

    public void addElement (int posx, int posy) {
        if (this.head == null) {
            this.head = new Node(posx, posy);
            this.length ++;
        } else {
            Node tmp = this.head;
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            tmp.next = new Node(posx, posy);
            this.length ++;
        }
    }
}
