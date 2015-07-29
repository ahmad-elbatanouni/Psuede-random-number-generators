package pseudo.random.generators;

import java.util.Calendar;

public class MidSquare {
    private static int seed, index;
    private static int[] seedsList = {3203, 5536, 1771, 4913, 5961, 7859, 8743, 2744, 9954, 1453};
    private Calendar time;
    private int lastGenerated;
    public MidSquare(int seed) {
        lastGenerated = seed;
    }
    
    public MidSquare() {
        time = Calendar.getInstance();
        index = time.get(Calendar.SECOND) % 10;
        lastGenerated = seedsList[index];
    }
    
    public double Random() {
        lastGenerated *= lastGenerated;
        String theSquare = String.valueOf(lastGenerated);
        String random_number = "" ;//+ theSquare.charAt(2) + theSquare.charAt(3) + theSquare.charAt(4) + theSquare.charAt(5);
        int counter = 1;
        int index = 1;
        while (counter <= 4) {
            if(theSquare.charAt(index) != '0') {
                random_number += theSquare.charAt(index);
                counter++;
            }
            index++;
        }
        lastGenerated = Integer.parseInt(random_number);
        double d = (double)lastGenerated / 10000;
        return d;
    }
    
    public static void main(String[] args) {
        MidSquare s = new MidSquare();
        double d = 0;
        for(int i = 0; i < 100; i++) {
//            System.out.println(s.Random());
            d += s.Random();
//            d += Math.random();
        }
//        System.out.println(77 >>> 6);
        System.out.println(d / 100);
    }
}
