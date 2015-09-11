package producerConsumer;

/**
 * Created by alex on 9/4/15.
 */
public class Consumer extends Thread {

    private PCQueue queue;


    public Consumer(PCQueue queue) {
        this.queue = queue;

    }

    public void run() {
        while (true) {
            synchronized (queue) {

                if (queue.isEmpty()) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println(queue.getItemFromQueue());
            }

        }

    }

}
