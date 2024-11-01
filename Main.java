public class Main {

    public static void main(String[] args) {


        Videoteca videoteca = new Videoteca();
        videoteca.registerUser();
        ManagementFilm managementFilm = new ManagementFilm(videoteca);
        managementFilm.startProgram();
    }
}
