package cn.com.jiahang.core.manager.system.controller;

import cn.com.jiahang.core.manager.system.entity.SysUser;
import cn.com.jiahang.core.manager.system.service.ISysUserService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import cn.com.jiahang.core.common.api.vo.Result;
import cn.com.jiahang.core.common.constant.CommonConstant;
import cn.com.jiahang.core.common.system.api.ISysBaseAPI;
import cn.com.jiahang.core.common.system.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author scott
 * @since 2018-12-17
 */
@Controller
@RequestMapping("/thirdLogin")
@Slf4j
public class ThirdLoginController {
	@Autowired
	private ISysUserService sysUserService;

	@Autowired
	private ISysBaseAPI sysBaseAPI;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getLoginUser/{token}", method = RequestMethod.GET)
	@ResponseBody
	public Result<JSONObject> getLoginUser(@PathVariable("token") String token) throws Exception {
		Result<JSONObject> result = new Result<JSONObject>();
		String username = JwtUtil.getUsername(token);

		//1. 校验用户是否有效
		SysUser sysUser = sysUserService.getUserByName(username);
		result = sysUserService.checkUserIsEffective(sysUser);
		if(!result.isSuccess()) {
			return result;
		}
		JSONObject obj = new JSONObject();
		//用户登录信息
		obj.put("userInfo", sysUser);
		//token 信息
		obj.put("token", token);
		result.setResult(obj);
		result.setSuccess(true);
		result.setCode(200);
		sysBaseAPI.addLog("用户名: " + username + ",登录成功[第三方用户]！", CommonConstant.LOG_TYPE_1, null);
		return result;
	}

}
