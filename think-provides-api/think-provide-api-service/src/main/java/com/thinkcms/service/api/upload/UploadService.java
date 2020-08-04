package com.thinkcms.service.api.upload;

import com.thinkcms.service.dto.upload.Chunk;
import com.thinkcms.core.utils.ApiResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LG
 * @since 2019-11-11
 */
public interface UploadService {


	/**
	 * 普通续传
	 * @param file
	 * @return
	 */
	ApiResult uploadFile(MultipartFile file);

	/**
	 * 断点续传
	 * @param  chunk
	 * @return
	 */
	ApiResult keepUploadFile(Chunk chunk);


	ApiResult deleteFile(Map<String,String> params);


	ApiResult checkFileIsExist(Chunk chunk);
}