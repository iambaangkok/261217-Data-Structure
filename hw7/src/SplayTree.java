import java.util.LinkedList;
import java.util.Queue;

public class SplayTree extends BTreePrinter {
    Node root;

    public SplayTree(Node root) {
        this.root = root;
        root.parent = null; // Clear parent of the root (Important for SplayTree)
    }

    // zig() function will move up the node x one level
    // Case 1: x == root
    // Case 2-3: x.parent == root (sig from left, zig from right)
    // Case 4-5: x.parent != root (sig from left, zig from right)
    public void zig(Node x) {
        if (x == null) {
            System.out.println("Node x is null!!");
            return;
        }
        Node y = x.parent;
        if (y == null) {
            System.out.println("Cannot perform Zig operation on the root node");
        } else if (y == root) { // If the node is a child of the root
            if (x.key < y.key) {// Zig from left
                if (x.right != null) { // transfer x's right child
                    y.left = x.right;
                    x.right.parent = y;
                } else {
                    y.left = null;
                }
                // swap x y
                y.parent = x;
                x.right = y;
                root = x;
                root.parent = null;
            } else { // Zig from right
                if (x.left != null) { // transfer x's left child
                    y.right = x.left;
                    x.left.parent = y;
                } else {
                    y.right = null;
                }
                // swap x y
                y.parent = x;
                x.left = y;
                root = x;
                root.parent = null;
            }
        } else { // node y have a parent : w
            Node w = y.parent;
            if (x.key < y.key) {// Zig from left
                if (x.right != null) { // transfer x's right child
                    y.left = x.right;
                    x.right.parent = y;
                } else {
                    y.left = null;
                }
                // swap x y
                x.right = y;
                y.parent = x;
            } else { // Zig from right
                if (x.left != null) { // transfer x's left child
                    y.right = x.left;
                    x.left.parent = y;
                } else {
                    y.right = null;
                }
                // swap x y
                y.parent = x;
                x.left = y;
            }
            // rewire grandparent w
            if (w.left == y) {
                w.left = x;
            } else if (w.right == y) {
                w.right = x;
            }
            x.parent = w;
        }

    }

    // zigzig() function will move up node x two levels along the outer path
    // Pls call zig() to perform zigzig()
    public void zigzig(Node x) { // zig parent then zig self
        zig(x.parent);
        zig(x);
    }

    // zigzag() function will move up node x two levels along the inner path
    // Pls call zig() to perform zigzag()
    public void zigzag(Node x) { // zig self twice
        zig(x);
        zig(x);
    }

    // This function will interatively splay (move up) node x all the way to the
    // root
    public void splay(Node x) {
        while (x != null && x != root) {
            Node y = x.parent;
            if (y == root) {
                zig(x);
                break;
            }else{
                Node w = y.parent;
                if (x.key < y.key && y.key < w.key) { // left outer
                    zigzig(x);
                } else if (x.key > y.key && y.key < w.key) { // left inner
                    zigzag(x);
                }else if (x.key < y.key && y.key > w.key) { // right inner
                    zigzag(x);
                } else if (x.key > y.key && y.key > w.key) { // right outer
                    zigzig(x);
                }else{
                    break;
                }
            }
        }
    }

    // Modify this function to have the splaying feature
    // This can be done by calling the splay() function
    public void insert(int key) {
        //System.out.print(key + " :");
        Node newNode = new Node(key);
        Node node = root;
        if (root == null) { // check if emptytree
            root = newNode;
            return;
        }
        while (node != null) {
            //System.out.print(" " + node.key);
            // insert
            if (key == node.key) { // don't insert
                break;
            } else if (key < node.key) {
                //System.out.print(" left");
                if (node.left != null) {
                    node = node.left;
                } else {
                    node.left = newNode;
                    newNode.parent = node;
                    break;
                }
            } else {
                //System.out.print(" right");
                if (node.right != null) {
                    node = node.right;
                } else {
                    node.right = newNode;
                    newNode.parent = node;
                    break;
                }
            }
        }

        // splay here
        splay(newNode);
        //System.out.println("");
        return;
    }

    // Modify this function to have the splaying feature (if withSplay is true)
    // This can be done by calling the splay() function
    public Node find(int search_key, boolean withSplay) {
        Node currNode = root;
        while (currNode != null) {
            if (currNode.key == search_key) {
                // splay here, when we found the node
                if(withSplay){
                    splay(currNode);
                }
                return currNode;
            }
            if (currNode.key > search_key) { // if search_key is less than nodekey -> search in
                currNode = currNode.left; // find in left subtree
            } else if (currNode.key < search_key) {
                currNode = currNode.right;
            }
        }
        return null; 
    }

    // This delete() is different than what you learned in BSTree and AVLTree before
    // Use the algorithm learned in the class to implement this function
    public void delete(int key) {
        Node target = find(key, true);
        
        SplayTree tree1 = new SplayTree(root.left);
        SplayTree tree2 = new SplayTree(root.right);
        tree2.splay(tree2.findMin());
        
        tree2.root.left = tree1.root;
        tree1.root.parent = tree2.root;
        root = tree2.root; // set new root & unconnect targetNode
    }

    // This function does not have the splaying feature
    public Node findMin() {
        Node currNode = root;
        while (currNode != null) {
            if (currNode.left == null) { // node.left = null means current node is the left-most node -> min node
                return currNode;
            } else {
                currNode = currNode.left; // otherwise go evenmore left
            }
        }
        return null;
    }

    // This is another version of height() called iterative method to find BST
    // height
    // This function is complete, no need to edit
    @SuppressWarnings("unchecked")
    public int height() {
        if (root == null)
            return -1;
        Queue<Node> q = new LinkedList();
        q.add(root);
        int height = -1;
        while (true) {
            int nodeCount = q.size();
            if (nodeCount == 0)
                return height;
            height++;
            while (nodeCount > 0) {
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

    public SplayTree() {
    } // Dummy Constructor, no need to edit

    // This is the editable testcase (test#4)
    // No need to edit
    public static void test4() {
        BSTree2 tree1 = new BSTree2();
        long start = System.currentTimeMillis();
        int N = 40000;
        for (int i = 0; i < N; i++)
            tree1.insert(i);
        System.out.println("Time for sequentially inserting " + N + " objects into BST = "
                + (System.currentTimeMillis() - start) + " msec");
        start = System.currentTimeMillis();
        for (int i = 0; i < N; i++)
            tree1.find((int) (Math.random() * N));

        System.out.println("Time for finding " + N + " different objects in BST= "
                + (System.currentTimeMillis() - start) + " msec");
        SplayTree tree2 = new SplayTree();
        start = System.currentTimeMillis();
        for (int i = 0; i < N; i++)
            tree2.insert(i);

        System.out.println("Time for sequentially inserting " + N + " objects into SplayTree = "
                + (System.currentTimeMillis() - start) + " msec");
        start = System.currentTimeMillis();
        for (int i = 0; i < N; i++)
            tree2.find((int) (Math.random() * N), true);
        System.out.println("Time for finding " + N + " different objects in SplayTree = "
                + (System.currentTimeMillis() - start) + " msec");

        System.out.println("Which one is faster: BSTree or SplayTree?");
    }

}