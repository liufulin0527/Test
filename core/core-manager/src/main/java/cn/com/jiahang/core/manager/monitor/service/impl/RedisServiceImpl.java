package cn.com.jiahang.core.manager.monitor.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import cn.com.jiahang.core.common.util.oConvertUtils;
import cn.com.jiahang.core.manager.monitor.domain.RedisInfo;
import cn.com.jiahang.core.manager.monitor.service.RedisService;
import cn.com.jiahang.core.manager.monitor.exception.RedisConnectException;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * Redis 监控信息获取
 *
 * @Author MrBird
 */
@Service("redisService")
@Slf4j
public class RedisServiceImpl implements RedisService {

	@Resource
	private RedisConnectionFactory redisConnectionFactory;

	/**
	 * Redis详细信息
	 */
	@Override
	public List<RedisInfo> getRedisInfo() throws RedisConnectException {
		Properties info = redisConnectionFactory.getConnection().info();
		List<RedisInfo> infoList = new ArrayList<>();
		RedisInfo redisInfo = null;
		for (Map.Entry<Object, Object> entry : info.entrySet()) {
			redisInfo = new RedisInfo();
			redisInfo.setKey(oConvertUtils.getString(entry.getKey()));
			redisInfo.setValue(oConvertUtils.getString(entry.getValue()));
			infoList.add(redisInfo);
		}
		return infoList;
	}

	@Override
	public Map<String, Object> getKeysSize() throws RedisConnectException {
		Long dbSize = redisConnectionFactory.getConnection().dbSize();
		Map<String, Object> map = new HashMap<>();
		map.put("create_time", System.currentTimeMillis());
		map.put("dbSize", dbSize);

		log.info("--getKeysSize--: " + map.toString());
		return map;
	}

	@Override
	public Map<String, Object> getMemoryInfo() throws RedisConnectException {
		Map<String, Object> map = null;
		Properties info = redisConnectionFactory.getConnection().info();
		for (Map.Entry<Object, Object> entry : info.entrySet()) {
			String key = oConvertUtils.getString(entry.getKey());
			if ("used_memory".equals(key)) {
				map = new HashMap<>();
				map.put("used_memory", entry.getValue());
				map.put("create_time", System.currentTimeMillis());
			}
		}
		log.info("--getMemoryInfo--: " + map.toString());
		return map;
	}
}