package com.phoenix.producer;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/file-response")
public class FileResponseController {

  private final FileResponseService fileResponseService;


  public FileResponseController(FileResponseService fileResponseService) {
    this.fileResponseService = fileResponseService;
  }

  @PostMapping("/send")
  public String sendData(){
    fileResponseService.sendMessage();;

    return "Message sent to the RabbitMQ Successfully";
  }


}
