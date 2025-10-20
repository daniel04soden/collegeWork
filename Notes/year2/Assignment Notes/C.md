# General ideas

- Reverse dijkstras or floyd warshall algo (shortest pairs, longest
  pairs may work may not) -
- could be really time complex given we\'d loop over the top row,
  determine the longest path, then follow that while things are being
  generated
- consider travelling salesman simplified to just 1s and 0s -
  <https://www.geeksforgeeks.org/travelling-salesman-problem-using-dynamic-programming/>
- DFS with a backtrack - if the current distance between last node to
  this code are too big, backtrack and pick next...
- Tricky because am i calculating from the bottom up on each move, or am
  i doing it left to right via columns... \# Steps for decision

1.  Loop over height
2.  Loop over rows
3.  check closest elements (up left diag, right diag)
