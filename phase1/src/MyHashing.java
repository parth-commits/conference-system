public class MyHashing {
    private int seed = 100;

    public MyHashing (){}

    public MyHashing (int x){
        this.seed = x;
    }

    public int hash (String temp){
        char[] charArray = temp.toCharArray();
        int count = 0;
        for (char c : charArray) {
            count += (int) c;
        }
        return count;
    }

    public int hash (int y){
        int temp = seed;
        seed = y;
        return temp;

    }
    testtesttesttesttest
}
