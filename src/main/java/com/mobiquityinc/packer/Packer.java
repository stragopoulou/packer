package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.exception.APIExceptionCodes;
import com.mobiquityinc.model.Item;
import com.mobiquityinc.model.Package;
import com.mobiquityinc.utils.PackageParser;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Packer {

  public static String pack(String filePath) throws APIException {

    StringBuilder result = new StringBuilder();

    if (filePath == null || filePath.isEmpty()) {
      throw new APIException(APIExceptionCodes.EMPTY_FILEPATH.getMsg());
    }

    List<String> lineItemsToPack = readFileLines(filePath);

    Package finalPackage;
    for (String line : lineItemsToPack) {
      finalPackage = solveKnapsack(PackageParser.parsePackage(line));
      result.append(finalPackage.getSelectedItems()).append("\n");
    }

    return result.toString().trim();
  }

  private static List<String> readFileLines(String filepath) {

    List<String> lines = null;
    try {
      lines = Files.readAllLines(Paths.get(filepath),
          StandardCharsets.UTF_8);
    } catch (FileNotFoundException e) {
      throw new APIException(APIExceptionCodes.FILE_NOT_EXISTS.getMsg());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return lines;

  }

  public static Package solveKnapsack(Package pack) {
    int capacity = pack.getWeightCapacity();
    //sort items with weight asc to use package with less weight in case of two packages with same price
    pack.getItems().sort(Comparator.comparingInt(Item::getWeight));
    List<Item> items = pack.getItems();
    int numOfItems = pack.getItems().size();

    //use of 2d array to store the cost for all capacities including the zero capacity
    int[][] matrix = new int[numOfItems + 1][capacity + 1];

    // init the first line of the matrix to 0, to describe the zero capacity/ zero cost case
    for (int i = 0; i <= capacity; i++) {
      matrix[0][i] = 0;
    }

    //iteration on all possible items
    for (int i = 1; i <= numOfItems; i++) {
      // we iterate on each capacity
      for (int j = 0; j <= capacity; j++) {
        if (items.get(i - 1).getWeight() > j) {
          matrix[i][j] = matrix[i - 1][j];
        } else {
          // we maximize value at this rank in the matrix
          matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i - 1][j - items.get(i - 1).getWeight()]
              + items.get(i - 1).getCost());
        }
      }
    }

    int result = matrix[numOfItems][capacity];
    List<Item> itemsSolution = new ArrayList<>();

    for (int i = numOfItems; i > 0 && result > 0; i--) {
      if (result != matrix[i - 1][capacity]) {
        itemsSolution.add(items.get(i - 1));
        // we remove items value and weight
        result -= items.get(i - 1).getCost();
        capacity -= items.get(i - 1).getWeight();
      }
    }

    itemsSolution.sort(Comparator.comparingInt(Item::getIndex));
    return new Package(matrix[numOfItems][capacity], itemsSolution);
  }


}
