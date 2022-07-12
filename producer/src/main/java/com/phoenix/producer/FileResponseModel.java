package com.phoenix.producer;

public class FileResponseModel {

  private String fileId;

  private String name;

  private String type;

  private String extension;

  private long size;

  public String getFileId() {
    return fileId;
  }

  public void setFileId(String fileId) {
    this.fileId = fileId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getExtension() {
    return extension;
  }

  public void setExtension(String extension) {
    this.extension = extension;
  }

  public long getSize() {
    return size;
  }

  public void setSize(long size) {
    this.size = size;
  }

  @Override
  public String toString() {
    return "FileResponseModel{" +
        "fileId='" + fileId + '\'' +
        ", name='" + name + '\'' +
        ", type='" + type + '\'' +
        ", extension='" + extension + '\'' +
        ", size=" + size +
        '}';
  }
}
