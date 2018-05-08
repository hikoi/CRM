package com.crm.core.device.utils.handler;

import com.crm.core.device.consts.DeviceType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeviceTypeHandler extends BaseTypeHandler<DeviceType> implements TypeHandler<DeviceType>{

    public void setNonNullParameter(PreparedStatement ps, int i, DeviceType parameter, JdbcType jdbcType) throws SQLException{
        ps.setInt(i, parameter.getId());
    }

    public DeviceType getNullableResult(ResultSet rs, String columnName) throws SQLException{
        return DeviceType.getById(rs.getInt(columnName));
    }

    public DeviceType getNullableResult(ResultSet rs, int columnIndex) throws SQLException{
        return DeviceType.getById(rs.getInt(columnIndex));
    }

    public DeviceType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException{
        return DeviceType.getById(cs.getInt(columnIndex));
    }
}
