# Package Challenge

Developed in Spring Boot Framework using Maven build tool.

### Approach
- **Algorithm**: Variation of Knapsack Problem Solved with Dynamic Programmic. Knapsack solution requires integer values of weights, so the input weights are normalized by multiplying them by 100. Sorted items by weights ascending provided to knapsack algorithm to return package with less weight in case of more than one packages with the same price.

- **Data Structure**: A two dimensional array (matrix) is used to store the maximum price that can be attained with weight less than or equal to w using items up to i (first i items).
A list is also used to represent the items contained in a package.

- **Exception Handling**: Custom Enum Class with Exception is created to throw APIException with specific messages.

- **Parser**: PackageParser class was implemented to parse the file input, using String split and tokenizing methods in Java.

### Testing
- **Unit Testing**: Test Driven Development process was followed throughout the implementation process, testing error handling and test cases.
