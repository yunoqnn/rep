import java.util.Scanner;

public class Menu {

static Scanner sc = new Scanner(System.in);
    
    BinaryTree<Integer> strTree = new BinaryTree<>();
    
    public static void menu_items() {
    		System.out.println(" 	INTEGER TREE ");
    		System.out.println("1. Insert element to tree");
    		System.out.println("2. Print traversals of tree");
    		System.out.println("3. Find size of tree");
    		System.out.println("4. Check if the tree contains entered element");
    		System.out.println("5. Delete ");
			System.out.println("6. Return back to main menu");
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
    		subMenu2();
    		break;
    	default:
    		System.out.println("Choose number from the menu!");
    		break;
    	}
    		
    }
    public static void subMenu1() {
    	BinaryTree<Integer> intTree = new BinaryTree<>();
    	intTree.insert(1); 
    	intTree.insert(2);
    	intTree.insert(3);
    	intTree.insert(4);
    	intTree.insert(5);
    	intTree.insert(6);
    	intTree.insert(7);
    	intTree.insert(8);
    	intTree.insert(9);
    	System.out.println("Given tree: ");
    	intTree.inorderTraversal();
		
		do {
		menu_items();
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
	        System.out.println("\nPostOrder traversal of the Integer tree:");
	        intTree.postorderTraversal();
			System.out.println("\nPreOrder traversal of the Integer tree:");
	        intTree.preorderTraversal();
	        break;
		case 3:
			System.out.printf("size: ",+intTree.size());
			break;
		case 4:
			System.out.println("Enter element to check:");
			int element2 = sc.nextInt();
			System.out.println(intTree.contains(element2));
			break;
		case 5:
			System.out.println("Enter element to delete");
			int element3 = sc.nextInt();
			intTree.delete(element3);
			System.out.println("Tree: ");
			intTree.inorderTraversal();
			break;
		case 6:
			Menu();
			break;
        default:
            System.out.println("Wrong input! Select from the menu.");
		} } while(true);
    }
	public static void subMenu2() {
    	BinaryTree<String> strTree = new BinaryTree<>();
    	strTree.insert("Apple"); 
    	strTree.insert("Banana");
    	strTree.insert("Strawberry");
    	strTree.insert("Pineapple");
    	strTree.insert("Watermelon");
    	
    	System.out.println("Given tree: ");
    	strTree.inorderTraversal();
		
		do {
		menu_items();
        int choice = sc.nextInt();
		switch(choice) {
		case 1:
			System.out.println("Enter the element to add to tree");
			String element = sc.nextLine();
			strTree.insert(element);
			System.out.println("Tree: ");
			strTree.inorderTraversal();
			break;
		case 2:
			System.out.println("InOrder traversal of the Integer tree:");
	        strTree.inorderTraversal(); 
	        System.out.println("\nPostOrder traversal of the Integer tree:");
	        strTree.postorderTraversal();
			System.out.println("\nPreOrder traversal of the Integer tree:");
	        strTree.preorderTraversal();
	        break;
		case 3:
			System.out.printf("size: ",+strTree.size());
			break;
		case 4:
			System.out.println("Enter element to check:");
			String element2 = sc.nextLine();
			System.out.println(strTree.contains(element2));
			break;
		case 5:
			System.out.println("Enter element to delete");
			String element3 = sc.nextLine();
			strTree.delete(element3);
			System.out.println("Tree: ");
			strTree.inorderTraversal();
			break;
		case 6:
			Menu();
			break;
        default:
            System.out.println("Wrong input! Select from the menu.");
		} } while(true);
    }
    public static void main(String[] args) {
    	Menu();
    }

}
