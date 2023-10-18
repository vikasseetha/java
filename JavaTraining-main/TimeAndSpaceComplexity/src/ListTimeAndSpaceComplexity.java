import java.util.*;

public class ListTimeAndSpaceComplexity {
    BusinessLogic businessLogic = new BusinessLogic();
    ArrayList<Integer> arrayList = new ArrayList<>();
    LinkedList<Integer> linkedList = new LinkedList<>();
    int numElements = 10000000;

    /**
     * In this method we are inserting deleting elements in arrayList and also we are finding time and space complexity of arraylist
     * In this we are using insertelements method for adding elements which is available in BusinessLogic class
     */
    public void insertAndDeleteArrayList() {
        long arrayListStartTime = System.nanoTime();
        businessLogic.insertElements(arrayList, numElements);
        long arrayListEndTime = System.nanoTime();
        long arrayListTimeTaken = arrayListEndTime - arrayListStartTime;
        long arrayListMemoryUsage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        System.out.println("ArrayList Time and memory After Insertion of number of elements ");
        System.out.println("Time taken by ArrayList: while insertion " + arrayListTimeTaken + " nanoseconds");
        System.out.println("Memory usage of ArrayList: while insertion  " + arrayListMemoryUsage + " bytes");
        System.out.println("=======================================================================================");
        System.out.println();

        System.out.println("ArrayList Time and memory After  deletion ");
        long arrayListStartTime1 = System.nanoTime();
        long arrayListTotalMemory1 = Runtime.getRuntime().totalMemory();
        arrayList.remove(10000);
        long arrayListFreeMemory1 = Runtime.getRuntime().freeMemory();
        long arrayListEndTime1 = System.nanoTime();
        long arrayListTimeTaken1 = arrayListEndTime1 - arrayListStartTime1;
        long arrayListMemoryUsage1 = arrayListTotalMemory1 - arrayListFreeMemory1;
        System.out.println("ArrayList Time and memory before deletion ");
        System.out.println("Time taken by ArrayList: After Deletion " + arrayListTimeTaken1 + " nanoseconds");
        System.out.println("Memory usage of ArrayList: After Deletion  " + arrayListMemoryUsage1 + " bytes");

    }

    /**
     * In this method we are inserting deleting elements in LinkedList and also we are finding time and space complexity of Linkedlist
     * In this we are using insertelements method for adding elements which is available in BusinessLogic class
     */
    public void insertAndDeleteLinkedList() {

        //insert data
        long linkedListStartTime = System.nanoTime();
        businessLogic.insertElements(linkedList, numElements);
        long linkedListMemoryUsage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long linkedListEndTime = System.nanoTime();
        long linkedListTimeTaken = linkedListEndTime - linkedListStartTime;
        System.out.println("LinkedList Time and memory After Insertion of elements");
        System.out.println("Time taken by LinkedList: " + linkedListTimeTaken + " nanoseconds");
        System.out.println("Memory usage of LinkedList: " + linkedListMemoryUsage + " bytes");
        System.out.println();

        //Delete data
        System.out.println(" Time and Space after deletion of an element");
        long linkedListStartTime1 = System.nanoTime();
        linkedList.remove(1);
        long linkedListEndTime1 = System.nanoTime();
        long linkedListTimeTaken1 = linkedListEndTime1 - linkedListStartTime1;
        long linkedListMemoryUsage1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("LinkedList Time and memory After deletion of an element");
        System.out.println("Time taken by LinkedList after Deletion " + linkedListTimeTaken1 + " nanoseconds");
        System.out.println("Memory usage of LinkedList after Deletion " + linkedListMemoryUsage1 + " bytes");
        System.out.println();

    }

    /**
     * This is main method to call required methods
     *
     * @param args
     */
    public static void main(String[] args) {
        ListTimeAndSpaceComplexity listTimeAndSpaceComplexity = new ListTimeAndSpaceComplexity();
        listTimeAndSpaceComplexity.insertAndDeleteLinkedList();
        listTimeAndSpaceComplexity.insertAndDeleteArrayList();
    }

}
