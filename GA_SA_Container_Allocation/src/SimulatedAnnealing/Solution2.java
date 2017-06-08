package SimulatedAnnealing;


public class Solution2 {

	private int[] structure;
	private double fitness;
	private boolean alreadyCalculated;
	
	public Solution2()
	{
		structure = new int[Parameters.Slength];
		
		
		
		for (int i = 0 ; i <Parameters.Slength;i++)
		{
			structure[i] = (int) (Math.random() * Parameters.Slength)+1;
			
		}
		
		//fitness costs the most, we should save on calculating
		alreadyCalculated = false;
	}
	
	/*
	 * Generate neighbour - the probability of mutation will decrease according to something simillar 
	 * to the controller parameter, works best if it decreases slowly
	 * 
	 */
	
	public Solution2 generateNeighbour(int index)
	{
		//Neighbour definition - a solution with 1 different number max
		//Generating neighbour, copy from current
		Solution2 neighbour = new Solution2();
		
		//Copy
		for(int i =0; i<Parameters.Slength;i++)
		{
			neighbour.structure[i] = this.structure[i];
			
		}
		
		
		if ( Math.random() < Parameters.neighbourrate)
		{
			//If below rate, neighbour is randomized, random value cannot be the current value
			int randomcontainer = (int) (Math.random() * Parameters.Slength-1)+1;
			
			
			while(randomcontainer == neighbour.structure[index])
			{
				randomcontainer = (int) (Math.random() * Parameters.Slength-1)+1;
			}
			neighbour.structure[index] = randomcontainer;
			Parameters.neighbourrate /= Parameters.controldecreasingFactor;
		}
		
		
	
		return neighbour;
	}
	
	/*
	 * fitness calc
	 */
	
	public double fitness()
	{
		
		if(!alreadyCalculated)
		{
			double totalWeight;
			fitness = 0;
			boolean inUse = false;

			for (int i = 0; i < Parameters.Slength; i++) {

				totalWeight = 0;
				
				for(int z = 0; z<Parameters.Slength;z++)
				{
					
					if(structure[z] == i+1)
					{
						inUse = true;					
						totalWeight += Parameters.weights[z];
						if(totalWeight > Parameters.capacity)
						{						
							fitness = Parameters.Slength+1;
							inUse=false;
						}
					}
				}
				if(inUse && fitness!=Parameters.Slength+1)
				{
					fitness++;
					inUse=false;
				}
				
				
				alreadyCalculated = true;
			}
		}
		return fitness;
	}
	
	public void print()
	{
		
		for (int i = 0; i < Parameters.Slength; i++) {
			System.out.print(structure[i] + " ");
		}
		System.out.println("    Fitness: " + fitness());
	}
	
	
	public boolean betterOrEqual(Solution2 s)
	{
		if(Parameters.maximization)
		{
			return this.fitness() >= s.fitness();
		}else
			return this.fitness() <= s.fitness();
	}
}
