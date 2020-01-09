package cn.shuhan.plan.utils;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author kuangsha
 * @date 2020/1/9 15:52
 */
public class RedisUtil {

    private static RedisTemplate<String, Object> redisTemplate;

    /**
     * 写入缓存
     */
    public static void set(String key, Object value){
        redisTemplate.opsForValue().set(key,value);
    }

    /**
     * 读取缓存
     * @param key
     */
    public static Object get(String key){
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 写入缓存设置时效时间
     */
    public void set(String key, Object value, Long expireTime) {
        redisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
    }

    /**
     * 获取当前key的剩余时间
     */
    public Long getExpireTime(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 批量删除对应的value
     */
    public void remove(List<String> keys) {
        if (keys != null) {
            keys.forEach(this::remove);
        }
    }

    /**
     * 批量删除key
     */
    public void removePattern(String pattern) {
        Set<String> keys = redisTemplate.keys(pattern);
        if (keys != null) {
            keys.forEach(key -> redisTemplate.delete(keys));
        }
    }

    /**
     * 删除对应的value
     */
    public void remove(String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否存在
     */
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 批量获取值
     * @param keys
     * @return
     */
    public List<Object> multiGet(List<String> keys){
        List<Object> list = this.redisTemplate.opsForValue().multiGet(keys);
        return list;
    }

    /**
     * 哈希 添加
     */
    public void hmSet(String key, Object hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * 哈希获取数据
     */
    public Object hmGet(String key, Object hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 列表添加
     */
    public void lPush(String k, Object v) {
        redisTemplate.opsForList().rightPush(k, v);
    }

    /**
     * 列表 批量添加
     */
    public void lPushAll(String key, List<?> values) {
        redisTemplate.opsForList().leftPushAll(key, values);
    }

    /**
     * 列表获取
     */
    public List<Object> lRange(String k, long l, long l1) {
        return redisTemplate.opsForList().range(k, l, l1);
    }

    /**
     * 集合添加
     */
    public void add(String key, Object value) {
        redisTemplate.opsForSet().add(key, value);
    }

    /**
     * 集合获取
     */
    public Set<Object> setMembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 有序集合添加
     */
    public void zAdd(String key, Object value, double scoure) {
        redisTemplate.opsForZSet().add(key, value, scoure);
    }

    /**
     * 有序集合获取
     */
    public Set<Object> rangeByScore(String key, double scoure, double scoure1) {
        return redisTemplate.opsForZSet().rangeByScore(key, scoure, scoure1);
    }

    /**
     * redis计数器
     * @param key 计数源
     */
    public Long increment(String key) {
        return redisTemplate.opsForValue().increment(key, 1);
    }
}
