package com.cmk.micro.services.employee.service.mapper;

import com.cmk.micro.services.employee.dao.model.Employee;
import com.cmk.micro.services.employee.dao.model.Employee.EmployeeBuilder;
import com.cmk.micro.services.employee.dto.EmployeeDTO;
import com.cmk.micro.services.employee.dto.EmployeeDTO.EmployeeDTOBuilder;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-17T17:31:41+0530",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_231 (Oracle Corporation)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeeDTO getDTOfromEntity(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDTOBuilder employeeDTO = EmployeeDTO.builder();

        employeeDTO.id( employee.getId() );
        employeeDTO.name( employee.getName() );
        employeeDTO.age( employee.getAge() );
        employeeDTO.primaryAddress( employee.getPrimaryAddress() );

        return employeeDTO.build();
    }

    @Override
    public Employee getEntityFromDTO(EmployeeDTO employeeDTO) {
        if ( employeeDTO == null ) {
            return null;
        }

        EmployeeBuilder employee = Employee.builder();

        employee.id( employeeDTO.getId() );
        employee.name( employeeDTO.getName() );
        employee.age( employeeDTO.getAge() );
        employee.primaryAddress( employeeDTO.getPrimaryAddress() );

        return employee.build();
    }
}
