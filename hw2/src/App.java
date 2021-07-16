public class App {
    public static void main(String[] args) throws Exception {
        DynamicArray darr = new DynamicArray(2);
        darr.pushBack(1);
        darr.pushBack(2);
        darr.pushBack(-1);
        darr.pushBack(0);
        darr.pushBack(2);
        darr.printStructure();
        System.out.println(darr.popBack());
        darr.printStructure();
        darr.remove(2);
        darr.printStructure();
        System.out.println(darr.get(2));
        darr.set(2,20);
        System.out.println(darr.get(2));
        darr.printStructure();
        while(!darr.isEmpty()){
            System.out.println(darr.popBack());
        }
        darr.printStructure();
        darr.set(2,20);
    }
}
