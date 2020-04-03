//POJO -> plain old java object
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class OpenCSVReadAndParseToBean {
    private static final String SAMPLE_CSV_FILE_PATH = "./users_with_headres.csv";

    public static void main(String[] args) throws IOException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
        ) {
            CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                    .withType(CSVUser.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            Iterator<CSVUser> csvUserIterator = csvToBean.iterator();

            while (((Iterator) csvUserIterator).hasNext()) {
                CSVUser csvUser = csvUserIterator.next();
                System.out.println("Name : " + csvUser.getName());
                System.out.println("Email : " + csvUser.getEmail());
                System.out.println("PhoneNo : " + csvUser.getPhoneNo());
                System.out.println("Country : " + csvUser.getCountry());
            }
        }
    }
    // Reads all CSV contents into memory
    private static void readAllBeansAtOnce(CsvToBean<CSVUser> csvToBean) {
        List<CSVUser> csvUsers = csvToBean.parse();

        for(CSVUser csvUser: csvUsers) {
            System.out.println("Name : " + csvUser.getName());
            System.out.println("Email : " + csvUser.getEmail());
            System.out.println("PhoneNo : " + csvUser.getPhoneNo());
            System.out.println("Country : " + csvUser.getCountry());
        }
    }
}
