import java.util.ArrayList;

public class User {
    private long id;
    private String name;
    ArrayList<Film> filmsRent = new ArrayList<>();

    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void rentFilm(Film film) {
        filmsRent.add(film);
    }

    public void returnFilm(Film film) {
        boolean removed = filmsRent.remove(film);
        if (removed) {
            System.out.println("Film returned: " + film.getTitle());
        } else {
            System.out.println("Film not found in rented films.");
        }
    }

    public void indexRentedFilms() {
        if (filmsRent.isEmpty()) {
            System.out.println("No rented films.");
            return;
        }
        for (Film film : filmsRent) {
            System.out.println(film.getTitle() + " (" + film.getYear() + ")");
        }
    }

    public boolean hasRentedFilms() {
        if (filmsRent.isEmpty()) {
            System.out.println("No rented films.");
            return false;
        }
        for (Film film : filmsRent) {
            System.out.println("Rented films: " + film.getTitle() + " (" + film.getYear() + ")");
        }

        return true;
    }

    public Film findRentedFilmByTitle(String title) {

        for(int i = 0; i < filmsRent.size(); i++) {
            if(filmsRent.get(i).getTitle().equals(title)) {
                return filmsRent.get(i);
            }
        }

        return null;
    }
}
