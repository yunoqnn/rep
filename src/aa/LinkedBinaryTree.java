/** linked binary trees */

package aa;

import java.lang.reflect.*;

public class LinkedBinaryTree implements BinaryTree
{
   // instance data member
   private BinaryTreeNode root;  // root node

   // class data members
   static Method visit;      // visit method to use during a traversal
   static Object [] visitArgs = new Object [1];
                             // parameters of visit method
   static int count;         // counter
   static Class [] paramType = {BinaryTreeNode.class};
                             // type of parameter for visit
   static Method theAdd1;    // method to increment count by 1
   static Method theOutput;  // method to output node element

   // method to initialize class data members
   static
   { 
      try
      {
         Class lbt = LinkedBinaryTree.class;
         theAdd1 = lbt.getMethod("add1", paramType);
         theOutput = lbt.getMethod("output", paramType);
      }
      catch (Exception e) {}
         // exception not possible
   }

   // only default constructor available

   // class methods
   /** visit method that outputs element */
   public static void output(BinaryTreeNode t)
      {System.out.print(t.element + " ");}
   
   /** visit method to count nodes */
   public static void add1(BinaryTreeNode t)
      {count++;}

   // instance methods
   /** @return true iff tree is empty */
   public boolean isEmpty()
      {return getRoot() == null;}


   /** @return root element if tree is not empty
     * @return null if tree is empty */
   public Object root()
      {return (getRoot() == null) ? null : getRoot().element;}

   /** set this to the tree with the given root and subtrees
     * CAUTION: does not clone left and right */
   public void makeTree(Object root, Object left, Object right)
   {
      this.setRoot(new BinaryTreeNode(root,
                      ((LinkedBinaryTree) left).getRoot(),
                      ((LinkedBinaryTree) right).getRoot()));
   }

   /** remove the left subtree
     * @throws IllegalArgumentException when tree is empty
     * @return removed subtree */
   public BinaryTree removeLeftSubtree()
   {
      if (getRoot() == null)
         throw new IllegalArgumentException("tree is empty");

      // detach left subtree and save in leftSubtree
      LinkedBinaryTree leftSubtree = new LinkedBinaryTree();
      leftSubtree.setRoot(getRoot().leftChild);
      getRoot().leftChild = null;

      return (BinaryTree) leftSubtree;
   }

   /** remove the right subtree
     * @throws IllegalArgumentException when tree is empty
     * @return removed subtree */
   public BinaryTree removeRightSubtree()
   {
      if (getRoot() == null)
         throw new IllegalArgumentException("tree is empty");

      // detach right subtree and save in rightSubtree
      LinkedBinaryTree rightSubtree = new LinkedBinaryTree();
      rightSubtree.setRoot(getRoot().rightChild);
      getRoot().rightChild = null;

      return (BinaryTree) rightSubtree;
   }

   /** preorder traversal */
   public void preOrder(Method visit)
   {
      this.visit = visit;
      thePreOrder(getRoot());
   }

   /** actual preorder traversal method */
   static void thePreOrder(BinaryTreeNode t)
   {
      if (t != null)
      {
         visitArgs[0] = t;
         try {visit.invoke(null, visitArgs);}  // visit tree root
         catch (Exception e)
            {System.out.println(e);}
         thePreOrder(t.leftChild);             // do left subtree
         thePreOrder(t.rightChild);            // do right subtree
      }
   }

   /** inorder traversal */
   public void inOrder(Method visit)
   {
      this.visit = visit;
      theInOrder(getRoot());
   }

   /** actual inorder traversal method */
   static void theInOrder(BinaryTreeNode t)
   {
      if (t != null)
      {
         theInOrder(t.leftChild);              // do left subtree
         visitArgs[0] = t;
         try {visit.invoke(null, visitArgs);}  // visit tree root
         catch (Exception e)
            {System.out.println(e);}
         theInOrder(t.rightChild);             // do right subtree
      }
   }


   /** postorder traversal */
   public void postOrder(Method visit)
   {
      this.visit = visit;
      thePostOrder(getRoot());
   }

