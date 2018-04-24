

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import static myc45.MyC45.calcIofD;
import neo.table.Dataset;
import neo.utils.DatasetJpaController;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author SEED
 */
public class c45 {
    
    public c45() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void hello() throws FileNotFoundException {
            int availableProcessors = Runtime.getRuntime().availableProcessors();
            System.out.println("availableProcessors = " + availableProcessors);
//            System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "20");
            Properties properties = System.getProperties();
            String property = properties.getProperty("java.util.concurrent.ForkJoinPool.common.parallelism");
            System.out.println("property = " + property);
//            System.out.println("properties = " + properties);
//        ("java.util.concurrent.ForkJoinPool.common.parallelism", "20");
//        DatasetJpaController djp = new DatasetJpaController(javax.persistence.Persistence.createEntityManagerFactory("analisiKomparasiPU"));
//        List<Dataset> findDatasetEntities = djp.findDatasetEntities();
//        List<Dataset> DataLatih = new LinkedList<>(findDatasetEntities.subList(0, 100));
//        List<Dataset> DataUji = new LinkedList<>(findDatasetEntities.subList(100, 120));
        

   
     }
}
