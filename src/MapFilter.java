import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by gungr on 27-Dec-16.
 */
public class MapFilter {

    // Java8 - Filer a map example

    // before Java8
    public static void mapEntry(Map<Integer, String> map) {
        int result = -1;
        for(Map.Entry<Integer, String> entry : map.entrySet()){
            if("four".equals(entry.getValue())) {
                result = entry.getKey();
            }
        }

        System.out.println(result);
    }

    // With Java8, using stream(), filter() and collect()
    public static Map<Integer, String> mapStream(Map<Integer, String> map) {
        String result = map.entrySet().stream()
                .filter(m -> "four".equals(m.getValue()))
                .map(Map.Entry::getValue)
                .collect(Collectors.joining());
        System.out.println(result);

        Map<Integer, String> collect = map.entrySet().stream()
                .filter(m -> m.getKey() >= 2)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return collect;
    }

    // Example 2
    public static void mapStreamExample() {
        Map<Integer, String> hosting = new HashMap<>();
        hosting.put(1, "linode.com");
        hosting.put(2, "heroku.com");
        hosting.put(3, "digitalocean.com");
        hosting.put(4, "aws.amazon.com");

        String result = "";
        for(Map.Entry<Integer, String> entry : hosting.entrySet()) {
            if("aws.amazon.com".equals(entry.getValue())) {
                result = entry.getValue();
            }
        }
        System.out.println(result);

        // Map -> Stream -> Filter -> String
        String resultS = hosting.entrySet().stream()
                .filter(m -> "aws.amazon.com".equals(m.getValue()))
                .map(Map.Entry::getValue)   // replace lambda with method reference
                .collect(Collectors.joining());
        System.out.println(resultS);
    }

    // Example 3
    public static void mapStreamExample3() {
        Map<Integer, String> hosting = new HashMap<>();
        hosting.put(1, "linode.com");
        hosting.put(2, "heroku.com");
        hosting.put(3, "digitalocean.com");
        hosting.put(4, "aws.amazon.com");

        Map<Integer, String> collect = hosting.entrySet().stream()
                .filter(m -> m.getKey() >= 2)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        System.out.println(collect);
    }

    public static void listStream() {
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");

        List<String> collect = list.stream().filter(m -> m.contains("o"))
                .collect(Collectors.toList());

        System.out.println(collect);
    }


    public static void main(String[] args) {
        Map<Integer, String> map1 = new HashMap<>();
        map1.put(1, "one");
        map1.put(2, "two");
        map1.put(3, "three");
        map1.put(4, "four");
        map1.put(5, "four");

        Map<Integer, String> map2 = new HashMap<>();

        mapEntry(map1);
        mapEntry(map2);

        mapStream(map1);
        mapStream(map2);

        mapStreamExample();

        mapStreamExample3();

        listStream();
    }
}
