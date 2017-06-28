package edu.frontrange.csc240.exam1;

import java.util.Random;
import java.util.Scanner;

public class AccountTester
{
private final Random randomNumbers = new Random();
private final Scanner userInput = new Scanner(System.in);

public void testAccountClass()
{
	System.out.println("\n\n*** Testing the Account Class ***");

	System.out.printf("\nCurrent Interest Rate: %.2f%%", Account.CURRENT_RATE *
			100.0);

	System.out.println("\n\nCreating Account Objects");
	Account accountOne = new Account(5000.0);
	Account accountTwo = new Account(accountOne);
	accountOne.displayValues("\nInitial Values for Account One:");
	accountTwo.displayValues("\nInitial Values for Account Two:");

	System.out.println("\n\nExercising Account Transactions");
	accountOne.credit(0.0);
	accountOne.debit(-6);
	accountTwo.credit(-1.50);
	accountTwo.debit(100.0);
	accountTwo.debit(5000);
	exerciseTransactions(accountOne, 99, 500.0);
	exerciseTransactions(accountTwo, 250.0);
	accountOne.displayValues("\n\nUpdated Values for Account One:");
	accountTwo.displayValues("\nUpdated Values for Account Two:");

	System.out.println("\n\nExercising Future Value Calculators");
	System.out.printf("   Calculation 1: $%.2f\n", accountOne.futureValue(6.5,
			0.05));
	System.out.printf("   Calculation 2: $%.2f\n", accountOne.futureValue(15,
			0.05));
	System.out.printf("   Calculation 3: $%.2f\n", accountTwo.futureValue(6.5));
	System.out.printf("   Calculation 4: $%.2f\n", accountTwo.futureValue(15));
	System.out.println("\n\n*** Testing Complete ***\n");
}

private void exerciseTransactions(Account thisAccount, int testCount,
		double limit)
{
	int count = 0;
	while( count < testCount )
	{
		if( (count % 3) != 0 )
			thisAccount.credit(getDataValue(limit));
		else
			thisAccount.debit(getDataValue(limit));
		count++;
	}
}

private void exerciseTransactions(Account thisAccount, double limit)
{
	char answer;
	displayMenu();
	do
	{
		answer = getUserCommand();
		switch( answer )
		{
		case 'D':
			thisAccount.debit(getUserValue(limit));
			break;
		case 'C':
			thisAccount.credit(getUserValue(limit));
			break;
		case 'V':
			thisAccount.displayValues();
			break;
		case 'M':
			displayMenu();
			break;
		}
	}
	while( answer != 'Q' );
}

private double getDataValue(double maxValue)
{
	return 1.0 + randomNumbers.nextDouble() * maxValue;
}

private double getUserValue(double limit)
{
	double thisValue;
	do
	{
		System.out.printf("Please enter a value (0.0 -  %.2f): ", limit);
		thisValue = userInput.nextDouble();
		if( !(thisValue >= 0.0 && thisValue <= limit) )
			System.out.printf("? Invalid input: %.2f\n", thisValue);
	}
	while( thisValue < 0.0 || thisValue > limit );
	return thisValue;
}

private void displayMenu()
{
	System.out.println("\n\nPlease enter one of the following:");
	System.out.println(" D - to test the debit method");
	System.out.println(" C - to test the credit method");
	System.out.println(" V - to display the values of the Account");
	System.out.println(" M - to re-display this menu");
	System.out.println(" Q - to exit this test\n");
}

private char getUserCommand()
{
	char thisChar;
	boolean goodChar;
	do
	{
		System.out.print("Enter a command letter: ");
		thisChar = userInput.next().toUpperCase().charAt(0);
		goodChar = (thisChar == 'D' || thisChar == 'C' || thisChar == 'M' ||
				thisChar == 'Q' || thisChar == 'V');
		if( !goodChar )
			System.out.println("That is not a valid command letter");
	}
	while( goodChar == false );
	return thisChar;
}
} 