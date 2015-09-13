package PhilosophersProblem;

/**
 * Created by alex on 9/11/15.
 */
public class MainTable {
    private Fork[] forks;
    private Philosopher[] philosophers;

    public MainTable() {
        forks = new Fork[5];
        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Fork(i);
        }

        philosophers = new Philosopher[5];
    }

    public void init() {
        philosophers[0] = new Philosopher(this, forks[0], forks[1]);
        philosophers[1] = new Philosopher(this, forks[1], forks[2]);
        philosophers[2] = new Philosopher(this, forks[2], forks[3]);
        philosophers[3] = new Philosopher(this, forks[3], forks[4]);
        philosophers[4] = new Philosopher(this, forks[4], forks[0]);
    }

    public void startEating() {
        for(Philosopher i: philosophers) {
            i.start();
        }
    }

    public static void main(String[] args) {
        MainTable test = new MainTable();
        test.init();
        test.startEating();
    }
}
