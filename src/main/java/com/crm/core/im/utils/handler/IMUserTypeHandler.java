package com.crm.core.im.utils.handler;

import com.crm.core.im.consts.IMUserType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IMUserTypeHandler extends BaseTypeHandler<IMUserType> implements TypeHandler<IMUserType>{

    public void setNonNullParameter(PreparedStatement ps, int i, IMUserType parameter, JdbcType jdbcType) throws SQLException{
        ps.setInt(i, parameter.getId());
    }

    public IMUserType getNullableResult(ResultSet rs, String columnName) throws SQLException{
        return IMUserType.getById(rs.getInt(columnName));
    }

    public IMUserType getNullableResult(ResultSet rs, int columnIndex) throws SQLException{
        return IMUserType.getById(rs.getInt(columnIndex));
    }

    public IMUserType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException{
        return IMUserType.getById(cs.getInt(columnIndex));
    }
}
