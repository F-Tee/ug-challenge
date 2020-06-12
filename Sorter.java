package ug_challenge;

import java.util.ArrayList;
import java.util.Arrays;

public class Sorter {

    // Input set
    private static final int[] input = {2, 52, 15, 63, 47, 77, 71, 48, 28, 53, 100, 50, 90, 4, 73, 11, 7, 41, 74, 14, 75, 58, 5, 40, 78, 19, 32, 38, 82, 49, 81, 54, 99, 96, 18, 35, 93, 69, 76, 30, 91, 36, 85, 98, 57, 25, 23, 42, 20, 33, 37, 68, 64, 31, 1, 12, 43, 86, 59, 84, 67, 17, 72, 44, 66, 21, 79, 97, 22, 61, 46, 88, 26, 51, 89, 6, 60, 65, 95, 24, 56, 45, 39, 83, 10, 29, 9, 34, 13, 80, 55, 70, 3, 87, 8, 94, 27, 62, 16, 92};
    ArrayList<Integer> list = arrayToList();
    // List of removed items
    ArrayList<Integer> removed = new ArrayList<>();
    // List of added items
    ArrayList<Integer> added = new ArrayList<>();
    int size = input.length + 1;
    int index;
    int lowest;
    int outerIndex;

    public Sorter() {

    }

    public int[] getInput() {
        return input;
    }

    public void loop(int[] input) {
        // Determines the starting number of the sort and calls functions
        for (int j = 0; j < list.size() - 1; j++) {
            index = outerIndex = j;
            listSort(index);
            sortReset();
        }
        // Once the lowest sized list of removed items is determined
        // The sort is run once more using the remembered index of the lowest number
        listSort(lowest);
        // Information is then printed
        System.out.println("Input: " + Arrays.toString(input));
        System.out.println("Removed: " + removed);
        System.out.println("Sorted: " + added);
        System.out.println("Number of removed elements: " + removed.size());
    }

    // This is the main sorting function
    private void listSort(int index) {
        // If number to start from is not 0, then the preceding numbers are added to the removed list
        if (index != 0) {
            removed = new ArrayList<>(list.subList(0, index));
        }
        // The main sorting for loop
        for (int i = index; i < list.size(); i++) {
            // If the current number is not at the end, the number following it is less
            // and the number following it is less than the compared number then the number is removed
            if (i != list.size() - 1 && list.get(i + 1) <= list.get(i) && list.get(i + 1) > list.get(index)) {
                removed.add(list.get(i));
                // Else if the number is greater than or equal to the compared number it is added
                // Now the compared number is set to that
            } else if (list.get(i) >= list.get(index)) {
                added.add(list.get(i));
                index = i;
                // Else the number is removed
            } else {
                removed.add(list.get(i));
            }
        }
    }

    // This function checks whether the size of the current run is less than the lowest
    // If so, the lowest size is updated, and the lowest index is set
    // The added and removed lists are cleared for the next run
    private void sortReset() {
        if (removed.size() < size) {
            lowest = outerIndex;
            size = removed.size();
        }
        added.clear();
        removed.clear();
    }

    // This function adds each element of the array to the arraylist
    private ArrayList<Integer> arrayToList() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int value : input) {
            list.add(value);
        }
        return list;
    }

    public static void main(String[] args) {
        double start = System.currentTimeMillis();
        Sorter sort = new Sorter();
        sort.loop(sort.getInput());
        double end = System.currentTimeMillis();
        System.out.println("Time: " + ((end - start) / 1000) + " seconds.");
    }
}
