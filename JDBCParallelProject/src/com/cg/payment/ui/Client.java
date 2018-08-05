package com.cg.payment.ui;

import java.sql.Date;
import java.util.Scanner;

import com.cg.payment.Exception.PaymentException;
import com.cg.payment.bean.Payment;
import com.cg.payment.service.IPaymentService;
import com.cg.payment.service.PaymentService;

public class Client {
	Scanner sc = new Scanner(System.in);
	IPaymentService service = new PaymentService();

	public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	Client client = new Client();
	String opt = "";
	while (true) {
	System.out.println("1. Create Account\n2. Show balance\n3. Deposit amount\n"
	+ "4. Withdraw amount\n5. Transfer\n6. Transaction\n7. Exit");
	System.out.println("Enter choice:");
	opt = sc.nextLine();
	switch (opt) {
	case "1":
	client.accountCreation();
	break;
	case "2":
	client.showBalance();
	break;
	case "3":
	client.depositAmount();
	break;
	case "4":
	client.withdrawAmount();
	break;
	case "5":
	client.fundTransfer();
	break;
	case "6":
	client.printTransaction();
	break;
	case "7":
	sc.close();
	System.exit(0);
	break;
	default:
	System.out.println("Enter Correct option");
	break;
	}
	}
	}

	private void accountCreation() {
	Payment acc = new Payment();
	Client client = new Client();

	System.out.println("Enter Name:");
	acc.setName(client.sc.nextLine());
	System.out.println("Enter Mobile Number:");
	acc.setMobileNo(client.sc.nextLine());
	System.out.println("Enter Email ID:");
	acc.setEmail(client.sc.nextLine());
	System.out.println("Enter current balance:");
	acc.setBalance(Double.parseDouble(client.sc.nextLine()));
	//acc.setDate(""+ new Date());

	try {
	String id = service.createAccount(acc);
	System.out.println(id + " has been added successfully");
	} catch (PaymentException e) {
	System.out.println();
	System.err.println(e.getMessage());
	System.out.println();
	}

	}

	private void showBalance() {
	System.out.println("Enter mobile number:");
	String mobile = sc.nextLine();

	try {
	double bal = service.showBalance(mobile);
	System.out.println("Current balance:" + bal);
	} catch (PaymentException e) {
	System.out.println();
	System.err.println(e.getMessage());
	System.out.println();
	}
	}

	private void depositAmount() {
	System.out.println("Enter mobile number");
	String mobile = sc.nextLine();
	try {
	System.out.println("Enter amount to be deposited:");
	double amount = Double.parseDouble(sc.nextLine());
	Payment acc = service.deposit(mobile, amount);
	System.out.println("Account with mobile id:" + mobile
	+ " has been deposited with " + amount);
	System.out.println("Account Details:" + acc);

	} catch (PaymentException e) {
	System.out.println();
	System.err.println(e.getMessage());
	System.out.println();
	}

	}

	private void withdrawAmount() {
	System.out.println("Enter mobile number");
	String mobile = sc.nextLine();
	try {
	System.out.println("Enter amount to be withdrawn:");
	double amt = Double.parseDouble(sc.nextLine());
	Payment acc = service.withdraw(mobile, amt);
	System.out.println("Account with mobile id:" + mobile
	+ " has been withdrawn with " + amt);

	} catch (PaymentException e) {
	System.out.println();
	System.err.println(e.getMessage());
	System.out.println();
	}

	}

	private void fundTransfer() {
	System.out.println("Enter your mobile no:");
	String m1 = sc.nextLine();
	System.out.println("Enter receivers mobile number:");
	String m2 = sc.nextLine();
	System.out.println("Enter Transfer Amount:");
	double amount = Double.parseDouble(sc.nextLine());
	try {
	boolean res = service.fundTransfer(m1, m2, amount);
	if (res)
	System.out.println("Fund Transferred");

	} catch (PaymentException e) {
	System.out.println();
	System.err.println(e.getMessage());
	System.out.println();
	}

	}

	private void printTransaction() {
	System.out.println("Enter Mobile number");
	String mobile = sc.nextLine();
	try {
	service.printTransactionDetails(mobile);
	} catch (PaymentException e) {
	System.out.println();
	System.err.println(e.getMessage());
	System.out.println();
	}

	}
}
