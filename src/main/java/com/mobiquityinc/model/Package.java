package com.mobiquityinc.model;

import com.sun.deploy.util.StringUtils;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Package {

  private int weightCapacity;
  private List<Item> items;


  public String getSelectedItems() {
    if (items.isEmpty()) {
      return "-";
    } else
      return items.stream().map(Item -> String.valueOf(Item.getIndex()))
          .collect(Collectors.joining(","));
  }
}
