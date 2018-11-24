package tec.ac.cr.carpoolingtec.Logic;

public class List {

    public Node head = null;
    public int length = 0;

    public List(){}

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

    public Node searchElement (int id){
        Node tmp = this.head;
        while (tmp.id != id){
            tmp = tmp.next;
            }
        return tmp;
    }

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
