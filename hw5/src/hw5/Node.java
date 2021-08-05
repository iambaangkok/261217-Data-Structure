package hw5;

public class Node extends BTreePrinter{

    Node left;
    Node right;
    int data;

    public Node(int data) {
        this.data = data;
    }

    public void printTree() { // using superclass's method
        super.printTree(this);
    }

    public void printBFT() { // depth-by-depth
        Queue q = new Queue(50);
        System.out.print("BFT node sequence [ ");

        q.enqueue(this); // add root
        while(!q.isEmpty()){
            Node currNode = q.dequeue(); // get a node

            System.out.print("" + currNode.data + " ");
            
            // add its child to queue
            if(currNode.left != null){
                q.enqueue(currNode.left);
            }
            if(currNode.right != null){
                q.enqueue(currNode.right);
            }
        }

        System.out.println("]");
    }

    public void printDFT() { // PreOrder
        Stack s = new Stack(50);
        System.out.print("DFT node sequence [ ");

        s.push(this); // add root
        while(!s.isEmpty()){
            Node currNode = s.pop(); // get a node

            System.out.print("" + currNode.data + " ");

            // add its child (right first cuz thats how the output works)
            if(currNode.right != null){
                s.push(currNode.right);
            }
            if(currNode.left != null){
                s.push(currNode.left);
            }
        }

        System.out.println("]");
    }
}
