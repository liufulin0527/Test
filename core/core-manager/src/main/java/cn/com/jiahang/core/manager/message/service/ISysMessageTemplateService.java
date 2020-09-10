package cn.com.jiahang.core.manager.message.service;

import java.util.List;

import cn.com.jiahang.core.common.system.base.service.BaseService;
import cn.com.jiahang.core.manager.message.entity.SysMessageTemplate;

/**
 * @Description: 消息模板
 * @Author: jeecg-boot
 * @Date:  2019-04-09
 * @Version: V1.0
 */
public interface ISysMessageTemplateService extends BaseService<SysMessageTemplate> {
    List<SysMessageTemplate> selectByCode(String code);
}
