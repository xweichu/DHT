import java.util.*;

public class Node {

    private int id;
    private FingerTable fingerTable;
    private Node predecessor;
    private Map<Integer,String> localKeys;


    public Node(int id){
        this.id = id;
        predecessor = this;
        fingerTable = new FingerTable(this);
        localKeys = new HashMap<>();
    }

    public int getId(){
        return this.id;
    }


    public FingerTable getFingerTable(){
        return fingerTable;
    }

    public Node getPredecessor(){
        return this.predecessor;
    }

    public void setPredecessor(Node predecessor){
        this.predecessor = predecessor;
    }

    public Node getSuccessor(){
        return this.fingerTable.getEntry(1).getNode();
    }

    public void setSuccessor(Node successor){
        this.fingerTable.getEntry(1).setNode(successor);
    }


    public List<Integer> join(Node nodeP){

        if(nodeP != null){
            init_finger_table(nodeP);
            update_others();

        }else {
            for(int i=1; i<9; i++){
                fingerTable.getEntry(i).setNode(this);
            }
            predecessor = this;

        }

        Map<Integer,String> successorMap = this.getSuccessor().getLocalKeys();
        Map<Integer,String> myMap = this.getLocalKeys();
        List<Integer> keys = new ArrayList<>();

        for(Integer key: successorMap.keySet()){
            if(check_range_right_close(key,this.predecessor.getId(),this.getId())){
                String val = successorMap.get(key);
                myMap.put(key,val);
                keys.add(key);
            }
        }

        for(int i = 0; i< keys.size(); i++){
            successorMap.remove(keys.get(i));
        }

        Collections.sort(keys);

        return keys;

    }

    public void init_finger_table(Node nodeP){

        fingerTable.getEntry(1).setNode(nodeP.find_successor(fingerTable.getEntry(1).getStart()));

        predecessor = this.getSuccessor().getPredecessor();

        this.getSuccessor().setPredecessor(this);

        for(int i=1; i < 8; i++){

            if(check_range_left_close(fingerTable.getEntry(i+1).getStart(),this.getId(),fingerTable.getEntry(i).getNode().getId())){
                fingerTable.getEntry(i+1).setNode(fingerTable.getEntry(i).getNode());
            }
            else {
                fingerTable.getEntry(i+1).setNode(nodeP.find_successor(fingerTable.getEntry(i+1).getStart()));
            }

        }
    }


    public void update_others(){
        for(int i = 1 ; i < 9; i++){
            int p_id = (int)(this.getId() - Math.pow(2,i-1))+1;
            if(p_id<0) p_id = p_id + 255;
            Node p = find_predecessor(p_id);
            p.update_finger_table(this, i);
        }
    }

    public void update_finger_table(Node s , int i){

        if(check_range_left_close_2(s.getId(),fingerTable.getEntry(i).getStart(), fingerTable.getEntry(i).getNode().getId())){
            fingerTable.getEntry(i).setNode(s);
            Node p = predecessor;
            p.update_finger_table(s,i);
        }

    }




    public Node find_predecessor(int id){
        Node nodeP = this;
        while(!check_range_right_close(id, nodeP.getId(), nodeP.getSuccessor().getId())){
            nodeP = nodeP.closest_preceding_finger(id);
        }
        return nodeP;
    }


    public Node find_successor(int id){
        Node n_p = find_predecessor(id);
        return  n_p.getSuccessor();
    }


    public Node find_successor(int id, List<Integer> traces){
        Node n_p = find_predecessor(id, traces);
        return  n_p.getSuccessor();
    }

    public Node find_predecessor(int id, List<Integer> traces){
        Node nodeP = this;
        while(!check_range_right_close(id, nodeP.getId(), nodeP.getSuccessor().getId())){
            nodeP = nodeP.closest_preceding_finger(id);
            traces.add(nodeP.getId());
        }
        return nodeP;
    }



    public Node closest_preceding_finger(int id){

        for(int i = 8; i>0;i--){
            int fingerID = this.fingerTable.getEntry(i).getNode().getId();

            if(check_range_open(fingerID, this.getId(), id)) {
                return this.fingerTable.getEntry(i).getNode();
            }
        }
        return this;
    }


    public boolean check_range_open(int target, int left, int right){

        if(left == right){
            if(target == left) return false;
            return true;
        }

        if(left < right){
            if(target> left && target < right){
                return  true;
            }
        }else if(target > left || target < right){
            return  true;
        }

        return false;
    }

    public boolean check_range_right_close(int target, int left, int right){

        if(left == right) return true;

        if(left < right){
            if(target> left && target <= right){
                return  true;
            }
        }else if(target > left || target <= right){
            return  true;
        }

        return false;
    }


    public boolean check_range_left_close(int target, int left, int right){

        if(left == right) return true;

        if(left < right){
            if(target >= left && target < right){
                return  true;
            }
        }else if(target >= left || target < right){
            return  true;
        }

        return false;
    }

    public boolean check_range_left_close_2(int target, int left, int right){

        if(left == right) return false;

        return check_range_left_close(target,left,right);

    }



    public Map<Integer,String> getLocalKeys(){
        return localKeys;
    }


    public void insert(int key, String value){

        Node target = this.find_successor(key);
        Map<Integer,String> mp = target.getLocalKeys();
        mp.put(key,value);
    }

    public void remove(int key){

        Node target = this.find_successor(key);
        Map<Integer,String> mp = target.getLocalKeys();
        mp.remove(key);
    }

    public List<Integer> find(int key){
        List<Integer> traces = new ArrayList<>();
        if(this.getLocalKeys().containsKey(key)) {
            traces.add(this.getId());
            return traces;
        }

//        List<Integer> traces = new ArrayList<>();
        traces.add(this.getId());
        Node target = this.find_successor(key,traces);
        traces.add(target.getId());
        return traces;
    }


    public List<Integer> leave(){

        Map<Integer,String> successorMap = this.getSuccessor().getLocalKeys();
        Map<Integer,String> myMap = this.getLocalKeys();

        List<Integer> keys = new ArrayList<>();

        for(Integer key: myMap.keySet()){
            keys.add(key);
            String val = myMap.get(key);
            successorMap.put(key,val);
        }

        this.getSuccessor().setPredecessor(this.predecessor);
        update_others_leave();

        Collections.sort(keys);

        return keys;

    }

    public void update_others_leave(){
        for(int i = 1 ; i < 9; i++){
            int p_id = (int)(this.getId() - Math.pow(2,i-1))+1;
            if(p_id<0) p_id = p_id + 255;
            Node p = find_predecessor(p_id);
            p.update_finger_table_leave(this, i);
        }
    }

    public void update_finger_table_leave(Node s , int i){

        if(fingerTable.getEntry(i).getNode().getId() == s.getId()){
            fingerTable.getEntry(i).setNode(s.getSuccessor());
            Node p = predecessor;
            p.update_finger_table_leave(s,i);
        }

    }

    public void printTableAndKeys(){
        this.fingerTable.prettyPrint();
        System.out.println("Keys stored in the node:");
        List<Integer> keys = new ArrayList<>();
        for(Integer key: this.getLocalKeys().keySet()){
            keys.add(key);
        }
        Collections.sort(keys);
        System.out.println(keys);
    }

}
