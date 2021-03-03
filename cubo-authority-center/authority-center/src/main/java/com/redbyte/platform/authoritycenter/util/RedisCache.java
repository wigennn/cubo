package com.redbyte.platform.authoritycenter.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * spring redis 工具类
 **/
@Component
public class RedisCache {

    @Autowired
    public RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //加锁时间，单位纳秒， 即：加锁时间内执行完操作，如果未完成会有并发现象
    private static final Long LOCK_TIMEOUT = 10000000000L;

    //等待超时时间 3秒
    private static final Long LOCAK_WAITING = 3000000000L;

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key   缓存的键值
     * @param value 缓存的值
     * @return 缓存的对象
     */
    public <T> ValueOperations<String, T> setCacheObject(String key, T value) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        operation.set(key, value);
        return operation;
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key      缓存的键值
     * @param value    缓存的值
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     * @return 缓存的对象
     */
    public <T> ValueOperations<String, T> setCacheObject(String key, T value, Integer timeout, TimeUnit timeUnit) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        operation.set(key, value, timeout, timeUnit);
        return operation;
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public <T> T getCacheObject(String key) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 删除单个对象
     *
     * @param key
     */
    public void deleteObject(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 删除集合对象
     *
     * @param collection
     */
    public void deleteObject(Collection collection) {
        redisTemplate.delete(collection);
    }

    /**
     * 缓存List数据
     *
     * @param key      缓存的键值
     * @param dataList 待缓存的List数据
     * @return 缓存的对象
     */
    public <T> ListOperations<String, T> setCacheList(String key, List<T> dataList) {
        ListOperations listOperation = redisTemplate.opsForList();
        if (null != dataList) {
            int size = dataList.size();
            for (int i = 0; i < size; i++) {
                listOperation.leftPush(key, dataList.get(i));
            }
        }
        return listOperation;
    }

    /**
     * 右插入缓存List数据
     *
     * @param key      缓存的键值
     * @param dataList 待缓存的List数据
     * @return 缓存的对象
     */
    public <T> ListOperations<String, T> setCacheListByRight(String key, List<T> dataList) {
        ListOperations listOperation = redisTemplate.opsForList();
        if (null != dataList) {
            int size = dataList.size();
            for (int i = 0; i < size; i++) {
                listOperation.rightPush(key, dataList.get(i));
            }
        }
        return listOperation;
    }

    /**
     * 获得缓存的list对象
     *
     * @param key 缓存的键值
     * @return 缓存键值对应的数据
     */
    public <T> List<T> getCacheList(String key) {
        List<T> dataList = new ArrayList<T>();
        ListOperations<String, T> listOperation = redisTemplate.opsForList();
        Long size = listOperation.size(key);

        for (int i = 0; i < size; i++) {
            dataList.add(listOperation.index(key, i));
        }
        return dataList;
    }

    /**
     * 缓存Set
     *
     * @param key     缓存键值
     * @param dataSet 缓存的数据
     * @return 缓存数据的对象
     */
    public <T> BoundSetOperations<String, T> setCacheSet(String key, Set<T> dataSet) {
        BoundSetOperations<String, T> setOperation = redisTemplate.boundSetOps(key);
        Iterator<T> it = dataSet.iterator();
        while (it.hasNext()) {
            setOperation.add(it.next());
        }
        return setOperation;
    }

    /**
     * 获得缓存的set
     *
     * @param key
     * @return
     */
    public <T> Set<T> getCacheSet(String key) {
        Set<T> dataSet = new HashSet<T>();
        BoundSetOperations<String, T> operation = redisTemplate.boundSetOps(key);
        dataSet = operation.members();
        return dataSet;
    }

    /**
     * 缓存Map
     *
     * @param key
     * @param dataMap
     * @return
     */
    public <T> HashOperations<String, String, T> setCacheMap(String key, Map<String, T> dataMap) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        if (null != dataMap) {
            for (Map.Entry<String, T> entry : dataMap.entrySet()) {
                hashOperations.put(key, entry.getKey(), entry.getValue());
            }
        }
        return hashOperations;
    }

    /**
     * 获得缓存的Map
     *
     * @param key
     * @return
     */
    public <T> Map<String, T> getCacheMap(String key) {
        Map<String, T> map = redisTemplate.opsForHash().entries(key);
        return map;
    }

