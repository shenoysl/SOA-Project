//package ErrorHandler;

//import com.sun.xml.internal.ws.util.StringUtils;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**************************************************************************************
 * Error Message Module
 *
 * Component: Utility Module
 ***************************************************************************************
 * Function:
 * Returns error message in desired
 * ----------------------------------------------------------------------------------------------------------------------------------------
 * Input:
 * Parameters - <Error Code>
 * Output:
 * Return – error message
 * ----------------------------------------------------------------------------------------------------------------------------------------
 * Author Daniel Tran
 * Reviewers: Jordan Brodie
 * 04/20/2022 CMCS 355
 **************************************************************************************/

public class ErrorHandler {

    public static void main(String[] args) {

        // String language = args[0];
        String error = args[0];

        String str;
        String errorFile = null;
        String output;

        Process proc = null;
        Scanner scan = null;

        // Open the options file and find the error file that corresponds to the
        // language
        try {
            File optionsFile = new File("options.txt");
            FileReader fr = new FileReader(optionsFile);
            BufferedReader br = new BufferedReader(fr);

            while ((str = br.readLine()) != null) {

                String line = str;

                String strPattern = "\"[^\"]*\"";

                Pattern pattern = Pattern.compile(strPattern);
                Matcher matcher = pattern.matcher(line);

                while (matcher.find()) {
                    StringBuilder copyString = new StringBuilder("");
                    errorFile = matcher.group();
                    copyString.append(errorFile);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            proc = Runtime.getRuntime().exec("java service_broker TB " + errorFile + " " + error + " EQ");
            try {
                proc.waitFor();
            } catch (Exception e) {
            }

            scan = new Scanner(proc.getInputStream());

            output = scan.nextLine();
            System.out.print(output);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}