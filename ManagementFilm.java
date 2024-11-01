import java.util.Scanner;

public class ManagementFilm {
    private Videoteca videoteca;

    public ManagementFilm(Videoteca videoteca) {
        this.videoteca = videoteca;
    }

    public void startProgram() {

        Scanner scanner = new Scanner(System.in);
        boolean continueProgram = true;

        while (continueProgram) {

            System.out.println("Choose an option:");
            System.out.println("1. Add Film");
            System.out.println("2. Remove Film");
            System.out.println("3. Rent Film");
            System.out.println("4. Film Available");
            System.out.println("5. Rented Films List from user");
            System.out.println("6. Return rented Film");
            System.out.println("7. Exit");

            String chose = scanner.nextLine();

            if(videoteca.checkNumberInput(chose)) {
                switch (Integer.valueOf(chose)) {
                    case 1:
                        System.out.println("Insert Title of film to add");
                        String title = scanner.nextLine().toLowerCase();
                        System.out.println("Insert Year of the film");
                        String year = scanner.nextLine();
                        if(videoteca.checkNumberInput(year) && videoteca.checkStringInput(title)) {
                            if(videoteca.isFilmAvailableByTitle(title)){
                                System.out.println("Film already available");
                                break;
                            } else {
                                videoteca.addFilm(new Film(title, Integer.valueOf(year)));
                                System.out.println("Film added: " + title);
                            }
                        }

                        break;

                    case 2:
                        System.out.println("Insert Title of film to remove");
                        System.out.println("Available films: ");
                        for(Film film : videoteca.getFilmAvailable()) {
                            System.out.println(film.getTitle() + " (" + film.getYear() + ")");
                        }
                        String titleRemove = scanner.nextLine().toLowerCase();
                        videoteca.removeFilm(titleRemove);

                        break;

                    case 3:
                        System.out.println("Insert ID of user for rent");
                        String idUserRented = scanner.nextLine();

                        if(videoteca.checkNumberInput(idUserRented)) {

                            User userRented = videoteca.findUserById(Long.valueOf(idUserRented));
                            if (userRented != null) {

                                System.out.println("Available films:");
                                for (Film film : videoteca.getFilmAvailable()) {
                                    System.out.println(film.getTitle() + " (" + film.getYear() + ")");
                                }

                                System.out.println("Insert Title of film");
                                String titleRented = scanner.nextLine().toLowerCase();
                                Film filmToRent = new Film(titleRented);
                                boolean rented = videoteca.rentFilm(userRented, filmToRent);
                                if (!rented) {
                                    System.out.println("Failed to rent the film.");
                                }

                            } else {
                                System.out.println("User not found.");
                            }
                        }

                        break;

                    case 4:
                        System.out.println("Films available: ");
                        for(Film film : videoteca.getFilmAvailable()) {
                            System.out.println(film.getTitle() + " (" + film.getYear() + ")");
                        }

                        break;

                    case 5:
                        System.out.print("Insert ID of user: ");
                        String idUserRentedList = scanner.nextLine();
                        if(videoteca.checkNumberInput(idUserRentedList)) {
                            User userRentedList = videoteca.findUserById(Long.valueOf(idUserRentedList));
                            if (userRentedList != null) {
                                userRentedList.indexRentedFilms();
                            } else {
                                System.out.println("User not found.");
                            }
                        }

                        break;

                    case 6:
                        System.out.println("Insert ID of user: ");
                        String userId = scanner.nextLine();
                        if(videoteca.checkNumberInput(userId)) {
                            if(videoteca.findUserById(Long.valueOf(userId)) == null){
                                System.out.println("User not found.");
                                break;
                            } else {
                                User user = videoteca.findUserById(Long.valueOf(userId));
                                if(user.hasRentedFilms()){
                                    System.out.println("Insert film to return");
                                    String filmToReturn = scanner.nextLine().toLowerCase();
                                    Film filmReturn = user.findRentedFilmByTitle(filmToReturn);
                                    videoteca.returnFilm(user, filmReturn);
                                }
                            }
                        }

                        break;

                    case 7:
                        continueProgram = false;
                        break;

                    default:
                        System.out.println("Invalid option");
                }
            } else {
                startProgram();
            }
        }

        scanner.close();
    }
}
