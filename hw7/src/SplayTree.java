import java.util.LinkedList;
import java.util.Queue;

public class SplayTree extends BTreePrinter{
    Node root;
    
    public SplayTree(Node root){
        this.root = root;
        root.parent = null; // Clear parent of the root (Important for SplayTree)
    }
    
    // zig() function will move up the node x one level
    // Case 1: x == root
    // Case 2-3: x.parent == root (sig from left, zig from right)
    // Case 4-5: x.parent != root (sig from left, zig from right)
    public void zig(Node x){
        Node y = x.parent;
        if (y == null){
            System.out.println("Cannot perform Zig operation on the root node");
        }else if (y == root){ // If the node is a child of the root
            if (x.key < y.key){// Zig from left
            
            }   // Complete the rest of the cases yourself
        }
    }
    
    // zigzig() function will move up node x two levels along the outer path
    // Pls call zig() to perform zigzig()
    public void zigzig(Node x){
        // Do something    
    }
    
    
    // zigzag() function will move up node x two levels along the inner path
    // Pls call zig() to perform zigzag()
    public void zigzag(Node x){
        // Do something 
    }
    
    // This function will interatively splay (move up) node x all the way to the root
    public void splay(Node x){
        while (x !=null && x != root){
            Node y = x.parent;
            if (y == root){
                // Do something and break
                break;
            }
        }   // Complete the rest of the cases yourself
    }
    
    // Modify this function to have the splaying feature
    // This can be done by calling the splay() function
    public void insert(int key) {
        // Pls copy code from the previous problem
        // and add the splaying feature somewhere
    }
    
    // Modify this function to have the splaying feature (if withSplay is true)
    // This can be done by calling the splay() function
    public Node find(int search_key, boolean withSplay){
        // Pls copy code from the previous problem
        // and add the splaying feature somewhere (do not forget the withSplay check)
        return null; // Fix this
    }
    
    // This delete() is different than what you learned in BSTree and AVLTree before
    // Use the algorithm learned in the class to implement this function
    public void delete(int key) {

    }
    
    // This function does not have the splaying feature
    public Node findMin() {
        // Pls copy code from the previous problem
        // Do not add the splaying feature in this function
        return null; // Fix this
    }
    
    // This is another version of height() called iterative method to find BST height
    // This function is complete, no need to edit
    @SuppressWarnings("unchecked")
    public int height(){
        if (root==null)
            return -1;
        Queue<Node> q = new LinkedList();
        q.add(root);
        int height = -1;
        while (true){
            int nodeCount = q.size();
            if (nodeCount == 0)
                return height;
            height++;
            while (nodeCount > 0){
                Node newnode = q.remove();
                if (newnode.left != null)
                    q.add(newnode.left);
                if (newnode.right != null)
                    q.add(newnode.right);
                nodeCount--;
            }
        }
    }
    
    // This function is complete, no need to edit
    public void printTree() {
        if (root == null) {
            System.out.println("Empty tree!!!");
        } else {
            super.printTree(root);
        }
    }
    
    public SplayTree() {} // Dummy Constructor, no need to edit
    
    // This is the editable testcase (test#4)
    // No need to edit
    public static void test4(){
        BSTree2 tree1 = new BSTree2();
        long start = System.currentTimeMillis();
        int N = 40000;
        for (int i = 0; i < N; i++)
            tree1.insert(i);
        System.out.println("Time for sequentially inserting " + N + " objects into BST = " + (System.currentTimeMillis() - start) + " msec");
        start = System.currentTimeMillis();
        for (int i = 0; i < N; i++)
            tree1.find((int) (Math.random() * N ));

        System.out.println("Time for finding " + N + " different objects in BST= " + (System.currentTimeMillis() - start) + " msec");
        SplayTree tree2 = new SplayTree();
        start = System.currentTimeMillis();
        for (int i = 0; i < N; i++)
            tree2.insert(i);

        System.out.println("Time for sequentially inserting " + N + " objects into SplayTree = " + (System.currentTimeMillis() - start) + " msec");
        start = System.currentTimeMillis();
        for (int i = 0; i < N; i++)
            tree2.find((int)(Math.random() * N ), true);
        System.out.println("Time for finding " + N + " different objects in SplayTree = " + (System.currentTimeMillis() - start) + " msec");
        
        System.out.println("Which one is faster: BSTree or SplayTree?");
    }
    
}