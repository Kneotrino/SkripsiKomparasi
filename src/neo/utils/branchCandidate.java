/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neo.utils;

/**
 *
 * @author SEED
 */
public class branchCandidate {
    
    private String cabang;

    private Object fitur;

    private long countLeft;

    private long countNotLeft;

    private int totalData;

    /**
     * Get the value of totalData
     *
     * @return the value of totalData
     */
    public int getTotalData() {
        return totalData;
    }

    /**
     * Set the value of totalData
     *
     * @param totalData new value of totalData
     */
    public void setTotalData(int totalData) {
        this.totalData = totalData;
    }

    private long countFitur;

    private double infoGain;

    /**
     * Get the value of infoGain
     *
     * @return the value of infoGain
     */
    public double getInfoGain() {
        return infoGain;
    }

    /**
     * Set the value of infoGain
     *
     * @param infoGain new value of infoGain
     */
    public void setInfoGain(double infoGain) {
        this.infoGain = infoGain;
    }

    /**
     * Get the value of countFitur
     *
     * @return the value of countFitur
     */
    public long getCountFitur() {
        return countFitur;
    }
    
    public double getEntropyLeft()
    {                        
        Double temp = -getProporsiLeft()* log(getProporsiLeft(), 2);
        if (temp.isNaN() ) {
            return 0d;
        }
        return temp;
    }
    public double getEntropyNotLeft()
    {        
        Double temp = -getProporsiNotLeft()* log(getProporsiNotLeft(), 2);
        if (temp.isNaN() ) {
            return 0d;
        }
        return temp;
    }
    
    public double  getTotalEntropy()
    {
        return getEntropyLeft() + getEntropyNotLeft();
    }
    public  double log(double n, int base) {
        return Math.log(n) / Math.log(base);
    }  

    /**
     * Set the value of countFitur
     *
     * @param countFitur new value of countFitur
     */
    public void setCountFitur(long countFitur) {
        this.countFitur = countFitur;
    }
    
    public double getProporsiDariTotalData()
    {
        return (getCountLeft() + getCountNotLeft()) / (totalData *1d);
    }
    public double getTotalEntropyN()
    {
        return getProporsiDariTotalData()* getTotalEntropy();
    }

    @Override
    public String toString() {
        return "cabang = " + cabang
                + "\tfitur = " + fitur
//                + "\tN = " + totalData
                + "\tcountLeft =" + countLeft
                + "\tcountNotLeft =" + countNotLeft
                + "\tE(Left) =" + getEntropyLeft()
                + "\tE(notLeft) =" + getEntropyNotLeft()
                + "\tGain =" + getInfoGain()
//                + "\tP(Total) =" + getProporsiDariTotalData()
//                + "\tE(total) =" + getTotalEntropy()
//                + "\tE(total * N) =" + getTotalEntropyN()
//                + "\tP(Left) =" + getProporsiLeft()
//                + "\tP(notLeft) =" + getProporsiNotLeft()
                ;
                //To change body of generated methods, choose Tools | Templates.
    }

    private double totalEntropyClass;

    /**
     * Get the value of totalEntropyClass
     *
     * @return the value of totalEntropyClass
     */
    public double getTotalEntropyClass() {
        return totalEntropyClass;
    }

    /**
     * Set the value of totalEntropyClass
     *
     * @param totalEntropyClass new value of totalEntropyClass
     */
    public void setTotalEntropyClass(double totalEntropyClass) {
        this.totalEntropyClass = totalEntropyClass;
    }

    public branchCandidate(String cabang
            , Object fitur
            , long countLeft
            , long countNotLeft
            , int totalData
            , double totalEntropyClass
    
    ) {
        this.cabang = cabang;
        this.fitur = fitur;
        this.countLeft = countLeft;
        this.countNotLeft = countNotLeft;
        this.totalData = totalData;
        this.totalEntropyClass = totalEntropyClass;
    }

    
    /**
     * Get the value of countNotLeft
     *
     * @return the value of countNotLeft
     */
    public long getCountNotLeft() {
        return countNotLeft;
    }
    
    public double getGain(double sumEntropy)
    {
        return getTotalEntropyClass() - sumEntropy;
    }

    /**
     * Set the value of countNotLeft
     *
     * @param countNotLeft new value of countNotLeft
     */
    public void setCountNotLeft(long countNotLeft) {
        this.countNotLeft = countNotLeft;
    }

    /**
     * Get the value of countLeft
     *
     * @return the value of countLeft
     */
    public long getCountLeft() {
        return countLeft;
    }

    public double getProporsiLeft()
    {
        return getCountLeft() / (getCountFitur() * 1d);
    }
    public double getProporsiNotLeft()
    {
        return getCountNotLeft()/ (getCountFitur()* 1d);
    }
    /**
     * Set the value of countLeft
     *
     * @param countLeft new value of countLeft
     */
    public void setCountLeft(long countLeft) {
        this.countLeft = countLeft;
    }

    /**
     * Get the value of fitur
     *
     * @return the value of fitur
     */
    public Object getFitur() {
        return fitur;
    }

    /**
     * Set the value of fitur
     *
     * @param fitur new value of fitur
     */
    public void setFitur(Object fitur) {
        this.fitur = fitur;
    }

    /**
     * Get the value of cabang
     *
     * @return the value of cabang
     */
    public String getCabang() {
        return cabang;
    }

    /**
     * Set the value of cabang
     *
     * @param cabang new value of cabang
     */
    public void setCabang(String cabang) {
        this.cabang = cabang;
    }

}
