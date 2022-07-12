package com.phoenix.consumer;

public class WrongFileException extends Exception{

  public WrongFileException(String message) {
    super("Wrong file extension : " + message);
  }
}
