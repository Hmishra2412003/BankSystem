//Title: Account
//Abstract: This program returns variables for accounts
//Author: Harsh Mishra
//Date:10/02/2022

public class Account {

    public String ssn;
    public final int accountnumber;
    public int type;
    public double amount;

    public Account(String ssn, int accountnumber, int type, double amount) {
        this.ssn = ssn;
        this.accountnumber = accountnumber;
        this.type = type;
        this.amount = amount;
    }

    public int getaccountnumber() {
        return accountnumber;
    }

    public double getamount() {

        return amount;
    }

    public int gettype() {

        return type;
    }

    public String getssn() {

        return ssn;
    }

    public void setamount(double amount) {

        this.amount = amount;
    }

    public String print(){

        if(type == 1){
            return "Checking";
        }else{
            return "Savings";
        }
    }
}