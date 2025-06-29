---
title: My First Post
date: 2024-01-15
excerpt: Brett Hall and I interview David Deutsch, physicist and author of The Beginning of Infinity. Also see The Deutsch Files I, II, and III. I can only start with what understanding I want. And I know I’ve asked you this before, but I want to be pedantically exhaustive about connecting the four theories of The Fabric of Reality
author: Steve
slug: in-the-beginning
---

![The School of Athens](https://upload.wikimedia.org/wikipedia/commons/5/57/0_Chambre_de_Rapha%C3%ABl_-_%C3%89cole_d%27Ath%C3%A8nes_-_Mus%C3%A9es_du_Vatican.JPG)



The algorithm states that the greatest common divisor of two numbers can be derived by
\\[
\gcd(n_1, n_2) = \gcd(n_1 - n_2, n_2)  \\]
\\[\text{ where } n_1 > n_2
\\]


The difference its always divisible by the GCD i.e.
\\[
(n_2 - n_1) \\% \gcd(n_1, n_2) = 0
\\]

### Example: GCD for n1 = 7 and n2 = 2

\\[
\gcd(7, 2) = \gcd(7 - 2, 2) = \gcd(5, 2)  \\]

\\[
\gcd(5, 2) = \gcd(5 - 2, 2) = \gcd(3, 2)  \\]

\\[\gcd(3, 2) = \gcd(3 - 2, 2) = \gcd(1, 2)  \\]


\\[
\gcd(2, 1) = \gcd(2 - 1, 1) = \gcd(1, 1)  \\]

\\[
\gcd(1, 1) = \gcd(1 - 1, 1) = \gcd(0, 1)  \\]



**Hence GCD of n1 = 7, n2 = 2 is 1**

#### Note:

Observe that instead of subtracting the smaller number from the greater number repeatedly, the modulus operator can be used.

\\[
\gcd(7,2) =  \gcd(7\\%2, 2) = \gcd(1, 2)
\\]

The code for the algorithm can be written as follows:

```java
 class Solution {
    public int GCD(int n1, int n2) {
        while (n1!=0 && n2!=0){
            if (n1>n2)
                n1 = n1%n2;
            else
                n2 = n2%n1;
        }
        if (n2==0) return n1;
        return n2;
    }
 }
```


### Complexity Analysis:

**Time Complexity:** $O(\log(\min(N_1, N_2)))$
– where N1 and N2 are given numbers. Because in every iteration, the algorithm is dividing larger number with the smaller number resulting in time complexity.

**Space Complexity:** $O(1)$ – Using a couple of variables i.e., constant space.

*Image courtesy of [Wikipedia](https://en.wikipedia.org/wiki/Euclid)*