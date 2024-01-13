public class Path {
    private Character path;
    private Node head;
    private Node tail;

    public Path(Character path, Node head, Node tail) {
        this.path = path;
        this.head = head;
        this.tail = tail;
    }

    public Path() {
    }

    public Character getPath() {
        return path;
    }

    public void setPath(Character path) {
        this.path = path;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }
}
