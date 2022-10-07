package design.domain;

/**
 * @author radu.
 */
public class BaseEntity<ID> {

    // ID is a TEMPLATE argument, It can be anything
    private ID id;
    // id PROPERTY - PROPERTY means it has Getters and Setters

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    // toString method
    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                '}';
    }
}
