import linearprobing.*;
import org.testng.Assert;
import org.testng.annotations.*;


public class LinearProbingTester {

    LinearProbingHashTable<String, Integer> mapA = new LinearProbingHashTable<String, Integer>();
    @Test
    public void testMapFunctions() {
        Assert.assertTrue(mapA.isEmpty());
        mapA.put("A1", 124);
        mapA.put("A2", 125);
        Assert.assertEquals(mapA.toString(), "[<A1, 124>,<A2, 125>]");
        mapA.put("A3", 124);
        mapA.put("A5", 1022);
        mapA.put("A4", 123);
        mapA.put("A4", 1023);
        mapA.put("A5", 122);
        Assert.assertFalse(mapA.isEmpty());
        Assert.assertEquals(5, mapA.size());
        System.out.println(mapA);
        Assert.assertTrue(mapA.containsKey("A1"));
        Assert.assertFalse(mapA.containsKey("A10"));
        Assert.assertFalse(mapA.containsValue(10));
    }
}
