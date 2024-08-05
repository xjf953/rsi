package com.shawn.votesystem.model.convert;


import com.shawn.votesystem.utils.EncryptUtil;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * JPA自定义转换器: 入库加密,出库解密.
 *
 * @date 2022/7/7 9:57
 * @since 1.0.0
 */
@Converter
public class EncryptConverter implements AttributeConverter<String, String> {

    /**
     * 入库加密随机密码
     *
     * @param column
     * @date 2022-07-07 9:58
     * @since 1.0.0
     * @return java.lang.String
     * @throws
     */
    @Override
    public String convertToDatabaseColumn(String column) {
        if (!"".equals(column) && column != null) {
            return EncryptUtil.encryptByRandomSey(column);
        }
        return column;
    }

    @Override
    public String convertToEntityAttribute(String column) {
        if (!"".equals(column) && column != null) {
            return EncryptUtil.decryptByRandomSey(column);
        }
        return column;
    }
}
