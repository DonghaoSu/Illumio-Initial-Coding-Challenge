# Illumio-Initial-Coding-Challenge
My design and implementation of Illumio initial coding challenge for software engineer position.

# Test
In the file fw.csv, I wrote four rules. The first one is with specific port and IP address. The second one is with port range
and specific IP address. The third one is with specific port and IP address range. The fourth one is with port range and IP 
address range. For each rule, I tested corner cases and general cases. Finally, I add some random false tests. All tests are in
file FirewallTest.java.

# Design Choices
Nowadays, space is much cheaper than time in most cases. For this problem, a hashmap is a great data structure to use to have a time complexity of O(1). I just have each of the rules as key, and if they exist or not as value. I also create NetworkRule object which is in NetworkRule.java, and I just use the hashcode of each rule as the key that stores in the hashmap. This design takes a lot of space when we have port range or IP address range, but it is fine to sacrifice space of the time of O(1).

# Optimization
I was using hashmap to store the key value pair of network rules' hashcode and if it exist or not. However, it is not nessessary to store the value. If I have more time, I can definitely change hashmap to hashset or other data structure to save some space and have the same time complexity. Also, if I can talk to the manager, I want to make sure that having a tradeoff to minimize the time is what we really want. 

# Team
Platform team is more interesting to me.
