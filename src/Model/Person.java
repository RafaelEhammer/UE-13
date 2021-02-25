package Model;

public class Person
{
    private String name;
    private String address;
    private String number;

    public String getAddress()
    {
        return address;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getNumber()
    {
        return number;
    }
    public void setNumber(String number)
    {
        this.number = number;
    }
    public Person ()
    {
        setAddress("-");
        setName("-");
        setNumber("-");
    }
    public Person(String name, String address, String number)
    {
        this.name = name;
        this.address = address;
        this.number = number;
    }
    @Override
    public String toString()
    {
        return name + "," + address + "," + number;
    }
}