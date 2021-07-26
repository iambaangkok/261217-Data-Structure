package hw4;

public class Stock {
    private List list;
    private int totalShares;
    
    public Stock(String costBasis){
        switch (costBasis) {
            case "FIFO":
                list = new Queue();
                break;
            case "LIFO":
                list = new Stack();
                break;
            default:
                System.out.println("Invalid cost basis. Choose FIFO or LIFO");
                break;
        }
    }
    
    public void buy(int boughtShares, double boughtPrice){
        // create new buyOrder node and push it into the list
        list.push(new Node(boughtShares,boughtPrice));
        
        totalShares += boughtShares;
    }
    
    public void sell(int soldShares, double soldPrice){
        if (soldShares <= totalShares){
            double realizedGain = 0.0;
            double unrealizedGain = 0.0;
            totalShares -= soldShares;
            
            realizedGain = soldPrice * soldShares; // calculated here so less code in loop

            while(list.top() != null && soldShares > 0) {
                if(soldShares >= list.top().shares){
                    realizedGain -= list.top().shares*list.top().price;
                    soldShares -= list.top().shares;
                    list.pop();
                }else{
                    list.top().shares -= soldShares;
                    realizedGain -= soldShares*list.top().price;
                    soldShares = 0;
                }
            }

            unrealizedGain =  soldPrice * totalShares; // calculated here so less code in loop
            // create a Queue to help preserve the list when calculating
            String className = list.getClass().getName(); // get name of this class (to know if queue or stack)
            List q = new Queue();
            //System.out.println(className);
            if(className == Queue.class.getName()){
                q = new Queue();
            }
            if(className == Stack.class.getName()){
                q = new Stack();
            }
                
            // loop through every node to calculate unrealizedGain
            while(list.top() != null){
                q.push(new Node(list.top().shares, list.top().price));
                unrealizedGain -= list.top().shares * list.top().price;
                list.pop();
            }

            // recreate the list with element stored in Queue
            while(q.top() != null){
                list.push(new Node(q.top().shares, q.top().price));
                q.pop();
            }
            
            System.out.println("Realized P/L = " + realizedGain + " Unrealized P/L = " + unrealizedGain);
        }else{
            System.out.println("Sell command rejected");
        }
    }
    
    public void showList(){
        Node currentNode = list.top();
        System.out.print("head -> ");
        while (currentNode!=null){
            System.out.print("[" + currentNode.shares + "@" + currentNode.price + "B] -> ");
            currentNode = currentNode.next;
        }
        System.out.println("tail");
    }
}