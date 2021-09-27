public class BSTree2 extends BTreePrinter {
    Node root;

    // Implement this function using iterative method
    // Do not use recursion
    public Node find(int search_key) {
        Node currNode = root;
        while (currNode != null) {
            if (currNode.key == search_key) {
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

    // Implement this function using iterative method
    // Do not use recursion
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

    // Implement this function using iterative method
    // Do not use recursion
    public Node findMax() {
        Node node = root;
        while (node != null) {
            if (node.right == null) { // node.right = null means current node is the right-most node -> max node
                return node;
            } else {
                node = node.right; // otherwise go evenmore right
            }
        }
        return null;
    }

    // Implement this function using iterative method
    // Do not use recursion
    public void insert(int key) {
        Node newNode = new Node(key);
        Node node = root;
        if(root == null){ // check if emptytree
            root = newNode;
        }
        while (node != null) {
            // insert
            if (key == node.key) { // don't insert
                return;
            } else if (key < node.key) {
                if(node.left != null){
                    node = node.left;
                }else{ //insert
                    node.left = newNode;
                    newNode.parent = node;
                    return;
                }
            } else {
                if(node.right != null){
                    node = node.right;
                }else{ //insert
                    node.right = newNode;
                    newNode.parent = node;
                    return;
                }
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
}