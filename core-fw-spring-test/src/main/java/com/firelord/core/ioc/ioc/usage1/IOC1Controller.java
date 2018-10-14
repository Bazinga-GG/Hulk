package com.firelord.core.ioc.ioc.usage1;

import com.firelord.core.ioc.IOCUtils;
import com.firelord.core.ioc.ioc.usage1.JavaBeanByBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ioc1")
public class IOC1Controller {
    //#region Fields

    @Autowired
    private JavaBeanByBean javaBeanByBean;

    //#endregion

    //#region viaAutowire

    /**
     * STEP4.Use javabeans via @autowire
     */
    @RequestMapping("/viaAutowire")
    @ResponseBody
    public void viaAutowire() {
        javaBeanByBean.func();
    }

    //#endregion

    //#region viaIOCUtils

    /**
     * STEP5.Use javabeans via IOCUtils
     */
    @RequestMapping("/viaIOCUtils")
    @ResponseBody
    public void viaIOCUtils() {
        IOCUtils.getInstance().getBean(JavaBeanByBean.class).func();
    }

    //#endregion
}
