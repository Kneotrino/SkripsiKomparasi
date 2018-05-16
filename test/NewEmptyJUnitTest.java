/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import neo.table.Dataset;
import neo.table.naiveBayesPobabilitas;
import neo.utils.C45;
import neo.utils.DatasetJpaController;
import neo.utils.methodUtil;
import org.junit.Test;
import weka.classifiers.Evaluation;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
/**
 *
 * @author SEED
 */
public class NewEmptyJUnitTest {
    
    public NewEmptyJUnitTest() {
        
    }
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    
     public Attribute createAttribute(List<Dataset> Data,String key)
     {
        Function<Dataset, String> funcTemp = (Dataset a) -> (String) a.getMeta(key);         
        Map<String, Long> fun = Data
                .stream()
                .collect(Collectors.groupingBy(
                                     funcTemp , Collectors.counting()
                            ));
//        System.out.println("fun = " + fun);
        
        Set<String> keySet = fun.keySet();
        System.out.println("keySet = " + keySet);
        FastVector fvNominalVal = new FastVector(keySet.size());
         for (String string : keySet) {
            fvNominalVal.addElement(string);             
         }
        return new Attribute(key, fvNominalVal);
     }
    
//     public Attribute keyAttributes(List<Dataset> Data, int Key)
//     {
//        FastVector listAttributes = new FastVector(10);
//        listAttributes.addElement(new Attribute("satisfaction"));
//        listAttributes.addElement(new Attribute("evaluation"));
//        listAttributes.addElement(new Attribute("numberproject"));                
//        listAttributes.addElement(new Attribute("avaragehours"));                
//        listAttributes.addElement(new Attribute("timespendcompany"));                
//        listAttributes.addElement(new Attribute("workAccident"));                
//        listAttributes.addElement(new Attribute("promotion"));                
//        listAttributes.addElement(new Attribute("salary"));                
//        listAttributes.addElement(createAttribute(Data, "division"));
//        listAttributes.addElement(createAttribute(Data, "leftsString"));
//        return (Attribute) listAttributes.elementAt(Key);
//     }
     
