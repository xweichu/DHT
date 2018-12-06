public class TableEntry {

    private int start;
    private Node node;

    TableEntry(){
        this.start = -1;
        this.node = null;
    }

    TableEntry(int start, Node node){
        this.start = start;
        this.node = node;
    }

    public int getStart(){
        return start;
    }

    public Node getNode(){
        return  node;
    }

    public void setStart(int new_start){
        start = new_start;
    }


    public void setNode(Node new_node){
        node = new_node;
    }
}
