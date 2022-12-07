package grafikus;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Random;
public class DontesiFa {

    DontesiFa(String filename, int classIndex){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            Instances instances = new Instances(bufferedReader);
            PrintWriter kiir = new PrintWriter("dontesifa.txt");
            instances.setClassIndex(classIndex);
            Instances tanító, kiértékelő;
            J48 classifier;
            Evaluation evaluation;
            classifier = new J48();
            if(false) instances.randomize(new Random());
            tanító = new Instances(instances,0,9*instances.size()/10);
            kiir.println("tanító.size():"+tanító.size());
            kiértékelő = new Instances(instances,9*instances.size()/10,instances.size()/10);
            kiir.println("kiértékelő.size():"+kiértékelő.size());
            classifier.buildClassifier(tanító);
            evaluation = new Evaluation(kiértékelő);
            double[] eredmeny = evaluation.evaluateModel(classifier, kiértékelő);
            kiir.println("Correctly Classified Instances:"+(int)evaluation.correct());
            kiir.println("Incorrectly Classified Instances:"+(kiértékelő.size()-(int)evaluation.correct()));
            kiir.println(classifier.toString());
            kiir.println("\nGépiTanulás1: Vizsgálat részletesen, egyesével:");

            int TP=0, TN=0, FP=0, FN=0;
            //  TP:TP, TN:trueNegative, FP:falsePositive, FN:falseNegative
            for(int i=0;i<kiértékelő.size();i++){
                kiir.println(i+"\t"+(((Instances)kiértékelő).get(i)).classValue()+"\t"+eredmeny[i]);
                if((((Instances)kiértékelő).get(i)).classValue()==1 && eredmeny[i]==1)
                    TP++;
                if((((Instances)kiértékelő).get(i)).classValue()==1 && eredmeny[i]==0)
                    FN++;
                if((((Instances)kiértékelő).get(i)).classValue()==0 && eredmeny[i]==1)
                    FP++;
                if((((Instances)kiértékelő).get(i)).classValue()==0 && eredmeny[i]==0)
                    TN++;
            }
            kiir.println("\nTP="+TP+", "+"TN="+TN+", "+"FP="+FP+", "+"FN="+FN);
            kiir.close();
        }
        catch (Exception e) {
            System.out.println("Error Occurred!!!! \n" + e.getMessage());
        }
    }
}