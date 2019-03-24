package com.mobiquityinc.exception;

public enum APIExceptionCodes {
  EMPTY_FILEPATH(0, "Filepath parameter is empty"),
  FILE_NOT_EXISTS(1, "File not found on filepath provided"),
  PACKAGE_WEIGHT_MORE_THAN_100(2, "Package weight should be up to 100"),
  ITEMS_MORE_THAN_15(3, "Items to pack should be up to 15"),
  ITEM_WEIGHT_MORE_THAN_100(4, "Item weight should be up to 100"),
  ITEM_COST_MORE_THAN_100(5, "Item cost should be up to 100"),
  EMPTY_PARAMETER(6, "Empty parameter provided");

  private final int id;
  private final String msg;

  APIExceptionCodes(int id, String msg) {
    this.id = id;
    this.msg = msg;
  }

  public int getId() {
    return this.id;
  }

  public String getMsg() {
    return this.msg;
  }

}
