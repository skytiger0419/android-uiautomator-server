/*
 * The MIT License (MIT)
 * Copyright (c) 2015 xiaocong@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.github.uiautomator.stub;

import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.Configurator;
import com.github.uiautomator.stub.helper.FileUtil;
import org.json.JSONObject;
import java.io.File;

/**
 * Created by xiaocong@gmail.com on 12/26/13.
 */
public class ConfiguratorInfo {
   public static final String CONFIG_PATH = InstrumentationRegistry.getTargetContext().getFilesDir() + File.separator + "config.json";

    private long _actionAcknowledgmentTimeout = 0;
    private long _keyInjectionDelay = 0;
    private long _scrollAcknowledgmentTimeout = 0;
    private long _waitForIdleTimeout = 0;
    private long _waitForSelectorTimeout = 0;
    private boolean _flag = false;

    public ConfiguratorInfo() {
        Configurator config = Configurator.getInstance();
        this._actionAcknowledgmentTimeout = config.getActionAcknowledgmentTimeout();
        this._keyInjectionDelay = config.getKeyInjectionDelay();
        this._scrollAcknowledgmentTimeout = config.getScrollAcknowledgmentTimeout();
        this._waitForIdleTimeout = config.getWaitForIdleTimeout();
        this._waitForSelectorTimeout = config.getWaitForSelectorTimeout();
    }

    public long getActionAcknowledgmentTimeout() {
        return _actionAcknowledgmentTimeout;
    }

    public void setActionAcknowledgmentTimeout(long _actionAcknowledgmentTimeout) {
        this._actionAcknowledgmentTimeout = _actionAcknowledgmentTimeout;
    }

    public long getKeyInjectionDelay() {
        return _keyInjectionDelay;
    }

    public void setKeyInjectionDelay(long _keyInjectionDelay) {
        this._keyInjectionDelay = _keyInjectionDelay;
    }

    public long getScrollAcknowledgmentTimeout() {
        return _scrollAcknowledgmentTimeout;
    }

    public void setScrollAcknowledgmentTimeout(long _scrollAcknowledgmentTimeout) {
        this._scrollAcknowledgmentTimeout = _scrollAcknowledgmentTimeout;
    }

    public long getWaitForIdleTimeout() {
        return _waitForIdleTimeout;
    }

    public void setWaitForIdleTimeout(long _waitForIdleTimeout) {
        this._waitForIdleTimeout = _waitForIdleTimeout;
    }

    public long getWaitForSelectorTimeout() {
        return _waitForSelectorTimeout;
    }

    public void setWaitForSelectorTimeout(long _waitForSelectorTimeout) {
        this._waitForSelectorTimeout = _waitForSelectorTimeout;
    }

    public boolean isFlag() {
        return this._flag;
    }

    public void setFlag(boolean _flag) {
        this._flag = _flag;
    }

    public static void setConfigurator(ConfiguratorInfo info) {
        Configurator config = Configurator.getInstance();
        if(info.getActionAcknowledgmentTimeout() != 0){
            writeConfig("actionAcknowledgmentTimeout",info.getActionAcknowledgmentTimeout());
            config.setActionAcknowledgmentTimeout(info.getActionAcknowledgmentTimeout());
        }
        if(info.getKeyInjectionDelay() != 0){
            writeConfig("keyInjectionDelay", info.getKeyInjectionDelay());
            config.setKeyInjectionDelay(info.getKeyInjectionDelay());
        }
        if(info.getScrollAcknowledgmentTimeout() != 0){
            writeConfig("scrollAcknowledgmentTimeout", info.getScrollAcknowledgmentTimeout());
            config.setScrollAcknowledgmentTimeout(info.getScrollAcknowledgmentTimeout());
        }
        if(info.getWaitForIdleTimeout() != 0){
            writeConfig("waitForIdleTimeout", info.getWaitForIdleTimeout());
            config.setWaitForIdleTimeout(info.getWaitForIdleTimeout());
        }
        if(info.getWaitForSelectorTimeout() != 0){
            writeConfig("saitForSelectorTimeout", info.getWaitForSelectorTimeout());
            config.setWaitForSelectorTimeout(info.getWaitForSelectorTimeout());
        }

    }

    public static void restore() {
        Configurator config = Configurator.getInstance();
        config.setActionAcknowledgmentTimeout(3000);
        config.setKeyInjectionDelay(0);
        config.setScrollAcknowledgmentTimeout(200);
        config.setWaitForIdleTimeout(10000);
        config.setWaitForSelectorTimeout(10000);
        File f = new File(CONFIG_PATH);
        if(f.exists()){
            f.delete();
        }
    }

    public static void loadConfig(){
       String content = FileUtil.readFile(CONFIG_PATH);
       if(content != null){
           try{
               Configurator config = Configurator.getInstance();
               JSONObject obj = new JSONObject(content);
               config.setActionAcknowledgmentTimeout(obj.optLong("actionAcknowledgmentTimeout",3000));
               config.setKeyInjectionDelay(obj.optLong("keyInjectionDelay",0));
               config.setScrollAcknowledgmentTimeout(obj.optLong("scrollAcknowledgmentTimeout",200));
               config.setWaitForIdleTimeout(obj.optLong("waitForIdleTimeout",10000));
               config.setWaitForSelectorTimeout(obj.optLong("waitForSelectorTimeout",10000));
           }catch (Exception e){

           }
       }
    }

    private static void writeConfig(String key, long value){
        String content = FileUtil.readFile(CONFIG_PATH);
        try{
            JSONObject obj = null;
            if(content != null) {
                obj = new JSONObject(content);
            }else {
                obj = new JSONObject();
            }
            obj.put(key,value);
            FileUtil.writeFile(CONFIG_PATH,obj.toString());
        }catch (Exception e){

        }
    }


}
