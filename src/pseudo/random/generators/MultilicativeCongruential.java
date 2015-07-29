package pseudo.random.generators;

public class MultilicativeCongruential {
    
    /*
     * 
     * The formulae is: X[i + 1] = (a * X[i] + c) % m;
     * 
     */
    private long a, c, m;
    private long xZero, lastX;
    public MultilicativeCongruential(long a, long c, long xZero, long m) {
        this.a = a;
        this.c = c;
        this.xZero = xZero;
        this.m = m;
        lastX = xZero;
    }
    
    public MultilicativeCongruential() {
        xZero = System.currentTimeMillis( ) % Integer.MAX_VALUE;//123457;
        a = 16708;
        c = 0;
        m = (long) (Math.pow(2, 31) - 1);
        lastX = xZero;
    }
    
    public double Random() {
        lastX = (a * lastX + c) % m;
        return (double) lastX / Math.pow(2, 31) ;
    }
    
    
    public static void main(String[] args) {
        MultilicativeCongruential d = new MultilicativeCongruential();
        
        for (int i = 0; i < 1000; i++) {
            System.out.println(d.Random());
        }
    }
}
