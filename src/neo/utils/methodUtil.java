/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neo.utils;

import java.util.LinkedList;
import java.util.List;
import neo.table.Dataset;
import neo.table.deskriptif;

/**
 *
 * @author SEED
 */
public class methodUtil {
    
    
    static deskriptif avaragehoursSTD;
    static deskriptif timespendcompanySTD;
    static deskriptif numberprojectSTD;
    

            
    public static List<Dataset> NBtrain(List<Dataset> dataLatih,List<Dataset> dataUji,int k)
    {        
        
        
        return dataUji;
    }
    public static List<Dataset> KNN(List<Dataset> dataLatih,List<Dataset> dataUji,int k)
    {        
        System.out.println("dataLatih = " + dataLatih.size());
        System.out.println("dataUji = " + dataUji.size());
        
        avaragehoursSTD = new deskriptif();
        timespendcompanySTD = new deskriptif();
        numberprojectSTD = new deskriptif();
        
        //Data statistik data latih
        dataLatih.stream().forEachOrdered((d) -> {
            avaragehoursSTD.addValue(d.getAvaragehours());
            timespendcompanySTD.addValue(d.getTimespendcompany());
            numberprojectSTD.addValue(d.getNumberproject());
        });
        
        for (Dataset d : dataUji) {
            //Hitung jarak d ke semua himpunan datalatih
            for (Dataset x : dataLatih) {
                x.setDistance(euclideanFunction(d, x));
            }
            
            //Temukan k tetanga terdekat terdeket
            //Sort
                dataLatih.sort((o2, o1) -> Double.compare(o2.getDistance(), o1.getDistance()));
                //Pilihan k terdekat
                List<Dataset> nearest = new LinkedList<>(dataLatih.subList(0, k));
                //vote
                double sumKelas = 0;
                sumKelas = nearest.stream().map((dataset) -> dataset.getLeftsDouble()).reduce(sumKelas, (accumulator, _item) -> accumulator + _item);
                if (sumKelas > k/2d) 
                    d.setKelas(1d);
                else
                    d.setKelas(0d);
                System.out.println("sumKelas = " + sumKelas);

        }
        return dataUji;
    }
    
    public static double euclideanFunction(Dataset y, Dataset x)
    {               
        //Kontinus var
        double satisfactionDelta = Math.pow(y.getSatisfaction() - x.getSatisfaction(), 2);
        double evaluationDelta = Math.pow(y.getEvaluation()- x.getEvaluation(), 2);
        //Boolean Variabel
        double workaccidentDelta = Math.pow(y.getWorkaccident()- x.getWorkaccident(), 2);
        double promotionDelta = Math.pow(y.getPromotion()- x.getPromotion(), 2);
        //Kategorical
        double DivisionDelta = y.getDivision().equals(x.getDivision())?1d:0d;
        double SalaryDelta = y.getSalary().equals(x.getSalary())?1d:0d;
        //Numeric
            Double NormalisasiAvarageHoursY = Normalisasi(y.getAvaragehours(), avaragehoursSTD);
            Double NormalisasiAvarageHoursX = Normalisasi(x.getAvaragehours(), avaragehoursSTD);
        double avaragehoursDelta = Math.pow(NormalisasiAvarageHoursY - NormalisasiAvarageHoursX, 2);
            Double NormalisasiNumberprojectY = Normalisasi(y.getNumberproject(), numberprojectSTD);
            Double NormalisasiNumberprojectX = Normalisasi(x.getNumberproject(), numberprojectSTD);
        double NumberprojectDelta = Math.pow(NormalisasiNumberprojectY - NormalisasiNumberprojectX, 2);
            Double NormalisasiTimespendcompanyY = Normalisasi(y.getTimespendcompany(), timespendcompanySTD);
            Double NormalisasiTimespendcompanyX = Normalisasi(x.getTimespendcompany(), timespendcompanySTD);
        double timespendcompanyDelta = Math.pow(NormalisasiTimespendcompanyY - NormalisasiTimespendcompanyX, 2);
                
        //Sum       
        double sum = 
                  satisfactionDelta 
                + evaluationDelta
                + workaccidentDelta
                + promotionDelta
                + DivisionDelta
                + SalaryDelta
                + avaragehoursDelta
                + NumberprojectDelta
                + timespendcompanyDelta
                ;
        return Math.sqrt(sum);
    }
    
    public static Double Normalisasi(Integer origin, deskriptif desk )
    {
        return (origin - desk.getMean()) / (desk.getMax() - desk.getMin());
    }
}
