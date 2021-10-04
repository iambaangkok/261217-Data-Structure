public class HashTable {
    String[] arr;
    int size;
    int capacity;

    // Hashing parameters
    int p; // Big Prime (for PolyHash())
    int x; // Small number (for PolyHash())

    public HashTable(int cap, int p, int x) {
        arr = new String[cap];
        this.capacity = cap;
        this.p = p;
        this.x = x;
    }

    public void addString(String s) {
        // Insert the string at that index that no one occupy
        // You should call getIndex()
        int index = getIndex(s);
        if(arr[index] == null){
            arr[index] = s;
            size++;
        }
    }

    // This function has 2 roles
    // [1] Find an empty space to push the string object
    // [2] Find location of the string
    public int getIndex(String s) {
        // The algorithm is followed
        // 1. Hash the string and get the index
        int hashedIndex = polyHash(s, p, x);
        hashedIndex %= capacity;

        // 2. If the arr[index] is empty, then return index
        // 3. If the arr[index] equals the string, then also return index
        if(arr[hashedIndex] == null || arr[hashedIndex] == s){
            return hashedIndex;
        }

        // 4. Perform the quadratic probing and repeat 2.-3. until found the location
        int newIndex = hashedIndex;
        for(int i = 0 ; i < capacity; ++i){ // from slide: Topic7-3, page85
            newIndex = (newIndex+i)%capacity;
            if(arr[newIndex] == null || arr[newIndex] == s){
                break;
            }
        }
        return newIndex;
    }

    // Check the slide for the pseudocode
    // If you want to access a char of string s at index i, use s.charAt(i)
    // If you want to get the ASCII code of char c, use (int)c
    public static int polyHash(String s, int p, int x) {
        int hash = 0;
        for(int i = 0 ; i < s.length(); ++i){ //Sigma i=0 -> |S|-1 : (S[i]*x^i) mod p   // from slide: Topic7-2, page36-37
            hash = (hash*x + ((int)s.charAt(i)))%p;
        }
        return hash;
    }

    // This is editable testcase, no need to edit
    public static void test1() {
        int p = 1100101; // Big Prime (Hash key1)
        int x = 1001; // Small number (Hash key2)
        HashTable table = new HashTable(16, p, x);
        String[] names = { "a", "b", "c", "A", "B", "BA", "BBA", "aaa", "aaaaa", "0", "1", "11", "111",
                "abcdABCD01234" };
        for (int i = 0; i < names.length; i++)
            System.out.println("HashCode of '" + names[i] + "' is " + HashTable.polyHash(names[i], p, x));
    }

    // This is editable testcase, no need to edit
    public static void test2() {
        int p = 1100101; // Big Prime (Hash key1)
        int x = 1001; // Small number (Hash key2)
        HashTable table = new HashTable(16, p, x);
        String[] names = { "a", "b", "c", "A", "B", "BA", "BBA", "aaa", "aaaaa", "0", "1", "11", "111",
                "abcdABCD01234" };

        for (int i = 0; i < names.length; i++) {
            table.addString(names[i]);
            System.out.println("Index of '" + names[i] + "' is " + table.getIndex(names[i]));
        }
    }

    public static void test3() {
        int p = 1100101; // Big Prime (Hash key1)
        int x = 101; // Small number (Hash key2)
        HashTable table = new HashTable(16, p, x);
        String[] names = { "Abraham", "Andrew", "Benjamin", "Claudia", "Gabriel", "Esther", "Martha", "Rebecca",
                "Moses", "Timothy" };
        for (int i = 0; i < names.length; i++)
            System.out.println("HashCode of " + names[i] + " is " + HashTable.polyHash(names[i], p, x));

        for (int i = 0; i < names.length; i++) {
            table.addString(names[i]);
            System.out.println("Index of " + names[i] + " is " + table.getIndex(names[i]));
        }
    }
}