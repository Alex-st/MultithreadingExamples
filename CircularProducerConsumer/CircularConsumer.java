package CircularProducerConsumer;

import producerConsumer.PCQueue;

/**
 * Created by alex on 9/4/15.
 */
public class CircularConsumer extends Thread {

    private CircularBuffer buffer;


    public CircularConsumer(CircularBuffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        while (true) {

            System.out.println(buffer.getItemFromBuffer());

//            try {
//                Thread.sleep(60);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }

    }

}
