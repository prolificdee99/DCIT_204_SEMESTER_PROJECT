package app;

public class Locations {
    private String name;

    public Locations(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Locations){
            Locations other = (Locations) obj;
            return other.getName().equals(getName());
            }
        return false;
    }
    
    @Override
    public String toString() {
        return this.name;
    }    
}
