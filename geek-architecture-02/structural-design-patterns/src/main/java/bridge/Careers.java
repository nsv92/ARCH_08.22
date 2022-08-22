package bridge;

public class Careers implements WebPage {

    protected Theme theme;

    public Careers(Theme theme) {
        this.theme = theme;
    }

    @Override
    public void getContent() {
        System.out.println("Careers page in " + theme.getColour());
    }
}
