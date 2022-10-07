package recordhouse.RecordHouse.Domain;

import design.domain.BaseEntity;

/**
 * The type Record house.
 */
public class RecordHouse extends BaseEntity<String> {

    private String name;
    private int foundingYear;

    /**
     * Instantiates a new Record house.
     *
     * @param id   the id
     * @param name the name
     * @param year the year
     */
    public RecordHouse(String id, String name, int year)
    {
        this.foundingYear = year;
        this.name = name;
        this.setId(id);
    }

    /**
     * Instantiates a new Record house.
     */
    public RecordHouse(){
    }


    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() { return this.name;}

    /**
     * Gets founding year.
     *
     * @return the founding year
     */
    public int getFoundingYear() { return this.foundingYear;}

    /**
     * Sets name.
     *
     * @param newName the new name
     */
    public void setName(String newName) {  this.name = newName; }

    /**
     * Sets founding year.
     *
     * @param newFoundingYear the new founding year
     */
    public void setFoundingYear(int newFoundingYear) { this.foundingYear = newFoundingYear; }

    @Override
    public boolean equals(Object o) {

        if(this == o){
            return true;
        }

        RecordHouse recordHouse = (RecordHouse) o;

        if(!recordHouse.getId().equals(this.getId()))
            return false;

        if(this.getFoundingYear() != recordHouse.getFoundingYear())
            return false;

        return name.equals(recordHouse.getName());
    }

    @Override
    public int hashCode()
    {
        int result = this.getId().hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + foundingYear;
        return result;
    }

    @Override
    public String toString()
    {
        return "Record House {" +
                "Id = " + this.getId() +
                " ;Name = " + this.name +
                " ;Founding Year = " + this.foundingYear + "} " + super.toString() + "\n" ;
    }




}
