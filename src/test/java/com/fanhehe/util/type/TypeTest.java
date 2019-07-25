package com.fanhehe.util.type;

import org.junit.Test;
import org.junit.Assert;

import java.util.Map;
import java.util.HashMap;
import com.fanhehe.util.http.HttpUtil;
import com.fanhehe.util.result.IResult;

public class TypeTest {

    @Test
    public void evaluatesExpression() {
        Assert.assertEquals(true, Type.isPhone("13889441200"));
        Assert.assertEquals(true, Type.isEmail("13889441200@163.com"));
    }
}

