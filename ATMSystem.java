import java.util.Scanner;                    //prajwal eknath kapase (C02066)

public class ATMSystem {

    static class BankAccount {
        private String accountHolderName;
        private double balance;
        private int pin;   // PIN added

        public BankAccount(String accountHolderName, double initialBalance, int pin) {
            this.accountHolderName = accountHolderName;
            this.balance = initialBalance;
            this.pin = pin;
        }

        public boolean validatePin(int enteredPin) {
            return this.pin == enteredPin;
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            if (amount <= 0) {
                throw new IllegalArgumentException("Deposit amount must be positive.");
            }
            balance += amount;
            System.out.println("Successfully deposited: ₹" + amount);
        }

        public void withdraw(double amount) {
            if (amount <= 0) {
                throw new IllegalArgumentException("Withdrawal amount must be positive.");
            }
            if (amount > balance) {
                throw new ArithmeticException("Insufficient funds!");
            }
            balance -= amount;
            System.out.println("Successfully withdrawn: ₹" + amount);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter account holder name: ");
        String name = sc.nextLine();

        double initialBalance = 0;
        while (true) {
            try {
                System.out.print("Enter initial balance: ");
                initialBalance = sc.nextDouble();

                if (initialBalance < 0) {
                    throw new IllegalArgumentException("Initial balance cannot be negative.");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid number.");
                sc.nextLine();
            }
        }

        int pin;
        while (true) {
            try {
                System.out.print("Set a 4-digit PIN: ");
                pin = sc.nextInt();
                if (pin < 1000 || pin > 9999) {
                    throw new IllegalArgumentException("PIN must be a 4-digit number.");
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid 4-digit number.");
                sc.nextLine();
            }
        }

        BankAccount account = new BankAccount(name, initialBalance, pin);

        // PIN verification loop before menu
        int attempts = 0;
        boolean accessGranted = false;
        while (attempts < 3) {
            System.out.print("\nEnter your 4-digit PIN to access ATM: ");
            int enteredPin = sc.nextInt();
            if (account.validatePin(enteredPin)) {
                accessGranted = true;
                break;
            } else {
                attempts++;
                System.out.println("Incorrect PIN! Attempts left: " + (3 - attempts));
            }
        }

        if (!accessGranted) {
            System.out.println("Too many incorrect attempts! Account locked.");
            sc.close();
            return;
        }

        // ATM menu
        while (true) {
            try {
                System.out.println("\n ATM Menu ");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit Money");
                System.out.println("3. Withdraw Money");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");

                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Current Balance: ₹" + account.getBalance());
                        break;

                    case 2:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = sc.nextDouble();
                        account.deposit(depositAmount);
                        break;

                    case 3:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = sc.nextDouble();
                        account.withdraw(withdrawAmount);
                        break;

                    case 4:
                        System.out.println("Thank you for using ATM. Goodbye!");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice! Please select between 1-4.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (ArithmeticException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected Error: " + e.getMessage());
                sc.nextLine();
            } finally {
                System.out.println("Operation completed. Returning to menu...\n");
            }
        }
    }
}
