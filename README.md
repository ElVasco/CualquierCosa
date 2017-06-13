# Trains

Java solution for minimum path problem with weighted directed edges.

## The problem

We have a weighted directed graph representing towns in Kiwiland. Each edge connects two towns and if town A is connected to B with weight c doesn't necessary means that edge B-C weights c.

### General tasks

1. Compute distance along a certain route.
1. Number of different routes between two towns.
1. Shortest route between towns.

## Input

A directed graph G = (V, E, w) where:

1. V is the set of nodes. A node in V represents a town.
1. E is the set of edges. An edge represents the route between two towns.
1. w is a function w: E -> N+ that assigns the weight wij of edge (ei, ej).

### Constraints

1. A given route will never appear more than once.
1. For a given route, the starting and ending town will not be the same town.

### Assumptions

1. It seems for the input problem we only have 5 nodes A, B, C, D and E.
1. As a consequence of 1. an adjacency matrix with weights instead of binary numbers is sufficient.
1. I assume the 10 tasks as the only tasks we need to solve. For every task, we create a unittest.
1. I assume weights can be any positive number as I create W from (2, length(line)) for every line.  
