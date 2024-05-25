package com.project.lovlind.conmon.redis.repository;

import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RedisRepository {

  private final StringRedisTemplate redisTemplate;

  public void save(String key, String value, int minutes) {
    redisTemplate.opsForValue().set(key, value, minutes, TimeUnit.MINUTES);
  }

  public String findByKey(String key) {
    return redisTemplate.opsForValue().get(key);
  }

  public void delete(String key) {
    redisTemplate.delete(key);
  }
}
