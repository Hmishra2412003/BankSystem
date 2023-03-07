//Title: Transaction
//Abstract: This program returns variables for transactions.
//Author: Harsh Mishra
//Date: 09/29/2022


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Transaction {
    //    private final String time;
    public int accountnumber;
    public String type;
    public double amount;
    private String Date_tran;



    public Transaction(int accountnumber, String type, double amount) {
//        Date_tran = trasaction();
        this.accountnumber = accountnumber;
        this.type = type;
        this.amount = amount;
//        public String {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm:ss");
        Date_tran = now.format(formatter);
//            System.out.println(formatDateTime);
//            return formatDateTime;
    }
//    }


    public String getDate_tra() {

        return Date_tran;
    }

    public String trasaction(){

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm:ss");
        String formatDateTime = now.format(formatter);
        System.out.println(formatDateTime);
        return formatDateTime;
    }

    public int getaccountnumber() {

        return accountnumber;
    }

    public String gettype() {

        return type;
    }

    public double getamount() {

        return amount;
    }

}