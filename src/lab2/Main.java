package lab2;
import java.util.Scanner;

public class Main {

static Scanner sc = new Scanner(System.in);
    
    BinaryTree<Integer> strTree = new BinaryTree<>();
    
    public static void menu_items() {
    		System.out.println(" 	INTEGER TREE ");
    		System.out.println("1. Insert element to tree");
    		System.out.println("2. Print traversals of tree");
    		System.out.println("3. Find size of tree");
    		System.out.println("4. Check if the tree contains entered element");
    		System.out.println("5. Delete ");
    }
    public static void Menu() {

    	System.out.println("		MENU");
    	System.out.println("1. Use the tree made of integers");
    	System.out.println("2. Use the tree made of strings");
    	
    	int choice = sc.nextInt();
    	switch(choice) {
    	case 1:
    		subMenu1();
    		break;
    	case 2:
    		int choice3 = sc.nextInt();
    		break;
    	default:
    		System.out.println("Choose number from the menu!");
    		break;
    	}
    		
    }
    public static void subMenu1() {
    	BinaryTree<Integer> intTree = new BinaryTree<>();
    	menu_items();
    	intTree.insert(1);
		int choice = sc.nextInt();
		switch(choice) {
		case 1:
			System.out.println("Enter the element to add to tree");
			int element = sc.nextInt();
			intTree.insert(element);
			System.out.println("Tree: ");
			intTree.inorderTraversal();
			break;
		case 2:
			System.out.println("InOrder traversal of the Integer tree:");
	        intTree.inorderTraversal(); 
	        System.out.println("PreOrder traversal of the Integer tree:");
	        intTree.preorderTraversal();
	        System.out.println("PostOrder traversal of the Integer tree:");
	        intTree.postorderTraversal();
		case 3:
			System.out.printf("size: ",+intTree.size());
		}
    }
    public static void main(String[] args) {
    	Menu();
    }

}
