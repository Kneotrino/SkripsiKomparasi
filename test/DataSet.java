import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedHashMap;

// Stores a set of instances
public class DataSet {

	// Ordered list of instances
	public ArrayList<Instance> instances;
	public ArrayList<Attribute> attributes;
	
	public DataSet() {
		instances = new ArrayList<Instance>();
		attributes = new ArrayList<Attribute>();
	}
	
	// Add instance to data set (add all attributes before doing this)
	public void addInstance(String line) {			
		ArrayList<String> features = new ArrayList<String>(Arrays.asList(line.split(",")));
		String label = features.remove(features.size() - 1);
		LinkedHashMap<String, String> values = new LinkedHashMap<String, String>();
		for (int i = 0; i < features.size(); i++)
			values.put(attributes.get(i).name, features.get(i));
		instances.add(new Instance(values, label));
	}
	
	// Add attribute to data set
	public void addAttribute(String line) {
            // Break line into attributes and values
		ArrayList<String> splitLine = new ArrayList<String>(Arrays.asList(line.split(" ")));
		splitLine.remove("@attribute");
		splitLine.remove("{");
		splitLine.remove("}");
		
                String name;
                ArrayList<String> values;
                try {                    
                    values = new ArrayList<String>();
                    name = splitLine.get(0);
                    String raw = splitLine.get(1);
//                    System.out.println("raw = " + raw);
                    if (raw.equals("numeric")) {
                        values.add(raw);
                        attributes.add(new Attribute(values, name));
                    }
                    else {
                        raw = raw.replace("{", "");
                        raw = raw.replace("}", "");
                        values = new ArrayList<String>(Arrays.asList(raw.split(",")));
                        System.out.println("kategorical = " + values);
                        attributes.add(new Attribute(values, name));
                    }
                    
            } catch (Exception e) {
            }             
	}
	
	// Developer tool: print data set
	public void print() {
		for (Attribute attribute : attributes)
			attribute.print();
		for (Instance instance : instances)
			instance.print();
	}
	
}
