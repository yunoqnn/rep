package aa;
import java.util.Scanner;
public class Expression extends LinkedBinaryTree{
	public Expression() {
        super();
    }

    public Expression(Object root) {
        super();
        this.setRoot(new BinaryTreeNode(root));
    }
    
    public void printInfixExpression() {
        System.out.print("Infix Expression: ");
        printInfixExpression(getRoot());
        System.out.println();
    }

    public void printPrefixExpression() {
        System.out.print("Prefix Expression: ");
        preOrderOutput();
        System.out.println();
    }

    public void printPostfixExpression() {
        System.out.print("Postfix Expression: ");
        postOrderOutput();
        System.out.println();
    }

    public void buildExpressionTreeFromPrefix(String prefixExpression) {
        if (prefixExpression == null || prefixExpression.isEmpty()) {
            System.out.println("Invalid prefix expression");
            return;
        }
        char[] tokens = prefixExpression.toCharArray();
        int[] index = {0}; // start from prefix
        setRoot(buildExpressionTreeFromPrefixHelper(tokens, index));
    }

    private BinaryTreeNode buildExpressionTreeFromPrefixHelper(char[] tokens, int[] index) {
        if (index[0] >= tokens.length) {
            return null;
        }
        char token = tokens[index[0]++];
        if (Character.isDigit(token) || Character.isLetter(token)) {
            // create a node
            return new BinaryTreeNode(token);
        } else if (isOperator(token)) {
            BinaryTreeNode leftSubtree = buildExpressionTreeFromPrefixHelper(tokens, index);
            BinaryTreeNode rightSubtree = buildExpressionTreeFromPrefixHelper(tokens, index);
            return new BinaryTreeNode(token, leftSubtree, rightSubtree);
        } else {
            System.out.println("Invalid character in prefix expression: " + token);
            return null;
        }
    }

    public void buildExpressionTreeFromPostfix(String postfixExpression) {
        if (postfixExpression == null || postfixExpression.isEmpty()) {
            System.out.println("Invalid postfix expression");
            return;
        }
        char[] tokens = postfixExpression.toCharArray();
        int[] index = {tokens.length - 1}; // start from end of postfix expression
        setRoot(buildExpressionTreeFromPostfixHelper(tokens, index));
    }

    private BinaryTreeNode buildExpressionTreeFromPostfixHelper(char[] tokens, int[] index) {
        if (index[0] < 0) {
            return null;
        }
        char token = tokens[index[0]--];
        if (Character.isDigit(token) || Character.isLetter(token)) {
            return new BinaryTreeNode(token);
        } else if (isOperator(token)) {
            BinaryTreeNode rightSubtree = buildExpressionTreeFromPostfixHelper(tokens, index);
            BinaryTreeNode leftSubtree = buildExpressionTreeFromPostfixHelper(tokens, index);
            return new BinaryTreeNode(token, leftSubtree, rightSubtree);
        } else {
            System.out.println("Invalid character in postfix expression: " + token);
            return null;
        }
    }
    public void buildExpressionTreeFromInfix(String infixExpression) {
        if (infixExpression == null || infixExpression.isEmpty()) {
            System.out.println("Invalid infix expression");
            return;
        }
        char[] tokens = infixExpression.toCharArray();
        int[] index = {0}; // Start from infix expression beginning

        setRoot(buildExpressionTreeFromInfixHelper(tokens, index, 0));
    }

    private BinaryTreeNode buildExpressionTreeFromInfixHelper(char[] tokens, int[] index, int precedence) {
        if (index[0] >= tokens.length) {
            return null;
        }
        BinaryTreeNode leftSubtree = null;
        char token = tokens[index[0]];
        if (Character.isDigit(token) || Character.isLetter(token)) {
            leftSubtree = new BinaryTreeNode(token);
            index[0]++;
        } else if (token == '(') {
            index[0]++;
            leftSubtree = buildExpressionTreeFromInfixHelper(tokens, index, 0);
            index[0]++; 
        }

        while (index[0] < tokens.length) {
            token = tokens[index[0]];
            int tokenPrecedence = getPrecedence(token);
            if (tokenPrecedence <= precedence) {
                break;
            }
            index[0]++;
            BinaryTreeNode rightSubtree = buildExpressionTreeFromInfixHelper(tokens, index, tokenPrecedence);
            leftSubtree = new BinaryTreeNode(token, leftSubtree, rightSubtree);
        }

        return leftSubtree;
    }

    private int getPrecedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0; // invalid operator
        }
    }
    
    public int evaluateExpressionTree(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.leftChild == null && root.rightChild == null) {
            return Character.getNumericValue((char) root.element);
        }
        int leftValue = evaluateExpressionTree(root.leftChild);
        int rightValue = evaluateExpressionTree(root.rightChild);
        switch ((char)root.getElement()) {
            case '+':
                return leftValue + rightValue;
            case '-':
                return leftValue - rightValue;
            case '*':
                return leftValue * rightValue;
            case '/':
                if (rightValue != 0) {
                    return leftValue / rightValue;
                } else {
                    throw new ArithmeticException("Division by zero");
                }
            default:
                throw new IllegalArgumentException("Invalid operator: " + root.element);
        }
    }
// added comment
    public static void expressionMenu() {
		Scanner sc = new Scanner(System.in);
		Expression tree = new Expression();
		do {
			System.out.println("		MENU");
			System.out.println("1. Insert expression");
			System.out.println("2. Print expression by infix order");
			System.out.println("3. Print expression by prefix order");
			System.out.println("4. Print expression by postfix order");
			System.out.println("5. Calculate expression");
			int choice = sc.nextInt();
			switch(choice) {
			case 1:
				System.out.println("1. By Infix");
				System.out.println("2. By Prefix");
				System.out.println("3. By Postfix");
				System.out.println("4. Back to menu");
				int choice2 = sc.nextInt();
				switch(choice2) {
				case 1: 
					System.out.println("Enter infix expression");
					String in = sc.next();
			        tree.buildExpressionTreeFromInfix(in);
					break;
				case 2: 
					System.out.println("Enter prefix expression");
					String pre = sc.next();
					tree.buildExpressionTreeFromPrefix(pre);
					break;
				case 3: 
					System.out.println("Enter postfix expression");
					String post = sc.next();
					tree.buildExpressionTreeFromPostfix(post);
					break;
				case 4:
					expressionMenu();
					break;
				default:
					System.out.println("Wrong input. Choose from the menu!!");
					break;
				}
				break;
			case 2:
				tree.printInfixExpression();
				break;
			case 3:
				tree.printPrefixExpression();
				break;
			case 4:
				tree.printPostfixExpression();
				break;
			case 5:
				System.out.println("The result of the expression: "+tree.evaluateExpressionTree(tree.getRoot()));
				break;
			default:
				System.out.println("Choose from menu numbers!!");
				break;
			}
		}
		while(true);
	}
    public static void main(String[] args) {
    	 expressionMenu(); //2:48
	}
}
