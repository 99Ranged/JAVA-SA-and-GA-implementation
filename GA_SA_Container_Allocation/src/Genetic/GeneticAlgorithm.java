package Genetic;

public class GeneticAlgorithm {

	public static void main(String[] args) {

		Solution[] population = new Solution[Parameters.popSize]; 
		Solution[] newPopulation = new Solution[Parameters.popSize]; 
		int newPopIndivs = 0;
		
		for (int i = 0; i < Parameters.popSize; i++) {
			population[i] = new Solution();
		}

		for (int gen = 1; gen <= Parameters.NumGenerations; gen++) {
		
			newPopIndivs = 0;
			
			if (Parameters.elitism) {
				newPopulation[newPopIndivs] = bestIndiv(population);
				newPopIndivs++;
			}
			
			while (newPopIndivs < Parameters.popSize-1) {
				
				Solution parent1 = ranking(population);
				Solution parent2 = ranking(population);
				Solution son1 = new Solution();
				Solution son2 = new Solution();
				
				if (Math.random() < Parameters.crossoverRate) {
					crossover2p(parent1, parent2, son1, son2);
					
				} else {
					son1 = parent1.myCopy();
					son2 = parent2.myCopy();
				}
				
				newPopulation[newPopIndivs] = mutation(son1);
				newPopIndivs++;
				newPopulation[newPopIndivs] = mutation(son2);
				newPopIndivs++;

			}  // end of loop that fills a new population
			
			population = newPopulation;
			System.out.print("Generation: " + gen + ". ");
			
			bestIndiv(population).print();		
		}  // end of main loop of GAs	
	} // end of main method

	
	

	/*-----------------------------------------------
	 * 
	 *  tournament
	 */
	
	public static Solution tournament (Solution[] pop) {
		
		Solution best = pop[(int) (Math.random() * Parameters.popSize)].myCopy();
	
		for (int i = 0; i < Parameters.tournamentSize; i++) {
			
			Solution newSolution = pop[(int) (Math.random() * Parameters.popSize)];
			
			if (newSolution.betterOrEqual(best)) {
				best = newSolution.myCopy();
			}
			
		}
		
		return best;
		
	}
	
	
	
	// Rank Selection --------------------------------------------------------------
	public static Solution ranking (Solution[] pop)
		{
			Solution[] SortedSolution = new Solution[Parameters.popSize]; 		
			//Copy original array
			for(int i = 0; i<Parameters.popSize; i++)
			{
				SortedSolution[i] = pop[i].myCopy();
			}	
			
			quickSort(SortedSolution,0,Parameters.popSize-1);
		
			double[] SortedSolutionfit = new double[Parameters.popSize];
			double totalfitness = 0;
			
			//Depending on the maximization parameter, probabilities are calculated differently
			if(Parameters.Maximization)
			{
				for(int i = 0; i<Parameters.popSize; i++)
				{
					SortedSolutionfit[i] = i+1;
					totalfitness += SortedSolutionfit[i];
				}
			}else
			{
				for(int i = Parameters.popSize; i>0; i--)
				{
					SortedSolutionfit[i-1] = i;
					totalfitness += SortedSolutionfit[i-1];
				}
			}
				
			//Assign new probabilities
			for(int i = 0; i<Parameters.popSize; i++)
			{
				 SortedSolutionfit[i] = SortedSolutionfit[i]/totalfitness;
			}
			
			
			//Spin roulette wheel
			
			double value = Math.random();	
			//Check for maximization, so we spin the wheel in the same orientation
			if(Parameters.Maximization)
			{
				for(int i = 0; i<Parameters.popSize; i++)
				{
					value -= SortedSolutionfit[i];
					if(value <= 0)
					{
						return SortedSolution[i].myCopy();
					}
				}
			}else
			{
				for(int i = Parameters.popSize-1; i>0; i--)
				{
					value -= SortedSolutionfit[i-1];
					if(value <= 0)
					{
						return SortedSolution[i].myCopy();
					}
				}
			}
			
			//Else return best individual
			return  bestIndiv(pop);
			
		}
		
	// Roulette --------------------------------------------------------------------
		
	public static Solution wheelselection (Solution[] pop)
			{
				// Initialize the best solution with a random from the population				
				double[] SolutionProb = new double[Parameters.popSize]; 
				double totalfitness = 0;
				double totalprob = 0;
				//put probabilities for each solution in a probability array
				for(int i = 0; i<Parameters.popSize; i++)
				{
					// We will only use non-negative fitnesses
					if(pop[i].fitness()!=-1)
					totalfitness += pop[i].fitness();
				}
				
				//Put probabilities for each solution in a probability array
				for(int i = 0; i<Parameters.popSize; i++)
				{
					//Formula to give different probabilities based on fitness
					if(pop[i].fitness()!=-1)
					{				
						//Probability calculations for maximization
						if(Parameters.Maximization)
						{
						SolutionProb[i] = pop[i].fitness()/totalfitness;
						totalprob += SolutionProb[i];
						}else
						{
							//Parameters.stringlength = maximum fitness
							SolutionProb[i] = (Parameters.stringhLength + 1) - pop[i].fitness()
																			   /totalfitness;
							totalprob += (Parameters.stringhLength + 1) - SolutionProb[i];	
						}
					}
				}
				double value = Math.random() * totalprob;
				if(Parameters.Maximization)
				{
								
					for(int i = 0; i<SolutionProb.length; i++)
					{
						value -= SolutionProb[i];
						if(value <= 0)
						{
							return pop[i].myCopy();
						}
					}
				}else
				{
					for(int i = SolutionProb.length-1; i>0; i--)
					{
						value -= SolutionProb[i-1];
						if(value <= 0)
						{
							return pop[i].myCopy();
						}
					}
				}
				//Else return best individual
				return  bestIndiv(pop);
			}
	
		
	
