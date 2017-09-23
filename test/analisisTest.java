/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Random;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import neo.table.Dataset;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
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
//     @Test
     public void hello() {
        System.out.println("analisisTest.hello()");
        EntityManager entityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("analisiKomparasiPU").createEntityManager();
        Query query = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT d FROM Dataset d ORDER BY d.id");
        List<Dataset> list = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(query.getResultList());         

        Query createQuery = entityManager.createQuery("SELECT COUNT(d) FROM Dataset d");
        long get = (long) createQuery.getResultList().get(0);

        Map<String,Long> mapper = new LinkedHashMap<>();
        mapper.put("0.Data", get);
        mapper.put("0.Attribut", 9l);
        mapper.put("0.Kelas", 2l);
        mapper.put("0.Binary", 2l);
        mapper.put("0.Double", 2l);
        mapper.put("0.Enum", 3l);
        mapper.put("0.Numeric", 2l);
        
        NavigableSet<Double> groups = new TreeSet<>();
        BigDecimal temp = BigDecimal.ZERO;        
         for (int i = 0; i < 10; i++) {
             temp = temp.add(BigDecimal.valueOf(.1d));
             groups.add(temp.doubleValue());
         }
        NavigableSet<Integer> rangeInt = new TreeSet<>();
         for (int i = 0; i < 500; i+=10) {
             rangeInt.add(i);
         }
            List<Function> ListFun = new LinkedList<>();
            Function<Dataset, String> funcTemp;
            funcTemp = (Dataset a) -> "1.Satisfaction:"+groups.ceiling(a.getSatisfaction());
            ListFun.add(funcTemp);
            funcTemp = (Dataset a) -> "2.Evaluation:"+groups.ceiling(a.getSatisfaction());
            ListFun.add(funcTemp);
            funcTemp = (Dataset a) -> "3.Number Project:"+a.getNumberproject();
            ListFun.add(funcTemp);
            funcTemp = (Dataset a) -> "4.Avarage Montly Hours:"+ rangeInt.floor(a.getAvaragehours());
            ListFun.add(funcTemp);
            funcTemp = (Dataset a) -> "5.Time Spend Company:"+a.getTimespendcompany();
            ListFun.add(funcTemp);
            funcTemp = (Dataset a) -> "6.Work Accident:"+a.getWorkaccident();
            ListFun.add(funcTemp);
            funcTemp = (Dataset a) -> "7.Promotion:"+a.getPromotion();
            ListFun.add(funcTemp);
            funcTemp = (Dataset a) -> "8.Division:"+a.getDivision();
            ListFun.add(funcTemp);
            funcTemp = (Dataset a) -> "9.Left:"+a.getLefts();
            ListFun.add(funcTemp);

            for (Function function : ListFun) {
                     Map<String, Long> fun = list.stream()
                            .collect(Collectors.groupingBy(
                                    function, Collectors.counting()
                            ));
            mapper.putAll(fun);
         }
        Map<String , Long> map = new TreeMap<>();
        map.putAll(mapper);
       System.out.println("map = " + map.toString().replace(",", "\n"));
     }
//     @Test
     public void test1()
     {
         System.out.println("analisisTest.test1()");
         DescriptiveStatistics stats = new DescriptiveStatistics();
         System.out.println("stats = " + stats);
         List<Double> testData = IntStream.range(1, 100)
                 .mapToDouble(d -> d)
                 .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
         testData.forEach((d) -> {
             stats.addValue(d);
        });
         System.out.println("testData = " + testData);
         double max = stats.getMax();
         System.out.println("max = " + max);
        double mean = stats.getMean();
         System.out.println("mean = " + mean);
        double min = stats.getMin();
         System.out.println("min = " + min);
        long n = stats.getN();
        double[] sortedValues = stats.getSortedValues();
         System.out.println("n = " + n);
         int q1 = (int) (n/4);         
         System.out.println("q1 = " + sortedValues[q1]);
         int q2 = (int) (n/2);
         System.out.println("q2 = " + sortedValues[q2]);
         int q3 = q1+q2;
         System.out.println("q3 = " + sortedValues[q3]);
        double standardDeviation = stats.getStandardDeviation();
         System.out.println("standardDeviation = " + standardDeviation);
        double populationVariance = stats.getPopulationVariance();
         System.out.println("populationVariance = " + populationVariance);
        double variance = stats.getVariance();
         System.out.println("variance = " + variance);
        double sum = stats.getSum();
         System.out.println("sum = " + sum);         
     }
//     @Test
     public void test2()
     {
            System.out.println("analisisTest.test2()");
            EntityManager entityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("analisiKomparasiPU").createEntityManager();

            Query query = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT d FROM Dataset d ORDER BY d.id");
            List<Dataset> list = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(query.getResultList());         

            Query createQuery = entityManager.createQuery("SELECT COUNT(d) FROM Dataset d");
            long get = (long) createQuery.getResultList().get(0);
         System.out.println("get = " + get);
            List<String> nope = new LinkedList<>();
            nope.add("changeSupport");
            nope.add("serialVersionUID");
            nope.add("id");
            Dataset employee = new Dataset();
            employee.setDivision("foo");
            List<Dataset> temp = new LinkedList<>();
            temp.add(employee);
            List<? extends Number> beanList;
            
        try {
            for (Field field : Dataset.class.getDeclaredFields()) {
                beanList = getBeanList(field.getName(), list);
            }            
                int size = list.size();
                System.out.println("size = " + size);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(analisisTest.class.getName()).log(Level.SEVERE, null, ex);
        }
                          
     }
     private List<Double> getBeanList(String bean,List<Dataset> data) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException
     {
         System.out.println("bean = " + bean);
          List<Double> value = new LinkedList<>();
          List<String> nope = new LinkedList<>();
          nope.add("changeSupport");
          nope.add("serialVersionUID");
          nope.add("id");
          nope.add("division");
          if (nope.contains(bean)) {
              System.out.print("\tnope");
             return null;
         }

         for (Dataset dataset : data) {
             Field field = Dataset.class.getDeclaredField(bean);
             field.setAccessible(true);
             Class<?> type = field.getType();
             System.out.println("type = " + type);
             if (field.get(dataset) instanceof Double) {
                 value.add((Double) field.get(dataset));                 
             }
             if (field.get(dataset) instanceof Integer) {
                 value.add((Integer) field.get(dataset) * 1d);                 
//                 System.out.println("foo2");                 
//                 return value;
             }
//        try {
         }         
         return value;
     }
         private static void printFields(String prefix, Object container) throws IllegalAccessException {

        Class<? extends Object> class1 = null; 
        Package package1 = null;

        if (container != null)
            class1 = container.getClass();

        if (class1 != null)
            package1 = class1.getPackage();

        if (package1 == null || !"myOwnPackage".equals(package1.getName())) {
            System.out.println(container);
            return;
        }

        for (Field field : class1.getDeclaredFields()) {
            System.out.print(prefix+field.getName()+": ");
            // make private fields accessible
            field.setAccessible(true);
            Object value = field.get(container);
            printFields(prefix+"  ", value);
        }
    }
     @Test
     public void test3()
        {
            List<String> listString = new LinkedList<>();
            for (int i = 0; i < 10; i++) {
                listString.add("Key");
                listString.add("Value");
            }
            String[] strings = listString.stream().toArray(String[]::new);
        }
         
}
