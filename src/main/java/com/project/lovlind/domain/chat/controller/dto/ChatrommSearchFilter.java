package com.project.lovlind.domain.chat.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
// TODO 추후 필터를 위해 생성
public class ChatrommSearchFilter {
  private String search;
  private Integer count;

  public boolean isAllSearch() {
    return search == null && count == null;
  }
}
