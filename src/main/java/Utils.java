import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class Utils {

    public static void writeToCSV(Job job){


        FileWriter csvWriter = null;
        try {
            csvWriter = new FileWriter("./linkedInJobs.csv", true);


            if(LinkedInJobSearch.isEmptyJobFile){
                csvWriter.append("JobTitle;Company;Location;Date;Link").append("\n");
            LinkedInJobSearch.isEmptyJobFile=false;}

                csvWriter.append(job.getTitle())
                        .append(";")
                        .append(job.getCompany())
                        .append(";")
                        .append(job.getLocation())
                        .append(";")
                        .append(job.getDate())
                        .append(";")
                        .append(job.getLink())
                        .append("\n");
                csvWriter.flush();



            csvWriter.close();
        } catch (IOException e) {
            System.out.println("==Error while writing to file==");
            e.printStackTrace();
        }
    }

    public static List<Job> parseCsvFile() {

        String csvFilePath = "./linkedinJobs.csv";

        List<Job> jobs = new ArrayList<>();
        BufferedReader br = null;
        String line = "";
        String csvDelimiter = ";";

        try {
            br = new BufferedReader(new FileReader(csvFilePath));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvDelimiter);
                String title = data[0];
                String company = data[1];
                String location = data[2];
                String date = data[3];
                String link = data[4];

                Job job = new Job(title, company, location, date, link);
                jobs.add(job);

            }
        } catch (IOException e) {
            System.out.println("No saved file");
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Saved rows number - "+jobs.size());
        if(!jobs.isEmpty()){
            LinkedInJobSearch.isEmptyJobFile=false;}
        return jobs;
    }
    public static void deleteDuplicates() {


        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("./linkedInJobs.csv"));

        Set<String> lines = new HashSet<String>(10000); // maybe should be bigger
            int rows = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                rows++;
                lines.add(line);
            }
            System.out.println("Rows before cleaning - "+rows);
            System.out.println("Rows after cleaning - "+lines.size());
            reader.close();
            BufferedWriter writer = new BufferedWriter(new FileWriter("./linkedInJobs.csv"));
            for (String unique : lines) {
                writer.write(unique);
                writer.newLine();
            }
            writer.close();
        } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
            e.printStackTrace();
        }
    }}

