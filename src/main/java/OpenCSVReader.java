import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class OpenCSVReader {
    //reading csv file into SAMPLE_CSV_FILE_PATH
    private static final String SAMPLE_CSV_FILE_PATH = "data.csv";

    public static void main(String[] args) throws IOException {
        readRecordsOneByOne();
        readAllRecordAtOnce();
    }
    //Function to read records one by one
    public static void readRecordsOneByOne() throws IOException {
        try (

                Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));//Paths.get method is associated with import java.nio.file.Paths
                //path.get method converts path string into path instance
                CSVReader csvReader = new CSVReader(reader); //CSVReader is inbuilt class in java
        ) {
            // Reading Records One by One in a String array
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                System.out.println("Name : " + nextRecord[0]);
                System.out.println("Email : " + nextRecord[1]);
                System.out.println("Phone : " + nextRecord[2]);
                System.out.println("Country : " + nextRecord[3]);
            }
        }
    }
    //Function to read all records at once
    private static void readAllRecordAtOnce() throws IOException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
                CSVReader csvReader = new CSVReader(reader);
        ) {
            // Reading All Records at once into a List<String[]>
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                System.out.println("Name : " + record[0]);
                System.out.println("Email : " + record[1]);
                System.out.println("Phone : " + record[2]);
                System.out.println("Country : " + record[3]);
            }
        }
    }
}

