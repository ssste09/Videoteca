public class Film {
    private String title;
    private int year;

    public Film(String title, int year) {
        this.title = title;
        this.year = year;
    }

    public Film(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String toString() {
        return title + " (" + year + ")";
    }
}
