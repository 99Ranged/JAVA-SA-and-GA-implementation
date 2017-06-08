package Genetic;

public class Solution {
	
	private int[] structure;
	private double fitness;
	private boolean alreadyCalculated;
	
	/*
	 * Constructor
	 */
	public Solution() {
		structure = new int[Parameters.stringhLength];
	
		for (int i = 0; i < Parameters.stringhLength; i++) {
			
			structure[i] = (int) (Math.random() * Parameters.stringhLength)+1;		
		}
		alreadyCalculated = false;
	}
	
	/*
	 * myCopy
	 */
	
	public Solution myCopy() {
		Solution newSol = new Solution();
		for (int i = 0; i < Parameters.stringhLength; i++) {
			newSol.structure[i] = this.structure[i];
		}
		return newSol;
	}
	
	
	
	/*
	 * fitness
	 */
	
	public double fitness() {	
		//Check if already calculated to save computation power
		if (!alreadyCalculated) {
			double totalWeight;
			fitness = 0;
			
			/*Create a boolean to aid in sum of weights, otherwise the solution 
			 *would not be read in order
			*/boolean inUse = false;	
			for (int i = 0; i < Parameters.stringhLength; i++) {
				totalWeight = 0;
				for(int z = 0; z<Parameters.stringhLength;z++)
				{
					if(structure[z] == i+1)
					{
						inUse = true;
						totalWeight += Parameters.weights[z];
						if(totalWeight > Parameters.capacity)
						{
							fitness = Parameters.stringhLength+1;
							inUse=false;
						}
					}
				}
				if(inUse && fitness!=Parameters.stringhLength+1)
				{
					fitness++;
					inUse=false;
				}
				alreadyCalculated = true;
			}
		}
		return fitness;
	}
	

	
	/*
	 * print
	 */
	
	public void print() {
		for (int i = 0; i < Parameters.stringhLength; i++) {
			System.out.print(structure[i] + " ");
		}
		System.out.println(",    fitness: " + fitness());
	}
	
	/*
	 * betterOrEqual
	 */
	
	public boolean betterOrEqual(Solution s) {
		
		if (Parameters.Maximization) {
			return (this.fitness() >= s.fitness());
		} else {
			return (this.fitness() <= s.fitness());
		}
	}
	
	/*
	 * Changing structure methods
	 */
	
	public int getPosition (int i) {
		return structure[i];
	}
	
	public void setPosition(int i, int b) {
		structure[i] = b;
	}
	

}
