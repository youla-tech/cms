package com.thinkcms.service.service.upload;

import com.thinkcms.service.api.upload.UploadService;
import com.thinkcms.service.dto.upload.Chunk;
import com.thinkcms.service.service.resource.SysResourceServiceImpl;
import com.thinkcms.core.annotation.CacheClear;
import com.thinkcms.core.utils.ApiResult;
import com.thinkcms.core.utils.Checker;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class UploadServiceImpl extends BaseUpload implements UploadService {

    @Override
    public ApiResult uploadFile(MultipartFile file) {
        ApiResult result = uploadClient.readyUpload(file);
        if (result.ckSuccess()) {
            result = uploadClient.uploadFile(file);
            if (result.ckSuccess()) {
                result = uploadClient.uploadSuccess(result, file);
            } else {
                uploadClient.uploadError(result);
            }
        } else {
            uploadClient.uploadError(result);
        }
        return result;
    }

    @Override
    public ApiResult keepUploadFile(Chunk chunk) {
        MultipartFile file=chunk.getFile();
        String finshFlag="finshed";
        ApiResult result = uploadClient.readyUpload(file);
        if (result.ckSuccess()) {
            result = uploadClient.keepUploadFile(chunk);
            Map<String,Object> params=(Map) result.get("res");
            if(Checker.BeNotEmpty(params)){
                if (params.containsKey(finshFlag) && (Boolean) params.get(finshFlag)) {
                    result = uploadClient.uploadSuccess(result, file);
                }
            }
        } else {
            uploadClient.uploadError(result);
        }
        return result;
    }


    @CacheClear(keys = {"getfilePathById", "getByPks", "getByPk"}, clzs = {SysResourceServiceImpl.class})
    @Override
    public ApiResult deleteFile(Map<String, String> params) {
        return uploadClient.deleteFile(params);
    }

    @Override
    public ApiResult checkFileIsExist(Chunk chunk) {
        Map<String,Object> resultMap=checkFileIsExistByMd5(chunk.getIdentifier());
        return ApiResult.result(resultMap);
    }

}
