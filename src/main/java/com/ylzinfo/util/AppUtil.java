package com.ylzinfo.util;

import com.ylzinfo.service.IMmpayRouteService;
import com.ylzinfo.service.impl.MmpayRouteJsonServiceImpl;
import com.ylzinfo.service.impl.MmpayRouteServiceDbImpl;

/**
 * @author lcl
 * @date 2020/12/3
 */
public class AppUtil {
    private static IMmpayRouteService mmpayRouteService;
    private static final Object LOCK = new Object();

    public static IMmpayRouteService getService(Boolean dbWay) {
        if (mmpayRouteService == null) {
            synchronized (LOCK) {
                if (mmpayRouteService == null) {
                    if (dbWay) {
                        mmpayRouteService = new MmpayRouteServiceDbImpl();
                    } else {
                        mmpayRouteService = new MmpayRouteJsonServiceImpl();
                    }
                }

            }
        }
        return mmpayRouteService;
    }

}
