package producerConsumer;

/**
 * Created by alex on 9/4/15.
 */
public class Producer extends Thread{

    private PCQueue pcQueue;
    private Integer i;

    public Producer(PCQueue pcQueue, Integer i) {
        this.pcQueue = pcQueue;
        this.i = i;
    }

    public void run() {
        while (true) {

            synchronized (pcQueue) {
                pcQueue.addItemToQueue(i);
                pcQueue.notifyAll();
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
