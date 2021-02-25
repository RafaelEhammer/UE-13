package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.List;

public class Phonebook
{
    private List<Person> phonebook = new LinkedList<Person>();

    public void save()
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Phonebook.csv")))
        {
            for (int i = 0; i < phonebook.size(); i++)
            {
                bw.write(phonebook.get(i).toString());
                bw.newLine();
            }
            System.out.println("saved succesfully");
        }
        catch(Exception e)
        {
            System.out.println("Error while saving");
        }
    }
    public void load ()
    {
        phonebook.clear();
        try(BufferedReader br = new BufferedReader(new FileReader("Phonebook.csv")))
        {
            String s = "";
            while((s = br.readLine()) != null)
            {
                String split[] = s.split(",");
                phonebook.add(new Person(split[0], split[1], split[2]));
            }
            System.out.println("loaded succesfully");
        }
        catch(Exception e)
        {
            System.out.println("Error while loading");
        }
    }
    public void saveChanges (String name, String address, String phone, int index)
    {
        Person p = phonebook.get(index);
        p.setName(name);
        p.setAddress(address);
        p.setNumber(phone);
    }
    public void newSite()
    {
        phonebook.add(new Person());
    }
    public Phonebook()
    {
        this.newSite();
    }
    public Person getPerson(int index)
    {
        Person p = null;
        try
        {
            p = phonebook.get(index);
        }
        catch (Exception e)
        {

        }
        return p;
    }
    public int getSize()
    {
        return phonebook.size();
    }
    public void delete (int index)
    {
        try
        {
            phonebook.remove(index);
        }
        catch (Exception e)
        {
            System.out.println("Error while deleting");
        }
    }
}