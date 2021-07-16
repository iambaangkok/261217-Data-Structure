import java.util.Arrays;

public class App {
    public static void main(String[] args) throws Exception {
        int[] arr = {1,5,2,4,3,-3,1,2,5,0,-5,4};

        System.out.println(Arrays.toString(findNonSingleElements(arr)));
    }

    public static boolean[] findNonSingleElements(int[] arr){
        boolean[] pairedElement = new boolean[arr.length];

        for(int i = 0 ; i < arr.length; ++i)    {
            for(int j = 0 ; j < arr.length; ++j){
                if(arr[i] == arr[j] && i != j){
                    pairedElement[i] = true;
                    pairedElement[j] = true;
                }
            }
        }
        return pairedElement;
    }
}
