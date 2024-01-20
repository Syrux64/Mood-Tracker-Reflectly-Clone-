package mt.moodtracker;


import java.io.*;
import java.util.LinkedList;

public class FileHandling {

//    static String fileName = "classes\\mt\\moodtracker\\Data\\data.txt";
    static String fileName = "src\\main\\resources\\mt\\moodtracker\\Data\\data.txt";

    static String filePath = System.getProperty("user.dir") + File.separator +  "src\\main\\resources\\mt\\moodtracker\\Data";
//    static String filePath = System.getProperty("user.dir") + File.separator + "classes\\mt\\moodtracker\\Data";


    public static void replaceLine(int lineNumber, String newString, String path) {
        try {
            File file = new File(filePath + File.separator + path);

            if (!file.exists()) {
                System.out.println("File not found: " + fileName);
                return;
            }

            // Use FileReader to read the file
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Create a temporary file to write the modified content
            File tempFile = new File("temp.txt");
            FileWriter fileWriter = new FileWriter(tempFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Replace the specified line with the new string
            String currentLine;
            int currentLineNumber = 0;
            while ((currentLine = bufferedReader.readLine()) != null) {
                currentLineNumber++;
                if (currentLineNumber == lineNumber) {
                    bufferedWriter.write(newString);
                } else {
                    bufferedWriter.write(currentLine);
                }
                bufferedWriter.newLine();
            }

            // Close the readers and writers
            bufferedReader.close();
            bufferedWriter.close();

            // Replace the original file with the modified content
            file.delete();
            tempFile.renameTo(file);
            System.out.println("File Path: " + fileName);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readLine(int lineNumber, String path) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath + path));

            String currentLine;
            int currentLineNumber = 0;

            while ((currentLine = bufferedReader.readLine()) != null) {
                currentLineNumber++;

                if (currentLineNumber == lineNumber) {
                    bufferedReader.close(); // Close the reader once the line is found
                    return currentLine;
                }
            }

            bufferedReader.close(); // Close the reader if the line is not found
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null; // Return null if an error occurs or the line is not found
    }


    public static void appendLine(String contentToAppend, String path) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath + File.separator + path, true));
            bufferedWriter.write(contentToAppend);
            bufferedWriter.newLine();
            bufferedWriter.close();

            //System.out.println("String appended successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveStringData(LinkedList<String> data, String path) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath + File.separator + path))) {
            for (String element : data) {
                writer.write(element);
                writer.newLine(); // Add a new line for each element
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static LinkedList<String> loadStringData(String path) {
        LinkedList<String> data = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath + path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void saveIntData(LinkedList<Integer> data, String path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath + path))) {
            for (Integer element : data) {
                writer.write(element.toString()); // Convert integer to string
                writer.newLine(); // Add a new line for each element
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static LinkedList<Integer> loadIntData(String path) {
        LinkedList<Integer> data = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath + File.separator + path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(Integer.parseInt(line)); // Convert string to integer
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }


    public static void saveStringArrayData(LinkedList<String[]> data, String path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath + File.separator + path))) {
            for (String[] array : data) {
                for (String element : array) {
                    writer.write(element);
                    writer.write(", "); // Add a separator (e.g., comma) between elements
                }
                writer.newLine(); // Add a new line after each array
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static LinkedList<String[]> readStringArrayData(String path) {
        LinkedList<String[]> data = new LinkedList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath + path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into an array using the comma as a separator
                String[] elements = line.split(", ");
                data.add(elements);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }


    public static void emptyFile(){
        String[] files = {"cards", "causes", "data", "moodEntry_data", "moodEntry_date", "moodEntry_day",
                          "moodEntry_month", "moodEntry_moodDescription", "moodEntry_moodTitle", "moodEntry_moodValue",
                          "moodEntry_time", "moodName", "noMoodEntriesArray", "reflected", "reflected_check", "reflected_date",
                          "reflected_day", "reflected_month", "reflected_time"
                         };
        for (String file:files){
            String filePathChange = filePath + File.separator + file + ".txt";

            try {
                // Create a FileWriter object with the file path
                FileWriter fileWriter = new FileWriter(filePathChange);
                fileWriter.close();

                System.out.println("Text file emptied successfully.");
            } catch (IOException e) {
                System.err.println("Error while emptying the text file: " + e.getMessage());
            }
        }
    }


}
