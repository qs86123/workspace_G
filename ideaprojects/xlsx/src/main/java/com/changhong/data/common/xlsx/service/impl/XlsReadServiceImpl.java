package com.changhong.data.common.xlsx.service.impl;

import com.changhong.data.common.xlsx.domain.XlsReadColumn;
import com.changhong.data.common.xlsx.service.api.XlsReadService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Administrator on 16-10-12.
 */
public class XlsReadServiceImpl implements XlsReadService {
    @Override
    public <T> List<T> readToObjects(int offsetColumns, int offsetRows, File file, List<XlsReadColumn> mapper, Class<T> clazz) throws InvalidFormatException, IOException, InstantiationException, IllegalAccessException {
        return null;
    }

    @Override
    public <T> List<T> readToObjects(int offsetColumns, int offsetRows, InputStream is, List<XlsReadColumn> mapper, Class<T> clazz) throws InvalidFormatException, IOException, InstantiationException, IllegalAccessException {
        return null;
    }
}
