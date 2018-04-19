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
import java.util.logging.Level;
import java.util.logging.Logger;
import neo.table.Dataset;
import neo.utils.DatasetJpaController;
import neo.utils.methodUtil;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author SEED
 */
public class NewEmptyJUnitTest {
    
    public NewEmptyJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
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
     @Test
     public void testKNN() {
        DatasetJpaController djp = new DatasetJpaController(javax.persistence.Persistence.createEntityManagerFactory("analisiKomparasiPU"));
        List<Dataset> findDatasetEntities = djp.findDatasetEntities();
        List<Dataset> DataLatih = new LinkedList<>(findDatasetEntities.subList(0, 10000));
        Double sumLatihLeft = 0d;
         for (Dataset dataset : DataLatih) {
             sumLatihLeft += dataset.getLeftsDouble();
         }
        List<Dataset> DataUji = new LinkedList<>(findDatasetEntities.subList(10000, 14999));
        Double sumUjiLeft = 0d;
         for (Dataset dataset : DataUji) {
             sumUjiLeft += dataset.getLeftsDouble();
         }

        
        List<Dataset> KNN = methodUtil.KNN(DataLatih, DataUji, 5);
        System.out.println("sumLatihLeft = " + sumLatihLeft);
        System.out.println("sumUjiLeft = " + sumUjiLeft);
        Double sumUjiKelasKNN = 0d;
         for (Dataset dataset : KNN) {
             sumUjiKelasKNN += dataset.getKelas();
         }
         System.out.println("sumUjiKelasKNN = " + sumUjiKelasKNN);
     }
}
