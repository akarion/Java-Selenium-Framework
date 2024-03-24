package frameworkUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Arguments {

    private static final String defaultArguments = "--disable-notifications\n--disable-gpu";
    private static final String argumentsFile = "src/main/java/configuration/browser.arguments";

    public static void addArgument(String arg) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("src/main/java/configuration/browser.arguments", true);
            fileWriter.write("\n" + arg + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("No arguments file found");
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static List<String> readArguments() {
        //this method reads arguments so that they could be added to ChromeOptions

        File propertyFileName = new File(argumentsFile);
        List<String> arguments = new ArrayList<>();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(propertyFileName));

            String line;
            while ((line = reader.readLine()) != null) {
                arguments.add(line);
            }
        } catch (IOException e) {
            System.out.println("No arguments file found");
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return arguments;
    }

    public static void resetArguments() {
        //this method will restore the original arguments
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(argumentsFile);
            fileWriter.write(defaultArguments);
        } catch (IOException e) {
            System.out.println("No arguments file found");
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
