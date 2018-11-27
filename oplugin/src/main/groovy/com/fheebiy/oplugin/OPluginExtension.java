package com.fheebiy.oplugin;

/**
 * Created on 2018/11/27.
 *
 * @author bob zhou.
 * Description:
 */
public class OPluginExtension {

    private String name;

    private int version;

    /**
     * 注意这个属性的get和set方法, 不能用自动生成的方法
     */
    private boolean isOpen;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public boolean getIsOpen() {
        return isOpen;
    }
}