     public Instance singleInstance(FastVector listAttributes, Dataset data)
     {
         Instance single = new Instance(10);
         Instances dataUnlabeled = new Instances("TestInstances", listAttributes, 0);
         dataUnlabeled.add(single);
         dataUnlabeled.setClassIndex(dataUnlabeled.numAttributes() - 1);     
         single.setDataset(dataUnlabeled);
         for (int i = 0; i < 10; i++) {
                Attribute elementAt = (Attribute) listAttributes.elementAt(i);
                Object meta = data.getMeta(elementAt.name());              
                 if (meta instanceof Integer) {
                     int v = (int) meta;
                     double value = v*1d;                     
                     single.setValue(i, value);
                 }
                 else if (meta instanceof Double) {                                 
                     single.setValue(i, (double) meta);
                 }                 
                 else if (meta instanceof String) {                                 
                     single.setValue(i, (String) meta);
                 }
         }
//         System.out.println("single = " + single);
         return single;
     }
     public Instances createInstances(List<Dataset> Data)
     {
        int max = 10;
        FastVector listAttributes = new FastVector(max);
        listAttributes.addElement(new Attribute("satisfaction"));
        listAttributes.addElement(new Attribute("evaluation"));
        listAttributes.addElement(new Attribute("numberproject"));                
        listAttributes.addElement(new Attribute("avaragehours"));                
        listAttributes.addElement(new Attribute("timespendcompany"));                
        listAttributes.addElement(new Attribute("workAccident"));                
        listAttributes.addElement(new Attribute("promotion"));                
        listAttributes.addElement(new Attribute("salary"));                
        listAttributes.addElement(createAttribute(Data, "division"));
        listAttributes.addElement(createAttribute(Data, "leftsString"));

        
        
        Instances temp = new Instances("data", listAttributes, Data.size());
        temp.setClassIndex(max-1);        
         for (Dataset dataset : Data) {
            Instance x = new Instance(max);
            for (int i = 0; i < 10; i++) {
                 Attribute elementAt = (Attribute) listAttributes.elementAt(i);
                 Object meta = dataset.getMeta(elementAt.name()); 
                 if (meta instanceof Integer) {
                     int v = (int) meta;
                     double value = v*1d;                     
                     x.setValue(elementAt, value);
                 }
                 else if (meta instanceof Double) {                                 
                     x.setValue(elementAt, (double) meta);
                 }                 
                 else if (meta instanceof String) {                                 
                     x.setValue(elementAt, (String) meta);
                 }
                 
             }
//            System.out.println("x = " + x);     
            temp.add(x);
         }
         return temp;
     }
     @Test
     public void C45(){
        DatasetJpaController djp = new DatasetJpaController(javax.persistence.Persistence.createEntityManagerFactory("analisiKomparasiPU"));
        List<Dataset> findDatasetEntities = djp.findDatasetEntities();
        List<Dataset> DataLatih = new LinkedList<>(findDatasetEntities.subList(0, 10000));
        List<Dataset> DataUJI = new LinkedList<>(findDatasetEntities.subList(10000, 14000));
         
                 
         try {
             
            C45 tree = new C45();
            Instances tranning = createInstances(DataLatih);
            tree.buildClassifier(tranning);
            Instances testing = createInstances(DataUJI);
            Evaluation eTest = new Evaluation(tranning);
            eTest.evaluateModel(tree, testing);
            double errorRate = eTest.errorRate();
            System.out.println("errorRate = " + errorRate);
            double correct = eTest.correct();
            System.out.println("correct = " + correct);
            //
            testingC45(tree,DataUJI);
            int size = DataUJI.size();
             System.out.println("size = " + size);
            Double actualLeft = 0d;
            Double prediksiLeft = 0d;
            for (Dataset dataset : DataUJI) {
                 actualLeft += dataset.getLeftsDouble();
                 prediksiLeft += dataset.getKelas();
            }
             System.out.println("prediksiLeft = " + prediksiLeft);
             System.out.println("actualLeft = " + actualLeft);
                    
         } catch (Exception ex) {
            Logger.getLogger(NewEmptyJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
         }

     }
    //

//     @Test
     public void NB(){
        DatasetJpaController djp = new DatasetJpaController(javax.persistence.Persistence.createEntityManagerFactory("analisiKomparasiPU"));
        List<Dataset> findDatasetEntities = djp.findDatasetEntities();
        List<Dataset> DataLatih = new LinkedList<>(findDatasetEntities.subList(0, 12000));
        List<Dataset> DataUJI = new LinkedList<>(findDatasetEntities.subList(0, 4000));
        System.out.println("DataUJI = " + DataUJI.size());
        System.out.println("DataLatih = " + DataLatih.size());
        List<naiveBayesPobabilitas> NBtrain = methodUtil.NBtrain(DataLatih, 2);        
        methodUtil.NBclasificationAll(NBtrain, DataLatih, DataUJI);
//         for (naiveBayesPobabilitas bp : NBtrain) {
//             System.out.println("bp = " + bp);
//         }
//         



        Double actualLeft = 0d;
        Double prediksiLeft = 0d;
        for (Dataset dataset : DataUJI) {
             actualLeft += dataset.getLeftsDouble();
             prediksiLeft += dataset.getKelas();
        }
         System.out.println("prediksiLeft = " + prediksiLeft);
         System.out.println("actualLeft = " + actualLeft);
     }
//     @Test
     public void hello() {
         System.out.println("NewEmptyJUnitTest.hello()");
        try {
            
              com.opencsv.CSVReader reader = new CSVReader(new FileReader("E:\\SKIRPSI\\DATASET\\dataset3.csv"), ',');
             	HeaderColumnNameMappingStrategy<Dataset> beanStrategy = new HeaderColumnNameMappingStrategy<>();
	beanStrategy.setType(Dataset.class);	
	CsvToBean<Dataset> csvToBean = new CsvToBean<>();
	List<Dataset> emps = csvToBean.parse(beanStrategy, reader);	
//	System.out.println(emps);
              int size = emps.size();
              
        } catch (Exception ex) {
            Logger.getLogger(NewEmptyJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
//     @Test
     public void math() {
         double  x  = 120;
         double mean = 110;
         double max = 100;
         double min = 150;
         
         double y = (x - mean) / (max - min);
         System.out.println("y = " + y);

     }    
//     @Test
     public void testKNN() {
        DatasetJpaController djp = new DatasetJpaController(javax.persistence.Persistence.createEntityManagerFactory("analisiKomparasiPU"));
        List<Dataset> findDatasetEntities = djp.findDatasetEntities();
        List<Dataset> DataLatih = new LinkedList<>(findDatasetEntities.subList(0, 10000));
        Double sumLatihLeft = 0d;
         System.out.println("1");
         for (Dataset dataset : DataLatih) {
             sumLatihLeft += dataset.getLeftsDouble();
         }
         System.out.println("2");
        List<Dataset> DataUji = new LinkedList<>(findDatasetEntities.subList(10000, 10010));
        Double sumUjiLeft = 0d;
         for (Dataset dataset : DataUji) {
             sumUjiLeft += dataset.getLeftsDouble();
         }
         System.out.println("3");
        
        List<Dataset> KNN = methodUtil.KNN(DataLatih, DataUji, 1);
        System.out.println("KNN = " + KNN.size());
     }

    private void testingC45(C45 tree, List<Dataset> Data) {
        FastVector listAttributes = new FastVector(10);
        listAttributes.addElement(new Attribute("satisfaction"));
        listAttributes.addElement(new Attribute("evaluation"));
        listAttributes.addElement(new Attribute("numberproject"));                
        listAttributes.addElement(new Attribute("avaragehours"));                
        listAttributes.addElement(new Attribute("timespendcompany"));                
        listAttributes.addElement(new Attribute("workAccident"));                
        listAttributes.addElement(new Attribute("promotion"));                
        listAttributes.addElement(new Attribute("salary"));                
        listAttributes.addElement(createAttribute(Data, "division"));
        listAttributes.addElement(createAttribute(Data, "leftsString"));

        for (Dataset dataset : Data) {
            Instance singleInstance = singleInstance(listAttributes, dataset);
            double classifyInstance = 0;
            try {
                classifyInstance = tree.classifyInstance(singleInstance);
                dataset.setKelas(classifyInstance);
            } catch (Exception ex) {
                Logger.getLogger(NewEmptyJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
