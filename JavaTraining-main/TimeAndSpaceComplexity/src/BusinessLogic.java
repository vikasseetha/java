import java.util.*;

public class BusinessLogic {
    public void insertElements(ArrayList<Integer> list, int numElements) {
        for (int i = 0; i < numElements; i++) {
            list.add(i);
        }
    }

    public void insertElements(LinkedList<Integer> list, int numElements) {
        for (int i = 0; i < numElements; i++) {
            list.add(i);
        }
    }

    public void insertElements(HashSet<Integer> set, int numElements) {
        for (int i = 0; i < numElements; i++) {
            set.add(i);
        }
    }

    public void insertElements(TreeSet<Integer> set, int numElements) {
        for (int i = 0; i < numElements; i++) {
            set.add(i);
        }
    }

    public void insertElements(HashMap<Long, Long> map, long numElements) {
        for (long i = 0; i < numElements; i++) {
            map.put(i, i);
        }
    }

    public void insertElements(TreeMap<Long, Long> map, long numElements) {
        for (long i = 0; i < numElements; i++) {
            map.put(i, i);
        }
    }
}