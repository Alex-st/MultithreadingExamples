package PhilosophersProblem;

/**
 * Created by alex on 9/11/15.
 */
public class Philosopher extends Thread{
    private MainTable table;
    private Fork left;
    private Fork right;

    public Philosopher(MainTable table, Fork left, Fork right) {
        this.table = table;
        this.left = left;
        this.right = right;
    }

    public void tryToEat() {

        synchronized (left) {
            left.assignPhilosopher(this);

            if (right.hasOwner()) {
                left.releasePhilosopher();
                return;
            }

            synchronized (right) {
                right.assignPhilosopher(this);

                System.out.println("Philosopher "+this.getName()+" eats with left:"+left.getId()+", right:"+right.getId());

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                right.releasePhilosopher();
                left.releasePhilosopher();
            }

        }
    }

    public void run() {
        //for (int i = 0; i < 1000; i++) {
          for(;;) {
            tryToEat();
        }
    }
}
