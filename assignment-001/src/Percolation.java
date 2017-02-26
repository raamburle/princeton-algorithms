import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Percolation is a class used to store a simple NxN grid
 * This grid can be used to calculate the percolation threshold
 * using the Monte Carlo simulation
 *
 * @author Rohan Amburle
 * @version %I%, %G%
 * @since 1.0
 */
public class Percolation {
    // Size of the grid
    private final int gridSize;
    // Total number of open sites at any moment
    private int numberOfOpenSites;

    // Dummy head site
    private final int headSite;
    // Dummy tail site
    private final int tailSite;

    // Grid storing open/close state of individual sites
    private boolean[][] grid;

    // Union/find structure used to maintain site connections in the grid
    private WeightedQuickUnionUF siteTree;

    /**
     * Constructor
     *
     * @param n (required) Size of the grid. Has to be greater than zero
     */
    public Percolation(int n) {

        // Check if grid size is legal and create the grid.
        if (n < 1) {
            throw new IllegalArgumentException(
                    "Invalid grid size. Must be greater than zero");
        }
        gridSize = n;

        grid = new boolean[gridSize][gridSize];

        // Close all the sites. false is closed. true is open
        // Not necessary as default is false, but is a good practice
        for (int i = 1; i <= gridSize; i++) {
            for (int j = 1; j <= gridSize; j++) {
                grid[i - 1][j - 1] = false;
            }
        }

        numberOfOpenSites = 0;  // No open sites to begin with

        // Create site tree structure and calculate top/bottom site indices
        siteTree = new WeightedQuickUnionUF(gridSize * gridSize + 2);
        headSite = 0;
        tailSite = gridSize * gridSize + 1;
    }

    /**
     * Open the site at index [row, col] and connect it with the
     * adjoining open sites
     *
     * @param row (required) The row index of the site being opened.
     *            index must be between [1,gridSize]
     * @param col (required) The column index of the site being opened.
     *            index must be between [1,gridSize]
     */
    public void open(int row, int col) {
        // No need to check arguments here as isOpen() is being called

        if (!isOpen(row, col)) {
            grid[row - 1][col - 1] = true;
            numberOfOpenSites++;    // One more site opened up

            // Calculate indices of involved sites

            int currentSite = (row - 1) * gridSize + col;

            // Connect to top site if open
            if (row == 1) {
                // Site in top row. Connect to head site
                siteTree.union(currentSite, headSite);
            } else {
                if (isOpen(row - 1, col)) {
                    siteTree.union(currentSite, currentSite - gridSize);
                }
            }

            // Connect to bottom site if open
            if (row == gridSize) {
                // Site in bottom row. Connect to tail site
                siteTree.union(currentSite, tailSite);
            } else {
                if (isOpen(row + 1, col)) {
                    siteTree.union(currentSite, currentSite + gridSize);
                }
            }

            // Connect to left and right site if not in the boundary rows
            if (row != 1 && row != gridSize) {
                if (col != 1 && isOpen(row, col - 1)) {
                    siteTree.union(currentSite, currentSite - 1);
                }
                if (col != gridSize && isOpen(row, col + 1)) {
                    siteTree.union(currentSite, currentSite + 1);
                }
            }
        }
    }

    /**
     * Check if site at index [row, col] is open.
     *
     * @param row (required) The row index of the site being checked.
     *            index must be between [1,gridSize]
     * @param col (required) The column index of the site being checked.
     *            index must be between [1,gridSize]
     * @return <code>true</code> if the site is open;
     * <code>false</code> otherwise
     */
    public boolean isOpen(int row, int col) {
        checkArguments("Grid indices", gridSize, row, col);

        return grid[row - 1][col - 1];
    }

    /**
     * Check if site at index [row, col] is connected to an open
     * site in the top row.
     *
     * @param row (required) The row index of the site being checked.
     *            index must be between [1,gridSize]
     * @param col (required) The column index of the site being checked.
     *            index must be between [1,gridSize]
     * @return <code>true</code> if the site is full;
     * <code>false</code> otherwise
     */
    public boolean isFull(int row, int col) {
        checkArguments("Grid indices", gridSize, row, col);

        return siteTree.connected((row - 1) * gridSize + col, headSite);
    }

    /**
     * Returns the number of currently open sites in the grid
     *
     * @return Number of currently open sites in the grid
     */
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    /**
     * Checks if the current grid percolates
     *
     * @return <code>true</code> if the system percolates;
     * <code>false</code> otherwise
     */
    public boolean percolates() {
        return siteTree.connected(headSite, tailSite);
    }

    /**
     * Common method to validate if numeric arguments are valid
     * i.e. 1 &lt;= arg &lt;= grid size
     *
     * @param paramName (required) A simple description of the parameter to
     *                  print in the exception message
     * @param args      (required) A <code>vararg</code> list of integers
     *                  to be validated
     */
    private static void checkArguments(String paramName,
                                       int gridSize, int... args) {
        for (int arg : args) {
            if (arg < 1 || arg > gridSize) {
                throw new IllegalArgumentException(
                        paramName + " invalid" + "value: " + arg);
            }
        }
    }
}
