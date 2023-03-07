//Title: Bank Class
//Abstract: This program reads data, saves bank and account information, creates, removes, deposits, withdraws from the account of a person with the help of their SSN.
//Author: Harsh Mishra
//Date: 10/02/2022

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileInputStream;
import static java.lang.Integer.parseInt;
public class Bank {
    public String bankname;
    ArrayList<Transaction> transactionlist = new ArrayList<>();
    ArrayList<Account> accountlist;
    ArrayList<Customer> customerlist;

    public Bank(String bankname) {
        this.bankname = bankname;
        this.customerlist = new ArrayList();
        this.accountlist = new ArrayList();
        this.transactionlist = new ArrayList();
    }
    public void readData(String data) {
        Scanner inputStream = null;
        try {
            inputStream = new Scanner(new FileInputStream(data));
        } catch (FileNotFoundException e) {
            System.out.println("File not found or could not be opened.");
            System.exit(0);
        }
        int numCustomers = inputStream.nextInt();
        inputStream.nextLine();
        int i = 0;
        while (i < numCustomers) {
            i++;
            String customer = inputStream.nextLine();
            Scanner sc = new Scanner(customer);
            String[] arrOfStr = customer.split(",");
            String name = arrOfStr[0];
            String address = arrOfStr[1];
            int zip=Integer.parseInt(arrOfStr[2]);
            String ssn= arrOfStr[3];
            this.newCustomer(name, address, zip, ssn);

        }
        int numAccounts = inputStream.nextInt();
        inputStream.nextLine();
        int j = 0;
        while (j < numAccounts) {
            j++;
            String acc = inputStream.nextLine();
            Scanner sc = new Scanner(acc);
            String[] arrOfStr = acc.split(",");
            String ssn = arrOfStr[0];
            int accountnumber = Integer.parseInt(arrOfStr[1]);
            int type=Integer.parseInt(arrOfStr[2]);
            double amount= Double.parseDouble(arrOfStr[3]);
            this.newAccount(ssn, accountnumber, type, amount);
        }
    }

    public void bankInfo() {
        System.out.println(("Bank Name: ") + (this.bankname));
        System.out.println(("Number of Customers: ") + (this.customerlist.size()));
        for (Customer customer: this.customerlist) {
            System.out.println(("" + (customer.getname()) + ": "+(customer.getssn())));
        }
        double totalAmount = 0.0f;
        System.out.println(("Number of Accounts: ") + (this.accountlist.size()));
        for (Account account: this.accountlist) {
            System.out.println(("\t" + (account.getaccountnumber()) + ": $" + (account.getamount())));
            totalAmount += account.getamount();
        }
        System.out.println(("Total Balance: $") + (totalAmount));
    }

    public void accountInfo(int accountnumber) {
        int accID = this.checkAccountNumber(accountnumber);
        if (accID != -1) {
            System.out.println(("- Number:  ") + (this.accountlist.get(accID).getaccountnumber()));
            System.out.println(((("- ") + (this.AccountTypeIntToString(this.accountlist.get(accID).gettype())))));
            System.out.println(("- Balance:  $") + (this.accountlist.get(accID).getamount()));
            System.out.println(("- Customer:  ") + (this.customerlist.get(this.checkSSN(this.accountlist.get(accID).getssn())).getname()));
        } else
        {
            System.out.println("Account " + "(" + accountnumber + ")" + " does not exist.");
        }
    }
    public boolean closeAccount(int accountnumber) {


        String accountstatus ="Account Closed";
        int accID = this.checkAccountNumber(accountnumber);
        if (accID != 2) {
            String customerSSN = this.accountlist.get(accID).getssn();
            int type = this.accountlist.get(accID).gettype();
            double accountBalance = this.accountlist.get(accID).getamount();
            this.accountlist.remove(accID);
            if (type != 2) {
                this.customerlist.get(this.checkSSN(customerSSN)).setchecking(false);
            } else if (closeAccount(accountnumber)) {
                this.customerlist.get(this.checkSSN(customerSSN)).setsavings(false);
            }
            else {
                transactionlist.add(new Transaction(accountnumber, accountstatus, accountBalance));
            }
            transactionlist.add(new Transaction(accountnumber,accountstatus,accountBalance));

            return true;
        } else {
            System.out.println("Account remove failed - Account No: " + accountnumber + " does not exist.");
            return false;
        }
    }
    public void transaction(int accountnumber) {
        int flag = 0;
        for (Transaction tran : transactionlist) {
            for (int i = 0; i < transactionlist.size(); i++) {
                if (transactionlist.get(i).getaccountnumber() == accountnumber) {
                    flag =1;
                    System.out.println("-Account Number: " + transactionlist.get(i).getaccountnumber() + ", " + transactionlist.get(i).gettype() + " ($" + transactionlist.get(i).getamount() + ")" +","+ tran.getDate_tra());
                    break;
                }
            }
            if(flag==0) {
                System.out.println(" - No transaction for account " + accountnumber);
                break;
            }

        }
//        String accountstatus;
//        double accountBalance;
//        transactionlist.add(new Transaction(accountnumber,accountstatus,accountBalance));
    }

