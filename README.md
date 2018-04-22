# CompressedLiterature
Standard encoding schemes like ASCII are convenient and relatively efficient.  However weoften need to use data compression methods to store data as efficiently as possible.  My instructor has a large collection of raw text files of famous literature, including Leo Tolstoy’s War and Peaceconsisting of over 3 million characters, and he’d like to store these works more efficiently.  David Huffman developed a very efficient method for compressing data based on character frequencyin a message.

In this assignment I will implement Huffman’s coding algorithm in a CodingTree class.  This class will carry out all stages of Huffman’s encoding algorithm:

(1)counting the frequency of characters in a text file.
(2)creating one tree for each character with a non-zero count.
    (I)the tree has one node in it and a weight equal to the character’s count.
(3)repeating the following step until there is only a single tree:
    (I)merge the two trees with minimum weight into a single tree with weight equal tothe sum of the two tree weights by creating a new
    root and adding the two treesas left and right subtrees.
(4)labelling the single tree’s left branches with a 0 and right branches with a 1 and readingthe code for the characters stored in leaf nodes from the path from root to leaf.
(5)using the code for each character to create a compressed encoding of the message.
(6)provide a method to decode the compressed message.
