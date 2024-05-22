# Java Collections

Java Collections Framework is an importent part of java programming

![](../images/java_collections.png)

## Interfaces
java.util.Collection is the root interface of Collections Framework, it contains some importent methods such as size(), iterator(), add(), remove(), clear(), etc.

some other importent interfaces are:</br>
java.uitl.List</br>
java.util.Set</br>
java.util.Queue</br>
java.util.Map - Map interface does not inherit from The Collection interface, but it is part of Collections Framework</br>
Iterator interface - provides methods iterate over the elements of the Collection</br>
SortedSet interface
SortedMap interface

## Collection classes

### ArrayList
ArrayList is the resizable array implementation of the List interface.
It has a fase indexed access time, which means that retreiving elements from an ArrayList by an index is very quick.
but delete or add an elements is slow
### LinkedList
LinkedList, on the other hand, is implemented by using a linked list data (dose not use array to store elemets), so fast when modify elements (add or delete)
### HashSet
HashSet is implemented base on HashMap, and guarantees that no duplicate elements were added
### TreeSet
TreeSet is implemented base on TreeMap, the elements are ordered using nature ordering. or by a Comparator provided when constructed
```java
public class TreeSetTest {
    public static void main(String[] args) {
        Set<String> treeSet = new TreeSet<>();
        treeSet.add("G");
        treeSet.add("E");
        treeSet.add("E");
        treeSet.add("K");
        treeSet.add("S");
        treeSet.add("4");
        System.out.println(treeSet);

        Set<String> treeSet2 = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        treeSet2.add("G");
        treeSet2.add("E");
        treeSet2.add("E");
        treeSet2.add("K");
        treeSet2.add("S");
        treeSet2.add("4");
        System.out.println(treeSet2);
    }
}
```

output:
`[4, E, G, K, S]
 [S, K, G, E, 4]
`

## Map
### HashMap
### ConcurrentHashMap
### BiMap
A bimap i.e, bidirectional map is a map that preserves the uniqueness of its values as well as that of its keys. BiMaps support inverse view, which is another bimap containing the same entries as this bimap but with reversed keys and values.

Below given are some methods provided by Guava BiMap Interface :

| Method | Description | 
| ---- | ---- | 
| put(K key, V value) | Associate a specified value with the specified key in the map | 
| forcePut(K key, V value) | remove existing value before put with new | 
| inverse() | return the inverse view  | 
| putAll(Map<? extend K, ? extend V> map) | copy all the mappings from the specified map to this map | 
| values() | Returns a Collection view of values that contained in the map | 

Return Values & Exceptions :

* put : Throws IllegalArgumentException if the given value is already bound to a different key in this bimap. The bimap will remain unmodified in this event.
* forcePut : Returns the value which was previously associated with the key, which may be null, or null if there was no previous entry.
* putAll : Throws IllegalArgumentException if an attempt to put any entry fails. Note that some map entries may have been added to the bimap before the exception was thrown.
* values : Returns a Set, instead of the Collection specified in the Map interface, as a bimap has unique values.
* inverse : Returns the inverse view of this bimap.

Below given is the implementation for Guava BiMap interface :

### The Collections class

### Thread Safe Collection classes