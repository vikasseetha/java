import java.util.*;

public class SetSpaceAndTimeComplexity {
    HashSet<Integer> hashSet = new HashSet<>();
    TreeSet<Integer> treeSet = new TreeSet<>();

    BusinessLogic businessLogic = new BusinessLogic();
    int numElements = 100000;

    /**
     * In this method we are finding time and space complexity for insertion and removal operations for HashSet
     * in this we are using insertelements method for adding elements which is available in BusinessLogic class
     */
    public void insertAndDeleteHashSet() {
        long hashSetStartTime = System.nanoTime();
        businessLogic.insertElements(hashSet, numElements);
        long hashSetEndTime = System.nanoTime();
        long hashSetTimeTaken = hashSetEndTime - hashSetStartTime;
        long hashSetMemoryUsage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("HashSet Time And Memory After insertion of elements in Hashset ");
        System.out.println("Time taken by HashSet while insertion: " + hashSetTimeTaken + " nanoseconds");
        System.out.println("Memory usage of HashSet while insertion: " + hashSetMemoryUsage + " bytes");
        System.out.println("=============================================================================");


        long hashSetStartTime1 = System.nanoTime();
        hashSet.remove(100000);
        long hashSetEndTime1 = System.nanoTime();
        long hashSetTimeTaken1 = hashSetEndTime1 - hashSetStartTime1;
        long hashSetMemoryUsage1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("HashSet Time And Memory After Deletion Of an Element");
        System.out.println("Time taken by HashSet while Deletion: " + hashSetTimeTaken1 + " nanoseconds");
        System.out.println("Memory usage of HashSet while Deletion: " + hashSetMemoryUsage1 + " bytes");
        System.out.println("=============================================================================");

    }

    /**
     * In this method we are finding time and space complexity for insertion and removal operations for TreeSet
     * In this we are using insertelements method for adding elements which is available in BusinessLogic class
     */
    public void insertAndDeleteTreeSet() {


        long treeSetStartTime = System.nanoTime();
        businessLogic.insertElements(treeSet, numElements);
        long treeSetEndTime = System.nanoTime();
        long treeSetTimeTaken = treeSetEndTime - treeSetStartTime;
        long treeSetMemoryUsage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("TreeSet Time And Memory after insertion of elements");
        System.out.println("Time taken by TreeSet while insertion: " + treeSetTimeTaken + " nanoseconds");
        System.out.println("Memory usage of TreeSet while insertion: " + treeSetMemoryUsage + " bytes");
        System.out.println("Before Deletion");
        System.out.println();
        System.out.println("=============================================================================");


        long treeSetStartTime1 = System.nanoTime();
        treeSet.remove(78000);
        long treeSetEndTime1 = System.nanoTime();
        long treeSetTimeTaken1 = treeSetEndTime1 - treeSetStartTime1;
        long treeSetMemoryUsage1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        System.out.println();
        System.out.println("TreeSet Time And Memory after removal of an element");
        System.out.println("Time taken by TreeSet while Deletion: " + treeSetTimeTaken1 + " nanoseconds");
        System.out.println("Memory usage of TreeSet while Deletion: " + treeSetMemoryUsage1 + " bytes");

    }

    /**
     * This is main method to call required method
     *
     * @param args
     */
    public static void main(String[] args) {
        SetSpaceAndTimeComplexity setSpaceAndTimeComplexity = new SetSpaceAndTimeComplexity();
        setSpaceAndTimeComplexity.insertAndDeleteHashSet();
        setSpaceAndTimeComplexity.insertAndDeleteTreeSet();
    }
}
