package com.thinkcms.system.api.system;
import com.thinkcms.core.api.BaseService;
import com.thinkcms.system.dto.system.SysJobDto;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LG
 * @since 2019-11-19
 */
public interface SysJobService extends BaseService<SysJobDto> {


    /**
     * 任务启动、暂停、删除
     * @param v
     */
    void taskAction(SysJobDto v);
}