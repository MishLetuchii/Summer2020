package pack;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Catalog{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long uid;
    private String name;
    private String description;

    protected Catalog() {}

    public Catalog(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format(
                "Catalog[uid=%d, name='%s', description='%s']",
                uid, name, description);
    }
}