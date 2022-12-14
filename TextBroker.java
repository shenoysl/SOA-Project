
/*
TextBroker Utility Module
Author: Jordan Brodie 4/19/2022

*/
import java.io.*;
import java.util.*;

public class TextBroker {

    // TB PARM LIST ORDER <File Location>, <Key>, <"=" OR "<=" >
    public static void main(String args[]) {

        File textFile = new File(args[0]);

        String key = args[1];

        // Variables used for parsing data from text file
        BufferedReader br = null;
        String line = null;
        String[] data = null; // stores array that is returned from split function,

        try {
            br = new BufferedReader(new FileReader(textFile));

        } catch (FileNotFoundException e) {
            // CALL ERROR MESSAGE HANDLER "FILE NOT FOUND"
            System.out.print("File Not Found");
            System.exit(1);
        }

        try {

            // Switch statement for the last argument of the parameter
            switch (args[2]) {
                case "EQ":
                    // Search text file for string left of each comma thats EQUAL to the key from
                    // the parameter and return the data on the right.
                    while ((line = br.readLine()) != null) {

                        data = line.split(","); // data[0] = string left of comma, data[1] = string to the right
                        if (data[0].equals(key)) {
                            System.out.print(data[1]);
                            System.exit(0);
                        }

                    }
                    break;

                case "LTE":
                    // Search text file for number that is LESS THAN OR EQUAL to the key
                    while ((line = br.readLine()) != null) {

                        data = line.split(",");

                        if (data[0].equals("Over")) {
                            System.out.print(data[1]);
                            System.exit(0);

                        } else if (Integer.parseInt(key) <= Integer.parseInt(data[0])) {
                            System.out.print(data[1]);
                            System.exit(0);

                        }
                    }
                    break;

                // CALL ERROR MESSAGE HANDLER FOR LINE NOT FOUND

            }
            System.out.print("Line Not Found");
        } catch (IOException e) {
        }
        ;

    }
}