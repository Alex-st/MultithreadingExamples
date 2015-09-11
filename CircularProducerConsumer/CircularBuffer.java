package CircularProducerConsumer;

/**
 * Created by alex on 9/10/15.
 */
public class CircularBuffer {
    Integer[] buffer;
    int readIndexFlag;
    int writeIndexFlag;
    boolean previousOperationFlag; // true for write, false for read

    public CircularBuffer(int size){
        buffer = new Integer[size];

        //random write index initialization
        writeIndexFlag = (int)Math.random()*size;

        readIndexFlag = writeIndexFlag;
        previousOperationFlag = false;
    };

    public synchronized void addItemToBuffer(Integer item) {
        while (readIndexFlag == writeIndexFlag && previousOperationFlag == true) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        buffer[writeIndexFlag++] = item;

        previousOperationFlag = true;

        if (writeIndexFlag == buffer.length) {
            writeIndexFlag = 0;
        }

        notifyAll();
    }

    public synchronized Integer getItemFromBuffer(){
        while (readIndexFlag == writeIndexFlag && previousOperationFlag == false) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Integer res = buffer[readIndexFlag++];

//        buffer[readIndexFlag-1] = null;

        previousOperationFlag = false;

        if (readIndexFlag == buffer.length) {
            readIndexFlag = 0;
        }

        notifyAll();
        return res;
    }

    public static void main(String[] args) {
        CircularBuffer test = new CircularBuffer(8);

        CircularProducer p1 = new CircularProducer(test, 1);
        CircularProducer p2 = new CircularProducer(test, 2);
        CircularProducer p3 = new CircularProducer(test, 3);

        CircularConsumer c1 = new CircularConsumer(test);
        CircularConsumer c2 = new CircularConsumer(test);

        p1.start();
        p2.start();
        p3.start();
        c1.start();
        c2.start();
    }

}