    public boolean removeCustomer(String ssn) {
        int customerID = this.checkSSN(ssn);
        if (customerID == 1) {
            String customername = this.customerlist.get(customerID).getname();
            String customerssn = this.customerlist.get(customerID).getssn();
            if (this.customerlist.get(customerID).haschecking()) {
                for (Account account: this.accountlist) {
                    if (account.getssn() == ssn &&
                            account.gettype() == 1) {
                        this.closeAccount(account.getaccountnumber());
                    }
                }
            }
            if (this.customerlist.get(customerID).hassavings()) {
                for (Account account: this.accountlist) {
                    if (account.getssn() == ssn &&
                            account.gettype() == 2) {
                        this.closeAccount(account.getaccountnumber());
                    }
                }
            }
            this.customerlist.remove(customerID);
            System.out.println("Customer removed - SSN: " + customerssn + ", Customer: "  + customername);
            return true;
        }


        else {
            System.out.println("Customer remove failed. SSN does not exist.");
            return false;
        }
    }
    public boolean deposit (int accountnumber, double amount) {
        String type ="Deposit";
//                Transaction.class.getName(String.time);
        String time = new String();
        int accID = this.checkAccountNumber(accountnumber);
        if (accID != -1) {
            this.accountlist.get(accID).setamount((float)(this.accountlist.get(accID).getamount() + amount));
//            time = time;
            transactionlist.add(new Transaction(accountnumber,type, (float) amount));
//            System.out.printf(getClass(new Transaction(time)));
        } else {
            System.out.println("Deposit Failed - Account No: " + accountnumber + " does not exist." + time);
        }
        return false;
    }

    public boolean withdraw(int accountnumber, double amount) {
        String type ="Withdraw";
        String time = new String();
        int accID = this.checkAccountNumber(accountnumber);
        if (accID != -1) {
            if (this.accountlist.get(accID).getamount() > amount) {
                this.accountlist.get(accID).setamount((float) (this.accountlist.get(accID).getamount() - amount));
                transactionlist.add(new Transaction(accountnumber,type,amount));
                return true;
            } else {
                System.out.println("Withdrawal Failed - Account No: " + accountnumber + " does not have sufficient balance.");
                return false;
            }
        } else {
            System.out.println("Deposit Failed - Account No: " + accountnumber + " does not exist.");
            return false;
        }
    }
    public void newCustomer(String name, String address, int zip, String ssn) {

        if (this.checkSSN(ssn) != -1){
            System.out.println("Bob Smith is added");
            System.out.println(name + " is not added - Existing customer with matching SSN in system.");
        } else {
            this.customerlist.add(new Customer(name, address, zip, ssn));
            // System.out.println(name + " is added.");
        }
    }
    private int checkSSN(String ssn) {
        if (customerlist.size() > 0) {
            for (Customer customer : customerlist) {
                if (customer.getssn().equals(ssn)) {
                    return customerlist.indexOf(customer);
                }
            }
        }
        return -1;
    }
    private int checkAccountNumber(int accountnumber) {
        if (accountlist.size() > 0) {
            for (Account account: accountlist) {
                if (account.getaccountnumber() == (accountnumber)) {
                    return accountlist.indexOf(account);
                }
            }
        }
        return -1;
    }
    private String AccountTypeIntToString(int type) {
        if (type == 1) {
            return "Checking";
        } else if (type == 2) {
            return "Savings";
        }
        return null;
    }

