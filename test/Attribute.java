
import java.util.List;


// Holds data for an attribute
public class Attribute {
	
	public final String name;
	public final List<String> values;
	public final boolean numeric;
	public final boolean label;
	
	public Attribute(List<String> values, String name) {

                this.name = name;
		this.values = values;
//                System.out.print("name = " + name);
//                System.out.println("\t values = " + values);
//		if (values.size() == 1 && values.get(0).equals("real"))
//			numeric = true;
//		else 
//			numeric = false;
		if (values.size() == 1 && values.get(0).equals("numeric"))
			numeric = true;
		else 
			numeric = false;
		if (name.equals("leftsString"))
			label = true;
		else 
			label = false;
	}
	
	// Developer tool: print instance
	public void print() {
		if (numeric)
			System.out.println(name + ": numeric");
		else
			System.out.println(name + ": " + values);
	}

    @Override
    public String toString() {
        return "Attribute{" + "name=" + name + ", numeric=" + numeric + ", label=" + label + '}';
    }
        
	
}
