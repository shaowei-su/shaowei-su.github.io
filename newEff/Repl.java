class Repl {
     public static boolean isSquare(int c) {
         int sqrt = (int) Math.sqrt(c);
         System.out.println("sqrt = " + sqrt);
         return sqrt * sqrt == c;
     }
    public static void main(String[] args) {
        System.out.println("11 is " + isSquare(11));
    }
}
