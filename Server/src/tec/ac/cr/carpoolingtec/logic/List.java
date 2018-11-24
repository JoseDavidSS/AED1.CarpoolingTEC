package tec.ac.cr.carpoolingtec.logic;

public class List {

    public Node head = null;
    public int length = 0;

    public List(){}

    /**
     * Method that adds elements to the simple list
     * @param posx position in x
     * @param posy position in y
     * @param id node's ID
     */
    public void addElement (int posx, int posy, int id) {
        if (this.head == null) {
            this.head = new Node(posx, posy, id);
            this.length ++;
        } else {
            Node tmp = this.head;
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            tmp.next = new Node(posx, posy, id);
            this.length ++;
        }
    }

    /**
     * Method that searches for an element in the simple list by his ID
     * @param id node's ID
     * @return returns the node
     */
    public Node searchElement (int id){
        Node tmp = this.head;
        while (tmp.id != id){
            tmp = tmp.next;
            }
        return tmp;
    }

    /**
     * Method that prints the simple list whit all its node's values
     */
    public void printList(){
        Node tmp = this.head;
        String txt = "[";
        while (tmp != null){
            int id = tmp.getId();
            int posx = tmp.getPosx();
            int posy = tmp.getPosy();
            if (tmp.next != null) {
                txt += "(" + id + "," + posx + "," + posy + ") , ";
                tmp = tmp.next;
            }else {
                txt += "(" + id + "," + posx + "," + posy + ")]";
                break;

            }
        }
        System.out.println(txt);
    }
}
