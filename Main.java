import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Schema schema = new Schema();

        boolean hasAcceptStance = true;

        ArrayList<Path> paths = new ArrayList<>();

        System.out.println("Create Nodes: ");
        int max = 0;
        int k = -1;
        int i = 0;
        while (k != 0) { //Add Nodes and give stances
            System.out.println("\tInput Node " + i + " State. (Hint: Accept stance = true, Reject stance = false)");
            boolean state = scanner.nextBoolean();
            schema.addNode(new Node(i, state));
            System.out.println("\tDid you finish adding nodes? (Hint: yes or no)");
            String finish = scanner.next();
            if (finish.equalsIgnoreCase("yes")) {
                k = 0;
            }
            i += 1;
            max = i;
        }
        ArrayList<Node> nodes = schema.getNodes();

        if (Schema.isValidSchema(nodes)) {
            System.out.println("Connect Nodes: ");
            i = 0;
            k = -1;

            while (k != 0) {
                if (max == i) {
                    break;
                }
                while (true) {
                    System.out.println("\tInput Node " + i + " next Node ID: (Choose from the list below)");
                    for (Node node : nodes) {
                        System.out.print(node.getId() + " ");
                    }

                    int id = scanner.nextInt();
                    System.out.println("\tInput Path to This Node: (Hint: 1 or 0)");
                    String path = scanner.next();

                    Node currentNode = nodes.get(i);
                    if (!currentNode.getNextNodes().isEmpty() && path.equals("10")) {
                        throw new RuntimeException("Wrong path!");
                    }
                    if (currentNode.getNextNodes().isEmpty()) {
                        TreeMap<String, Node> nextNodes = new TreeMap<>();
                        nextNodes.put(path, nodes.get(id));
                        currentNode.setNextNodes(nextNodes);
                    } else {
                        currentNode.getNextNodes().put(path, nodes.get(id));
                    }
                    currentNode.getPath().add(new Path(path.charAt(0), currentNode, nodes.get(id)));

                    paths.add(new Path(path.charAt(0), currentNode, nodes.get(id)));

                    if (path.equals("10")) {
                        break;
                    }
                    if (currentNode.getNextNodes().size() == 2) {
                        break;
                    }
                    System.out.println("\tAdd another path? (Hint: yes or no)");
                    String another = scanner.next();

                    if (!another.equalsIgnoreCase("yes")) {
                        break;
                    }

                }

                if (max != i + 1) {
                    System.out.println("\tDid you finish connecting nodes? (Hint: yes or no)");
                    String finish = scanner.next();
                    if (finish.equalsIgnoreCase("yes")) {
                        k = 0;
                    }
                    i += 1;
                } else {
                    break;
                }

            }
        } else {
            hasAcceptStance = false;
        }

        System.out.println();
        System.out.println("-------------------------------------------------------------");
        System.out.println();

        System.out.println("Test Automata");

        boolean end = false;
        while(!end){
            System.out.println("\tInput String: (Hint: if empty, simply write e)");
            String input = scanner.next();
            if(!hasAcceptStance){
                if(input.equalsIgnoreCase("e")){
                    System.out.println("Machine has accepted current input!");
                }else {
                    System.out.println("Machine has rejected current input!");
                }
                break;
            }
            char[] inputCharArray = input.toCharArray();
            i = 0;
            int lastNodeId;
            boolean currentState = false;
            Node node = nodes.stream().findFirst().get();
            do {
                boolean exists = false;
                Character currentChar = inputCharArray[i];
                for (Path path : node.getPath()) {
                    if (path.getPath().equals(currentChar)) {
                        exists = true;
                        lastNodeId = path.getTail().getId();
                        currentState = nodes.get(lastNodeId).isState();
                        node = path.getTail();
                    } else {
                        currentState = false;
                    }
                }
                if(!exists){
                    currentState = false;
                    break;
                }
                i++;

            } while (input.length() != i);

            if(currentState){
                System.out.println("Machine has accepted current input!");
            }else{
                System.out.println("Machine has rejected current input!");
            }

            System.out.println("Do you want to quit? (Hint: true or false)");
            end = scanner.nextBoolean();
        }
    }
}
