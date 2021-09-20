public class AVLTree extends BTreePrinter{
    Node root;

    public void singleRotateFromLeft(Node y) {
        if(y == null){
            System.out.println("y is null");
            return;
        }
        if(y.left != null){ // check X null
            Node x = y.left;
            Node w = y;
            if(y.parent != null){
                w = y.parent;
            }
            if(x.right != null){
                y.left = x.right; // transfer inner brach
                y.left.parent = y;
            }else{
                y.left = null;
            }
            x.right = y; // rotation step 1
            y.parent = x; // rotation step 2
    
            if(w != null){
                // link grand parent
                if(w.left == y){
                    w.left = x; 
                }else if(w.right == y){
                    w.right = x;
                }
                x.parent = w;
            }
            if(y == root){ // y == root case
                x.parent = null;
                root = x;
            }
            // System.out.println("" + y.key +" " + y.parent.key + " " + x.key + " " + x.parent.key + " " + w.key);
        }
    }

    public void singleRotateFromRight(Node y) {
        if(y == null){
            System.out.println("y is null");
            return;
        }
        if(y.right != null){ // check X null
            Node x = y.right;
            Node w = y;
            if(y.parent != null){
                w = y.parent;
            }
            if(x.left != null){
                y.right = x.left; // transfer inner brach
                y.right.parent = y;
            }else{
                y.right = null;
            }
            x.left = y; // rotation step 1
            y.parent = x; // rotation step 2
            if(w != null){
                // link grand parent
                if(w.left == y){
                    w.left = x; 
                }else if(w.right == y){
                    w.right = x;
                }
                x.parent = w;
            }
            if(y == root){ // y == root case
                x.parent = null;
                root = x;
            }
        }
    }
    
    public void doubleRotateFromLeft(Node y) {
        if(y == null){
            System.out.println("y is null");
            return;
        }
        if(y.left != null){ // check x null
            Node x = y.left;
            Node z = y;
            Node w = y;
            if(y.parent != null){
                w = y.parent;
            }
            if(x.right != null){ // check z null
                z = x.right;

                // step one: swap x z
                if(z.left != null){ // assign outer node
                    x.right = z.left;
                    x.right.parent = x;
                }else{
                    x.right = null;
                }
                //swap x z
                y.left = z;
                z.parent = y;
                z.left = x;
                x.parent = z;

                // step two: swap y z
                if(z.right != null){ // assign outer node
                    y.left = z.right;
                    y.left.parent = y;
                }else{
                    y.left = null;
                }
                //swap y z
                if(w != null && w != y){
                    if(w.left == y){
                        w.left = z;
                    }else if(w.right == y){
                        w.right = z;
                    }
                }
                z.parent = w;
                z.right = y;
                y.parent = z;

                if(root == y){ // y == root case
                    z.parent = null;
                    root = z;
                }
            }
        }
    }

    public void doubleRotateFromRight(Node y) {
        if(y == null){
            System.out.println("y is null");
            return;
        }
        if(y.right != null){ // check x null
            Node x = y.right;
            Node z = y;
            Node w = y;
            if(y.parent != null){
                w = y.parent;
            }
            if(x.left != null){ // check z null
                z = x.left;

                // step one: swap x z
                if(z.right != null){ // assign outer node
                    x.left = z.right;
                    x.left.parent = x;
                }else{
                    x.left = null;
                }
                //swap x z
                y.right = z;
                z.parent = y;
                z.right = x;
                x.parent = z;

                // step two: swap y z
                if(z.left != null){ // assign outer node
                    y.right = z.left;
                    y.right.parent = y;
                }else{
                    y.right = null;
                }
                //swap y z
                if(w != null && w != y){
                    if(w.left == y){
                        w.left = z;
                    }else if(w.right == y){
                        w.right = z;
                    }
                }
                z.parent = w;
                z.left = y;
                y.parent = z;

                if(root == y){ // y == root case
                    z.parent = null;
                    root = z;
                }
            }
        }
    }
    

    public static void rebalance(AVLTree tree, Node node){ // rebalance Node "node" in AVLTree "tree"
        if(tree.root == null){
            System.out.println("Empty Tree!");
            return;
        }
        if(node == null){
            System.out.println("Null Node!");
            return;
        }
        int balanceFactor = 0;              // Calculate balanceFactor
        int hleft = height(node.left);
        int hright = height(node.right);
        balanceFactor = hleft-hright;

        if (Math.abs(balanceFactor) > 1){                          // Use balanceFactor to check if unbalanced?
            if (balanceFactor > 0 && node.left != null){                      // Use balanceFactor to check if left heavy?
                if (height(node.left.left) > height(node.left.right)){                  // Use the grandchild to check if Outer or Inner?
                    System.out.println("Perform SingleRotationFromLeft(Node " + node.key +")");   // left-left (Outer): SingleRotationFromLeft
                    tree.singleRotateFromLeft(node);
                }else{
                    System.out.println("Perform DoubleRotationFromLeft(Node " + node.key +")");   // left-right (Inner): DoubleRotationFromLeft
                    tree.doubleRotateFromLeft(node);
                }
            }else if(node.right != null){
                if (height(node.right.left) < height(node.right.right)){                  // Use the grandchild to check if Outer or Inner?
                    System.out.println("Perform SingleRotationFromRight(Node " + node.key +")");   // right-left (Inner): SingleRotationFromRight
                    tree.singleRotateFromRight(node);
                }else{
                    System.out.println("Perform DoubleRotationFromRight(Node " + node.key +")");   // right-right (Outer): DoubleRotationFromRight
                    tree.doubleRotateFromRight(node);
                }
            }
        }
    }
    
    // This function is complete, no need to edit
    public void insert(int key) {
        if (root == null) {
            root = new Node(key);
        } else {
            insert(this, root, key);
        }
    }
    
    // Fix this function to have the rebalancing feature
    // There should be rebalance() function calling somewhere in the code
    public static void insert(AVLTree tree, Node node, int key) {
        if (key == node.key) {
            System.out.println("Duplicated key:" + key);
        }else if (key < node.key) {//Go left
            if (node.left == null) {
                node.left = new Node(key);
                node.left.parent = node;
            }else {
                insert(tree, node.left, key);
            }
        }else{  // Go right
            if (node.right == null) {
                node.right = new Node(key);
                node.right.parent = node;
            }else {
                insert(tree, node.right, key);
            }
        }

        // During callback of recursive, check for unbalanced node in the path up until root

        int balanceFactor = 0;              // Calculate balanceFactor
        int hleft = height(node.left);
        int hright = height(node.right);
        
        balanceFactor = hleft-hright;
        // if(node != null){
        //     System.out.println("At node " + node.key + " " + hleft + " " + hright);
        // }
        if (Math.abs(balanceFactor) > 1){  
            rebalance(tree, node);
        }
    }
    

    // This function is for deleting the root node
    // If the node is not the root, please call the recursive version
    public void delete(int key) {
        // Pls copy the code from the previous homework
        // There should be 6 cases here
        // Non-root nodes should be forwarded to the static function
        Node findResult = find(key);
        
        if(root == null){ // nothing to delete -> error
            System.out.println("Empty Tree!!!");
        }else if(findResult == null){ // C5 key not found
            System.out.println("Key not found!!!");
        }else if(findResult == root){
            if(root.left == null && root.right == null){ // C1 root have no child -> just delete root
                root = null;
            }else if(root.right == null && root.left != null){ // C2 only left child -> promote left child
                root.left.parent = null;
                root = root.left;
            }else if(root.left == null && root.right != null){ // C3 only right child -> promote right child
                root.right.parent = null;
                root = root.right;
            }else if(root.left != null && root.right != null){ // C4 have both l r child -> promote min node of right subtree
                Node minRNode = findMin(root.right);
                root.key = minRNode.key; // just copy key, no need to re-wire
                delete(this,minRNode);
            }
        }else{ // C6 Otherwise delete non-root node
            delete(this,findResult);
        }
        return;
    }
    
    // Use this function to delete non-root nodes
    // Also, fix the code to have the rebalancing feature
    public static void delete(AVLTree tree, Node node){
        // Pls copy the code from the previous homework
        // There should be 7 cases here
        if(node.left == null && node.right == null && node.parent != null){ //C1,2 leaf node -> just delete it
            if(node.parent.left == node){ // C1 
                node.parent.left = null; 
            }else if(node.parent.right == node){ // C2
                node.parent.right = null; 
            }
        }else if(node.left != null && node.right == null && node.parent != null){ // C3,4 node with single left child -> promote left child
            if(node.parent.left == node){ // C3
                node.parent.left = node.left;
                node.left.parent = node.parent;
            }else if(node.parent.right == node){ // C4
                node.parent.right = node.left;
                node.left.parent = node.parent;
            }
        }else if(node.left == null && node.right != null && node.parent != null){ // C5,6 node with single right child -> promote right child
            if(node.parent.left == node){ // C5
                node.parent.left = node.right;
                node.right.parent = node.parent;
            }else if(node.parent.right == node){ // C6
                node.parent.right = node.right;
                node.right.parent = node.parent;
            }
        }else if(node.left != null && node.right != null){ // C7 have both l r child -> promote min node of right subtree
            Node minRNode = findMin(node.right);
            node.key = minRNode.key; // just copy key, no need to re-wire
            delete(tree,minRNode);
        }

        // Add code segments to enable the rebalancing feature
        // During callback of recursive, check for unbalanced node in the path up until root
        Node currNode = node.parent;
        while(currNode != null){
            int balanceFactor = 0;              // Calculate balanceFactor
            int hleft = height(currNode.left);
            int hright = height(currNode.right);
            
            balanceFactor = hleft-hright;
            // if(currNode != null){
            //     System.out.println("At node " + currNode.key + " " + hleft + " " + hright);
            // }
            if (Math.abs(balanceFactor) > 1){  
                rebalance(tree, currNode);
            }
            currNode = currNode.parent;
        }
        
    }
    
    public Node find(int search_key) {
        return find(root,search_key); //caller
    }
    
    public static Node find(Node node, int search_key) {
        if(node == null){
            return null;
        }
        if(node.key == search_key){
            return node;
        }
        if(node.left != null && node.key > search_key){ // if search_key is less than nodekey -> search in left subtree if it's not null
                return find(node.left,search_key);
        }else if(node.right != null && node.key < search_key){
                return find(node.right,search_key); // otherwise search in right subtree if it's not null
        }
        
        return null;
    }

    public static Node findMin(Node node){
        if(node == null){ // shouldn't happen unless root is null
            return null; 
        }
        if(node.left == null){ // node.left = null means current node is the left-most node -> min node
            return node;
        }else{
            return findMin(node.left); // otherwise go evenmore left
        }
    }

    public static Node findMax(Node node){
        if(node == null){ // shouldn't happen unless root is null
            return null; 
        }
        if(node.right == null){ // node.right = null means current node is the right-most node -> max node
            return node;
        }else{
            return findMax(node.right); // otherwise go evenmore right
        }
    }

    public Node findClosestLeaf(int search_key){
        return findClosestLeaf(root,search_key); //caller
    }
    
    public static Node findClosestLeaf(Node node, int search_key){
        // similar to find(Node,int)
        if(node == null){
            return null;
        }
        if(node.key == search_key){
            return node;
        }   
        if(node.key > search_key){ // if search_key is less than nodekey -> search in left subtree if it's not null
            if(node.left != null){
                return findClosestLeaf(node.left,search_key);
            }else{ // if it's null then current node is the closest
                return node;
            }
        }else{  // otherwise search in right subtree if it's not null
            if(node.right != null){
                return findClosestLeaf(node.right,search_key);
            }else{ // if it's null then current node is the closest
                return node;
            }
        }
    }

    public static boolean isMergeable(Node r1, Node r2){
        return (r1 == null || r2 == null) ||
        ((r1 != null && r2 != null) && (findMax(r1).key < findMin(r2).key)); // All nodes in T1 must be smaller than all nodes from T2
    }

    public static Node mergeWithRoot(Node r1, Node r2, Node t){
        //System.out.println("mwr");
        if (isMergeable(r1, r2) && t != null) {
            t.left = r1;
            if(r1 != null){
                r1.parent = t;
            }
            t.right = r2;
            if(r2 != null){
                r2.parent = t;
            }
            return t;
        } else {
            System.out.println("All nodes in T1 must be smaller than all nodes from T2");
            return null;
        }
    }

    public static Node AVLTreeMergeWithRoot(Node r1, Node r2, Node t){
        //System.out.println(r1.key + " " + r2.key + " " + t.key + " " + height(r1) + " " + height(r2));
        if (isMergeable(r1, r2)) {
            if(Math.abs(height(r1)-height(r2)) <= 1){
                //System.out.println("Just right");
                //t = findMax(r1);
                // tree.delete(t.key);
                // t.parent = null;
                mergeWithRoot(r1,r2,t);
                return t;
            }else if(height(r1) > height(r2)){
                //System.out.println("T1 heavier");
                if(r1.right != null){
                    Node rPrime = AVLTreeMergeWithRoot(r1.right, r2, t);
                    AVLTree tree = new AVLTree();
                    r1.right = rPrime;
                    if(rPrime!= null){
                        rPrime.parent = r1;
                    }
                    r1.parent = null;
                    tree.root = r1;
                    rebalance(tree,r1);
                    return tree.root;
                }else{
                    return null;
                }
            }else if(height(r1) < height(r2)){
                //System.out.println("T2 heavier");
                if(r2.left != null){
                    Node rPrime = AVLTreeMergeWithRoot(r1, r2.left, t);
                    AVLTree tree = new AVLTree();
                    r2.left = rPrime;
                    if(rPrime != null){
                        rPrime.parent = r2;
                    }
                    r2.parent = null;
                    tree.root = r2;
                    rebalance(tree,r2);
                    return tree.root;
                }else{
                    return null;
                }
            }else{
                return null;
            }
        } else {
            System.out.println("All nodes in T1 must be smaller than all nodes from T2");
            return null;
        }
    }
    
    public void merge(AVLTree tree2){
        if (isMergeable(this.root, tree2.root)){
            Node t = findMax(this.root);
            this.delete(t.key);
            root = AVLTreeMergeWithRoot(this.root, tree2.root, t);
        }else{
            System.out.println("All nodes in T1 must be smaller than all nodes from T2");
        }
    }
    
    public Node[] split(int key){
        return split(root, key); // caller
    }
    
    public static Node[] split(Node r, int key){ 
        Node[] arr = new Node[2];
        if(r != null){
            if(r.key > key){
                //System.out.print("left ");
                arr = split(r.left,key);
                arr[1] = AVLTreeMergeWithRoot(arr[1], r.right, r);
            }else if(r.key <= key){
                //System.out.print("right ");
                arr = split(r.right,key);
                arr[0] = AVLTreeMergeWithRoot(r.left, arr[0], r);
            }
        }
        if(r!= null){
            //System.out.print(r.key);
        }else{
            //System.out.print("null");
        }
        //System.out.println("   arr: " + ((arr[0] == null)? arr[0] : arr[0].key) + " " + ((arr[1] == null)? arr[1] : arr[1].key));
        return arr;
    }

    // Use this function to check the node height
    // This function is complete, no need to edit

    public static int height(Node node){
        if (node == null)
            return -1;
        else
            return 1 + Math.max(height(node.left), height(node.right));
    }
    
    // This function is complete, no need to edit
    public void printTree() {
        if (root == null) {
            System.out.println("Empty tree!!!");
        } else {
            super.printTree(root);
        }
    }
    
    public AVLTree() {} // Dummy Constructor, no need to edit
}