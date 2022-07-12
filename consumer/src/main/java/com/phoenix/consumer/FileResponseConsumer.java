package com.phoenix.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class FileResponseConsumer {
  private static final Logger LOG = LoggerFactory.getLogger(FileResponseConsumer.class);

  private static final long MAX_FILE_SIZE = 10000;




  @RabbitListener(queues = "file_data.queue", concurrency = "3-7")
  public void listenQ1(FileResponseModel fileResponse)
      throws InterruptedException, WrongFileException {
    LOG.info("Q1-FileResponse is {}", fileResponse);
    if ( "csv".equals(fileResponse.getExtension())){
      throw new WrongFileException(fileResponse.toString());
    }
  }

}
