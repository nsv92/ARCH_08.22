package bridge;

public class Bridge {

    public static void main(String[] args) {

        DarkTheme darkTheme = new DarkTheme();
        LightTheme lightTheme = new LightTheme();

        About about = new About(darkTheme);
        Careers careers = new Careers(lightTheme);

        about.getContent();
        careers.getContent();

    }
}
