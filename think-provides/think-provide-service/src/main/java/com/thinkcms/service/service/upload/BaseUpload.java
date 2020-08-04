package com.thinkcms.service.service.upload;
import com.thinkcms.core.annotation.DefaultUploadClient;
import com.thinkcms.core.utils.Checker;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;

public abstract class BaseUpload {

    public UploadClient uploadClient;

    @Autowired
    public void initClient(List<UploadClient> uploadClients) {
        if(Checker.BeNotEmpty(uploadClients)){
            uploadClients.forEach(uploadClient->{
                DefaultUploadClient defaultClient = uploadClient.getClass().getAnnotation(DefaultUploadClient.class);
                if(Checker.BeNotNull(defaultClient)){
                    this.uploadClient = uploadClient;
                    return ;
                }
            });
        }
    }


    public Map<String,Object> checkFileIsExistByMd5(String md5){
        return uploadClient.checkFileIsExistByMd5(md5);
    }
}
