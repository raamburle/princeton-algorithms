import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import static java.lang.System.exit;

/**
 * PercolationStats class is used to capture percolation threshold
 * using Monte Carlo simulation and capture statistical data
 *
 * @author Rohan Amburle
 * @version %I%, %G%
 * @since 1.0
 */
public class PercolationStats {

    // Maintains the number of sites at percolation for each run
    private double stats[];

    // No. of trials in this experiment
    private int trials;

    /**
     * Constructor
     *
     * @param n      (required) Size of the grid. Has to be greater than zero
     * @param trials (required) No. of independent trials to be performed.
     *               Has to be greater than zero
     */
    public PercolationStats(int n, int trials) {

        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Grid size and no. of " +
                    "trials must be greater than zero");
        }

        this.trials = trials;
        stats = new double[trials];
        for (int i = 0; i < trials; i++) {
            stats[i] = 0;

            Percolation grid = new Percolation(n);

            while (!grid.percolates()) {
                // Open a random site
                grid.open(StdRandom.uniform(n) + 1, StdRandom.uniform(n) + 1);
            }

            stats[i] = (double) grid.numberOfOpenSites() / (n * n);
        }
    }

    public double mean() {
        return StdStats.mean(stats);
    }

    public double stddev() {
        return StdStats.stddev(stats);
    }

    public double confidenceLo() {
        return (mean() - 1.96 * stddev() / Math.sqrt(trials));
    }

    public double confidenceHi() {
        return (mean() + 1.96 * stddev() / Math.sqrt(trials));
    }

    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("Invalid arguments");
            System.out.println("\nUsage:\n\nPercolationStats " +
                    "<grid size> <no. of trials>");
            exit(1);
        }

        int n = Integer.parseInt(args[0]);            // grid size
        int trials = Integer.parseInt(args[1]);       // no. of trials

        PercolationStats experiment = new PercolationStats(n, trials);
        //System.out.println(Arrays.toString(experiment.stats));
        System.out.println("Mean                   : " + experiment.mean());
        System.out.println("StdDev                 : " + experiment.stddev());
        System.out.println("95% confidence interval: ["
                + experiment.confidenceLo() + ", "
                + experiment.confidenceHi() + "]");
    }
}
