package gad.doublehashing;

public final class Main {
    private Main() {

    }

    public static void main(String[] args) {
//        DoubleHashTable<Integer, String> table = new DoubleHashTable<>(17, new IntHashableFactory());
//
//        table.insert(42, "Hallo");
//        table.insert(69, "Welt");
//
//        System.out.println(table.find(42));
        String[] strs = new String[]{"flower", "flow", "flight"};

       // int i = 0;
        char[] a1;
        char[] a0;
        char[] a2 = new char[0];
        //System.out.println(a0);
        String s = "";

        for (int i = 1; i < strs.length; i++) {
            a1 = strs[i].toCharArray();
            a0 = strs[i - 1].toCharArray();
             System.out.println(a0);
            int length= strs[i].length();


            //System.out.println(strs[i].length());
            if (length > strs[i - 1].length()) {
                length = strs[i - 1].length();
            }else{

                for (int j = 0; j < length; j++) {
                    System.out.println(a0);


                    if (a1[j] == a0[j]) {
                        //System.out.println(a1[j]);
                        // System.out.println(a0[j]);
                        s = s + a1[j];

                    }
                    //System.out.println(s);

                }


                //  System.out.println(s);
            }
        }
        System.out.println(s);

    }
}
