import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Quiz {
    List<String> questions = new ArrayList<String>();
    List<String> answers = new ArrayList<String>();

    int n;
    int q = 5; // You can change the number of question freely.

    public void text(){
        try {
		    Path path = Paths.get("text_q.txt");
		    List<String> lines = Files.readAllLines(path);
			
		    for (String str : lines) {
                questions.add(str);
                n++;
		    }
    	} catch(IOException ioex) {
			//ioex.printStackTrace();
            new Quiz().anomaly();
	    }

        try {
    		Path path = Paths.get("text_a.txt");
	    	List<String> lines = Files.readAllLines(path);
			
			for (String str : lines) {
                answers.add(str);
	    	}
		} catch(IOException ioex) {
    		ioex.printStackTrace();
            new Quiz().anomaly();
	    }
        
        System.out.println("------------------------------");
    }

    public void QandA(){
        List<Integer> num = new ArrayList<Integer>();
        List<String> wrong_a = new ArrayList<String>();
        List<String> wrong_q = new ArrayList<String>();

        int correct = 0;
        int wrong = 0;

        Scanner scanner = new Scanner(System.in);
        for(int i = 0; i < n; i++){
            num.add(i);
        }
        Collections.shuffle(num);

        for(int i = 0; i < q; i++){
            System.out.print((i+1) + ": " + questions.get(num.get(i)) + ": ");
            String ans = scanner.next();
            if(answers.get(num.get(i)).equals(ans) == true) {
                System.out.println("yes");
                correct++;
            }
            else {
                wrong_a.add(answers.get(num.get(i)));
                wrong_q.add(questions.get(num.get(i)));
                System.out.println("no, the correct answer is: " + answers.get(num.get(i)));
                wrong++;
            }
        }

        System.out.println("Result: " + correct + " / " + q + " pt");
        System.out.println((double)correct / q * 100 + "%");
        if(wrong == 0){
            System.out.println("Perfect!!!!");
        }
        else {
            System.out.println("Wrong answers");
            for(int i = 0; i < wrong; i++){
                System.out.println(wrong_q.get(i) + ": " + wrong_a.get(i));
            }
        }
    }

    public void anomaly(){
        try {
		    Path path = Paths.get("aa.txt");
		    List<String> lines = Files.readAllLines(path);
	
			for (String str : lines) {
                System.out.println(str);
		    } 
    	} catch(IOException ioex) {
		    ioex.printStackTrace();
	    }

        System.exit(0);
    }

    public static void main(String[] args){
        Quiz quiz = new Quiz();
        quiz.text();
        quiz.QandA();
    }
}