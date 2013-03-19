package ga;

public class GeneticAlgorithm {
	protected Reproduction repFun;
	protected Mutation mutFun;
	protected Population population;
	protected long currentGen;
	protected long generations;
	public double average[], best[];
	
	
	public GeneticAlgorithm(long numOfGenerations, Population pop, 
			Reproduction rep, Mutation mut)
	{
		population = pop;
		repFun = rep;
		mutFun = mut;
		generations = numOfGenerations;
		average = new double[(int)generations];
		best = new double[(int)generations];
	}
	
	protected void advanceGeneration(){
		currentGen++;
		population.restoreElite();
		repFun.reproduce(population);
		mutFun.mutate(population);
		population.recalculate();
		average[(int)currentGen-1] = population.average();
		best[(int)currentGen-1] = population.getBest().fitness();
	}
	
	/**
	 * The main method of the GAs, it searches for the best individual
	 * for the fitness function.
	 */
	public Individual search(){
		currentGen = 0;
		mutFun.reset();
		while(currentGen<generations){
			advanceGeneration();
		}

		return population.getBest();
	}
	
	public double[] getAvgHistory(){
		return average;
	}
	
	public double[] getBstHistory(){
		return best;
	}
}