	/*-----------------------------------------------
	 * 
	 *  crossover
	 */
	
	public static void crossover (Solution p1, Solution p2, Solution s1, Solution s2) {
		
		int crossoverPoint = (int) (Math.random() * (Parameters.stringhLength-1));
		
		for (int i = 0; i < Parameters.stringhLength; i++) {
			
			if (i <= crossoverPoint) {
				
				s1.setPosition(i, p1.getPosition(i));
				s2.setPosition(i, p2.getPosition(i));
				
			} else {
				s1.setPosition(i, p2.getPosition(i));
				s2.setPosition(i, p1.getPosition(i));

			}
		}
	}
	
	/*-----------------------------------------------
	 * 
	 *  crossover with 2 crossover points
	 */
	
	public static void crossover2p (Solution p1, Solution p2, Solution s1, Solution s2) {
		
		int crossoverPoint1 = (int) (Math.random() * (Parameters.stringhLength-1)+1);
		int crossoverPoint2 = (int) (Math.random() * (Parameters.stringhLength-1)+1);
		
		
		for (int i = 0; i < Parameters.stringhLength; i++) {
			
			if (i <= Math.min(crossoverPoint1, crossoverPoint2)) {
				
				s1.setPosition(i, p1.getPosition(i));
				s2.setPosition(i, p2.getPosition(i));
				
			} else 
				if (i > Math.max(crossoverPoint1, crossoverPoint2)){
				s1.setPosition(i, p1.getPosition(i));
				s2.setPosition(i, p2.getPosition(i));

			}else
			{
				s1.setPosition(i, p2.getPosition(i));
				s2.setPosition(i, p1.getPosition(i));
			}
		}
		
		
	}
	
	/*-----------------------------------------------
	 * 
	 *  mutation
	 */
	
	public static Solution mutation (Solution s) {
		
		Solution result = s.myCopy();
		for (int i = 0; i < Parameters.stringhLength; i++) {
			if (Math.random() < Parameters.mutationRate) {
				result.setPosition(i,  result.getPosition((int) (Math.random() 
											* Parameters.stringhLength-1)+1));
			} 
		}
		return result;
	}
	
	
	
	/*-----------------------------------------------
	 * 
	 *  bestIndiv
	 */
	
	public static Solution bestIndiv (Solution[] pop) {
		Solution best = pop[0].myCopy();
		for (int i = 1; i < pop.length; i++) {
			
			if (pop[i].betterOrEqual(best)) {
				best = pop[i].myCopy();
			}
			
		}
		return best;
	}
	
	
	//Quicksort algorithm (used in roulette wheel selection) -----------------------------------------------------------
	public static void quickSort(Solution[] arr, int low, int high) 
	    {
	        //check for empty or null array
	        if (arr == null || arr.length == 0){
	            return;
	        }
	         
	        if (low >= high){
	            return;
	        }
	 
	        //Get the pivot element from the middle of the list
	        int middle = low + (high - low) / 2;
	        Solution pivot = arr[middle];
	 
	        // make left < pivot and right > pivot
	        int i = low, j = high;
	        while (i <= j) 
	        {
	            //Check until all values on left side array are lower than pivot
	            while (arr[i].fitness() < pivot.fitness()) 
	            {
	                i++;
	            }
	            //Check until all values on left side array are greater than pivot
	            while (arr[j].fitness() > pivot.fitness()) 
	            {
	                j--;
	            }
	            //Now compare values from both side of lists to see if they need swapping 
	            //After swapping move the iterator on both lists
	            if (i <= j) 
	            {
	                swap (arr, i, j);
	                i++;
	                j--;
	            }
	        }
	        //Do same operation as above recursively to sort two sub arrays
	        if (low < j){
	            quickSort(arr, low, j);
	        }
	        if (high > i){
	            quickSort(arr, i, high);
	        }
	    }
	     //Quicksort swap
	    public static void swap (Solution array[], int x, int y)
	    {
	        Solution temp = array[x];
	        array[x] = array[y];
	        array[y] = temp;
	    }
	

	//Mutation swap
	public static Solution swap (Solution s, int x, int y)
	 	{    
	       int temp2 = s.getPosition(x);
	       int temp3 = s.getPosition(y);
	       s.setPosition(x, temp3);
	       s.setPosition(y, temp2);   
	       return s;
	    }    
	    
	 
	
	
}  // end of class
