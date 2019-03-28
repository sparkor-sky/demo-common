package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class Node {
    private String info;

    public Node(){}

    public Node(String info){
        this.info = info;
    }

    public String getInfo(){
        return info;
    }
}

class LeafNode extends Node{

    public LeafNode(String info){
        super(info);
    }
}


class BranchNode extends Node{

    private List<Node> childs = new LinkedList<>();

    public BranchNode(String info){
        super(info);
    }

    public void addNode(Node node){
        this.childs.add(node);
    }

    public List<Node> getChilds(){
        return this.childs;
    }
}
