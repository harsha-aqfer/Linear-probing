import linearprobing.*;

public class LinearProbingTester {

    public static void main(String[] args) {
        // create a map

        LinearProbingHashTable<String, Integer> mapA = new LinearProbingHashTable<>();
        System.out.println(" mapA is empty (true)==:" + mapA.isEmpty());
        mapA.put("A1", 124);
        mapA.put("A2", 125);
        System.out.println("Entries inside mapA: " + mapA);
        mapA.put("A3", 124);
        mapA.put("A5", 1022);
        mapA.put("A4", 123);
        mapA.put("A4", 1023);
        mapA.put("A5", 122);
        System.out.println("==========<<>>==========");
        System.out.println("mapA is not empty (false)==:" + mapA.isEmpty());
        System.out.println("Size of mapA is (5)  ==>" + mapA.size());
        System.out.println("Entries inside mapA: " + mapA);
        System.out.println("mapA contains key \"A1 \"==>" + mapA.containsKey("A1"));
        System.out.println("mapA does not contain key \"A10 \"==>" + mapA.containsKey("A10"));
        System.out.println("mapA does not contain value 10 \"==>" + mapA.containsValue(10));
        System.out.println("mapA contains value 122 ==>" + mapA.containsValue(122));
        System.out.println("==========<<test entrySet and print  >>==========");
        Iterable<Entry<String, Integer>> mapAitr = mapA.entrySet();
        for (Entry<String, Integer> e : mapAitr) {
            System.out.println(e);
        }
        System.out.println("==========<<test keySet and Print Keys>>==========");
        for (String s : mapA.keySet()) {
            System.out.println(s);
        }
        System.out.println("==========<<test values and Print Values >>==========");
        for (Integer J : mapA.values()) {
            System.out.println(J);
        }

        System.out.println("==========<<>>==========");
        System.out.println("Entries inside mapA: " + mapA);
        System.out.println("return value associated with \"A3\" 124 ==>" + mapA.get("A3"));
        mapA.put("A3", 555); // update A3 value
        System.out.println("return value associated with \"A3\" 555 ==>" + mapA.get("A3"));
        System.out.println("==========<<>>==========");
        System.out.println("remove key \"A5\" and return value 122 " + mapA.remove("A5"));
        System.out.println("return null since \"A9\" exist==>" + mapA.remove("A9"));
        System.out.println("==========<<>>==========");
        System.out.println("return null since \"A9\" exist==>" + mapA.get("A9"));
        System.out.println("return null since \"A5\" exist==>" + mapA.get("A5"));
        System.out.println("==========<<>>==========");
        System.out.println("return null since \"A5\" exist==>" + mapA.remove("A5"));
        System.out.println("==========<<>>==========");

        LinearProbingHashTable<String, Integer> mapB = new LinearProbingHashTable<>(10);
        for (int i = 0; i < 30; i += 2) {
            mapB.put("B" + i, i);
        }
        System.out.println("==========<<>>==========");
        System.out.println("Entries inside mapB: \n" + mapB);


        LinearProbingHashTable<Integer, String> mapC = new LinearProbingHashTable<>(5, .25);
        for (int i = 0; i < 10; i += 2) {
            mapC.put(i, "C" + i);
        }
        for (int i = 0; i < 10; i += 2) {
            mapC.put(i, "CC" + i);
        }
        System.out.println("==========<<>>==========");
        System.out.println("Entries inside mapC: \n" + mapC);
    }

}