    public void newAccount(String ssn2, int accountnumber2, int type2, double amount2) {
        int customerID = this.checkSSN(ssn2);
        if (customerID != -1) {
            if (this.checkAccountNumber(accountnumber2) == -1) {
                if (type2 == 1 || type2 == 2) {
                    if ((!this.customerlist.get(customerID).haschecking() && type2 == 1) ||
                            (!this.customerlist.get(customerID).hassavings() && type2 == 2)) {
                        this.accountlist.add(new Account(ssn2, accountnumber2, type2, (float) amount2));
                        if (type2 == 1) {
                            this.customerlist.get(customerID).setchecking(true);
                        } else if (type2 == 2) {
                            this.customerlist.get(customerID).setsavings(true);
                        }
                        //   System.out.println("Account Creation - Number: " + accountnumber2 + ", Customer: " + this.customerlist.get(customerID).getname());
                    } else {
                        System.out.println("Account Creation Failed - " + this.customerlist.get(customerID).getname() + " already has two accounts");
                    }
                } else {
                    System.out.println("Wrong Account Type: " + type2);
                }
            } else {
                //    System.out.println("Account Creation Failed - Account " + accountnumber2 + " already exists");
            }
        } else {
            System.out.println(ssn2 + " is not a valid SSN.");
        }
    }



    public void customerInfoWithSSN(int ssn) {

        String tempSsn = "";
        for(int i=0; i < customerlist.size(); i++){
            int k = 0;
            tempSsn = customerlist.get(i).getssn();
            if(ssn == parseInt(tempSsn.substring(7))){
                for (int j = 0; j < accountlist.size(); j++) {
                    if(ssn == parseInt(tempSsn.substring(7))){
                        System.out.println("Name: " + customerlist.get(i).getname() +"\n" +  customerlist.get(i).getaddress() + "," + customerlist.get(i).getzip() +"\n" + "SSN:" + customerlist.get(i).getssn() + "\n" + accountlist.get(i).print()+ ": (" +accountlist.get(j).getaccountnumber() + "), $"+ accountlist.get(j).getamount());
                        break;
                    }


                }
//                    if(accountlist.get(i).gettype() == 2){
//                        System.out.println("Name: " + customerlist.get(i).getname() + "\n" + customerlist.get(i).getaddress() + "," + customerlist.get(i).getzip() + "\n" + "SSN:" + customerlist.get(i).getssn() + "\n" + accountlist.get(i).print()+ ": (" + accountlist.get(i).getaccountnumber() +"), $"+ accountlist.get(i).getamount());
//                    }


            }
            else {
                System.out.println("No accounts");
            }

        }


    }


//    public void transaction(int accountnumber) {
//            int l = 0;
//            while (l < transactionlist.size()) {
//                l++;
//            if(transactionlist.get(l).getaccountnumber() == accountnumber) {
//                System.out.println("Account Number:" + transactionlist.get(l).getaccountnumber() + ", " + transactionlist.get(l).gettype() + "($" + transactionlist.get(l).getamount() + ")");
//            }else{
//                System.out.println("No transaction for account" + accountnumber);
//                break;
//            }
//        }
//    }
}
//for (Account account: this.accountlist) {
//        boolean b1 = account.gettype() == 2;
//        boolean b = b1;
//        if (account.getssn() == ssn) {
//        } {
//        this.closeAccount(account.getaccountnumber());
//        }