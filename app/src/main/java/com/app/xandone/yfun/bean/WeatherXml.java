package com.app.xandone.yfun.bean;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

/**
 * author: xandone
 * created on: 2017/8/24 13:25
 */
@Root(name = "Profiles", strict = false)//需要解析的头部
public class WeatherXml {
    @ElementList(required = true, inline = true, entry = "Weather")
    private List<WeatherXmlData> list = new ArrayList<>();

    public List<WeatherXmlData> getList() {
        return list;
    }

    public void setList(List<WeatherXmlData> list) {
        this.list = list;
    }
}
