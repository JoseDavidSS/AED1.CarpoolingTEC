package tec.ac.cr.carpoolingtec.logic;

public class Node {
    public Node next;
    public int id;
    private int posx;
    private int posy;

    /**
     * Constructor de la clase.
     * @param posx Eje x del vértice inicial de la lista.
     * @param posy Eje x del vértice final de la lista.
     */
    public Node(int posx, int posy, int id) {
        this.next = null;
        this.id = id;
        this.posx = posx;
        this.posy = posy;
    }

    /**
     * Método para obtener la posición en x inicial de la línea.
     * @return entero con la posición.
     */
    public int getPosx() {
        return this.posx;
    }

    /**
     * Método para modificar la posición en x inicial de la línea.
     * @param posx entero con la nueva posición.
     */
    public void setPosx(int posx) {
        this.posx = posx;
    }

    /**
     * Método para obtener la posición en x final de la línea.
     * @return entero con la posición.
     */
    public int getPosy() {
        return this.posy;
    }

    /**
     * Método para modificar la posición en x final de la línea.
     * @param posy entero con la nueva posición.
     */
    public void setPosy(int posy) {
        this.posy = posy;
    }
}
