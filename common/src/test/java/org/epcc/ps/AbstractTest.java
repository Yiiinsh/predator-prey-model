package org.epcc.ps;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author shaohan.yin
 * Created on 09/10/2017
 */
public abstract class AbstractTest {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Rule
    public TestName testName = new TestName();

    @Before
    public void beforeAbstractTest() {
        logger.info(remarkableMessage("[begin test][{}]"), testName.getMethodName());
    }

    @After
    public void afterAbstractTest() {
        logger.info(remarkableMessage("[end   test][{}]"), testName.getMethodName());
    }

    protected String remarkableMessage(String msg) {
        return String.format("------------%s------------\n",msg);
    }
}
