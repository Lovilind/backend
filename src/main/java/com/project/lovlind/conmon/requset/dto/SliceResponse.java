package com.project.lovlind.conmon.requset.dto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SliceResponse<T> {
  private T content;
  private SliceInfo sliceInfo;
  private String code;
  private String timeStamp;

  public SliceResponse(T content, SliceInfo sliceInfo, String code) {
    this.content = content;
    this.sliceInfo = sliceInfo;
    this.code = code;
    this.timeStamp =
        LocalDateTime.now(ZoneId.of("Asia/Seoul"))
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
  }
}
