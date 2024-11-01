import java.util.ArrayList;
import java.util.Scanner;

public class Videoteca {
    private ArrayList<Film> filmAvailable;
    private ArrayList<User> userRegistered;

    public Videoteca() {
        filmAvailable = new ArrayList<>();
        userRegistered = new ArrayList<>();
        filmAvailable.add(new Film("harry Potter", 2000));
        filmAvailable.add(new Film("terminator", 1990));
        filmAvailable.add(new Film("la grande scommessa", 2005));
        filmAvailable.add(new Film("avatar", 2018));
    }

    public void addFilm(Film film) {
        filmAvailable.add(film);
    }

    public void removeFilm(String title) {
        for (int i = 0; i < filmAvailable.size(); i++) {
            if (filmAvailable.get(i).getTitle().equals(title)) {
                filmAvailable.remove(i);
                System.out.println("Film removed: " + title);
                return;
            }
        }
        System.out.println("Film not found: " + title);
    }

    public void registerUser() {
        Scanner scanner = new Scanner(System.in);
        boolean isRegistered = false;

        do {
            System.out.println("Register User");
            System.out.println("Insert ID of user");
            String idUser = scanner.nextLine();
            if(checkNumberInput(idUser)) {
                System.out.println("Insert Name of user");
                String nameUser = scanner.nextLine();
                if(checkStringInput(nameUser)) {
                    User registeredUser = new User(Long.valueOf(idUser), nameUser);
                    userRegistered.add(registeredUser);
                    System.out.println("User registered: " + registeredUser.getName());
                    isRegistered = true;
                }
            }
        } while (!isRegistered);
    }

    public boolean rentFilm(User user, Film film) {

        if (!userRegistered.contains(user)) {
            System.out.println("User not registered");
            return false;
        }

        for (Film availableFilm : filmAvailable) {
            if (availableFilm.getTitle().equals(film.getTitle())) {
                user.rentFilm(availableFilm);
                filmAvailable.remove(availableFilm);
                System.out.println("Film rented: " + availableFilm.getTitle());
                return true;
            }
        }

        System.out.println("Film not available");
        return false;
    }

    public void returnFilm(User user, Film film) {
        user.returnFilm(film);
        filmAvailable.add(film);
    }


    public ArrayList<Film> getFilmAvailable() {
        return filmAvailable;
    }

    public boolean isFilmAvailableByTitle(String title) {
        for (Film film : filmAvailable) {
            if (film.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }

    public User findUserById(long id) {
        for (User user : userRegistered) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
    public boolean checkNumberInput(String num) {

        if(checkStringInput(num)) {

            for(int i = 0; i < num.length(); i++) {

                if(!Character.isDigit(num.charAt(i))) {
                    System.out.println("L'input deve essere un numero");
                    return false;
                }
            }
            return true;
        }

        System.out.println("L'input non può essere vuoto");
        return false;
    }

    public boolean checkStringInput(String string) {

        String s = string.trim();
        if(s.isEmpty()) {
            System.out.println("L'input non può essere vuoto");
            return false;
        }

        return true;

    }
}
