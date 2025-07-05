---
title: Euclidean Algorithm
date: March 15 2025
excerpt: A fundamental method for computing the greatest common divisor (GCD) of two integers. Starting from the principle of repeated subtraction and moving toward an optimized version using the modulus operator, the post provides both mathematical insight and practical implementation in Java. It also includes a complexity analysis that highlights the algorithm’s efficiency in terms of time and space.
author: Steve Boby George
slug: in-the-beginning
---

![The School of Athens](https://upload.wikimedia.org/wikipedia/commons/5/57/0_Chambre_de_Rapha%C3%ABl_-_%C3%89cole_d%27Ath%C3%A8nes_-_Mus%C3%A9es_du_Vatican.JPG)


The algorithm states that the greatest common divisor (GCD) of two numbers can be derived using the following identity:

```math
\gcd(n_1, n_2) = \gcd(n_1 - n_2, n_2) \\

\text{where } n_1 > n_2
```

The difference is always divisible by the GCD, i.e.,

```math
(n_1 - n_2) \bmod \gcd(n_1, n_2) = 0
```

### Example: GCD for n1 = 7 and n2 = 2

```math
\gcd(7, 2) = \gcd(7 - 2, 2) = \gcd(5, 2)
```

```math
\gcd(5, 2) = \gcd(5 - 2, 2) = \gcd(3, 2)
```

```math
\gcd(3, 2) = \gcd(3 - 2, 2) = \gcd(1, 2)
```

```math
\gcd(2, 1) = \gcd(2 - 1, 1) = \gcd(1, 1)
```

```math
\gcd(1, 1) = \gcd(1 - 1, 1) = \gcd(0, 1)
```

**Hence, the GCD of n1 = 7 and n2 = 2 is 1.**


### Optimization with Modulus

Instead of repeated subtraction, we can use the modulus operator:

```math
\gcd(7 \bmod 2, 2) = \gcd(1, 2)
```


The code for the algorithm can be written as follows:

```java
class Solution {
    public int GCD(int n1, int n2) {
        while (n1 != 0 && n2 != 0) {
            if (n1 > n2)
                n1 = n1 % n2;
            else
                n2 = n2 % n1;
        }
        return (n2 == 0) ? n1 : n2;
    }
}
```


### Complexity Analysis

* **Time Complexity:**

  ```math
  O(\log(\min(n_1, n_2)))
  ```

  where N1 and N2 are given numbers. Because in every iteration, the algorithm is dividing larger number with the smaller number resulting in time complexity.

* **Space Complexity:**

  ```math
  O(1)
  ```

  Since only a couple of variables i.e., constant space.


