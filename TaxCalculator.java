

/**************************************************************************************
 * Tax Calculator Module
 *
 * Component: Task Module
 ***************************************************************************************
 * Function:
 * Determines federal taxes 
 * ----------------------------------------------------------------------------------------------------------------------------------------
 * Input:
 * Parameters - Strings of tax year, filing status, and gross income
 * Output:
 * Return – tax that user will need to pay
 * ----------------------------------------------------------------------------------------------------------------------------------------
 * Author: Satya Shenoy
 * Reviewers: Jordan Brodie
 * 04/22/2022 CMCS 355
 **************************************************************************************/

import java.io.*;
import java.util.Scanner;

public class TaxCalculator {

	public static void main(String[] args) {

		/***************************************************************************
		 * Parse the command line for the user's tax year, income, and filing status
		 * Create a String variable to combine tax year and filing status (i.e. "2021S")
		 *************************************************************************/

		String year = args[0];

		String status = args[1];

		String income = args[2];

		String output;

		Process proc = null;

		String usertax = year + status;

		/***************************************************************************
		 * Call the service broker which to call the text broker using the user's
		 * parameters
		 *************************************************************************/

		try {

			proc = Runtime.getRuntime().exec("java service_broker TB " + usertax + ".txt " + income + " LTE");

			try {
				proc.waitFor();
			} catch (Exception e) {
			}
			;

			Scanner scan = new Scanner(proc.getInputStream());

			output = scan.nextLine();

			/***************************************************************************
			 * Checks for errors such as a tax year not being found
			 * If user enters a tax year that is not available, call error message handler
			 * with unique code and print out error message
			 * Otherwise, parse the tax rate from the text broker into a double
			 * Multiply the tax rate and the user income to get the final tax that user
			 * needs to pay
			 * Output how much user needs to pay
			 *************************************************************************/

			if (output.equals("File Not Found")) {

				proc = Runtime.getRuntime().exec("java service_broker Msg 903");

				scan = new Scanner(proc.getInputStream());

				output = scan.nextLine();

				System.out.println(output);

			}

			else {
				double percent = Double.parseDouble(output);

				double userincome = Double.parseDouble(income);

				double tax = percent * userincome;

				String taxstring = String.valueOf(tax);

				System.out.println(taxstring);

			}

		} catch (IOException e) {
		}

	}

}