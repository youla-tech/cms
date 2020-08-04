package com.thinkcms.core.utils;

import java.util.HashMap;
import java.util.Map;

public class ApiResult extends HashMap<String, Object>{
	
	public static final int defaultOkCode=0;
	
	public static final int defaultErrorCode=-1;

    public static final String code="code";

    public static final String message="msg";

    public static final String result="res";


	public  String getMessage() {
	    Object msg=this.get(message);
        if(Checker.BeNotNull(msg)){
            return (String)msg;
        }else{
            return (String)get(getCode());
        }
    }

    public <T> T getRes(Class<?> T) {
        return (T)this.get(result);
    }

    public int getCode() {
        return (Integer) this.get(code);
    }

    public  Object getObj() {
        return this.get(result);
    }


    public static ApiResult result(Object obj, int mycode) {
		ApiResult apiResult=new ApiResult();
		Map<String, Object> res=new HashMap<>();
		res.put(result, obj);
		res.put(code, mycode);
		res.put(message, get(mycode));
		apiResult.putAll(res);
		return apiResult;
	}
	
    public static ApiResult result(Object obj) {
    	ApiResult apiResult=new ApiResult();
    	Map<String, Object> res=new HashMap<>();
		res.put(result, obj);
		res.put(code, defaultOkCode);
		res.put(message, get(defaultOkCode));
		apiResult.putAll(res);
		return apiResult;
	}
    
    public static ApiResult result(int mycode) {
    	ApiResult apiResult=new ApiResult();
    	Map<String, Object> res=new HashMap<>();
		res.put(code, mycode);
		res.put(message, get(mycode));
		apiResult.putAll(res);
		return apiResult;
	}
    
    public static ApiResult result(int mycode, String msg) {
    	ApiResult apiResult=new ApiResult();
    	Map<String, Object> res=new HashMap<>();
		res.put(code, mycode);
		res.put(message,msg);
		apiResult.putAll(res);
		return apiResult;
	}
    
    public static ApiResult result() {
    	ApiResult apiResult=new ApiResult();
    	Map<String, Object> res=new HashMap<>();
		res.put(code, defaultOkCode);
		res.put(message, get(defaultOkCode));
		apiResult.putAll(res);
		return apiResult;
	}
    
    public boolean ckSuccess() {
    	Integer res= (Integer) this.get(code);
    	return res==0;
    }
    


