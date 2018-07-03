import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import neo.table.Dataset;
import neo.utils.DatasetJpaController;
import static neo.utils.methodUtil.createInstances;
import weka.core.Instances;
import weka.core.converters.ArffSaver;

// Main class for decision tree learner
public class DTLearner {
	
	// Extracts data set from ARFF file
	@SuppressWarnings("resource")
	private static DataSet read(String filename) {
		DataSet data = new DataSet();
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(filename));
		} catch (FileNotFoundException exception) {
			System.out.println("Error: could not find file");
			System.exit(1);
		}
		String line;
		scanner.nextLine(); // Skip first line
		while (scanner.hasNextLine()) {
			line = scanner.nextLine();
			if (line.equals("@data"))
				break;
			data.addAttribute(line);
		}
		while (scanner.hasNextLine())
			data.addInstance(scanner.nextLine());
		return data;
	}
	
	public static void main(String[] args) {
            
                DatasetJpaController djp = new DatasetJpaController(javax.persistence.Persistence.createEntityManagerFactory("analisiKomparasiPU"));
                List<Dataset> findDatasetEntities = djp.findDatasetEntities();
                List<Dataset> DataLatih = new LinkedList<>(findDatasetEntities.subList(0, 10000));
                List<Dataset> DataUji = new LinkedList<>(findDatasetEntities.subList(10001, 14999));


                Instances train = createInstances(DataLatih);
                Instances test = createInstances(DataUji);

                ArffSaver saverTrain = new ArffSaver();
                saverTrain.setInstances(train);
                File fileTrain = new File("/data/train.arff");    
            try {
                saverTrain.setFile(fileTrain);
                saverTrain.writeBatch();
            } catch (IOException ex) {
                Logger.getLogger(DTLearner.class.getName()).log(Level.SEVERE, null, ex);
            }

                ArffSaver saverTest = new ArffSaver();
                saverTest.setInstances(test);
                File fileTest = new File("/data/test.arff");    
            try {
                saverTest.setFile(fileTest);
                saverTest.writeBatch();
            } catch (IOException ex) {
                Logger.getLogger(DTLearner.class.getName()).log(Level.SEVERE, null, ex);
            }
//                System.out.println("fileTrain = " + fileTrain.getAbsolutePath());
//                System.out.println("fileTest = " + fileTest.getAbsolutePath());

		int stopValue = 0;
//                
//                File fileTrain = new File("/data/Train.arff");    
//                System.out.println("file = " + file.getAbsolutePath());
                DataSet trainingSet = read(fileTrain.getAbsolutePath());
		DataSet testingSet = read(fileTest.getAbsolutePath());
                System.out.println("trainingSet = " + trainingSet.instances.size());
                System.out.println("testingSet = " + testingSet.instances.size());

                DecisionTree C45 = new DecisionTree(stopValue);                
		C45.train(trainingSet);
		C45.test(testingSet.instances);
	}
	
}
