package com.self.ws;

import org.springframework.stereotype.Component;
import com.self.wsIntegration.types.UploadResonse;

@Component("wsSuccessResponseBuilder")
public class WsSuccessResponseBuilder {

    public UploadResonse buildResponse() {
    	UploadResonse response = new UploadResonse();
		response.setSuccess(true);
        return response;
    }

}
