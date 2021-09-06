
// This Tree needs to inherit BTreePrinter
public class Tree extends BTreePrinter{
    Node root;
      
    public Tree(Node root){
        this.root = root;
    }
    
    public Tree(){} // Dummy constructor (No need to edit)
    
    public void printTree(){
        if(root == null){
            System.out.println("Empty tree!!!");
        }
        super.printTree(root);
    }

    public static void printNode(Node node){
        if(node == null){
            System.out.println("Node not found!!!");    
        }else{
            System.out.println(node.key);
        }
    }
        
    public Node find(int search_key){
        return find(root,search_key); //caller
    }
    
    public static Node find(Node node, int search_key){
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
    
    
    public Node findMin(){
        return findMin(root); //caller
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
    
    public Node findMax(){
        return findMax(root); //caller
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
    
    public Node findClosest(int search_key){
        // Try not to use recursion: okayyyyy
        
        Node current, closest;
        closest = current = root;
        int min_diff = Integer.MAX_VALUE;

        // Use while loop to traverse from root to the closest leaf
        while(current != null){
            if(current.key == search_key){
                closest = current;
                break;
            }
            int newMinDiff = Math.abs(search_key-current.key);
            if(newMinDiff < min_diff){ // set new Mindiff & closest
                min_diff = newMinDiff;
                closest = current;
            }
            if(current.left != null && current.key > search_key){ // traverse left subtree
                current = current.left;    
            }else if(current.right != null && current.key < search_key){ // traverse right subtree
                current = current.right;    
            }else{
                break;
            }
        }
        
        return closest;
    }
    
    // Implement this function using the findClosestLeaf technique
    // Do not implement the recursive function
    public void insert(int key) {
        Node newNode = new Node(key);
        Node closest = findClosestLeaf(key);
        // get the the parent of the node we need to insert
        if(closest == null){ 
            root = newNode;
            return;
        }
        // insert
        if(key == closest.key){ // don't insert
            return;
        }else if(key < closest.key){
            closest.left = newNode;
        }else{
            closest.right = newNode;
        }
    }
    
    public void printPreOrderDFT(){
        System.out.print("PreOrder DFT node sequence [ ");
        printPreOrderDFT(root);
        System.out.println("]");
    }
    
    public static void printPreOrderDFT(Node node){  // parent, left, right
        if(node == null) return;
        System.out.print("" + node.key + " ");

        if(node.left != null){
            printPreOrderDFT(node.left);
        }
        if(node.right != null){
            printPreOrderDFT(node.right);
        }
        return;
    }
    
    public void printInOrderDFT(){
        System.out.print("InOrder DFT node sequence [ ");
        printInOrderDFT(root);
        System.out.println("]");
    }
    
    public static void printInOrderDFT(Node node){ // left, parent, right
        if(node == null) return;

        if(node.left != null){
            printInOrderDFT(node.left);
        }
        System.out.print("" + node.key + " ");
        if(node.right != null){
            printInOrderDFT(node.right);
        }
        return;
    }
    
    public void printPostOrderDFT(){
        System.out.print("PostOrder DFT node sequence [ ");
        printPostOrderDFT(root);
        System.out.println("]");
    }
    
    public static void printPostOrderDFT(Node node){ // left, right, parent
        
        if(node == null) return;

        if(node.left != null){
            printPostOrderDFT(node.left);
        }
        if(node.right != null){
            printPostOrderDFT(node.right);
        }
        System.out.print("" + node.key + " ");

        return;
    }
    
    public static int height(Node node){
        // Use recursion to implement this function
        if(node == null){
            return 0;
        }

        int maxHeight = 0; // maxHeight of node = 1 + height(left, right)

        if(node.left != null){
            maxHeight = Math.max(maxHeight,height(node.left) + 1);
        }
        if(node.right != null){
            maxHeight = Math.max(maxHeight,height(node.right) + 1);
        }
        return maxHeight;
    }
    
    public static int size(Node node){
        // Use recursion to implement this function
        // size = #children + 1(itself)
        if(node == null){
            return 0;
        }

        int s = 1;
        if(node.left != null){
            s += size(node.left);
        }
        if(node.right != null){
            s += size(node.right);
        }
        return s;
    }
    
    public static int depth(Node root, Node node){
        if(node == null || node == root){
            return 0;
        }

        // depth of node = 1 + depth(parent)

        int d = 0;
        if(node.parent != null){
            d += 1 + depth(root, node.parent);
        }
        return d;

    }
    
    public int height(){ // Tree height
        
        return (root==null)? -1 : height(root);
    }
    
    public int size(){ // Tree size
        return size(root);
    }
    
    public int depth(){ // Tree depth
        return (root==null)? -1 : height();
    }
    
    public Node findKthSmallest(int k){
        return findKthSmallest(root, k);
    }
    
    public static Node findKthSmallest(Node node, int k){
        int leftSize = 0;
        if(node == null){ // this case shouldn't happen
            return null;
        }

        if(node.left != null){
            leftSize = size(node.left);
        }

        if(leftSize + 1 == k){ // self-explanatory
            return node;
        }else if(k <= leftSize){ // search in left subtree
            return findKthSmallest(node.left, k);
        }else if(k > leftSize + 1){ 
            return findKthSmallest(node.right,k-(leftSize+1)); // dont forget to change k because we just skipped the entire left subtree
        }

        return node; // this return shouldn't happen
    }
    
    public static Node findNext(Node node){
        if(node==null){ // this shouldn't happen
            return null;
        }

        if(node.right != null){ // return left most node of right subtree
            return leftDescendant(node.right);
        }else{
            return rightAncestor(node);
        }
    }
    
    public static Node leftDescendant(Node node){// Case 1 (findMin)
        // this function should be recursive : we could just call findMin(node); but i guess we'll have to write excess code

        if(node == null){ // shouldn't happen unless root is null
            return null; 
        }
        if(node.left == null){ // node.left = null means current node is the left-most node
            return node;
        }else{
            return leftDescendant(node.left); // otherwise go evenmore left
        }

    }
    
    public static Node rightAncestor(Node node) {// Case 1 (first right parent)
        // this function should be recursive

        if(node == null){ // shouldn't happen unless root is null
            return null; 
        }
        if(node.parent == null){ // node.parent = null means current node is the most 'parent' node
            return node;
        }else if(node.key < node.parent.key){ // found the first parent that is more than inself
            return node.parent;
        }else{
            return rightAncestor(node.right); // otherwise go evenmore up
        }
    }   
    
    public List rangeSearch(int x, int y){
        // This function utilizes findClosest() and findNext()
        List list = new List(100);
        
        Node current = findClosest(x); // first element that's in-range
        while(current != null && current.key <= y){ // loop findNext(node) until out of range
            if(current.key >= x)
                list.append(current);
            current = findNext(current);
        }
        
        return list;
    }
    
         
    // This function is for deleting the root node
    // If the node is not the root, please call the recursive version
    public void delete(int key) {
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
                delete(minRNode);
            }
        }else{ // C6 Otherwise delete non-root node
            delete(findResult);
        }
        return;
    }

    // Use this function to delete non-root nodes
    public static void delete(Node node){
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
            delete(minRNode);
        }
        return;
    }
}
