import java.util.LinkedList

object Recursion {
    fun getAllLists(elements: LinkedList<Ride>, lengthOfList: Int): LinkedList<LinkedList<Ride>> {
        //initialize our returned list with the number of elements calculated above
        val allLists = LinkedList<LinkedList<Ride>>()

        //lists of length 1 are just the original elements
        if (lengthOfList == 1) {
            for (element in elements) {
                val list = LinkedList<Ride>()
                list.add(element)
                allLists.add(list)
            }
        } else {
            //the recursion--get all lists of length 3, length 2, all the way up to 1
            val allSublists = getAllLists(elements, lengthOfList - 1)

            //append the sublists to each element
            for (i in elements.indices) {
                for (j in allSublists.indices) {
                    if (allSublists[j].contains(elements[i])) {
                        // Already occurs in the list
                        continue
                    }
                    //add the newly appended combination to the list
                    val list = LinkedList<Ride>()
                    list.add(elements[i])
                    list.addAll(allSublists[j])
                    allLists.add(list)
                }
            }
        }
        return allLists
    }
}
