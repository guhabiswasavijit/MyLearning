package com.self.ws;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class ErrorDataBean {
	private String recordNo;
	private String errorMessage;	
	private boolean failed;

}
