/**
 * @author  Ahmed Metwally
 */


// This class is intended to calculate all the halstead complexity metrics   
public class HalsteadMetrics {
	
	public int DistOperators, DistOperands, TotOperators, TotOperands;	
	private int Vocabulary=0;
	private int Proglen=0; 
	private double CalcProgLen=0; 
	private double Volume=0; 
	private double Difficulty=0;
	private double Effort=0;  
	private double TimeReqProg=0;
	private double TimeDelBugs=0;
	
	
	// Initialize the variables in the constructor 
	public HalsteadMetrics() {
		DistOperators=0;
		DistOperands=0;
		TotOperators=0;
		TotOperands=0;
	}
	
	
	
	
	
	// calculate the Program vocabulary
	public int getVocabulary()
	{
		Vocabulary=DistOperators+DistOperands;
		System.out.println("Vocabulary= "+ Vocabulary);
		return Vocabulary;
	}
	
	
	// calculate the Volume
	public double getVolume()
	{
		Volume=(TotOperators+TotOperands)*(Math.log(DistOperators+DistOperands)/Math.log(2));
		System.out.println("Volume= "+ Volume);
		return Volume;
	}
	
}
