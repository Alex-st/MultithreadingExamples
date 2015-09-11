import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by alex on 9/9/15.
 */
public class BigIntegerPowerOfTwo {
    BigInteger trueValue;
    AtomicReference<BigInteger> referenceValue = new AtomicReference<BigInteger>();

    public BigIntegerPowerOfTwo() {
        referenceValue.set(BigInteger.valueOf(1));
    }

    public BigInteger next() {
        for(;;){

            //System.out.println(trueValue.intValue());

            BigInteger curr = referenceValue.get();
            BigInteger next = curr.multiply(BigInteger.valueOf(2));

           // System.out.println("next:"+next.intValue());

            if ( referenceValue.compareAndSet(curr, next)) {
                return next;
            }
        }
    }

    public static void main(String[] args) {
        final BigIntegerPowerOfTwo test = new BigIntegerPowerOfTwo();

        ExecutorService executor = new ThreadPoolExecutor(
          5, // initial core pool size
          10,// max possible pool size
          100L, TimeUnit.MILLISECONDS, // keep alive - after such time number of threads become decreasing by executor
          new LinkedBlockingQueue<Runnable>(30), // queue - in what data structure tasks will be stored (child of Blocking queue)
          new ThreadPoolExecutor.CallerRunsPolicy() // handler - strategy how new tasks will be processed after task queue will be fulfilled
        );



        //Thread[] threads = new Thread[8];
        for (int i = 0; i < 30; i++) {
            executor.submit(new Runnable() {
                public void run() {
                    for (int j = 0; j < 8; j++) {
                        System.out.println(test.next().intValue()+" / "+Thread.currentThread().getName());
                    }
                }
            });
        }
        executor.shutdown();
    }
}
