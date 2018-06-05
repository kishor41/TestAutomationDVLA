package filereader.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import filereader.enums.MimeType;
import filereader.pojo.FileDetails;
import filereader.pojo.VehicleDetails;

public class FileReaderServiceImplTest {

    @Rule
    public TemporaryFolder folderLevel1 = new TemporaryFolder();

    private File file1;
    private File file2;
    private File file3;
    private File file4;
    private File file5;
    @Rule
    public TemporaryFolder emptyFolder = new TemporaryFolder();

    private FileReaderService fileReaderService;

    private static String MIMETYPE_TXT = "text/plain";

    @Before
    public void setUp() throws Exception {

        file1 = folderLevel1.newFile("testfile1.csv");
        file2 = folderLevel1.newFile("testfile2.csv");
        file3 = folderLevel1.newFile("testfile3.csv");
        file4 = folderLevel1.newFile("testfile4.txt");
        file5 = folderLevel1.newFile("testfile5.xls");

        FileWriter fileWriter = new FileWriter(file1);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print("LT54XFN,HONDA,GRAY");
        printWriter.close();


        FileWriter fileWriter3 = new FileWriter(file3);
        PrintWriter printWriter3 = new PrintWriter(fileWriter3);
        printWriter3.print("WR12WMK,FORD,RED");
        printWriter3.close();

        FileWriter fileWriter5 = new FileWriter(file5);
        PrintWriter printWriter5 = new PrintWriter(fileWriter5);
        printWriter5.print("DA61WAL BMW WHITE");
        printWriter5.close();

        fileReaderService = new FileReaderServiceImpl();
    }


    @Test
    public void testOneDirectoryDown() throws Exception {
        File fileLevel1 = folderLevel1.newFile("level1.csv");
        FileWriter fileWriter = new FileWriter(fileLevel1);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print("RH54RPS,KIA,BLUE");
        printWriter.close();
        assertTrue(fileLevel1.exists());
        List<FileDetails> fileDetailsList = fileReaderService.getFileContents(folderLevel1.getRoot().getPath());
        assertEquals(4, fileDetailsList.size());
    }

    @Test
    public void testMimeTypeCSV () throws Exception {
        List<FileDetails> fileDetailsList = fileReaderService.getFileContents(folderLevel1.getRoot().getPath());
        FileDetails fileDetails = fileReaderService.getFileDetailsFromFile("testfile1.csv");
        assertEquals(MimeType.CSV,fileDetails.getMimeType());
    }

    @Test
    public void testMimeTypeExcel() throws Exception {
        List<FileDetails> fileDetailsList = fileReaderService.getFileContents(folderLevel1.getRoot().getPath());
        FileDetails fileDetails = fileReaderService.getFileDetailsFromFile("testfile5.xls");
        assertEquals(MimeType.EXCEL,fileDetails.getMimeType());
    }


    @Test
    public void testVehicleDetailsAddedCSV() throws Exception {
        List<FileDetails> fileDetailsList = fileReaderService.getFileContents(folderLevel1.getRoot().getPath());
        VehicleDetails vehicleDetails = fileReaderService.getVehicleDetailsFromFile("testfile3.csv");
        assertEquals("WR12WMK", vehicleDetails.getRegNumber());
        assertEquals("FORD", vehicleDetails.getMake());
        assertEquals("RED", vehicleDetails.getColour());
    }

    @Test
    public void testVehicleDetailsAddedXLS() throws Exception {
        List<FileDetails> fileDetailsList = fileReaderService.getFileContents(folderLevel1.getRoot().getPath());
        VehicleDetails vehicleDetails = fileReaderService.getVehicleDetailsFromFile("testfile5.xls");
        assertEquals("DA61WAL", vehicleDetails.getRegNumber());
        assertEquals("BMW", vehicleDetails.getMake());
        assertEquals("WHITE", vehicleDetails.getColour());
    }

}
