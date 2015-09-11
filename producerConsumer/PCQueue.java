package producerConsumer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by alex on 9/4/15.
 */
public class PCQueue {
    Queue<Integer> queue;

    public PCQueue() {
        queue = new LinkedList<Integer>();
    }

    public synchronized void addItemToQueue(Integer i) {
        queue.offer(i);
    }

    public synchronized Integer getItemFromQueue() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public static void main(String[] args) {
        PCQueue test = new PCQueue();
        Producer p1 = new Producer(test, 1);
        Producer p2 = new Producer(test, 2);

        Consumer c1 = new Consumer(test);
        Consumer c2 = new Consumer(test);

        p1.start();
        p2.start();
        c1.start();
        c2.start();

    }
}
