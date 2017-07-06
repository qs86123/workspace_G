package com.changhong.data.jxw.ktr.common;

import com.changhong.data.jxw.ktr.entity.TableInputAndTableOutPut;

public class GetTableOutput
{
    private static GetTableOutput tableOutput = new GetTableOutput();
    private TableInputAndTableOutPut out;

    private GetTableOutput()
    {
        TableInputAndTableOutPut out = new TableInputAndTableOutPut();
    }

    public static GetTableOutput getTableOutput()
    {
        if (null == tableOutput)
        {
            return new GetTableOutput();
        }
        return tableOutput;
    }

    public TableInputAndTableOutPut tableOutput()
    {
        return out;
    }
}
