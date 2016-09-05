/**
 * Created by p.bell on 05.09.2016.
 */
public class Multiplesof3and5P1 {
    public static void main(String[] args) {
        System.out.println(findSum(1000));
    }

    private static double findSum(int number) {
        double sum=0;
        for(int i=3 ; i< number; i++){
            if(i%3 ==0 || i% 5 ==0){
                sum+=i;
            }
        }
        return sum;
    }
}
