public class BSTree2 extends BTreePrinter{
    Node root;
    
    // Implement this function using iterative method
    // Do not use recursion
    public Node find(int search_key){
        return null; // Fix this
    }

    // Implement this function using iterative method
    // Do not use recursion
    public Node findMin(Node node){
        return null; // Fix this
    }

    // Implement this function using iterative method
    // Do not use recursion
    public Node findMax(){
        return null; // Fix this
    }
    
    // Implement this function using iterative method
    // Do not use recursion
    public void insert(int key) {
        
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