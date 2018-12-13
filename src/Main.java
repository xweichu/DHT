import java.util.*;

public class Main {


    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static void main(String[] args){

//
////        Node nd_255 = new Node(255);
////        nd_255.insert(0,"");
////        nd_255.insert(255,"");
////        nd_255.insert(62,"");
////        nd_255.insert(63,"");
////        nd_255.insert(64,"");
////
////
////
////        Node nd_63 = new Node(63);
////        nd_63.join(nd_255);
////
////
////
////
////        nd_63.printTableAndKeys();
////        nd_255.printTableAndKeys();
//
//
       // Test case 1: c3

        Node node_2 = new Node(2);
        Node node_102 = new Node(102);
        Node node_203 = new Node(203);
        Node node_55 = new Node(55);
        Node node_177 = new Node(177);
        Node node_23 = new Node(23);
        Node node_155 = new Node(155);
        Node node_235 = new Node(235);


        // The nodes join the network and insert keys

        List<Integer> all_keys = new ArrayList<>();

        List<Integer> join_keys = new ArrayList<>();
        node_55.join(null);
        for(int i=0; i<10; i++){
            int gen = ((i+1)*8+3)*9%255;
            join_keys.add(gen);
            node_55.insert(gen,"");
        }
        all_keys.addAll(join_keys);
        System.out.println("Node 055 joins, and 10 keys are inserted via this node:"+join_keys);


        join_keys = new ArrayList<>();
        node_177.join(node_55);
        for(int i=33; i<43; i++){
            int gen = ((i+1)*7+3)*5%255;
            join_keys.add(gen);
            node_177.insert(gen,"");
        }
        all_keys.addAll(join_keys);
        System.out.println("Node 177 joins, and 10 keys are inserted via this node:"+join_keys);


        join_keys = new ArrayList<>();
        node_2.join(node_177);
        for(int i=75; i<85; i++){

            int gen = ((i+5)*7+8)*8%255;
            join_keys.add(gen);
            node_2.insert(gen,"");
        }
        all_keys.addAll(join_keys);
        System.out.println("Node 002 joins, and 10 keys are inserted via this node:"+join_keys);


        join_keys = new ArrayList<>();
        node_203.join(node_177);
        for(int i=142; i<152; i++){

            int gen = ((i+2)*2+3)*9%255;
            join_keys.add(gen);
            node_203.insert(gen,"");
        }
        all_keys.addAll(join_keys);
        System.out.println("Node 203 joins, and 10 keys are inserted via this node:"+join_keys);

        join_keys = new ArrayList<>();
        node_102.join(node_2);
        for(int i=203; i<213; i++){

            int gen = ((i+8)*9+3)*2%255;
            join_keys.add(gen);
            node_102.insert(gen,"");
        }
        all_keys.addAll(join_keys);
        System.out.println("Node 102 joins, and 10 keys are inserted via this node:"+join_keys);

        join_keys = new ArrayList<>();
        node_23.join(node_102);
        for(int i=234; i<244; i++){

            int gen = ((i+8)*6+1)*5%255;
            join_keys.add(gen);
            node_23.insert(gen,"");
        }
        all_keys.addAll(join_keys);
        System.out.println("Node 023 joins, and 10 keys are inserted via this node:"+join_keys);

        join_keys = new ArrayList<>();
        node_155.join(node_203);
        for(int i=123; i<133; i++){

            int gen = ((i+8)*2+3)*3%255;
            join_keys.add(gen);
            node_155.insert(gen,"");
        }
        all_keys.addAll(join_keys);
        System.out.println("Node 155 joins, and 10 keys are inserted via this node:"+join_keys);

        join_keys = new ArrayList<>();
        node_235.join(node_2);
        for(int i=61; i<71; i++){

            int gen = ((i+5)*2+3)*5%255;
            join_keys.add(gen);
            node_235.insert(gen,"");
        }
        all_keys.addAll(join_keys);
        System.out.println("Node 235 joins, and 10 keys are inserted via this node:"+join_keys);

        Collections.sort(all_keys);

//        System.out.println("All keys:"+all_keys);


        System.out.println("\nRubric 1 test, build finger table correctly:");
        node_2.printTableAndKeys();
        node_23.printTableAndKeys();
        node_55.printTableAndKeys();
        node_102.printTableAndKeys();
        node_155.printTableAndKeys();
        node_177.printTableAndKeys();
        node_203.printTableAndKeys();
        node_235.printTableAndKeys();


        System.out.println("\nRubric 2 test, key migration when new nodes joining:");
        Node node_76 = new Node(76);
        List<Integer> keys_migrated = node_76.join(node_235);
        System.out.println("keys migrated when Node "+node_76.getId()+" joining:"+ keys_migrated);

        Node node_36 = new Node(36);
        keys_migrated = node_36.join(node_23);
        System.out.println("keys migrated when Node "+node_36.getId()+" joining:"+ keys_migrated);


        System.out.println("\nRubric 3 test, lookup keys:");
        List<Integer> traces_1 = node_2.find(209);
        System.out.println("Find key 209 " + "via node 2:" + traces_1);
        List<Integer> traces_2 = node_155.find(35);
        System.out.println("Find key 35 " + "via node 155:" + traces_2);

        System.out.println("\nRubric 4 test, nodes leave:");
        List<Integer> leave_traces = node_55.leave();
        System.out.println("Keys migrated to its successor when node 55 leaving:" + leave_traces);
        List<Integer> leave_traces_2 = node_203.leave();
        System.out.println("Keys migrated to its successor when node 203 leaving:" + leave_traces_2);


        join_keys = new ArrayList<>();
        for(int i=186; i<199; i++) {
            int gen = ((i+9)*9+8)*3%255;
            join_keys.add(gen);
            node_177.insert(gen,"");
        }

        Collections.sort(join_keys);
        System.out.println("Keys inserted after nodes leaving:"+join_keys);

        System.out.println("\nFinal status of the DHT:");
        node_2.printTableAndKeys();
        node_23.printTableAndKeys();
        node_36.printTableAndKeys();
        node_76.printTableAndKeys();
        node_102.printTableAndKeys();
        node_155.printTableAndKeys();
        node_177.printTableAndKeys();
        node_235.printTableAndKeys();


        List<Integer> random_keys = new ArrayList<>();

        //Randomly Generate 5 nodes, and make them sparse evenly in the ring with the range of 0-255;

        Node[] node_list = new Node[5];

        for(int i = 0; i< 5; i++){
            node_list[i] = new Node(i* 50 + 10 + new Random().nextInt(10));
        }

        //Nodes joins the DHT and insert keys;
        for(int i = 1; i< 5; i++){
            node_list[i].join(node_list[i-1]);
        }

        // Let's insert all possible keys via a random node:
        for(int i=0; i<100; i++){
            int gen = getRandomNumberInRange(0,255);
            random_keys.add(gen);
            node_list[new Random().nextInt(5)].insert(gen,"");
        }

        //Print the DHT:
        System.out.println("\n\n\nRandomly Generated DHT:");
        for(int i = 0; i<5; i++){
            node_list[i].printTableAndKeys();
        }

        Node rdm_node = node_list[new Random().nextInt(5)];
        int rdm_key = new Random().nextInt(99);
        List<Integer> rdm_traces = rdm_node.find(random_keys.get(rdm_key));
        System.out.println("\nFind a random number "+random_keys.get(rdm_key)+" via a random node "+rdm_node.getId()+" in the ring:"+ rdm_traces);

        //Join 2 random nodes via existing random nodes:
        Node[] new_nodes = new Node[2];
        List<Integer> rdm_trace;
        for(int i =0 ;i<2; i++){
            Node nd = new Node(40*(i+1) + 150*i + new Random().nextInt(10));
            Node rdm_nd = node_list[new Random().nextInt(5)];
            new_nodes[i] = nd;
            rdm_trace = nd.join(rdm_nd);
            System.out.println("keys migrated when Node "+nd.getId()+" joining via Node " + rdm_nd.getId()+":" + rdm_trace);
        }

        //Previous nodes leave the ring:
        int rdm_leave  = new Random().nextInt(5);
        rdm_trace = node_list[rdm_leave].leave();
        System.out.println("Keys migrated to its successor when node "+node_list[rdm_leave].getId()+" leaving:" + rdm_trace);


        List<Integer> leave_insert_keys = new ArrayList<>();
        for(int i=0; i<10; i++){
            int gen = getRandomNumberInRange(0,255);
            leave_insert_keys.add(gen);
            node_list[new Random().nextInt(5)].insert(gen,"");
        }
        System.out.println("Insert 10 keys after nodes left:" + leave_insert_keys);


        //Print final status of DHT:
        System.out.println("\n\nFinal status of DHT:");
        for(int i = 0; i<5; i++){
            if(i!=rdm_leave) {
                node_list[i].printTableAndKeys();
            }
        }

        for(int i =0; i<2; i++){
            new_nodes[i].printTableAndKeys();
        }






//        Node node_2 = new Node(6);
//        Node node_9 = new Node(38);
//        Node node_67 = new Node(99);
//        Node node_138 = new Node(167);
//        Node node_220 = new Node(204);
//
//        node_2.join(null);
//        node_138.join(node_2);
//        node_9.join(node_138);
//        node_67.join(node_2);
//        node_220.join(node_138);
//
//        for(int i = 0 ; i<256; i++){
//            node_2.insert(i,"Str"+i);
//        }
//
//        Node node_169 = new Node(107);
//
//        node_169.join(node_67);
//
//        List<Integer> traces = node_9.find(145);
//
//        node_2.printTableAndKeys();

//        System.out.println("-----Node002-----");
//        node_2.getFingerTable().prettyPrint();
//        System.out.println(node_2.getLocalKeys());
//        System.out.println("-----Node009-----");
//        node_9.getFingerTable().prettyPrint();
//        System.out.println(node_9.getLocalKeys());
//        System.out.println("-----Node067-----");
//        node_67.getFingerTable().prettyPrint();
//        System.out.println(node_67.getLocalKeys());
//        System.out.println("-----Node138-----");
//        node_138.getFingerTable().prettyPrint();
//        System.out.println(node_138.getLocalKeys());
//        System.out.println("-----Node169-----");
//        node_169.getFingerTable().prettyPrint();
//        System.out.println(node_169.getLocalKeys());
//        System.out.println("-----Node220-----");
//        node_220.getFingerTable().prettyPrint();
//        System.out.println(node_220.getLocalKeys());
//
//        System.out.println("-----Find Key 145 via Node 9-----");
//        System.out.println(traces);
//
//        node_67.leave();
//
//        System.out.println("Status of all nodes after Node 67 left:");
//        System.out.println("-----Node002-----");
//        node_2.getFingerTable().prettyPrint();
//        System.out.println(node_2.getLocalKeys());
//        System.out.println("-----Node009-----");
//        node_9.getFingerTable().prettyPrint();
//        System.out.println(node_9.getLocalKeys());
//        System.out.println("-----Node138-----");
//        node_138.getFingerTable().prettyPrint();
//        System.out.println(node_138.getLocalKeys());
//        System.out.println("-----Node169-----");
//        node_169.getFingerTable().prettyPrint();
//        System.out.println(node_169.getLocalKeys());
//        System.out.println("-----Node220-----");
//        node_220.getFingerTable().prettyPrint();
//        System.out.println(node_220.getLocalKeys());


//        Node node_2 = new Node(2);
//        Node node_9 = new Node(9);
//        Node node_67 = new Node(67);
//        Node node_138 = new Node(138);
//        Node node_220 = new Node(220);
//
//        node_2.join(null);
//        node_138.join(node_2);
//        node_9.join(node_138);
//        node_67.join(node_2);
//        node_220.join(node_138);
//
//        for(int i = 0 ; i<250;i++){
//            node_2.insert(i,"Str"+i);
//        }
//
//        Node node_169 = new Node(169);
//
//        node_169.join(node_67);
//
//        List<Integer> traces = node_9.find(145);
//
//        System.out.println("-----Node002-----");
//        node_2.getFingerTable().prettyPrint();
//        System.out.println(node_2.getLocalKeys());
//        System.out.println("-----Node009-----");
//        node_9.getFingerTable().prettyPrint();
//        System.out.println(node_9.getLocalKeys());
//        System.out.println("-----Node067-----");
//        node_67.getFingerTable().prettyPrint();
//        System.out.println(node_67.getLocalKeys());
//        System.out.println("-----Node138-----");
//        node_138.getFingerTable().prettyPrint();
//        System.out.println(node_138.getLocalKeys());
//        System.out.println("-----Node169-----");
//        node_169.getFingerTable().prettyPrint();
//        System.out.println(node_169.getLocalKeys());
//        System.out.println("-----Node220-----");
//        node_220.getFingerTable().prettyPrint();
//        System.out.println(node_220.getLocalKeys());
//
//        System.out.println("-----Find Key 145 via Node 9-----");
//        System.out.println(traces);
//
//        node_67.leave();
//
//        System.out.println("Status of all nodes after Node 67 left:");
//        System.out.println("-----Node002-----");
//        node_2.getFingerTable().prettyPrint();
//        System.out.println(node_2.getLocalKeys());
//        System.out.println("-----Node009-----");
//        node_9.getFingerTable().prettyPrint();
//        System.out.println(node_9.getLocalKeys());
//        System.out.println("-----Node138-----");
//        node_138.getFingerTable().prettyPrint();
//        System.out.println(node_138.getLocalKeys());
//        System.out.println("-----Node169-----");
//        node_169.getFingerTable().prettyPrint();
//        System.out.println(node_169.getLocalKeys());
//        System.out.println("-----Node220-----");
//        node_220.getFingerTable().prettyPrint();
//        System.out.println(node_220.getLocalKeys());





    }
}
