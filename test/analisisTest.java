/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import java.io.FileReader;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import neo.table.Dataset;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author SEED
 */
public class analisisTest {
    
    public analisisTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("analisisTest.setUpClass()");
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
     @Test
     public void hello() {
        System.out.println("analisisTest.hello()");
        EntityManager entityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("analisiKomparasiPU").createEntityManager();
        Query query = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT d FROM Dataset d ORDER BY d.id");
        List<Dataset> list = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(query.getResultList());         
        System.out.println("list = " + list.size());
        Map<String,Integer> mapper = new LinkedHashMap<>();
        mapper.put("data", list.size());
        mapper.put("attribut", 9);
        mapper.put("kelas", 2);
        mapper.put("binary", 2);
        mapper.put("double", 2);
        mapper.put("enum", 3);
        mapper.put("numeric", 2);
        
        Map<Object, Long> countSalary = list.stream()
                        .collect(Collectors.groupingBy(a -> a.getSalary()+"", Collectors.counting()));
        Map<Object, Long> countDalary = list.stream()
                        .collect(Collectors.groupingBy(a -> a.getDivision(), Collectors.counting()));
        System.out.println("countSalary = " + countSalary);
        System.out.println("countDalary = " + countDalary);
        System.out.println("mapper = " + mapper);

        
     }
}
