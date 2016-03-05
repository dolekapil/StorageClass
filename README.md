# StorageClass
Implementation of Storage Class without usage of existing java classes.

Given this interface:
 1      public interface Competition<E> {
 2              // Appends the specified element to this storage.
 3              // Returns true if the element could be added to this storage
 4              boolean add(E e);
 5              // Returns true if this storage contains the specified element.
 6              boolean contains(E o);
 7              // Removes the first occurrence of the specified element in this storage.
 8              // If the storage does not contain the element, it is unchanged.
 9              // Returns true if the element could be removed from this storage
10              boolean remove(E o);
11              // Returns the component at the specified index.
12              E elementAt(int index);
13              // Sorts the storage
14              // Returns the sorted storage
15              Competition<E>  sort();
16              // Returns the number of components in this storage.
17              int size();
18      }

This is implementation of a storage class. The compiler can not produce any warning during compilation. You can not use any compiler flags. You can not use any existent java classes for the storage functionally.
The following assumption is true:
•
No more than 100000 different string objects will be stored in the storage.
•
No more than 1000000 string objects will be stored in the storage.
