import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class LinkedInJobSearch {

    public static Boolean isEmptyJobFile = true;
    public static void main(String[] args) throws InterruptedException {
       // Utils.parseCsvFile();
       // Utils.deleteDuplicates();
        parseLI();
    }
    public static void parseLI() throws InterruptedException {
    List<Job> savedJobs =   Utils.parseCsvFile();

    String requiredJobTitle = "performance%2Btest%2Bengineer";
    String requiredLocation = "United%2BStates";
    String geoId = "103644278";
    requiredLocation+="&sortBy=DD";

    int newRows = 0;
    for (int position = 0; position < 1000; position += 25) {
        String url = "https://www.linkedin.com/jobs-guest/jobs/api/seeMoreJobPostings/search?keywords="+requiredJobTitle+"&geoId="+geoId+"&location="+requiredLocation+"&start=" + position;

        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            System.out.println("Error while connecting to position "+position);
            continue;
        }
        Elements jobs = doc.select("li");

        for (Element job : jobs) {
            String jobTitle = job.select("h3.base-search-card__title").text().trim();
            String company = job.select("h4.base-search-card__subtitle").text().trim();
            String location = job.select("span.job-search-card__location").text().trim();
            String date = job.select("time.job-search-card__listdate").attr("datetime");
            String link = job.select("a.base-card__full-link").attr("href").split("\\?")[0];


            if((jobTitle.contains("Load")||
                    jobTitle.contains("load")||
                    jobTitle.contains("Performance")||
                    jobTitle.contains("performance"))&&!date.contains("2022")){

                if(savedJobs.stream().noneMatch(o -> o.getLink().equals(link))){

                    newRows++;
                    Utils.writeToCSV(new Job(jobTitle, company, location, date, link));}

            }
        }

        System.out.println("Position - "+position+", new rows - "+newRows);
        if(position==975){position=position-1;}
        Thread.sleep(5000);
    }

    Utils.deleteDuplicates();
}
}





