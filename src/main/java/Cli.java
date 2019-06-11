import java.io.*;

public class Cli {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter max size of cache");
        LRUCache<Integer, Integer> cache = new LRUCache<>(Integer.parseInt(reader.readLine()));
        String line = null;

        while (!(line = reader.readLine()).isEmpty()) {
            if (line.charAt(0) == '*') {
                cache.keys().map(cache::get).forEach(System.out::println);
            } else {
                String[] splitted = line.split("\\s+");
                if (splitted.length == 1) {
                    try {
                        int key = Integer.parseInt(splitted[0]);
                        if (cache.get(key) == null) {
                            System.out.println("There is no item for this key in cache");
                        } else {
                            System.out.println(cache.get(key));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Wrong input");
                    }
                } else if (splitted.length == 2) {
                    int key = Integer.parseInt(splitted[0]);
                    int value = Integer.parseInt(splitted[1]);

                    try {
                        Integer deletedKey = cache.put(key, value);
                        System.out.print(cache.size());
                        if (deletedKey != null) {
                            System.out.print(" " + deletedKey);
                        }
                        System.out.println();
                    } catch (NumberFormatException e) {
                        System.out.println("Wrong input");
                    }
                } else {
                    System.out.println("Wrong input");
                }
            }
        }

        reader.close();
    }
}
