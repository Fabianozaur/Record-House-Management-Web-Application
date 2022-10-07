package design.persistance.xml;

import javax.xml.bind.annotation.XmlAnyElement;
import java.util.ArrayList;
import java.util.List;

public class XmlModelCollection<T extends XmlModel> implements XmlModel {

    @XmlAnyElement(lax = true)
    private List<T> collection;

    public XmlModelCollection() {
    }

    public XmlModelCollection(List<T> collection) {
        this.collection = new ArrayList<>();
        this.collection.addAll(collection);
    }

    public List<T> GetItems() {
        return collection;
    }
}
