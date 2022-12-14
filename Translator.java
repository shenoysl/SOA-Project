
/**************************************************************************************
 * Translator Module
 *
 * Component: Task Module
 ***************************************************************************************
 * Function:
 * Translates a given english word into a selected foreign language
 * ----------------------------------------------------------------------------------------------------------------------------------------
 * Input:
 * Parameters - Strings of the foreign language and the english word
 * Output:
 * Return – the english word user entered alongside its translation
 * ----------------------------------------------------------------------------------------------------------------------------------------
 * Author: Satya Shenoy
 * Reviewers: Jordan Brodie
 * 04/22/2022 CMCS 355
 **************************************************************************************/


import java.io.*;
import java.util.Scanner;

public class Translator {

	public static void main(String[] args) {

		/***************************************************************************
		 * Parse the command line for the user's english word and the translation language
		 * *************************************************************************/

		String engword = args[1];

		String language = args[0];

		String output;

		Process proc = null;

		Scanner scan = null;

		/***************************************************************************
		 * Call the service broker to call the textbroker using the user's parameters
		 * *************************************************************************/

		try {

			proc = Runtime.getRuntime().exec("java service_broker TB " + language + ".txt " + engword + " EQ");

			try {
				proc.waitFor();
			} catch (Exception e) {
			}

			scan = new Scanner(proc.getInputStream());

			output = scan.nextLine();


		/***************************************************************************
		 * Check for errors such as a language not being found or a word not being found
		 * If such an error exists, call service broker to call the error message handler 
		 * Use unique error code and print out error message
		 * Otherwise, print out the user's english word and the foreign word
		 * *************************************************************************/

			
			  if(output.equals("File Not Found")){
			 
			 proc = Runtime.getRuntime().exec("java service_broker Msg 805 " + engword + " EQ");
			 
			  scan = new Scanner(proc.getInputStream());
			  
			  output = scan.nextLine();
			 
			 System.out.println(output);
			 
			 
			 
			  }
			  else if(output.equals("Line Not Found")){
			 
			  proc = Runtime.getRuntime().exec("java service_broker Msg 813 " + engword +" EQ");
			  
			  scan = new Scanner(proc.getInputStream());
			  
			  output = scan.nextLine();
			  
			  System.out.println(output);
			  
			  
			  }
			  
			  else{
			  
			  
			  System.out.println(engword+" "+output);
			  
			  
			  }
			 


		}

		catch (IOException e) {

		}

	}

}