public class Main {

    public static void main(String[] args){

//        FingerTable ft = new FingerTable(6);
//        ft.prettyPrint();

//        Node nd = new Node(6);
//        nd.getFingerTable().prettyPrint();
//        nd.join(null);
//        nd.getFingerTable().prettyPrint();

        Node nd = new Node(1);
        nd.join(null);
        Node nd4 = new Node(4);
        Node nd8 = new Node(8);

        Node nd115 = new Node(115);


        nd8.join(nd);
        nd4.join(nd);
//        nd115.join(nd8);

        nd8.insert(2, "sss");

        nd4.insert(112,"fwfwew");

        nd.remove(112);





//        Node nd128 = new Node(128);
//        nd128.join(nd4);
//
//        Node nd64 = new Node(64);
//        nd64.join(nd128);

        nd.getFingerTable().prettyPrint();
        System.out.println("----------");
        nd4.getFingerTable().prettyPrint();
        System.out.println("----------");
        nd8.getFingerTable().prettyPrint();

        System.out.println(nd.getLocalKeys().toString());
        System.out.println(nd4.getLocalKeys().toString());
        System.out.println(nd8.getLocalKeys().toString());

//        System.out.println("----------");
//        nd115.getFingerTable().prettyPrint();

//        System.out.println("----------");
//        nd128.getFingerTable().prettyPrint();
//        System.out.println("----------");
//        nd64.getFingerTable().prettyPrint();


    }
}
