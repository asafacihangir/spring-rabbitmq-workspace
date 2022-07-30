package com.phoenix.consumer.model;


import java.util.ArrayList;
import java.util.List;

public class SmsContent {

  private String content;

  private List<String> recipients = new ArrayList<>();

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public List<String> getRecipients() {
    return recipients;
  }

  public void setRecipients(List<String> recipients) {
    this.recipients = recipients;
  }

  @Override
  public String toString() {
    return "SmsContent{" +
        "content='" + content + '\'' +
        ", recipients=" + recipients +
        '}';
  }
}
