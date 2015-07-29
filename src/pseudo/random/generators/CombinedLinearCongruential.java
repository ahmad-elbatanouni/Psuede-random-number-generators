package pseudo.random.generators;

public class CombinedLinearCongruential {
    
    private long a1, m1, a2, m2;
    private long x1Zero, x2Zero, lastX1, lastX2, lastX;

    public CombinedLinearCongruential() {
        this.a1 = 40014;
        this.m1 = 2147483563;
        this.a2 = 20692;
        this.m2 = 2147483399;
        this.x1Zero = System.currentTimeMillis( ) % Integer.MAX_VALUE;
        this.x2Zero = System.currentTimeMillis( ) % Integer.MAX_VALUE + 2140099;
        this.lastX1 = x1Zero;
        this.lastX2 = x2Zero;
    }

    public CombinedLinearCongruential(long a1, long m1, long a2, long m2, long x1Zero, long x2Zero) {
        this.a1 = a1;
        this.m1 = m1;
        this.a2 = a2;
        this.m2 = m2;
        this.x1Zero = x1Zero;
        this.x2Zero = x2Zero;
        this.lastX1 = x1Zero;
        this.lastX2 = x2Zero;
    }
    
    public double Random() {
        lastX1 = (a1 * lastX1) % m1;
        lastX2 = (a2 * lastX1) % m2;
        lastX = Math.abs(lastX1 - lastX2) % m1;
        return (double) lastX / m1 ;
    }
    
    public static void main(String[] args) {
        CombinedLinearCongruential d = new CombinedLinearCongruential();
        
        for (int i = 0; i < 1000; i++) {
            System.out.println(d.Random());
        }
    }
}
