
/***********************************************************************
 * Service Broker Module
***************************************************************************
 * Function: Facilitates the processes between programs
 **************************************************************************
 * Input: Parameters: service code, tax or translator
 * Output: Translated output or desired tax table
 * ***********************************************************************
 * Author: Alex Welk 
 * Reviewers: Jordan Brodie
 * Version: 4/21/22
 * CMSC 355
 *************************************************************************/
import java.io.*;
import java.lang.*;
import java.util.Scanner;

public class service_broker {
    public static void main(String[] args) throws IOException {
        // initializing variables
        File services = new File("Service-1.txt");
        String key = args[0];
        String output = null;
        Process proc = null;

        BufferedReader br = null;
        String line = null;
        String[] data = null; // stores array that is returned from split function,

        String parmString = "";
        for (int i = 1; i < args.length; i++) {
            parmString = parmString + args[i] + " "; //
        }

        // System.out.print(parmString); Stub

        try {
            br = new BufferedReader(new FileReader(services));

        } catch (FileNotFoundException e) {

        }

        while ((line = br.readLine()) != null) {

            data = line.split(",");
            if (data[0].equals(key)) {
                proc = Runtime.getRuntime().exec(data[1] + " " + parmString);
                try {
                    proc.waitFor(); ///
                } catch (Exception e) {
                }
                Scanner scan = new Scanner(proc.getInputStream()); //
                output = scan.nextLine();
                System.out.print(output); ///
                System.exit(0);
            }

        }
        proc = Runtime.getRuntime().exec("java ErrorHandler 703");
        try {
            proc.waitFor(); ///
        } catch (Exception e) {
        }
        Scanner scan = new Scanner(proc.getInputStream()); //

        output = scan.nextLine(); //
        System.out.println(output); //
        System.exit(0);
    }
}
