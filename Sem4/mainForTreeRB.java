package Sem4;

public class mainForTreeRB {
    public static void main(String[] args) {
        TreeRedBlack<Integer> myTree = new TreeRedBlack<>();

        System.out.println(myTree);

        myTree.add(50);
        myTree.add(45);
        myTree.add(55);
        myTree.add(1);
        myTree.add(49);
        myTree.add(30);

        System.out.println(myTree);

        System.out.println("\nПоиск элемента:");
        System.out.println(myTree.find(45));
        System.out.println(myTree.find(50));
        System.out.println(myTree.find(1));
    }
}
