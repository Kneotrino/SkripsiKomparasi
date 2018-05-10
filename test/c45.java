

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
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import static myc45.MyC45.calcIofD;
import neo.table.Dataset;
import neo.table.naiveBayesPobabilitas;
import neo.utils.DatasetJpaController;
import neo.utils.branchCandidate;
import neo.utils.gain;
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
        DatasetJpaController djp = new DatasetJpaController(javax.persistence.Persistence.createEntityManagerFactory("analisiKomparasiPU"));
        List<Dataset> findDatasetEntities = djp.findDatasetEntities();
        List<Dataset> DataLatih = new LinkedList<>(findDatasetEntities.subList(0, 10));
        List<Dataset> DataUji = new LinkedList<>(findDatasetEntities.subList(110, 120));
//        makan();
        //count root
        List<gain> rootGain = createGain("lefts", DataLatih);
        List<String> metaCandidate = new LinkedList<>();
        
        metaCandidate.add("workAccident");
        metaCandidate.add("Salary");                
        metaCandidate.add("Division");
//        metaCandidate.add("evaluation");
    
//         for (gain g : rootGain) {
//             System.out.println("g {" + g+"}");
//         }         

         List<branchCandidate> candidates = createBranchCandidate(metaCandidate,DataLatih, totalEntropy(rootGain));         
         branchSetGain(candidates);
//         for (branchCandidate candidate : candidates) {
//             System.out.println("candidate {" + candidate+"}");
//         }

         branchCandidate selectMax = selectMax(candidates);
         String newKey = selectMax.getCabang();
         divideList(newKey, DataLatih);
         //2
         metaCandidate.remove(newKey);
         
         
         List<gain> rootNewGain = createGain(newKey, DataLatih);
         List<branchCandidate> newCandidates = createBranchCandidate(metaCandidate,DataLatih, totalEntropy(rootGain));         
         branchSetGain(newCandidates);
//         for (branchCandidate candidate : candidates) {
//             System.out.println("candidate {" + candidate+"}");
//         }

         branchCandidate selectNewMax = selectMax(newCandidates);
         System.out.println("selectNewMax = " + selectNewMax.getCabang());
//        Map<Object, Long> collect = candidates
//                .stream()
//                .collect(Collectors.groupingBy(e -> e.getFitur(), Collectors.counting()));
        
     }
     
     public void divideList(String divide, List<Dataset> data)
     {
         System.out.println("divide = " + divide);
         Map<Object, List<Dataset>> collect = data
                    .stream()
                    .collect( Collectors.groupingBy( a -> a.getMeta(divide)));
         System.out.println("collect = " + collect);
     }
     
     public List<gain> createGain(String key,List<Dataset> data)
     {
         System.out.println("key = " + key);
         Map<Object, Long> rootMap = data
                                        .stream()
                                        .collect(Collectors.groupingBy(e -> e.getLefts(), Collectors.counting()));
         List<gain> listGain = new LinkedList<>();         
         int size = data.size();
//         System.out.println("counts = " + rootMap);
         for (Object object : rootMap.keySet()) {             
             Long frek = rootMap.get(object);
             listGain.add( new gain(object, size, frek, key));
             double proporsi = frek/ (size * 1d);
             double entropy = entropy(proporsi);
//             System.out.println("entropy = " + entropy);
         }
         
         return listGain;
     }
    public  double log(double n, int base) {
        return Math.log(n) / Math.log(base);
    }   
    
    public double entropy(double value )
    {        
        return -value * log(value, 2);
    }

    private List<branchCandidate> createBranchCandidate(List<String> metaCandidate,List<Dataset> data) {
        List<branchCandidate> candidates = new LinkedList<>();
        for (String string : metaCandidate) {
            Map<Object, Long> countMap = data
                                        .stream()
                                        .collect(Collectors.groupingBy(e -> e.getMeta(string), Collectors.counting()));
//            System.out.println("countMap = " + countMap);
            Map<Object, Long> countMapMeta = 
                data
                    .stream()
                    .collect(Collectors.groupingBy(e -> e.getMetaClass(string, e.getMeta(string)), Collectors.counting()));
//            System.out.println("countMapMeta = " + countMapMeta);
            

            for (Object object : countMap.keySet()) {
                Long frek = countMap.get(object);
                long countLeft = 
                        countMapMeta.get(string+";"+object+";"+1) == null?
                        0l:countMapMeta.get(string+";"+object+";"+1);
                long countNotLeft = 
                        countMapMeta.get(string+";"+object+";"+0) == null?
                        1l:countMapMeta.get(string+";"+object+";"+0);
                branchCandidate candidate = 
                        new branchCandidate(
                                string, 
                                object,
                                countLeft, 
                                countNotLeft, 
                                data.size(),
                                0d);
                candidate.setCountFitur(countLeft+countNotLeft);
                candidates.add(candidate);
            }
        }
        return candidates;
    }

    private void makan() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private double totalEntropy(List<gain> rootGain) {
        return rootGain.stream().mapToDouble( gain::getEntropy).sum();
        
    }

    private List<branchCandidate> createBranchCandidate(List<String> metaCandidate, List<Dataset> DataLatih, double totalEntropy) {
//        System.out.println("totalEntropy = " + totalEntropy);
        List<branchCandidate> candidates = new LinkedList<>();
        for (String string : metaCandidate) {
            Map<Object, Long> countMap = DataLatih
                                        .stream()
                                        .collect(Collectors.groupingBy(e -> e.getMeta(string), Collectors.counting()));
//            System.out.println("countMap = " + countMap);
            Map<Object, Long> countMapMeta = 
                DataLatih
                    .stream()
                    .collect(Collectors.groupingBy(e -> e.getMetaClass(string, e.getMeta(string)), Collectors.counting()));
//            System.out.println("countMapMeta = " + countMapMeta);
            

            for (Object object : countMap.keySet()) {
                Long frek = countMap.get(object);
                long countLeft = 
                        countMapMeta.get(string+";"+object+";"+1) == null?
                        0l:countMapMeta.get(string+";"+object+";"+1);
                long countNotLeft = 
                        countMapMeta.get(string+";"+object+";"+0) == null?
                        1l:countMapMeta.get(string+";"+object+";"+0);
                branchCandidate candidate = 
                        new branchCandidate(
                                string, 
                                object,
                                countLeft, 
                                countNotLeft, 
                                DataLatih.size(), 
                                totalEntropy);
                candidate.setCountFitur(countLeft+countNotLeft);
                candidates.add(candidate);
            }
        }
//        branchSetGain(candidates);
        return candidates;
    }

    private void branchSetGain(List<branchCandidate> candidates) {
        Map<Object, Double> totalByDept = candidates.stream()
        .collect(Collectors.groupingBy(branchCandidate::getCabang,
                 Collectors.summingDouble(branchCandidate::getTotalEntropyN)));
         System.out.println("totalByDept = " + totalByDept);  

         for (branchCandidate candidate : candidates) {
             Set<Object> keySet = totalByDept.keySet();
             for (Object object : keySet) {
                 if (candidate.getCabang().equals(object)) {
                         double gain = candidate.getGain(totalByDept.get(object));
                         candidate.setInfoGain(gain);
                     }
                 }             
            }
         
    }

    private branchCandidate selectMax(List<branchCandidate> candidates) {
        
        double maxValue = 0d;
        branchCandidate maxBranch = null;
        for (branchCandidate candidate : candidates) {
            if (candidate.getInfoGain() > maxValue ) {
                maxBranch = candidate;
                maxValue = candidate.getInfoGain();                
            }
        }
        return maxBranch;
    }
}
