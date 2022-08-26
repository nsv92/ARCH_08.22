package bridge;

public class About implements WebPage {

    protected Theme theme;

    public About(Theme theme) {
        this.theme = theme;
    }

    @Override
    public void getContent() {
        System.out.println("About page in " + theme.getColour());
    }

}
