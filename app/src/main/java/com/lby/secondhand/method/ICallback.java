package com.lby.secondhand.method;

import java.util.Map;

public interface ICallback {
    void onSuccess(Map result);

    void onFailure(String errorCode);
}
