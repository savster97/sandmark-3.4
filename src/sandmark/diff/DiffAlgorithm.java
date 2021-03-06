package sandmark.diff;

/** A DiffAlgorithm will perform a comparison of any two jar files,
 *  reporting similarities and/or differences.
 *  The algorithm can be applied by calling run()
 *  explicitly or creating a new thread: <code>new Thread(myAlgorithm).start()</code>.
 *  Once the thread has completed, call <code>getResults()</code>.
 *  @author Zach Heidepriem
 */

public abstract class DiffAlgorithm implements Runnable, Monitorable {    

    protected sandmark.program.Application app1;
    protected sandmark.program.Application app2;
    protected sandmark.diff.DiffOptions options; 
    protected sandmark.diff.Result[] results;
    
    /** Construct a DiffAlgorithm
     *  @param a the first Application
     *  @param b the second Application
     *  @param o the set of DiffOptions to use when diffing
     */
    public DiffAlgorithm(sandmark.program.Application a,
                         sandmark.program.Application b,
                         sandmark.diff.DiffOptions o){
        app1 = a;
        app2 = b;
        options = o;     
    }
    
    /** @return the name of this algorithm
     */    
    public abstract String getName();

    /** @return a short description of this algorithm
     */   
    public abstract String getDescription();   

    /** @return an array of Result objects generated by this algorithm.
     */   
    public abstract sandmark.diff.Result[] getResults();

    /** @param r the Result object to color
     *  @return a Coloring object generated by this algorithm for a given Result object
     */   
    public abstract sandmark.diff.Coloring[] color(sandmark.diff.Result r);    

    /**The run method creates and fills the results for this algorithm.
     * After calling run(), call getResults() to retrieve the results.
     */
    public abstract void run();    

    /** Diff two given objects using this algorithm
     *  @param o1 the first object
     *  @param o2 the second object
     *  @return a Result object generated by this algorithm
     */
    public abstract sandmark.diff.Result diff(sandmark.program.Object o1, 
                                              sandmark.program.Object o2);   

    /** @return a measure of the expected time to apply the algorithm. 
     *  See <code>getCurrent()</code>
     */
    public abstract int getTaskLength();
    
    /** Intended to be called while <code>run()</code> is being run in a separate thread.
     *  @return a measure of how much diffing the alorithm has completed.  
     */
    public abstract int getCurrent();       

    /** Stop this from running
     */
    public abstract void stop();

    /** @return the name of this algorithm
     */
    public String toString(){
        return getName();
    }
}
