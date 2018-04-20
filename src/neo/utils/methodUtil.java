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
import neo.table.naiveBayesPobabilitas;

/**
 *
 * @author SEED
 */
public class methodUtil {
    
    
    static deskriptif avaragehoursSTD;
    static deskriptif timespendcompanySTD;
    static deskriptif numberprojectSTD;
    

            
    public static List<naiveBayesPobabilitas> NBtrain(List<Dataset> dataLatih,int k)
    {        
        List<naiveBayesPobabilitas> tempTrain = new LinkedList<>();
        System.out.println("Laplace Smoothing K = " + k);
        avaragehoursSTD = new deskriptif();
        timespendcompanySTD = new deskriptif();
        numberprojectSTD = new deskriptif();
        
        //Data statistik data latih
        dataLatih.stream().forEachOrdered((d) -> {
            avaragehoursSTD.addValue(d.getAvaragehours());
            timespendcompanySTD.addValue(d.getTimespendcompany());
            numberprojectSTD.addValue(d.getNumberproject());
        });        
        naiveBayesPobabilitas workAccidentTrue = new naiveBayesPobabilitas("workAccident;True");
        naiveBayesPobabilitas workAccidentFalse = new naiveBayesPobabilitas("workAccident;False");
        
        naiveBayesPobabilitas promotionTrue = new naiveBayesPobabilitas("promotion;True");
        naiveBayesPobabilitas promotionFalse = new naiveBayesPobabilitas("promotion;False");
        
        naiveBayesPobabilitas avaragehoursTrue = new naiveBayesPobabilitas("avaragehours;True");
        naiveBayesPobabilitas avaragehoursFalse = new naiveBayesPobabilitas("avaragehours;False");

        naiveBayesPobabilitas timespendcompanyTrue = new naiveBayesPobabilitas("timespendcompany;True");
        naiveBayesPobabilitas timespendcompanyFalse = new naiveBayesPobabilitas("timespendcompany;False");
        
        naiveBayesPobabilitas numberprojectTrue = new naiveBayesPobabilitas("numberproject;True");
        naiveBayesPobabilitas numberprojectFalse = new naiveBayesPobabilitas("numberproject;False");
        
        tempTrain.add(workAccidentTrue);
        tempTrain.add(workAccidentFalse);
        tempTrain.add(promotionTrue);
        tempTrain.add(promotionFalse);
        tempTrain.add(avaragehoursTrue);
        tempTrain.add(avaragehoursFalse);
        tempTrain.add(timespendcompanyTrue);
        tempTrain.add(timespendcompanyFalse);
        tempTrain.add(numberprojectTrue);
        tempTrain.add(numberprojectFalse);
                
        //hitung kejadian
        for (Dataset dataset : dataLatih) {
            counterNaiveBayes(workAccidentTrue,workAccidentFalse,dataset.getWorkaccident()==1?true:false,dataset.getLefts());                            
            counterNaiveBayes(promotionTrue,promotionFalse,dataset.getPromotion()==1?true:false,dataset.getLefts());                            
            
            counterNaiveBayes(avaragehoursTrue,
                    avaragehoursFalse,
                    dataset.getAvaragehours()>avaragehoursSTD.getMean()?true:false,
                    dataset.getLefts());                            
            counterNaiveBayes(timespendcompanyTrue,
                    timespendcompanyFalse,
                    dataset.getTimespendcompany()>timespendcompanySTD.getMean()?true:false,
                    dataset.getLefts());                            
            counterNaiveBayes(numberprojectTrue,
                    numberprojectFalse,
                    dataset.getNumberproject()>numberprojectSTD.getMean()?true:false,
                    dataset.getLefts());                            
        }
        
        //hitung probabilitas
        for (naiveBayesPobabilitas bp : tempTrain) {
            double pLeft= (bp.getCountLeft() + k) / (bp.getSumLeft() + (2d*k));
            double pNotLeft= (bp.getCountNotLeft()+ k) / (bp.getSumNotLeft()+ (2d*k));
            
            bp.setProbabilitasLeft(pLeft);
            bp.setProbabilitasNotLeft(pNotLeft);
        }
        return tempTrain;
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

    private static void counterNaiveBayes(naiveBayesPobabilitas NBPTrue, naiveBayesPobabilitas NBPFalse, Boolean value, Integer DataClass) {
            if (value) {
                NBPTrue.addCount();
                if (DataClass == 1) {
                    NBPTrue.addCountLeft();
                }
                else {
                    NBPTrue.addCountNotLeft();
                }
            }
            else    {
                NBPFalse.addCount();
                if (DataClass == 1) {
                    NBPFalse.addCountLeft();
                }
                else {
                    NBPFalse.addCountNotLeft();
                }
            
            }
            if (DataClass == 1) {
                NBPTrue.addSumLeft();
                NBPFalse.addSumLeft();
            }
            else {
                NBPTrue.addSumNotLeft();
                NBPFalse.addSumNotLeft();
            }
            
    }
}
