import java.util.*;

public class MapSpaceAndTimeComplexity {
    long numElements = 10000;
    HashMap<Long, Long> hashMap = new HashMap<>();
    TreeMap<Long, Long> treeMap = new TreeMap<>();
    BusinessLogic businessLogic = new BusinessLogic();

    /**
     * In this method we are finding time and space complexity for insertion and removal operations for HashMap
     * In this we are using insertelements method for adding elements which is available in BusinessLogic class
     */
    public void hashMapInsertAndDeleteOperation() {
        long hashMapStartTime = System.nanoTime();
        businessLogic.insertElements(hashMap, numElements);
        long hashMapEndTime = System.nanoTime();
        long hashMapTimeTaken = hashMapEndTime - hashMapStartTime;
        long hashMapMemoryUsage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();


        System.out.println("HashMap Time And Memory after insertion");
        System.out.println("Time taken by HashMap while insertion " + hashMapTimeTaken + " nanoseconds");
        System.out.println("Memory usage of HashMap while insertion " + hashMapMemoryUsage + " bytes");
        System.out.println("=======================================================================");
        System.out.println();

        long hashMapStartTime1 = System.nanoTime();
        hashMap.remove(120l);
        long hashMapEndTime1 = System.nanoTime();
        long hashMapTimeTaken1 = hashMapEndTime1 - hashMapStartTime1;
        long hashMapMemoryUsage1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();


        System.out.println("HashMap Time And Memory after removal of an element");
        System.out.println("Time taken by HashMap After Deletion " + hashMapTimeTaken1 + " nanoseconds");
        System.out.println("Memory usage of HashMap After Deletion " + hashMapMemoryUsage1 + " bytes");
        System.out.println("=======================================================================");
        System.out.println();


    }

    /**
     * In this method we are finding time and space complexity for insertion and removal operations for TreeMap
     * In this we are using insertelements method for adding elements which is available in business logic
     */
    public void treeMapInsertAndDeleteOperation() {
        long treeMapStartTime = System.nanoTime();
        businessLogic.insertElements(treeMap, numElements);
        long treeMapEndTime = System.nanoTime();
        long treeMapTimeTaken = treeMapEndTime - treeMapStartTime;
        long treeMapMemoryUsage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("TreeMap Time And Memory usage After Insertion");
        System.out.println("Time taken by TreeMap while insertion: " + treeMapTimeTaken + " nanoseconds");
        System.out.println("Memory usage of TreeMap while insertion : " + treeMapMemoryUsage + " bytes");
        System.out.println();

        long treeMapStartTime1 = System.nanoTime();
        treeMap.remove(120l);
        long treeMapEndTime1 = System.nanoTime();
        long treeMapTimeTaken1 = treeMapEndTime1 - treeMapStartTime1;
        long treeMapMemoryUsage1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("TreeMap Time And Memory usage before delete");
        System.out.println("Time taken by TreeMap After Deletion: " + treeMapTimeTaken1 + " nanoseconds");
        System.out.println("Memory usage of TreeMap After Deletion : " + treeMapMemoryUsage1 + " bytes");
        System.out.println();
    }

    /**
     * This is main method in the class for calling required methods
     *
     * @param args
     */
    public static void main(String[] args) {
        MapSpaceAndTimeComplexity mapSpaceAndTimeComplexity = new MapSpaceAndTimeComplexity();
        mapSpaceAndTimeComplexity.treeMapInsertAndDeleteOperation();
        mapSpaceAndTimeComplexity.hashMapInsertAndDeleteOperation();

    }
}
