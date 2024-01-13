import java.util.ArrayList;
import java.util.TreeMap;

public class Node {
    private Integer id;
    private boolean state;
    private TreeMap<String, Node> nextNodes = new TreeMap<>();
    private ArrayList<Path> paths = new ArrayList<>();

    public ArrayList<Path> getPath() {
        return paths;
    }

    public void setPath(ArrayList<Path> paths) {
        this.paths = paths;
    }

    public Node(Integer id, boolean state, TreeMap<String, Node> nextNodes) {
        this.id = id;
        this.state = state;
        this.nextNodes = nextNodes;
    }

    public Node(Integer id, boolean state) {
        this.id = id;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public TreeMap<String, Node> getNextNodes() {
        return nextNodes;
    }
    public boolean hasNextNode(){
        return nextNodes == null;
    }

    public void setNextNodes(TreeMap<String, Node> nextNodes) {
        this.nextNodes = nextNodes;
    }

    public Node getNextNode(String key){
        if(nextNodes.containsKey(key)){
            return nextNodes.get(key);
        }
        return null;
    }

}
