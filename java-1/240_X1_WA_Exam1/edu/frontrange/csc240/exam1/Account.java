package edu.frontrange.csc240.exam1;

public class Account
{
private double balance;
private int credits;
private int debits;
private double totalCredits;
private double totalDebits;
public static final double CURRENT_RATE = 0.045;

public Account(double initialBalance)
{
	if( initialBalance >= 0.0 )
		balance = initialBalance;
	else
		showMessage("? Incorrect initial balance: $%.2f\n", initialBalance);
}

public Account(Account fromAccount)
{
	balance = fromAccount.getBalance();
}

public void credit(double amount)
{
	if( amount > 0.0 )
	{
		balance += amount;
		totalCredits += amount;
		credits++;
	}
	else
		showMessage("? Invalid amount for a Credit: $%.2f\n", amount);
}

public void debit(double amount)
{
	if( amount > 0.0 )
		if( balance >= amount )
		{
			balance -= amount;
			totalDebits += amount;
			debits++;
		}
		else
			showMessage("? Debit of $%.2f exceeds balance of $%.2f", amount,
					balance);
	else
		showMessage("? Invalid amount for a Debit: $%.2f\n", amount);
}

public double getBalance()
{
	return balance;
}

public void displayValues()
{
	displayValues("Account Values:");
}

public void displayValues(String displayMessage)
{
	showMessage(displayMessage);
	showMessage("   Balance is $%.2f\n", balance);
	showMessage("   %d credits totaling $%.2f\n", credits, totalCredits);
	showMessage("   %d debits totaling $%.2f\n", debits, totalDebits);
}

public double futureValue(double years, double rate)
{
	return balance * Math.pow(1.0 + rate, years);
}

public double futureValue(int years, double rate)
{
	double amount = balance;
	for( int count = 0; count < years; count++ )
		amount += (amount * rate);
	return amount;
}

public double futureValue(double years)
{
	return balance * Math.pow(1.0 + CURRENT_RATE, years);
}

private void showMessage(String message)
{
	System.out.println(message);
}

private void showMessage(String message, double firstValue)
{
	System.out.printf(message, firstValue);
}

private void showMessage(String message, double firstValue, double secondValue)
{
	System.out.printf(message, firstValue, secondValue);
}

private void showMessage(String message, int firstValue, double secondValue)
{
	System.out.printf(message, firstValue, secondValue);
}
} 
