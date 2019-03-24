package com.mobiquityinc.utils;


import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.exception.APIExceptionCodes;
import com.mobiquityinc.model.Item;
import com.mobiquityinc.model.Package;
import com.mobiquityinc.packer.Constants;
import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PackageParser {

  public static Package parsePackage(String inputLine) throws APIException {
    Package pack = new Package();

    String[] lineArray = inputLine.split(ParserConstants.PACKAGE_WEIGHT_SEPARATOR, 2);
    String capacityInfo = lineArray[0];
    String possibleItemsInfo = lineArray[1];

    int weightCapacity = getPackageWeightCapacity(capacityInfo);
    if (weightCapacity > Constants.PACKAGE_MAX_WEIGHT) {
      throw new APIException(APIExceptionCodes.PACKAGE_WEIGHT_MORE_THAN_100.getMsg());
    }
    //package weight is multiplied by 100 because items weights are double values of double precision
    // and we want to normalize these values by multiplying them by 100 and casting them to integers
    pack.setWeightCapacity(weightCapacity * 100);
    pack.setItems(getPossibleItems(possibleItemsInfo));

    return pack;
  }

  private static int getPackageWeightCapacity(String capacityInfo) {
    if (capacityInfo == null || capacityInfo.isEmpty()){
      throw new APIException(APIExceptionCodes.EMPTY_PARAMETER.getMsg());
    }
    return Integer.parseInt(capacityInfo.trim());
  }

  private static List<Item> getPossibleItems(String possibleItemsInfo){

    if (possibleItemsInfo == null || possibleItemsInfo.isEmpty()){
      throw new APIException(APIExceptionCodes.EMPTY_PARAMETER.getMsg());
    }
    //split items info in substrings
    String[] stringItems = possibleItemsInfo.trim().split(ParserConstants.ITEMS_SEPARATOR);

    List<Item> possibleItems = new ArrayList<>();
    for (String stringItem : stringItems) {
      String[] itemDetails = stringItem.split(ParserConstants.ITEM_ATTRIBUTE_SEPARATOR);
      int id = Integer.parseInt(itemDetails[0].substring(1));

      int itemWeight = (int) Double.parseDouble(itemDetails[1]);
      if (itemWeight > Constants.ITEM_MAX_WEIGHT) {
        throw new APIException(APIExceptionCodes.ITEM_WEIGHT_MORE_THAN_100.getMsg());
      }

      int itemCost = Integer.parseInt(itemDetails[2].substring(1, itemDetails[2].length() - 1));
      if (itemCost > Constants.ITEM_MAX_COST) {
        throw new APIException(APIExceptionCodes.ITEM_COST_MORE_THAN_100.getMsg());
      }
      Item item = new Item(id, itemWeight * 100, itemCost);
      possibleItems.add(item);

      if (possibleItems.size() > Constants.MAX_POSSIBLE_ITEMS) {
        throw new APIException(APIExceptionCodes.ITEMS_MORE_THAN_15.getMsg());
      }
    }

    return possibleItems;
  }

}
