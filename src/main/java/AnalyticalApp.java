
import java.time.LocalDateTime;
import java.util.List;
import model.Record;
import service.FileReader;
import service.FileWriter;
import service.RecordParser;
import service.RecordService;
import service.impl.FileReaderImpl;
import service.impl.FileWriterImpl;
import service.impl.RecordParserImpl;
import service.impl.RecordServiceImpl;

public class AnalyticalApp {
    private static final String INPUT_FILE_PATH =
            "src/main/resources/input.txt";
    private static final String OUTPUT_FILE_PATH =
            "src/main/resources/output" + LocalDateTime.now() + ".txt";

    public static void main(String[] args) {

        FileReader reader = new FileReaderImpl();
        List<String> inputStrings = reader.readFromFile(INPUT_FILE_PATH);

        RecordParser parser = new RecordParserImpl();
        List<Record> records = parser.parse(inputStrings);

        RecordService recordService = new RecordServiceImpl();
        String result = recordService.doAnalytic(records);

        FileWriter writer = new FileWriterImpl();
        writer.writeToFile(result, OUTPUT_FILE_PATH);

        System.out.println("Analytic is done!\nThe processed data file is here: "
                + OUTPUT_FILE_PATH + "\n" + "Thank you for using my service.\n"
                + "Have a nice day!\nпутін хуйло!\nСлава Україні!");
    }
}