	@SuppressWarnings("serial")
    private static final Map<Integer, String> errCodeToErrMsg = new HashMap<Integer, String>(){{
        // ---业务异常----
        put(5000,"当前登录用户不能删除");
        put(5001, "内置角色不能删除!");
        put(5002, "操作失败,请确保当前角色下不存在人员后删除该角色!");
        put(5003, "內置組織不能刪除!");
        put(5004, "组织下存在用户不能刪除!");
        put(5005, "原始密码不正确!");
        put(5006, "用户邮箱已存在!");
        put(5007, "用户账户已存在!");

        put(401, "授权失败，请确认账号密码是否正确");
        put(421, "用户已被锁定!");
        put(403, "权限不足");
        put(404, "接口不存在");
        put(4030, "JWT IS NULL");
        put(421, "账号已被锁定,请联系管理员");
        put(4001, "主键不能为空!");

        put(6000, "3dsession 获取失败!");
        put(6001, "用户信息获取失败!");
        put(7000, "登录超时!");
        put(7001, "密码输入次数过多,5分钟后重试!");
        put(7002, "用户权限不足!");
        /*----------business----*/
        put(20000, "文件不存在!");
        put(20001, "模板文件写入失败!");
        put(20002, "删除失败!");
        put(20003, "栏目模型不存在!");
        put(20004, "文件上传失败!");
        put(20005, "文件删除失败!");
        put(20006, "静态化页面创建失败!");
        put(20007, "栏目编码已存在!");
        put(20008, "任务启动失败!");
        put(20009, "任务暂停失败!");
        put(20010, "任务删除失败!");
        put(20011, "任务修改失败!");
        put(20012, "片段编码已存在!");
        put(20013, "栏目下不存在已发布的文章,无法静态化!");
        put(20014, "栏目下存在文章,不能删除!");
        put(20015, "打包失败!");
        put(20016, "只能上传zip!");
        put(20017, "上传失败!");
        put(20018, "参数不合法!");
        put(20019, "推荐文章最多50条记录!");
        put(20020, "solr 同步失败!");
        put(20021, "模板不能为空!");
        put(20022, "演示账户不允许操作!");
        put(20023, "该分类下存在标签不允许删除!");
        put(20024, "标签分类名称不合法或已存在!");
        put(20025, "标签已存在!");
        put(20026, "文件上传失败!");
        put(20027, "文件大小不允许超过100M!");
        put(20028, "license证书不合法");
        put(20029, "license证书日期不合法");
        put(20030, "license证书授权已过期");
        put(20031, "license证书不存在");
        put(20032, "license授权域名不合法");
        put(20033, "license文件不正确");
        put(20034, "license证书授权未生效");
        put(-1, "系统繁忙");
        put(0, "操作成功");
        put(10001, "不正确的签名");
        put(10002, "秘钥不能为空!");
        put(10003, "不合法的code凭证");
        put(40003, "不合法的OpenID");
        put(40004, "不合法的媒体文件类型");
        put(40005, "不合法的文件类型");
        put(40006, "不合法的文件大小");
        put(40007, "不合法的媒体文件id");
        put(40008, "不合法的消息类型");
        put(40009, "不合法的图片文件大小");
        put(40010, "不合法的语音文件大小");
        put(40011, "不合法的视频文件大小");
        put(40012, "不合法的缩略图文件大小");
        put(40013, "不合法的APPID");
        put(40014, "不合法的access_token");
        put(40015, "不合法的菜单类型");
        put(40016, "不合法的按钮个数");
        put(40163, "不合法的code,该code 已被使用");
        put(40017, "不合法的按钮类型");
        put(40018, "不合法的按钮名字长度");
        put(40019, "不合法的按钮KEY长度");
        put(40020, "不合法的按钮URL长度");
        put(40021, "不合法的菜单版本号");
        put(40022, "不合法的子菜单级数");
        put(40023, "不合法的子菜单按钮个数");
        put(40024, "不合法的子菜单按钮类型");
        put(40025, "不合法的子菜单按钮名字长度");
        put(40026, "不合法的子菜单按钮KEY长度");
        put(40027, "不合法的子菜单按钮URL长度");
        put(40028, "不合法的自定义菜单使用用户");
        put(40029, "不合法的oauth_code");
        put(40030, "不合法的refresh_token");
        put(40031, "不合法的openid列表");
        put(40032, "不合法的openid列表长度,一次只能拉黑20个用户");
        put(40033, "不合法的请求字符，不能包含\\uxxxx格式的字符");
        put(40035, "不合法的参数");
        put(40037, "不合法的模板id");
        put(40038, "不合法的请求格式");
        put(40039, "不合法的URL长度");
        put(40050, "不合法的分组id");
        put(40051, "分组名字不合法");
        put(40053, "不合法的actioninfo，请开发者确认参数正确");
        put(40056, "不合法的Code码");
        put(40059, "不合法的消息id");
        put(40071, "不合法的卡券类型");
        put(40072, "不合法的编码方式");
        put(40078, "不合法的卡券状态");
        put(40079, "不合法的时间");
        put(40080, "不合法的CardExt");        
        put(40097, "参数不正确，请参考字段要求检查json字段");
        put(40099, "卡券已被核销");
        put(40100, "不合法的时间区间");
        put(40116, "不合法的Code码");
        put(40122, "不合法的库存数量");
        put(40124, "会员卡设置查过限制的 custom_field字段");
        put(40127, "卡券被用户删除或转赠中");
        put(40130, "不合法的openid列表长度, 长度至少大于2个");//invalid openid list size, at least two openid
        put(41001, "缺少access_token参数");
        put(41002, "缺少appid参数");
        put(41003, "缺少refresh_token参数");
        put(41004, "缺少secret参数");
        put(41005, "缺少多媒体文件数据");
        put(41006, "缺少media_id参数");
        put(41007, "缺少子菜单数据");
        put(41008, "缺少oauth code");
        put(41009, "缺少openid");
        put(41011, "缺少必填字段");
        put(41012, "缺少cardid参数");
        put(42001, "access_token超时");
        put(42002, "refresh_token超时");
        put(42003, "oauth_code超时");
        put(43001, "需要GET请求");
        put(43002, "需要POST请求");
        put(43003, "需要HTTPS请求");
        put(43004, "需要接收者关注");
        put(43005, "需要好友关系");
        put(43009, "自定义SN权限，请前往公众平台申请");
        put(43010, "无储值权限，请前往公众平台申请");
        put(43100, "修改模板所属行业太频繁");
        put(44001, "多媒体文件为空");
        put(44002, "POST的数据包为空");
        put(44003, "图文消息内容为空");
        put(44004, "文本消息内容为空");
        put(45001, "多媒体文件大小超过限制");
        put(45002, "消息内容超过限制");
        put(45003, "标题字段超过限制");
        put(45004, "描述字段超过限制");
        put(45005, "链接字段超过限制");
        put(45006, "图片链接字段超过限制");
        put(45007, "语音播放时间超过限制");
        put(45008, "图文消息超过限制");
        put(45009, "接口调用超过限制");
        put(45010, "创建菜单个数超过限制");
        put(45015, "回复时间超过限制");
        put(45016, "系统分组，不允许修改");
        put(45017, "分组名字过长");
        put(45018, "分组数量超过上限");
        put(45027, "模板与所选行业不符");//template conflict with industry
        put(45028, "没有群发配额");//has no masssend quota
        put(45030, "该cardid无接口权限");
        put(45031, "库存为0");
        put(45033, "用户领取次数超过限制get_limit");
        put(45056, "创建的标签数过多，请注意不能超过100个");
        put(45057, "该标签下粉丝数超过10w，不允许直接删除");
        put(45058, "不能修改0/1/2这三个系统默认保留的标签");
        put(45059, "有粉丝身上的标签数已经超过限制");
        put(45157, "标签名非法，请注意不能和其他标签重名");
        put(45158, "标签名长度超过30个字节");
        put(45159, "非法的tag_id");
        put(46001, "不存在媒体数据");
        put(46002, "不存在的菜单版本");
        put(46003, "不存在的菜单数据");
        put(46004, "不存在的用户");
        put(46005, "不存在的门店");
        put(47001, "解析JSON/XML内容错误");
        put(48001, "api功能未授权");
        put(48004, "api接口被封禁，请登录mp.weixin.qq.com查看详情");
        put(49003, "传入的openid不属于此AppID");
        put(50001, "用户未授权该api");
        put(50002, "用户受限，可能是违规后接口被封禁");
        put(61451, "参数错误(invalid parameter)");
        put(61452, "无效客服账号(invalid kf_account)");
        put(61453, "客服帐号已存在(kf_account exsited)");
        put(61454, "客服帐号名长度超过限制(仅允许10个英文字符，不包括@及@后的公众号的微信号)(invalid kf_acount length)");
        put(61455, "客服帐号名包含非法字符(仅允许英文+数字)(illegal character in kf_account)");
        put(61456, "客服帐号个数超过限制(10个客服账号)(kf_account count exceeded)");
        put(61457, "无效头像文件类型(invalid file type)");
        put(61450, "系统错误(lg error)");
        put(61500, "日期格式错误");
        put(65104, "门店的类型不合法，必须严格按照附表的分类填写");
        put(65105, "图片url 不合法，必须使用接口1 的图片上传接口所获取的url");
        put(65106, "门店状态必须未审核通过");
        put(65107, "扩展字段为不允许修改的状态");
        put(65109, "门店名为空");
        put(65110, "门店所在详细街道地址为空");
        put(65111, "门店的电话为空");
        put(65112, "门店所在的城市为空");
        put(65113, "门店所在的省份为空");
        put(65114, "图片列表为空");
        put(65115, "poi_id 不正确");
        put(65301, "不存在此menuid对应的个性化菜单");
        put(65302, "没有相应的用户");
        put(65303, "没有默认菜单，不能创建个性化菜单");
        put(65304, "MatchRule信息为空");
        put(65305, "个性化菜单数量受限");
        put(65306, "不支持个性化菜单的帐号");
        put(65307, "个性化菜单信息为空");
        put(65308, "包含没有响应类型的button");
        put(65309, "个性化菜单开关处于关闭状态");
        put(65310, "填写了省份或城市信息，国家信息不能为空");
        put(65311, "填写了城市信息，省份信息不能为空");
        put(65312, "不合法的国家信息");
        put(65313, "不合法的省份信息");
        put(65314, "不合法的城市信息");
        put(65316, "该公众号的菜单设置了过多的域名外跳（最多跳转到3个域名的链接）");
        put(65317, "不合法的URL");
        put(65400, "API不可用，即没有开通/升级到新客服功能");
        put(65401, "无效客服帐号");
        put(65402, "帐号尚未绑定微信号，不能投入使用");
        put(65403, "客服昵称不合法");
        put(65404, "客服帐号不合法");
        put(65405, "帐号数目已达到上限，不能继续添加");
        put(65406, "已经存在的客服帐号");
        put(65407, "邀请对象已经是本公众号客服");
        put(65408, "本公众号已发送邀请给该微信号");
        put(65409, "无效的微信号");
        put(65410, "邀请对象绑定公众号客服数量达到上限（目前每个微信号最多可以绑定5个公众号客服帐号）");
        put(65411, "该帐号已经有一个等待确认的邀请，不能重复邀请");
        put(65412, "该帐号已经绑定微信号，不能进行邀请");
        put(65413, "不存在对应用户的会话信息");
        put(65414, "客户正在被其他客服接待");
        put(65415, "客服不在线");
        put(65416, "查询参数不合法");
        put(65417, "查询时间段超出限制");
        put(9001001, "POST数据参数不合法");
        put(9001002, "远端服务不可用");
        put(9001003, "Ticket不合法");
        put(9001004, "获取摇周边用户信息失败");
        put(9001005, "获取商户信息失败");
        put(9001006, "获取OpenID失败");
        put(9001007, "上传文件缺失");
        put(9001008, "上传素材的文件类型不合法");
        put(9001009, "上传素材的文件尺寸不合法");
        put(9001010, "上传失败");
        put(9001020, "帐号不合法");
        put(9001021, "已有设备激活率低于50%，不能新增设备");
        put(9001022, "设备申请数不合法，必须为大于0的数字");
        put(9001023, "已存在审核中的设备ID申请");
        put(9001024, "一次查询设备ID数量不能超过50");
        put(9001025, "设备ID不合法");
        put(9001026, "页面ID不合法");
        put(9001027, "页面参数不合法");
        put(9001028, "一次删除页面ID数量不能超过10");
        put(9001029, "页面已应用在设备中，请先解除应用关系再删除");
        put(9001030, "一次查询页面ID数量不能超过50");
        put(9001031, "时间区间不合法");
        put(9001032, "保存设备与页面的绑定关系参数错误");
        put(9001033, "门店ID不合法");
        put(9001034, "设备备注信息过长");
        put(9001035, "设备申请参数不合法");
        put(9001036, "查询起始值begin不合法");
        put(9001037, "备份失败");
        put(9001038, "恢复备份失败");

    }};

    /**
     * 通过返回码获取返回信息
     * @param errCode 错误码
     * @return {String}
     */
    public static String get(int errCode){
        String result = errCodeToErrMsg.get(errCode);
        return result != null ? result : "未知返回码：" + errCode;
    }
}
