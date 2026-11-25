package lw06.unguided;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    Scanner sc = new Scanner(Main.class.getResourceAsStream("treenumbers.txt"));

    BinarySearchTree bst = new BinarySearchTree();
    Node root = null;
    String[] numbers = sc.nextLine().split(" ");

        for (String number : numbers) {
            bst.insert(Integer.parseInt(number));
    }

    int target = sc.nextInt();

    System.out.print("Preorder  : ");
    bst.preOrder();
   
    System.out.print("Inorder   : ");
    bst.inOrder();
  
    System.out.print("Postorder : ");
    bst.postOrder();

    System.out.println("Search " + target + " : " + bst.search(target));
    System.out.println("Nodes : " + bst.countNodes());
    System.out.println("Leaves : " + bst.countLeaves());

    sc.close();
    }
}

