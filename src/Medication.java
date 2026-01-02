public class Medication implements Billable 
{
    private String name;
    private double price;
    private String category;
    private String form;

    // default constructor
    public Medication() {
        this.name = "";
        this.price = 0.0;
        this.category = "";
        this.form = "";
    }

    // constructor
    public Medication(String name, double price, String category, String form) 
    {
        this.name = name;
        this.price = price;
        this.category = category;
        this.form = form;
    }

    // setter and getter
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public double getPrice()
    {
        return price;
    }
    public void setPrice(double price)
    {
        this.price = price;
    }

    public String getCategory()
    {
        return category;
    }
    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getForm()
    {
        return form;
    }
    public void setForm(String form)
    {
        this.form = form;
    }

    // interface method
    @Override
    public double getFee() {
        return price;
    }

    @Override
    public String toString() {
        return name + " - RM " + price;
    }
}