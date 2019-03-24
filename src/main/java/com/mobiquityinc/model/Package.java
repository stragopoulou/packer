package com.mobiquityinc.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Packet {

  private int weight;
  private List<Item> items;
}
