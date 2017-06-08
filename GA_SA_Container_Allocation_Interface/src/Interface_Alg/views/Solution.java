package Interface_Alg.views;

public class Solution {
	
	private int[] structure;
	private double fitness;
	private boolean alreadyCalculated;
	
	/*
	 * Constructor
	 */
	public Solution() {
		//Stringlength = numcontainers
		
		structure = new int[Parameters.stringhLength];
		
		/*
		 * while structure != null
		 * select random weight
		 * assign position in structure corresponding to weight
		 * select random weight
		 * check if weight sum > capacity
		 * 	Yes = increase position
		 * 	No  = Random, if good, assign position in structure and repeat, if not good, skip and increase position
		 */
		/* Weights map
		*  1 , 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14
		   99, 94,79,64,50,46,43,37,32,19, 18,  7,  6, 3		
		*/
		
		//Define solutiion initialization for testing purposes
		//structure = new int[]{1,2,10,4,5,6,7,8,9,10,10,10,10,10};
		
		//Randomize solution initialization
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
	 * Get structure
	 */
	
	public String getstructure()
	{
		String struct = "[ ";
		for(int i = 0; i<this.structure.length; i++)
		{
			struct += String.valueOf(this.structure[i]);
			
			if (i == this.structure.length-1)
			{
				struct +=", " +  String.valueOf(this.structure[i]) + " ]";
			}else
			{
				struct += ", ";
			}
		}
		
		return struct;
		
		
		
	}

	
	/*
	 * fitness
	 */
	
	public double fitness() {
		
		if (!alreadyCalculated) {
			double totalWeight;
			fitness = 0;
			boolean inUse = false;
			
			
		
		
			//Ver para cada número (contentor) quantas vezes ele aparece
			for (int i = 0; i < Parameters.stringhLength; i++) {
				
				//Comparar o numero do contentor com a sua presença dentro da solução de forma a 
				//conseguir calcular se os weights não ultrapassam o limite
				
				//Vamos recalculando os total weights cada vez que analisamos um contentor novo
				totalWeight = 0;
				
				for(int z = 0; z<Parameters.stringhLength;z++)
				{
					
					/*
					 * Iteração 1: Comparamos todos os numeros da solução de forma a percebermos quais os weights
					 * que estão presentes no contentor 1
					 */
					if(structure[z] == i+1)
					{
						/* Criamos uma variavel booleana para verficar se o contentor corresponde com a solução e 
						*  assim incrementamos a fitness ( numero de contentores usados)
						*/
						inUse = true;
						
						totalWeight += Parameters.weights[z];
						//Debug message
						//System.out.println("Iteration number: " + i + "found a match with number: " + structure[z] + ", the total weight is: "
						//		+ totalWeight);
						if(totalWeight > Parameters.capacity)
						{
							//Debug message
							//System.out.println("We got a container overexceeding, container number: " + structure[z]);
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
		//WORK IN PROGRESS
			/*if (totalWeight <= Parameters.capacity) {
				
				fitness = countContainer(structure);
							
			} 
			else {
				fitness = -1;	
			}
		
			
		
		*/
			
			
			
		return fitness;
	}
	
	/*
	 * Count number of containers
	 */
	public int countContainer(boolean[] z){
		int counter = 0;
		for(int i = 0; i<z.length;i++)
		{
			if(z[i])
			{
				counter++;
			}
		}
		
		return counter;
	}
	
	/*
	 * print
	 */
	
	public void print() {
		System.out.println("MAPA DE WEIGHTS:");
		System.out.println("[99 94 79 64 50 46 43 37 32 19 18 7 6 3]");
		for (int i = 0; i < Parameters.stringhLength; i++) {
			System.out.print(structure[i] + " ");
		}
		System.out.println(",    " + fitness());
	
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
	 * getStructure
	 */
	
	public int getPosition (int i) {
		return structure[i];
	}
	
	public void setPosition(int i, int b) {
		structure[i] = b;
	}
	

}
