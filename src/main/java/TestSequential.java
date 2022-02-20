import java.math.BigInteger;
import java.util.Calendar;
import java.util.Random;

public class TestSequential {
  public static void main(String[] args) {
    long start = Calendar.getInstance().getTimeInMillis();
    for (int i = 0; i < 20; i++ ) {
      BigInteger bigInteger = new BigInteger(2000, new Random());
      System.out.println("Prime: " + bigInteger.nextProbablePrime());
    }
    long finish = Calendar.getInstance().getTimeInMillis();
    System.out.println("Took: "+ (finish - start));
  }
}
