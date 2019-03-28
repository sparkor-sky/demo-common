package factory;

public class MaleCatFactory implements CatFactory {
    @Override
    public Cat createWhiteCat(String name) {
        return new WhiteMaleCat(name);
    }

    @Override
    public Cat createBlackCat(String name) {
        return new BlackMaleCat(name);
    }

    @Override
    public Cat createBrownCat(String name) {
        return new BrownMaleCat(name);
    }
}
