package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 *  Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start to end, such that:

 Only one letter can be changed at a time
 Each intermediate word must exist in the dictionary
 For example,

 Given:
 start = "hit"
 end = "cog"
 dict = ["hot","dot","dog","lot","log"]

 As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 return its length 5.

 Note:

 Return 0 if there is no such transformation sequence.
 All words have the same length.
 All words contain only lowercase alphabetic characters.
 */
public class LadderLength {

    /**
     * Use BFS for iterating all the different words by changing one character at a time.
     * Also, since we need to keep track of visited nodes as well, we are keeping a set of visited nodes.
     */
    public int ladderLength(String start, String end, HashSet<String> dict) {

        if (start.equals(end) || dict.size() < 1) {
            return 0;
        }

        ArrayList<String> queue = new ArrayList<String>();  // Use BFS
        HashMap<String, Integer> map = new HashMap<String, Integer>(); // used to track the level
        HashSet<String> visited = new HashSet<String>(); // used to track visited Strings

        queue.add(start);
        map.put(start, 0);
        visited.add(start);

        while (!queue.isEmpty()) {
            String currentString = queue.remove(0);

            for (int i = 0; i < currentString.length(); i++) {
                for (int j = 0; j < 26; j++) {
                    StringBuilder sb = new StringBuilder(currentString);
                    sb.setCharAt(i, (char) ('a' + j));
                    String newString = sb.toString();

                    if (newString.equals(end)) {
                        return map.get(currentString) + 2; // found the result, use Two because we consider start and end step as well
                    }

                    if (dict.contains(newString) && !newString.equals(currentString) && !visited.contains(newString)) {
                        queue.add(newString);
                        visited.add(newString);
                        map.put(newString, map.get(currentString) + 1); // increase the level by adding one from its prev node
                    }
                }
            }
        }
        return 0;
    }

    public ArrayList<ArrayList<String>> findLadders(String start, String end, HashSet<String> dict) {
        if (start.equals(end) || dict.size() < 1) {
            return null;
        }

        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        ArrayList<String> queue = new ArrayList<String>();  // Use BFS
        HashMap<String, Integer> map = new HashMap<String, Integer>(); // used to track the level
        HashSet<String> visited = new HashSet<String>(); // used to track visited Strings
        HashMap<String, ArrayList<String>> pathSoFar = new HashMap<String, ArrayList<String>>();
        int bestLevelSoFar = 0;
        queue.add(start);
        map.put(start, 0);
        visited.add(start);

        ArrayList<String> startArray = new ArrayList<String>();
        startArray.add(start);
        pathSoFar.put(start, startArray);

        while (!queue.isEmpty()) {
            String currentString = queue.remove(0);

            if (map.get(currentString) > bestLevelSoFar && result.size() != 0)
                continue;

            for (int i = 0; i < currentString.length(); i++) {
                for (int j = 0; j < 26; j++) {
                    StringBuilder sb = new StringBuilder(currentString);
                    sb.setCharAt(i, (char) ('a' + j));
                    String newString = sb.toString();

                    if (newString.equals(end)) {
                        ArrayList<String> oldArray = pathSoFar.get(currentString);
                        ArrayList<String> newArray = createNewList(oldArray, newString);
                        pathSoFar.put(newString, newArray);

                        result.add(newArray);

                        bestLevelSoFar = map.get(currentString);
                    }

                    if (dict.contains(newString)
                            && !newString.equals(currentString)
                            && (!visited.contains(newString) )) {    //|| map.get(newString) > map.get(currentString)

                        queue.add(newString);
                        visited.add(newString);
                        map.put(newString, map.get(currentString) + 1); // increase the level by adding one from its prev node

                        ArrayList<String> oldArray = pathSoFar.get(currentString);
                        ArrayList<String> newArray = createNewList(oldArray, newString);
                        pathSoFar.put(newString, newArray);

                    }
                }
            }
        }
        return result;

    }

    private ArrayList<String> createNewList(ArrayList<String> oldArray, String currentNode) {
        ArrayList<String> newArray =  new ArrayList<String>();
        for (String node : oldArray)
            newArray.add(node);
        newArray.add(currentNode);
        return newArray;
    }

    public static void main(String[] args) {
        LadderLength ladderLength = new LadderLength();
        HashSet<String> set = new HashSet<String>();


        set.add("ted"); set.add("tex"); set.add("red"); set.add("tax"); set.add("tad");  set.add("den");
        set.add("rex"); set.add("pee");

        ArrayList<ArrayList<String>> result = ladderLength.findLadders("red", "tax", set);
        for (ArrayList<String> array : result) {
            for (String s : array) {
                System.out.print(s + " ");
            }
            System.out.println();
        }

        System.out.println("================");
//
//        set.clear();
//        set.add("hot");
//        set.add("dot");
//        set.add("dog");
//        set.add("lot");
//        set.add("log");
//        System.out.println(ladderLength.ladderLength("hit", "cog", set));
//        result = ladderLength.findLadders("hit", "cog", set);
//        for (ArrayList<String> array : result) {
//            for (String s : array) {
//                System.out.print(s + " ");
//            }
//            System.out.println();
//        }
//
//        System.out.println("================");
//
//
//        set.clear();
//        set.add("a");  set.add("b"); set.add("c");
//        result = ladderLength.findLadders("a", "c", set);
//        for (ArrayList<String> array : result) {
//            for (String s : array) {
//                System.out.print(s + " ");
//            }
//            System.out.println();
//        }
//
//        System.out.println("================");
    }

}
