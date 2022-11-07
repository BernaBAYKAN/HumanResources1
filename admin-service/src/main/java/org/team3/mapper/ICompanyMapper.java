package org.team3.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.team3.dto.request.NewCompanyRequestDto;
import org.team3.dto.response.AllCompanyDtoResponse;
import org.team3.repository.entity.Company;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ICompanyMapper {

    ICompanyMapper INSTANCE = Mappers.getMapper(ICompanyMapper.class);


    AllCompanyDtoResponse toCompanyResponse(Company company);
    Company toCompany(NewCompanyRequestDto dto);
}