    /**
     * 获得缓存的基本对象列表
     *
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    public Collection<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /*************************************重写锁*****************************/
    /**
     * 加锁
     * 取到锁加锁，取不到锁，则等待超时时间1秒，如果取不到，则返回
     *
     * @param lockKey
     * @return
     */
    public Long lock(String lockKey) {
        Long waitingStartTime = System.nanoTime();
        try {
            do { //循环获取锁
                //锁时间
                Long lockTimeout = System.nanoTime() + LOCK_TIMEOUT + 1;
                if (setnx(lockKey, lockTimeout.toString())) {
                    //设置超时时间，释放内存
                    stringRedisTemplate.expire(lockKey, LOCK_TIMEOUT, TimeUnit.NANOSECONDS);
                    return lockTimeout;
                } else {
                    //获取redis里面的时间
                    String result = stringRedisTemplate.opsForValue().get(lockKey);
                    Long currtLockTimeoutStr = result == null ? null : Long.parseLong(result);
                    //锁已经失效
                    if (currtLockTimeoutStr != null && currtLockTimeoutStr < System.nanoTime()) {
                        //判断是否为空，不为空时，说明已经失效，如果被其他线程设置了值，则第二个条件判断无法执行
                        //获取上一个锁到期时间，并设置现在的锁到期时间
                        Long oldLockTimeoutStr =
                                Long.valueOf(stringRedisTemplate.opsForValue().getAndSet(lockKey, lockTimeout.toString()));
                        if (oldLockTimeoutStr != null && oldLockTimeoutStr.equals(currtLockTimeoutStr)) {
                            //多线程运行时，多个线程签好都到了这里，但只有一个线程的设置值和当前值相同，它才有权利获取锁
                            //设置超时间，释放内存
                            stringRedisTemplate.expire(lockKey, LOCK_TIMEOUT, TimeUnit.NANOSECONDS);
                            //返回加锁时间
                            return lockTimeout;
                        }
                    }
                }
            } while ((System.nanoTime() - waitingStartTime) < LOCAK_WAITING);
        } catch (Throwable e) {
            return null;
        }
        return null;
    }

    public Long lock(String lockKey, long lockTime) {
        Long waitingStartTime = System.nanoTime();
        try {
            do { //循环获取锁
                //锁时间
                Long lockTimeout = System.nanoTime() + lockTime + 1;
                if (setnx(lockKey, lockTimeout.toString())) {
                    //设置超时时间，释放内存
                    stringRedisTemplate.expire(lockKey, lockTime, TimeUnit.NANOSECONDS);
                    return lockTimeout;
                } else {
                    //获取redis里面的时间
                    String result = stringRedisTemplate.opsForValue().get(lockKey);
                    Long currtLockTimeoutStr = result == null ? null : Long.parseLong(result);
                    //锁已经失效
                    if (currtLockTimeoutStr != null && currtLockTimeoutStr < System.nanoTime()) {
                        //判断是否为空，不为空时，说明已经失效，如果被其他线程设置了值，则第二个条件判断无法执行
                        //获取上一个锁到期时间，并设置现在的锁到期时间
                        Long oldLockTimeoutStr = Long.valueOf(stringRedisTemplate.opsForValue().getAndSet(lockKey, lockTimeout.toString()));
                        if (oldLockTimeoutStr != null && oldLockTimeoutStr.equals(currtLockTimeoutStr)) {
                            //多线程运行时，多个线程签好都到了这里，但只有一个线程的设置值和当前值相同，它才有权利获取锁
                            //设置超时间，释放内存
                            stringRedisTemplate.expire(lockKey, lockTime, TimeUnit.NANOSECONDS);
                            //返回加锁时间
                            return lockTimeout;
                        }
                    }
                }
            } while ((System.nanoTime() - waitingStartTime) < LOCAK_WAITING);
        } catch (Throwable e) {
            return null;
        }
        return null;
    }

    /**
     * 解锁
     *
     * @param lockKey
     * @param lockValue
     */
    public void unlock(String lockKey, Long lockValue) {
        if (lockValue == null) {
            return;
        }
        //获取redis中设置的时间
        String result = stringRedisTemplate.opsForValue().get(lockKey);
        Long currtLockTimeoutStr = result == null ? null : Long.valueOf(result);
        //如果是加锁者，则删除锁， 如果不是，则等待自动过期，重新竞争加锁
        if (currtLockTimeoutStr != null && currtLockTimeoutStr.longValue() == lockValue.longValue()) {
            stringRedisTemplate.delete(lockKey);
        }
    }

    /**
     * @param key
     * @param value
     * @return
     */
    public boolean setnx(final String key, final String value) {
        BoundValueOperations<String, String> boundValueOperations = stringRedisTemplate.boundValueOps(key);
        return boundValueOperations.setIfAbsent(value);
    }


    // ================================Map=================================

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * HashGet
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public Object hget(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * HashSet
     *
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    public boolean hmset(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * HashSet 并设置时间
     *
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public boolean hmset(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    public boolean hset(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public boolean hset(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     * @return
     */
    public double hincr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * hash递减
     *
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于0)
     * @return
     */
    public double hdecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    /**
     * 批量删除key
     *
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }

    /**
     * 判断是否含有某个key
     *
     * @param key
     * @return
     */
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }
}
