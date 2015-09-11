package QuaziAtomicBank;

/**
 * Created by alex on 9/10/15.
 */
public class BankTransactionManager extends Thread{

    private Bank bank;

    public BankTransactionManager(Bank bank) {
        this.bank = bank;
    }

    public void run() {
        int accountFromIndex = (int)(Math.random()*bank.getBankSize());
        int accountToIndex = (int)(Math.random()*bank.getBankSize());

        int amount = (int)(Math.random()*500);

        try {
            System.out.println(accountFromIndex+" -->"+accountToIndex+" by "+amount);
            bank.transaction(accountFromIndex, accountToIndex, amount);
        } catch (IllegalArgumentException e) {
            e.getMessage();
        }

    }
}
