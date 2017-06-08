package SimulatedAnnealing;

public class SimulatedAnnealing 
{

	public static void main(String[] args) {


		double sumfitnesses = 0;
		System.out.println();
		//Random solution initialization
		Solution2 current = new Solution2();
		Solution2 neighbour;
		Solution2 bestSofar = current;

		
		//2. Main loop
		
		for (int iter = 0; iter < Parameters.NumIterations; iter++)
		{
			
			//Monitor solution
			//System.out.print(" -Iteration. " + iter);
			//current.print();
			
			{
				//2.1
				//get random index for neighbours - Simillar to mutation in GA
				// we multiply by random 0-1 and cast to int. 1 is exluded so we will never get lenght
				int randomIndex = (int) (Math.random() * Parameters.Slength);
				neighbour = current.generateNeighbour(randomIndex);
			
				/*
				 * 2.2 - We assume problem is maximization
				 * We will have this decided late
				 * fitness method checks if it was already calculated
				*/
				
				if(neighbour.betterOrEqual(current))
				{
					current = neighbour;
					if(neighbour.betterOrEqual(bestSofar))
					{					
					bestSofar = neighbour;
		
					}
					
				} 
				else 
				{
					//Decide if accept new neighbour if < fitness
					if(Math.random() <
							Math.exp(((-1.0) * 
									Math.abs(current.fitness() - neighbour.fitness() )) / 
									Parameters.controlParamenter))
					{
						//System.out.println("Accepted new neighbour, cFitness: " + current.fitness() + ", nFitness: " + neighbour.fitness());
						current = neighbour;
						
					}
				}
				
				sumfitnesses += current.fitness();
				
				System.out.print("" + iter + ", ");
				System.out.print("");
					//System.out.print(sumfitnesses/41);
					bestSofar.print();
					sumfitnesses = 0;
				
				
			} //end internal for
			
			Parameters.controlParamenter = Parameters.controlParamenter / Parameters.controldecreasingFactor;
		}
		//System.out.println("");
		//System.out.print(" Best Solution is: ");
		//bestSofar.print();
	}

}

	

