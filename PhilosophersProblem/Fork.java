package PhilosophersProblem;

/**
 * Created by alex on 9/11/15.
 */
public class Fork {
    private int id;
    private Philosopher owner;

    public Fork(int id) {
        this.id = id;
    }

    public void assignPhilosopher(Philosopher ph) {
        owner = ph;
    }

    public void releasePhilosopher() {
        owner = null;
    }

    public boolean hasOwner() {
        return owner != null;
    }

    public int getId() {
        return id;
    }
}
