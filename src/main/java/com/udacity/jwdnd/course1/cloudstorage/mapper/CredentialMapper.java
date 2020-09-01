package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Insert("INSERT INTO CREDENTIALS(url,username,key,password,userid) values (#{url},#{username},#{key},#{password},#{userId})")
    @Options(useGeneratedKeys = true,keyProperty ="credentialId" )
    int createCredential(Credential credential);

@Select("SELECT * FROM CREDENTIALS WHERE userid=#{userId}")
    List<Credential> getAllUserCredentials(Integer userId);;

@Select("SELECT * FROM CREDENTIALS WHERE credentialid=#{credentialId} and userid=#{userId}")
    Credential getCredential(Integer credentialId,Integer userId);

@Update("UPDATE CREDENTIALS SET url=#{url},username=#{username},key=#{key},password=#{password} WHERE credentialid=#{credentialId} AND userid=#{userId}")
int updateCredential(Integer credentialId,String url,String username,String key,String password,Integer userId);

@Delete("DELETE FROM CREDENTIALS WHERE credentialid=#{credentialId} AND userid=#{userId}")
    int deleteCredential(Integer credentialId,Integer userId);
}
