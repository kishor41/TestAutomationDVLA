package filereader.service;

import filereader.enums.MimeType;
import filereader.pojo.FileDetails;
import filereader.pojo.VehicleDetails;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.Scanner;


public final class FileReaderUtil {

    public static FileDetails setFileDetails(File file) throws IOException {

        FileDetails fileDetails = new FileDetails();
        fileDetails.setName(file.getName());
        fileDetails.setFileSize(file.length());

        //set the mimetype
        //get the mimetype from the file using the operating system to
        //detect it
        String mimeType = Files.probeContentType(file.toPath());
        //see if the mimetype is in the list of accepted ones
        //if not return null as we can't process the file any further
        try {
            fileDetails.setMimeType(MimeType.find(mimeType));
        } catch (IllegalStateException e){
            e.printStackTrace();
            return null;
        }

        //set the file extension
        String extension = "";
        int i = file.getName().lastIndexOf('.');
        if (i > 0) {
            extension = file.getName().substring(i+1);
        }
        fileDetails.setFileExtension(extension);

        //set the vehicle details
        VehicleDetails vd = setVehicleDetals(file);
        if(vd == null ) {
            return null;
        }
        fileDetails.setVehicleDetails(vd);
        return fileDetails;
    }

    
    private static VehicleDetails setVehicleDetals(File file) throws IOException{
        Scanner scanner = new Scanner(file);
        String line = "";
        String[] info;
        if(scanner.hasNext()) {
            line = scanner.nextLine();
        }
        scanner.close();
        //the separator is , for csv else it is space for xls files
        if(line.contains(",")) {
            info = line.split(",");
        } else {
            info = line.split("\\s+");
        }
        VehicleDetails vehicleDetails = new VehicleDetails();
        //if the line cannot be split into 3 tokens then we return null as we don't know
        //where to split the tokens otherwise
        if(info.length != 3) {
            return null;
        }
        //Store the details as uppercase with no white spaces
        vehicleDetails.setRegNo(info[0].toUpperCase().trim());
        vehicleDetails.setMake(info[1].toUpperCase().trim());
        vehicleDetails.setColour(info[2].toUpperCase().trim());

        return vehicleDetails;
    }
}
