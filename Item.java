
/**
 * Write a description of class Item here.
 *
 * @author M. Schuringa
 * @version 0.1
 */
public class Item
{
    private String name;
    private String description;
    private double weight;
    
    /**
     * Creat a new item with the give descriptions and weight.
     */
    public Item(String name, String description, double weight)
    {
        this.name = name;
        this.description = description;
        this.weight = weight;
    }
    
    /**
     * Return the item's name.
     * @return The name.
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Return the item's weight.
     * @return The weight.
     */
    public double getWeight()
    {
        return weight;
    }
    
    /**
     * Return a description of the item.
     * @return A description
     */
    public String getDescription()
    {
        return description;
    }
}
