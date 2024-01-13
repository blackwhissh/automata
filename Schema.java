import java.util.ArrayList;

public class Schema {
    private final ArrayList<Node> nodes = new ArrayList<>();
    public Schema() {
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }
    public Node getNode(Integer index){
        return nodes.get(index);
    }
    public void addNode(Node node){
        if (node != null){
            nodes.add(node);
        }
    }
    public static boolean isValidSchema(ArrayList<Node> nodes){ //If it has any accept stance
        boolean found = false;
        for(Node node : nodes){
            if(node.isState()){
                found = true;
                break;
            }
        }
        return found;
    }

}
