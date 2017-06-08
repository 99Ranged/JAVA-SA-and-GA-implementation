package Interface_Alg.views;


public class Solution2 {

	private int[] structure;
	private double fitness;
	private boolean alreadyCalculated;
	
	public Solution2()
	{
		structure = new int[Parameters_SA.Slength];
		
		
		
		for (int i = 0 ; i <Parameters_SA.Slength;i++)
		{
			structure[i] = (int) (Math.random() * Parameters_SA.Slength)+1;
			
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
		for(int i =0; i<Parameters_SA.Slength;i++)
		{
			neighbour.structure[i] = this.structure[i];
			
		}
		
		
		if ( Math.random() < Parameters_SA.neighbourrate)
		{
			//If below rate, neighbour is randomized, random value cannot be the current value
			int randomcontainer = (int) (Math.random() * Parameters_SA.Slength-1)+1;
			
			
			while(randomcontainer == neighbour.structure[index])
			{
				randomcontainer = (int) (Math.random() * Parameters_SA.Slength-1)+1;
			}
			neighbour.structure[index] = randomcontainer;
			Parameters_SA.neighbourrate /= Parameters_SA.controldecreasingFactor;
		}
		
		
	
		return neighbour;
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
	 * fitness calc
	 */
	
	public double fitness()
	{
		
		if(!alreadyCalculated)
		{
			double totalWeight;
			fitness = 0;
			boolean inUse = false;
			
			
		
		
			//Ver para cada número (contentor) quantas vezes ele aparece
			for (int i = 0; i < Parameters_SA.Slength; i++) {
				
				//Comparar o numero do contentor com a sua presença dentro da solução de forma a 
				//conseguir calcular se os weights não ultrapassam o limite
				
				//Vamos recalculando os total weights cada vez que analisamos um contentor novo
				totalWeight = 0;
				
				for(int z = 0; z<Parameters_SA.Slength;z++)
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
						
						totalWeight += Parameters_SA.weights[z];
						//Debug message
						//System.out.println("Iteration number: " + i + "found a match with number: " + structure[z] + ", the total weight is: "
						//		+ totalWeight);
						if(totalWeight > Parameters_SA.capacity)
						{
							//Debug message
							//System.out.println("We got a container overexceeding, container number: " + structure[z]);
							fitness = Parameters_SA.Slength+1;
							inUse=false;
						}
					}
				}
				if(inUse && fitness!=Parameters_SA.Slength+1)
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
		//System.out.println("MAPA DE WEIGHTS:");
		//System.out.println("[99 94 79 64 50 46 43 37 32 19 18 7 6 3]");
		for (int i = 0; i < Parameters_SA.Slength; i++) {
		//	System.out.print(structure[i] + " ");
		}
		System.out.println("    " + fitness());
	}
	
	
	public boolean betterOrEqual(Solution2 s)
	{
		if(Parameters_SA.maximization)
		{
			return this.fitness() >= s.fitness();
		}else
			return this.fitness() <= s.fitness();
	}
}
