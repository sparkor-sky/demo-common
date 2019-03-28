package tree;

import java.util.List;

public class Main {
    public static void main(String[] args){
        BranchNode root = new BranchNode("a");
        root.addNode(new LeafNode("b"));

        BranchNode c = new BranchNode("c");
        c.addNode(new LeafNode("d"));
        c.addNode(new LeafNode("e"));
        c.addNode(new LeafNode("f"));
        root.addNode(c);

        printTree(root);
    }

    public static void printTree(Node root){
        if(null != root){
            System.out.println(root.getInfo());
        }

        if(root instanceof BranchNode){
            List<Node> childs = ((BranchNode) root).getChilds();
            for(Node node:childs){
                printTree(node);
            }
        }
    }
}
