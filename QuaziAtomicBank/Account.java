package QuaziAtomicBank;

/**
 * Created by alex on 9/10/15.
 */
public class Account {
    private int budget;

    public Account(int budget) {
        this.budget = budget;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public void increaseBudget(int amount) {
        budget+=amount;
    }

    public void decreaseBudget(int amount) {
        budget-=amount;
    }
}
