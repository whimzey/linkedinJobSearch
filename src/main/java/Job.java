import java.util.Objects;

class Job {
    private final String title;
    private final String company;
    private final String location;
    private final String link;
    private final String date;

    public Job(String title, String company, String location, String date, String link) {
        this.title = title;
        this.company = company;
        this.location = location;
        this.link = link;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return getTitle().equals(job.getTitle()) && getCompany().equals(job.getCompany()) && getLocation().equals(job.getLocation()) && getLink().equals(job.getLink());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getCompany(), getLocation(), getLink());
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    }

    public String getLink() {
        return link;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Job{" +
                "link='" + link + '\'' +
                '}';
    }
}
