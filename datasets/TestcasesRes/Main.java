/**
 * 
 */

/**
 * @author Ahmed Metwally
 *
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.eclipse.jdt.core.compiler.IProblem;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;



public class Main {
	 

	// retrieve all .java files in the directory and subdirectories.
	public static List<String> retrieveFiles(String directory) {
		List<String> Files = new ArrayList<String>();
		File dir = new File(directory);
		
		if (!dir.isDirectory())
		{			
			 System.out.println("The provided path is not a valid directory");
			 System.exit(1);
		}
				
		for (File file : dir.listFiles()) {
			if(file.isDirectory())
			{
				Files.addAll(retrieveFiles(file.getAbsolutePath()));
			}
			if (file.getName().endsWith((".java"))) 
			{
				Files.add(file.getAbsolutePath());
			}
		}
		
		return Files;
	}
	
	
	
	public static void main(String[] args) throws IOException {
		// get the Directory name from the user
		String DirName=null;
		Scanner user_input = new Scanner( System.in );
		System.out.print("Enter Directory Name: ");
		DirName = user_input.next( );
		user_input.close();		
		System.out.println("Directory Name is: " + DirName);
		
		// retrieve all .java files in the directory and subdirectories. 
		List<String> JavaFiles=retrieveFiles(DirName);
		
		// parse files in a directory to list of char array
		List<char[]> FilesRead=ParseFilesInDir(JavaFiles);	
				
		ASTVisitorMod ASTVisitorFile;		
		int DistinctOperators=0;
		int DistinctOperands=0;
		int TotalOperators=0;
		int TotalOperands=0;
		int OperatorCount=0;
		int OperandCount=0;
		 		
		// Construct the AST of each java file. visit different nodes to get the number of operors and operands
		// Retrieve the number of distinct operators, distinct operands, 
		// total operators, and total operands for each .java file in the directory. 
		// Sum each parameter from different files together to be used in Halstead Complexity metrics. 
		for(int i=0; i<FilesRead.size(); i++)
		{	
			
			System.out.println("Now, AST parsing for : "+ JavaFiles.get(i));			
			ASTVisitorFile=parse(FilesRead.get(i));
			DistinctOperators+=ASTVisitorFile.oprt.size();
			DistinctOperands+=ASTVisitorFile.names.size();			
			
			OperatorCount=0;
			for (int f : ASTVisitorFile.oprt.values()) {				
				OperatorCount+= f;			
			}
			TotalOperators+=OperatorCount;
			
			OperandCount=0;
			for (int f : ASTVisitorFile.names.values()) {
				OperandCount += f;
			}
			TotalOperands+=OperandCount;
			
			System.out.println("Distinct Operators in this .java file = "+ ASTVisitorFile.oprt.size());
			System.out.println("Distinct Operands in this .java file = "+ ASTVisitorFile.names.size());
			System.out.println("Total Operators in this .java file = "+ OperatorCount);
			System.out.println("Total Operands in this .java file = "+ OperandCount);
			System.out.println("\n");
		}
		
		System.out.println("\n");
		System.out.println("Overall Distinct Operators in the directory= "+ DistinctOperators);
		System.out.println("Overall Distinct Operands in the directory= "+ DistinctOperands);
		System.out.println("Overall Total Operators in the directory= "+ TotalOperators);
		System.out.println("Overall Total Operands in the directory= "+ TotalOperands);		
		
		// calculate Halstead Complexity Metrics
		System.out.println("\n");
		System.out.println("###### Halstead Complexity Metrics ######");
		HalsteadMetrics hal = new HalsteadMetrics();
		 
		hal.setParameters(DistinctOperators, DistinctOperands, TotalOperators, TotalOperands);
		hal.getVocabulary();

	}
}
