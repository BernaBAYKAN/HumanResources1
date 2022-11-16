package org.team3.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import org.team3.dto.request.UserProfileRequestDto;

import org.team3.dto.response.DetailInformationResponseDto;

import org.team3.repository.entity.User;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IUserMapper {

    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    User toCompanyManager(UserProfileRequestDto dto);

    DetailInformationResponseDto toDetail(User user);





    User toUser(UserProfileRequestDto dto);








}
