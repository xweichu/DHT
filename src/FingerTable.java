import java.util.*;

public class FingerTable {

        private int nodeId;
        private TableEntry[] fingerEntries;

        public FingerTable(Node node){

            this.nodeId = node.getId();
            fingerEntries = new TableEntry[9];

            for(int i=1; i<9;i++){
                int start  = ((int)(nodeId + Math.pow(2,i-1)) % 256);
                fingerEntries[i] = new TableEntry(start,node);
            }
        }

        public TableEntry getEntry(int index){

//            System.out.println(index);
            return fingerEntries[index];
        }

        // TODO: complete print function

        public void prettyPrint() {

            for(int i = 1; i<9; i++){

                int start = fingerEntries[i].getStart();
                int node_id = fingerEntries[i].getNode().getId();
                System.out.println( String.format("Start: %3d",start) + " Node:" + node_id);
            }

        }

}