   /** actual postorder traversal method */
   static void thePostOrder(BinaryTreeNode t)
   {
      if (t != null)
      {
         thePostOrder(t.leftChild);            // do left subtree
         thePostOrder(t.rightChild);           // do right subtree
         visitArgs[0] = t;
         try {visit.invoke(null, visitArgs);}  // visit tree root
         catch (Exception e)
            {System.out.println(e);}
      }
   }

   /** level order traversal */
   public void levelOrder(Method visit)
   {
      ArrayQueue q = new ArrayQueue();
      BinaryTreeNode t = getRoot();
      while (t != null) 
      {
         visitArgs[0] = t;
         try {visit.invoke(null, visitArgs);}  // visit tree root
         catch (Exception e)
            {System.out.println(e);}
   
         // put t's children on queue
         if (t.leftChild != null) 
            q.put(t.leftChild);
         if (t.rightChild != null)
            q.put(t.rightChild);
   
         // get next node to visit
         t = (BinaryTreeNode) q.remove();
      }
   }
     
   /** output elements in preorder */
   public void preOrderOutput()
      {preOrder(theOutput);}
  
   /** output elements in inorder */
   public void inOrderOutput()
      {inOrder(theOutput);}
  
   /** output elements in postorder */
   public void postOrderOutput()
      {postOrder(theOutput);}
  
   /** output elements in level order */
   public void levelOrderOutput()
      {levelOrder(theOutput);}

   /** count number of nodes in tree */
   public int size()
   {
      count = 0;
      preOrder(theAdd1);
      return count;
   }

   /** @return tree height */
   public int height()
      {return theHeight(getRoot());}

   /** @return height of subtree rooted at t */
   static int theHeight(BinaryTreeNode t)
   {
      if (t == null) return 0;
      int hl = theHeight(t.leftChild);  // height of left subtree
      int hr = theHeight(t.rightChild); // height of right subtree
      if (hl > hr) return ++hl;
      else return ++hr;
   }
   
   public boolean isOperator(char c) {
       return c == '+' || c == '-' || c == '*' || c == '/';
   }
   
   public void printInfixExpression(BinaryTreeNode node) {
       if (node != null) {
           if (isOperator((char) node.element)) {
               System.out.print("(");
           }

           printInfixExpression(node.leftChild);
           System.out.print(node.element);

           printInfixExpression(node.rightChild);

           if (isOperator((char) node.element)) {
               System.out.print(")");
           }
       }
   }
  /* public void printInfixExpression(BinaryTreeNode node) {
       if (node != null) {
           boolean needParentheses = isOperator((char) node.element) &&
                   (node.leftChild != null || node.rightChild != null);

           if (needParentheses) {
               System.out.print("(");
           }

           printInfixExpression(node.leftChild);
           System.out.print(node.element);

           printInfixExpression(node.rightChild);

           if (needParentheses) {
               System.out.print(")");
           }
       }
   } */
   

   public BinaryTreeNode getRoot() {
	return root;
}

public void setRoot(BinaryTreeNode root) {
	this.root = root;
}

/** test program */
   public static void main(String [] args)
   {
      LinkedBinaryTree a = new LinkedBinaryTree(),
                       x = new LinkedBinaryTree(), 
                       y = new LinkedBinaryTree(), 
                       z = new LinkedBinaryTree();
      y.makeTree(new Integer(1), a, a);
      z.makeTree(new Integer(2), a, a);
      x.makeTree(new Integer(3), y, z);
      y.makeTree(new Integer(4), x, a);

      System.out.println("Preorder sequence is ");
      y.preOrderOutput();
      System.out.println();

      System.out.println("Inorder sequence is ");
      y.inOrderOutput();
      System.out.println();
      

      System.out.println("Postorder sequence is ");
      y.postOrderOutput();
      System.out.println();

      System.out.println("Level order sequence is ");
      y.levelOrderOutput();
      System.out.println();

      System.out.println("Number of nodes = " + y.size());

      System.out.println("Height = " + y.height());
   }
}
