package com.thinkcms.core.handler;
import com.thinkcms.core.utils.ApiResult;
import lombok.Data;

@Data
public class CustomException extends RuntimeException  {

	public ApiResult r;

	public CustomException(ApiResult r) {
		super(r.getMessage());
		this.r = r;
	}

}
