package com.example.firstkotlinapp

fun main() {
//    arrayDemo()
//    listDemo()
//    setDemo()
//    mapDemo()
//    arrayListDemo()
}

// Array
//  Collection of data elements through pre-allocation of memory hence no extension at runtime
fun arrayDemo() {
    // array declaration
//    val numbers: IntArray = intArrayOf(1,2,3,4,5)
//    val numbers = intArrayOf(1,2,3,4,5)   // using type inference for constructor
    val numbers = arrayOf(1, 2, 3, 4, 5)    // using generic constructor that decides through type inference

//    println(numbers)  // printing reference address of the array
//    println(numbers.contentToString())    // printing array elements though utility method

    // printing array elements individually
//    for (n in numbers) {
//        println(n)
//    }

    val numbersD: DoubleArray = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0);
    println("initial values ${numbersD.contentToString()}")

    numbersD[0] = 5.0
    numbersD[1] = 4.0
    numbersD[2] = 3.0
    numbersD[3] = 2.0
    numbersD[4] = 1.0

    println("final values ${numbersD.contentToString()}")

    val days = arrayOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")

    val fruits = arrayOf(Fruit("Apple", 2.5), Fruit("Grape", 4.5))
    println(fruits.contentToString())

    for (fruit in fruits) {
        println("${fruit.name}")
    }

    for (index in fruits.indices) {
        println("${fruits[index].name} is in index ${index}")
    }

    // array can hold heterogeneous data type as their elements
    val mix = arrayOf("Sun", "Mon", 1, 2, 3, Fruit("Mango", 6.0))
}

data class Fruit(val name: String, val price: Double)

// List
//  Collection of data elements that allow extension at runtime
fun listDemo() {
    val months = listOf(
        "January", "February", "March", "April",
        "May", "June", "July", "August",
        "September", "October", "November", "December"
    )

    // list can also hold heterogeneous data type as their elements
    val anyTypes = listOf(1, 2, 3, true, false, "String")

    println(anyTypes.size)

    // accessing random elements through subscript operator
    println(months[1])

    for (month in months) {
        println(month)
    }

    // mutable val declared list
    val nums = listOf(1, 2, 3)
    val additionalNums = nums.toMutableList()
    val newNums = arrayOf(4, 5, 6)
    additionalNums.addAll(newNums)

    println(additionalNums)

    val days = mutableListOf<String>("Monday", "Tuesday", "Wednesday")
    days.add("Thursday")
    println(days)
    days[0] = "Sunday"
    println(days)
    days.removeAt(3)
    println(days)
    days.add("Thursday")
    days.add("Friday")
    days.add("Saturday")
    days.add("Sunday")
    days.add("Funday")
    println(days)
    val removalList = mutableListOf<String>("Sunday", "Funday")
    days.removeAll(removalList)
    println(days)

}

// Set
//  Collection of elements that eliminates duplicate elements
//  Unordered Set is unordered i.e. the elements are not sorted
//  Ordered Set is ordered, where elements are sorted in defined order
fun setDemo() {
    // immutable set
    val fruits = setOf("Apple", "Mango", "Banana", "Apple")
    println("size: $fruits.size")
    println("set: $fruits")
    println("sorted set: ${fruits.toSortedSet()}")

    // mutable set
    val newFruits = fruits.toMutableSet()
    newFruits.add("Water Melon")
    newFruits.add("Peach")
    println(newFruits)
}

// Map
//  Collection of elements that store data in the form of key-value pairs where keys are unique
//  Unordered Map is unordered i.e. the elements are not sorted in the order of keys
//  Ordered Map is ordered, where elements are sorted in defined order of keys
fun mapDemo() {
    // immutable map
    val daysOfWeek = mapOf(
        1 to "Monday",
        2 to "Tuesday",
        3 to "Wednesday",
        4 to "Thursday",
        5 to "Friday",
    )

    println(daysOfWeek[2])

    for (key in daysOfWeek.keys) {
        println("${key} -> ${daysOfWeek[key]}")
    }

    val fruitsMap = mapOf(
        "Fav" to Fruit("Mango", 3.0),
        "Ok" to Fruit("Apple", 2.0),
    )
    println(fruitsMap)

    // mutable map
    val newDaysOfWeek = daysOfWeek.toMutableMap()
    newDaysOfWeek[7] = "Sunday"
    newDaysOfWeek[6] = "Saturday"
    println(newDaysOfWeek)
    println(newDaysOfWeek.toSortedMap() )
}

// ArrayList
//  ArrayLists are used to create dynamic arrays, i.e. whose size can be increased or decreased at runtime based on our requirement.
//  The ArrayList class provides both read and write functionality.
//  The ArrayList follows the sequence of insertion order.
//  An ArrayList is non-synchronized.
//  It may also contain duplicates.
fun arrayListDemo() {
    // Constructors of ArrayList

    // cons used to create empty ArrayList
    val al1 = ArrayList<Int>()
    // Even-though we declared it using val, we can still add without any restriction due to its mutable nature by default
    // We can change the content of the ArrayList (1,2,3,...) but not the ArrayList assigned to the variable (al1)
    al1.add(1)
    al1.add(2)
    al1.add(3)

    // cons used to create a specific sized ArrayList
    val al2 = ArrayList<Int>(5)
    al2.add(4)
    al2.add(5)
    al2.add(6)

    val l1 = listOf<Int>(1, 2, 3)
    // cons to create an ArrayList filled with elements of a collection
    val al3 = ArrayList<Int>(l1)
    println(al3)

    // For most of the cases ArrayList acts similar to List

    // Empty ArrayList
    val al4 = ArrayList<String>()
    al4.add("one")
    al4.add("two")
    al4.add("three")

    println("---contents of ArrayList---")
    for (e in al4) {
        println(e)
    }

    // ArrayList using Collection
    val al5 = ArrayList<String>(5)
    val l2 = mutableListOf<String>()

    l2.add("ONE")
    l2.add("TWO")
    l2.add("THREE")

    al5.addAll(l2)

    println("---Collection based initialization---")
    println(al5)

    // Fill elements in ArrayList using iterator
    println("---iterators---")
    val itr = al5.iterator()
    while (itr.hasNext()) {
        println(itr.next())
    }
    println("size of al5: ${al5.size}")
}