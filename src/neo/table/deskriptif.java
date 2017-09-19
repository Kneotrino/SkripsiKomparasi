/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neo.table;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.descriptive.rank.Median;

/**
 *
 * @author SEED
 */
public class deskriptif extends DescriptiveStatistics{

    private String nameDesc;

    public static final String PROP_NAMEDESC = "nameDesc";

    /**
     * Get the value of nameDesc
     *
     * @return the value of nameDesc
     */
    public String getNameDesc() {
        return nameDesc;
    }
    public Double getQuartal1()
    {
                int q1 = (int) (this.getN()/4);         
                double[] sortedValues = getSortedValues();
                return sortedValues[q1];
    }
    public Double getQuartal2()
    {
                int q2 = (int) (this.getN()/2);         
                double[] sortedValues = getSortedValues();
                return sortedValues[q2];
    }
    public Double getQuartal3()
    {
                int q3 = (int) ( (this.getN()/4) * 3 );         
                double[] sortedValues = getSortedValues();
                return sortedValues[q3];
    }
    public double getMedian(){
        Median median = new Median();
        double medianValue = median.evaluate(this.getValues());
        return medianValue;
}

    private double modeValue;

    public static final String PROP_MODEVALUE = "modeValue";

    /**
     * Get the value of modeValue
     *
     * @return the value of modeValue
     */
    public double getModeValue() {
        return modeValue;
    }

    /**
     * Set the value of modeValue
     *
     * @param modeValue new value of modeValue
     */
    public void setModeValue(double modeValue) {
        double oldModeValue = this.modeValue;
        this.modeValue = modeValue;
        propertyChangeSupport.firePropertyChange(PROP_MODEVALUE, oldModeValue, modeValue);
    }
    /**
     * Set the value of nameDesc
     *
     * @param nameDesc new value of nameDesc
     */
    public void setNameDesc(String nameDesc) {
        String oldNameDesc = this.nameDesc;
        this.nameDesc = nameDesc;
        propertyChangeSupport.firePropertyChange(PROP_NAMEDESC, oldNameDesc, nameDesc);
    }

    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * Add PropertyChangeListener.
     *
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Remove PropertyChangeListener.
     *
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public deskriptif() {
    }

    public deskriptif(double[] initialDoubleArray) {
        super(initialDoubleArray);
    }
    

    private static final long serialVersionUID = -6146922200465878012L;
        public static void main(String[] args) {
            System.out.println("neo.table.deskriptif.main()");
            List<Double> testData = IntStream.range(1, 100)
                    .mapToDouble(d -> d)
                    .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
            deskriptif stat = new deskriptif();
                        
            testData.forEach((v) -> stat.addValue(v));
            double mean = stat.getMean();
            System.out.println("mean = " + mean);
//        neo.form.mainForm.main(args);
    }
}
