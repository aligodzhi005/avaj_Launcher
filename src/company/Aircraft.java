package company;

public abstract class Aircraft {
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    private static long idCounter = 0;

    protected Aircraft(String name, Coordinates coordinates) {
        this.name = name;
        this.coordinates = coordinates;
        this.id = nextId();
    }

    private long nextId() {
        return ++idCounter;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
