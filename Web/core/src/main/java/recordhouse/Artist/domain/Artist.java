package recordhouse.Artist.domain;

import design.domain.BaseEntity;

public class Artist extends BaseEntity<String> {
    private String stageName;
    private String firstName;
    private String lastName;

    public Artist() {}

    public Artist(String id, String stageName, String firstName, String lastName) {
        this.setId(id);
        this.stageName = stageName;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getStageName(){
        return this.stageName;
    }

    public void setStageName(String stageName){
        this.stageName = stageName;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Artist artist = (Artist) o;

        if (!this.getId().equals(artist.getId())) return false;
        if (!stageName.equals(artist.stageName)) return false;
        if (!firstName.equals(artist.firstName)) return false;
        return lastName.equals(artist.lastName);

    }

    @Override
    public int hashCode() {
        int result = stageName.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Artist{" + "\n" +
                "   Stage Name = " + stageName + ",\n" +
                "   First Name = " + firstName + ",\n" +
                "   Last Name = " + lastName + "\n" +
                "} " + super.toString();
    }
}
