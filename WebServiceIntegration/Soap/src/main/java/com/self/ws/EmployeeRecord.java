package com.self.ws;

import java.io.Serializable;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@CsvRecord(separator = "\\,",skipFirstLine = true)
@Getter @Setter @NoArgsConstructor
public class EmployeeRecord implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@DataField(pos = 1, required = true)
	private String employeeId;
	@DataField(pos = 2, required = true)
	private String firstNmae;
	@DataField(pos = 3, required = true)
	private String lastName;
	@DataField(pos = 4, required = true)
	private String email;
	@DataField(pos = 5, required = true)
	private String phoneNumber;
	@DataField(pos = 6, required = true)
	private String hireDate;
	@DataField(pos = 7, required = true)
	private String jobId;
	@DataField(pos = 8, required = true)
	private String salary;
	@DataField(pos = 9, required = true) 
	private String commisionPct;
	@DataField(pos = 10, required = false)
	private String managerId;
	@DataField(pos = 11, required = false)
	private String departmentId;
	
	
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstNmae=" + lastName + ", email="
				+ email + ", phoneNumber=" + phoneNumber + ", hireDate=" + hireDate
				+ ", jobId=" + jobId + ",salary="+salary+",commisionPct="+commisionPct+",managerId="+managerId+",departmentId="+departmentId+"]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeRecord other = (EmployeeRecord) obj;
		if (employeeId == null) {
			if (other.employeeId != null)
				return false;
		} else if (!employeeId.equals(other.employeeId))
			return false;
		return true;
	}
	
}
