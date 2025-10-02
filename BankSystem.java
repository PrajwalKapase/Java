import java.util.Scanner;

public class BankSystem {                        // Prajwal Kapase (C02066)

    // Parent class
    static class BankAccount {
        protected String accountHolderName;
        protected String accountNumber;
        protected double balance;

        public BankAccount(String accountHolderName, String accountNumber, double initialBalance) {
            this.accountHolderName = accountHolderName;
            this.accountNumber = accountNumber;
            this.balance = initialBalance;
        }

        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                System.out.println("Deposited ₹" + amount + ". New balance: ₹" + balance);
            } else {
                System.out.println("Invalid deposit amount.");
            }
        }

        public void withdraw(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                System.out.println("Withdrawn ₹" + amount + ". New balance: ₹" + balance);
            } else {
                System.out.println("Invalid or insufficient funds.");
            }
        }

        public void checkBalance() {
            System.out.println("Current Balance: ₹" + balance);
        }

        public void displayAccountInfo() {
            System.out.println("Account Holder: " + accountHolderName);
            System.out.println("Account Number: " + accountNumber);
            checkBalance();
        }
    }

    // Child class inheriting from BankAccount
    static class SavingsAccount extends BankAccount {
        public SavingsAccount(String accountHolderName, String accountNumber, double initialBalance) {
            super(accountHolderName, accountNumber, initialBalance);
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Account Holder Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Account Number: ");
        String number = scanner.nextLine();
        System.out.print("Enter Initial Balance: ");
        double balance = scanner.nextDouble();

        SavingsAccount account = new SavingsAccount(name, number, balance);

        int choice;
        do {
            System.out.println("\n--- Banking System Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Account Info");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    account.checkBalance();
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 4:
                    account.displayAccountInfo();
                    break;
                case 5:
                    System.out.println("Thank you for using the banking system.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 5);

        scanner.close();
    }
}
