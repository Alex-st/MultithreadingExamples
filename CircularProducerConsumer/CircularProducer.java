package CircularProducerConsumer;

import producerConsumer.PCQueue;

/**
 * Created by alex on 9/4/15.
 * Circular producer adds its personal int number to buffer
 */
public class CircularProducer extends Thread{

    private CircularBuffer buffer;
    private Integer i;

    public CircularProducer(CircularBuffer buffer, Integer i) {
        this.buffer = buffer;
        this.i = i;
    }

    public void run() {
        while (true) {

            buffer.addItemToBuffer(i);
            System.out.println(i+" Added");

//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}
