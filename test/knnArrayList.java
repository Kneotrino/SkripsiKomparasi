/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import neo.table.Dataset;
import neo.table.deskriptif;
import neo.table.naiveBayesPobabilitas;
import neo.utils.DatasetJpaController;
import neo.utils.methodUtil;
import static neo.utils.methodUtil.Normalisasi;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author SEED
 */
public class knnArrayList {
    
    public knnArrayList() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void hello() {
        DatasetJpaController djp = new DatasetJpaController(javax.persistence.Persistence.createEntityManagerFactory("analisiKomparasiPU"));
        List<Dataset> findDatasetEntities = djp.findDatasetEntities();
        List<Dataset> DataLatih = new LinkedList<>(findDatasetEntities.subList(0, 10000));
        List<Dataset> DataUji = new LinkedList<>(findDatasetEntities.subList(10000, 10010));
        
         knnArrayListTest(DataLatih, DataUji,5 );
        
        
     
     }

        deskriptif avaragehoursSTD;
        deskriptif timespendcompanySTD;
        deskriptif numberprojectSTD;
    public void knnArrayListTest(List<Dataset> DataLatih, List<Dataset> DataUji, int i) {
        System.out.println("dataLatih = " + DataLatih.size());
        System.out.println("dataUji = " + DataUji.size());
//        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "15");
        avaragehoursSTD = new deskriptif();
        timespendcompanySTD = new deskriptif();
        numberprojectSTD = new deskriptif();
        
        //Data statistik data latih
        DataLatih.stream().forEach((d) -> {
            avaragehoursSTD.addValue(d.getAvaragehours());
            timespendcompanySTD.addValue(d.getTimespendcompany());
            numberprojectSTD.addValue(d.getNumberproject());
        });    
        ArrayList<Dataset> arrayListDataUji = new ArrayList<>(DataUji);
        ArrayList<Dataset> arrayListDataLatih = new ArrayList<>(DataLatih);
        
        for (Dataset dataset : arrayListDataUji) {
            int indexOf = arrayListDataUji.indexOf(dataset);            
            System.err.println("indexOf = " + indexOf +"/"+arrayListDataUji.size());

            arrayListDataLatih
//                    .parallelStream()
                    .forEach((x) -> {
                x.setDistance(euclideanFunction(dataset, x));
            });
            DataLatih.sort((o2, o1) -> Double.compare(o2.getDistance(), o1.getDistance()));
            //Pilihan k terdekat
            List<Dataset> nearest = new LinkedList<>(DataLatih.subList(0, i));
            //vote
            double sumKelas = 0d;
            for (Dataset y : nearest) {
                sumKelas += y.getLeftsDouble();
            }

            double half = i/2d;
            if (sumKelas > half)
                dataset.setKelas(1d);
            else
                dataset.setKelas(0d);            
        }
    }
    public double euclideanFunction(Dataset y, Dataset x)
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

     
}
