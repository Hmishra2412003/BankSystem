//Title: Customer
//Abstract: This program returns variables of a customer.
//Author: Harsh Mishra
//Date:09/30/2022

public class Customer {
    public String name;
    public String address;
    public int zip;
    public String ssn;
    public boolean savings;
    public boolean checking;

    public Customer (String name, String address, int zip, String ssn) {
        this.name = name;
        this.address = address;
        this.zip = zip;
        this.ssn = ssn;
        this.savings = false;
        this.checking = false;
    }

    String getname() {
        return this.name;
    }
    String getaddress() {

        return this.address;
    }

    int getzip() {

        return this.zip;
    }

    String getssn() {

        return this.ssn;
    }

    void setchecking(boolean val) {

        this.checking = val;
    }

    void setsavings(boolean val) {

        this.checking = val;
    }

    boolean haschecking() {

        return this.checking;
    }

    boolean hassavings() {

        return this.savings;
    }

}