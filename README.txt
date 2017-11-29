1. 
The runtime of the offline step is: O(N*M) 
N being the number of words in the dictionary and M being the maximum length of any word. This is because I have looped through each word in the dictionary, and for each of those words, I have looped through their characters.


The runtime of the online step is: O(L + N*L)
N being the number of words in the dictionary, and L being the length of the word entered. (Assuming hash maps facilitate almost constant lookup time, i.e. O(1), if the hash function is aptly random).



2. 
The memory consumed is: O(N*M)
N being the number of words in the dictionary and M being the maximum length of any word. This is because each key in the main HashMap contains another HashMap as its value (containing the M letters).


3. If I did not have enough memory to process and store all dictionary words, I'd have to use parallelism across machines to distribute the words (with an input buffer), according to a consistent and well-distributing hash function. Then, for each entered input, it would be sent to all the machines for processing.
The final output would be an aggregation of the returned outputs from each machine (since the words in the dictionary are unique, we don't have to worry about checking for overlapping words). 



Outline:
First, I looped through all words in the dictionary, adding them to a List and HashMap. The HashMap mapped each word to another
HashMap, containing the frequency of each character in the word. (HashMap because it facilitates almost constant lookup time).
Then, on entering each word, I loop through the dictionary words, eliminating them as soon as they disqualify from the anagram search.
This is achieved by looping through the unique characters of the entered word, and checking for its frequency with the frequency
of the word in the dictionary.
Once I've added all words to the output list, I call Java's built in Collections.sort() on the list, and print it out.


TESTING
I tested my code with an online dictionary. I have included the test results (as a screenshot) and the dictionary as text.txt

I also tested with the input given in the question specs. It is included as ‘Test - Given Input’ and ‘given_test.txt’

I also had small unit tests as a part of the debugging process, but had to remove them as they altered the function signatures temporarily. 


NOTES for possible mismatches in expected output:

NOTE 1: During testing my code, I ran into an error where the apostrophe in the words in the dictionary were considered different
        from the apostrohpe I entered as inputs. On debugging using IntelliJ, I found that the HashMaps correctly store the frequency,
        but the difference in nature of the apostrophes resulted in the words not being added to the output.
        I don't know if this issue will arise with the autograder's dictionary file as well.

NOTE 2: Calling the sort() method assumes that Capital letters are to be displayed before small letters (according to ascii code).
      I verified this with the proctor. 
      However, if we'd want to display strictly according to the order in the English alphabet, regardless of the letter being capital or not,
      I'd create a comparator class that would implement Java's Comparator interface.
      While overriding the compareTo() method, I'd take the 2 input strings, create a loop from a-z, and check which string has the letter
      (or its capitalized form) first.
      I'd send this comparator class as another parameter to the sort() method.
      
NOTE 3: The print function assumes that there is a space after the last word displayed. If we didn't want that, I'd loop through the list
      with variable i as the index. When i == outputs.size() - 2, it would indicate the last word, and I would not print an extra space for that.
