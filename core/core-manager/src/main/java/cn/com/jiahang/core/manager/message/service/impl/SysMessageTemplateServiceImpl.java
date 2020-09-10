package cn.com.jiahang.core.manager.message.service.impl;

import cn.com.jiahang.core.common.system.base.service.impl.BaseServiceImpl;
import cn.com.jiahang.core.manager.message.entity.SysMessageTemplate;
import cn.com.jiahang.core.manager.message.service.ISysMessageTemplateService;
import cn.com.jiahang.core.manager.message.mapper.SysMessageTemplateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Description: 消息模板
 * @Author: jeecg-boot
 * @Date:  2019-04-09
 * @Version: V1.0
 */
@Service
public class SysMessageTemplateServiceImpl extends BaseServiceImpl<SysMessageTemplateMapper, SysMessageTemplate> implements ISysMessageTemplateService {

    @Autowired
    private SysMessageTemplateMapper sysMessageTemplateMapper;


    @Override
    public List<SysMessageTemplate> selectByCode(String code) {
        return sysMessageTemplateMapper.selectByCode(code);
    }
}
