import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;

/**
 * Created by mokarakaya on 05.09.2016.
 */
public class SummationofPrimesP10 {

    public static void main(String[] args) {
        SummationofPrimesP10 summationofPrimesP10=new SummationofPrimesP10();
        NumberFormat formatter = new DecimalFormat("###");
        System.out.println(formatter.format(summationofPrimesP10.findSum(2000000)));
    }

    private double findSum(int limit) {
        double sum=0;
        boolean[] primes = fillSieve(limit);
        for(int i=0;i<limit;i++){
            if(primes[i]){
                sum+=i;
            }
        }
        return sum;
    }


    //set up the primesieve
    public boolean[] fillSieve(int limit) {
        boolean[] primes=new boolean[limit];
        Arrays.fill(primes,true);        // assume all integers are prime.
        primes[0]=primes[1]=false;       // we know 0 and 1 are not prime.
        for (int i=2;i<primes.length;i++) {
            //if the number is prime,
            //then go through all its multiples and make their values false.
            if(primes[i]) {
                for (int j=2;i*j<primes.length;j++) {
                    primes[i*j]=false;
                }
            }
        }
        return primes;
    }
}
