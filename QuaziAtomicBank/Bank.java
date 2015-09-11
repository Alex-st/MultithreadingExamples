package QuaziAtomicBank;

/**
 * Created by alex on 9/10/15.
 */
public class Bank {
    Account[] accounts;

    public Bank(int size) {
        accounts = new Account[size];

        for(int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account((int)(Math.random()*1000));
            //System.out.println(i.getBudget());
        }
    }

    public int getBankSize(){
        return accounts.length;
    }

    public void transaction(int fromIndex, int toIndex, int amount) throws IllegalArgumentException{
        if (amount < 0)
            throw new IllegalArgumentException("amount should be more then 0");

        if (accounts[fromIndex].getBudget() > accounts[toIndex].getBudget()) {
            synchronized (accounts[fromIndex]) {
                int sumToTransact = amount;

                synchronized (accounts[toIndex]) {
                    if (sumToTransact > accounts[fromIndex].getBudget()) {
                        sumToTransact = accounts[fromIndex].getBudget();
                    }

                    accounts[fromIndex].decreaseBudget(sumToTransact);
                    accounts[toIndex].increaseBudget(sumToTransact);

                }
            }
        }

        if (accounts[toIndex].getBudget() > accounts[fromIndex].getBudget()) {
            synchronized (accounts[toIndex]) {
                int sumToTransact = amount;

                synchronized (accounts[fromIndex]) {
                    if (sumToTransact > accounts[fromIndex].getBudget()) {
                        sumToTransact = accounts[fromIndex].getBudget();
                    }

                    accounts[fromIndex].decreaseBudget(sumToTransact);
                    accounts[toIndex].increaseBudget(sumToTransact);

                }
            }
        }
    }

    public int getWholeBankBudget(){
        int sum = 0;
        for (Account i: accounts) {
            sum+=i.getBudget();
        }
        return sum;
    }


    public static void main(String[] args) {
        Bank test = new Bank(300);

        System.out.println(test.accounts[5].getBudget());

        System.out.println("Initial budget: "+test.getWholeBankBudget());

        BankTransactionManager[] manager = new BankTransactionManager[1000];

        for (int i = 0; i < manager.length; i++) {
            manager[i] = new BankTransactionManager(test);
        }

        for (int i = 0; i < manager.length; i++) {
            manager[i].start();
        }

        for (int i = 0; i < manager.length; i++) {
            try {
                manager[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Final budget: "+test.getWholeBankBudget());
    }
}
