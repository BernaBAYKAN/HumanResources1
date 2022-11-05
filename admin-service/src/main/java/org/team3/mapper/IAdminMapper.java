package org.team3.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.team3.dto.request.AdminProfileRequestDto;
import org.team3.dto.request.EditProfileRequestDto;
import org.team3.repository.entity.Admin;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IAdminMapper {

    IAdminMapper INSTANCE = Mappers.getMapper(IAdminMapper.class);

     Admin toAdmin(EditProfileRequestDto dto);
    Admin toAdminProfile(AdminProfileRequestDto dto);

}
