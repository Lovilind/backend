package com.project.lovlind.conmon.utils.response;

import java.net.URI;
import org.springframework.web.util.UriComponentsBuilder;

public abstract class UrlCreator {

  private UrlCreator() {}

  public static URI createUri(String defaultUrl1, long resourceId) {
    return UriComponentsBuilder.newInstance()
        .path(defaultUrl1 + "/{resource-id}")
        .buildAndExpand(resourceId)
        .toUri();
  }
}
