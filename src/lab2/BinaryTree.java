package lab2;

public class BinaryTree<T extends Comparable<T>> {
	
	private Node<T> root; // comment
	
	public static class Node<T extends Comparable<T>> {
        T value;
        Node<T> left;
        Node<T> right;

        Node(T value) {
            this.value = value;
            left = null;
            right = null;
        }
    }
	public BinaryTree() {
        this.root = null;
    }

    public void insert(T value) {
        root = insertRec(root, value);
    }

    private Node<T> insertRec(Node<T> root, T value) {
        if (root == null) {
            root = new Node<>(value);
            return root;
        }

        if (value.hashCode() < root.value.hashCode()) {
            root.left = insertRec(root.left, value);
        } else if (value.hashCode() > root.value.hashCode()) {
            root.right = insertRec(root.right, value);
        }

        return root;
    }
    public boolean contains(T value) {
        return containsRec(root, value);
    }

    private boolean containsRec(Node<T> root, T value) {
        if (root == null) {
            return false;
        }

        if (value.compareTo(root.value) < 0) {
            return containsRec(root.left, value);
        } else if (value.compareTo(root.value) > 0) {
            return containsRec(root.right, value);
        } else {
            return true;
        }
    }

    public void delete(T value) {
    	if (contains(value)) {
            root = deleteRec(root, value);
        } else {
            System.err.println("Value " + value + " does not exist in the tree.");
        }
    }

    private Node<T> deleteRec(Node<T> root, T value) {
        if (root == null) {
            return null;
        }

        if (value.compareTo(root.value) < 0) {
            root.left = deleteRec(root.left, value);
        } else if (value.compareTo(root.value) > 0) {
            root.right = deleteRec(root.right, value);
        } else {
            // node with no leaf nodes
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left == null) {
                // node with one node (no left node)
                return root.right;
            } else if (root.right == null) {
                // node with one node (no right node)
                return root.left;
            } else {
                // nodes with two nodes
                // search for min number in right sub tree
                T minValue = findMin(root.right);
                root.value = minValue;
                root.right = deleteRec(root.right, minValue);
            }
        }

        return root;
    }

    private T findMin(Node<T> root) {
        if (root.left != null) {
            return findMin(root.left);
        }
        return root.value;
    }

    public void inorderTraversal() {
        inorderRec(root);
        System.out.println(); // Line break after traversal
    }

    private void inorderRec(Node<T> root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.value + " ");
            inorderRec(root.right);
        }
    }
    public void preorderTraversal() {
        preorderRec(root);
        System.out.println(); // Line break after traversal
    }

    private void preorderRec(Node<T> root) {
        if (root != null) {
            System.out.print(root.value + " ");
            preorderRec(root.left);
            preorderRec(root.right);
        }
    }

    public void postorderTraversal() {
        postorderRec(root);
        System.out.println(); // Line break after traversal
    }

    private void postorderRec(Node<T> root) {
        if (root != null) {
            postorderRec(root.left);
            postorderRec(root.right);
            System.out.print(root.value + " ");
        }
    }
    public int size() {
        return size(root);
    }

    private int size(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return 1 + size(node.left) + size(node.right);
    }
    public int height() {
        return heightRec(root);
    }

    private int heightRec(Node<T> root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = heightRec(root.left);
            int rightHeight = heightRec(root.right);

            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

	public static void main(String[] args) {
		 	BinaryTree<Integer> bt = new BinaryTree<>();
		 
	        bt.insert(50);
	        bt.insert(30);
	        bt.insert(20);
	        bt.insert(40);
	        bt.insert(70);
	        bt.insert(60);
	        bt.insert(80);
	        
	        BinaryTree<String> tree = new BinaryTree<>();
	        tree.insert("Left");
	        tree.insert("Root");
	        tree.insert("Right");
	        
	        System.out.println("InOrder traversal of the Integer tree:");
	        bt.inorderTraversal(); 
	        System.out.println("PreOrder traversal of the Integer tree:");
	        bt.preorderTraversal();
	        System.out.println("PostOrder traversal of the Integer tree:");
	        bt.postorderTraversal();
	        
	        System.out.println("Size: "+bt.size());

	        System.out.println("\nDelete 20");
	        bt.delete(20);
	        System.out.println("Inorder traversal of the modified tree");
	        bt.inorderTraversal();

	        System.out.println("\nCheck if tree contains 40");
	        System.out.println(bt.contains(40)); // Should print: true

	        System.out.println("Check if tree contains 100");
	        System.out.println(bt.contains(100)); // Should print: false
	        
	        System.out.println("Size: "+bt.size());
	        
	        
	        
	        System.out.println("\nTree of Strings: ");
	        tree.inorderTraversal();
	        System.out.println("Size: "+tree.size());
	}
}
