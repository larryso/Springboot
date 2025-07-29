package com.larry.test.application.service;

import com.larry.service.JsonHelper;
import org.junit.jupiter.api.Test;

/**
 * Simple unit test
 */
public class JsonHelperTest {

    @Test
    void testGetJsonPath() {
        String attrName = "additionalAttr.cecret";
        String expected = "$.additionalAttr.cecret";
        String actual = JsonHelper.getJsonPath(attrName);
        assert actual.equals(expected) : "Expected: " + expected + ", but got: " + actual;
    }
    @Test
    void testGetJsonPathEmptyAttrName() {
        String attrName = "";
        String expected = "$.";
        String actual = JsonHelper.getJsonPath(attrName);
        assert actual.equals(expected) : "Expected: " + expected + ", but got: " + actual;
    }
}
