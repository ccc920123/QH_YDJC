package com.scxd.lawqinghai.common;

import com.scxd.lawqinghai.bean.Md_cartype;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class Md_System_Datas {

    public static List<Md_cartype> wljyy;
    public static List<Md_cartype> jsjqbj;
    public static List<Md_cartype> czfs;
    public static List<Md_cartype> fxyclyy;
    public static List<Md_cartype> clzt;
    public static List<Md_cartype> zdaq;

    /**
     * 未启用该数据，采用字典方式加载数据
     * @return
     */
    public static List<Md_cartype> getWljyy() {
        if (wljyy != null) {
            return wljyy;
        }
        wljyy = new ArrayList<>();
        wljyy.add(new Md_cartype("01", "未发现车辆"));
        wljyy.add(new Md_cartype("03", "车辆掉头或绕行"));
        wljyy.add(new Md_cartype("02", "车辆未停或强行闯卡"));
        wljyy.add(new Md_cartype("09", "其他"));
        return wljyy;
    }

    public static List<Md_cartype> getCzfs() {
        if (czfs != null) {
            return czfs;
        }
        czfs = new ArrayList<>();
        czfs.add(new Md_cartype("1", "放行"));
        czfs.add(new Md_cartype("2", "现场开具处罚文书"));
        czfs.add(new Md_cartype("3", "移交其他部门"));
        czfs.add(new Md_cartype("4", "当事人接受处理"));
        czfs.add(new Md_cartype("5", "扣留机动车"));
        czfs.add(new Md_cartype("6", "已处罚"));
        czfs.add(new Md_cartype("7", "当事人拒绝处理"));
        czfs.add(new Md_cartype("8", "教育告知后放行"));
        return czfs;
    }

    public static List<Md_cartype> getClzt() {
        if (clzt != null) {
            return clzt;
        }
        clzt = new ArrayList<>();
        clzt.add(new Md_cartype("1", "正常"));
        clzt.add(new Md_cartype("2", "无牌"));
        clzt.add(new Md_cartype("3", "套牌"));
        clzt.add(new Md_cartype("4", "假牌"));
        return clzt;
    }

    public static List<Md_cartype> getFxyclyy() {
        if (fxyclyy != null) {
            return fxyclyy;
        }
        fxyclyy = new ArrayList<>();
        fxyclyy.add(new Md_cartype("01", "布控信息有误"));
        fxyclyy.add(new Md_cartype("02", "号牌识别错误"));
        fxyclyy.add(new Md_cartype("03", "违法记录错误"));
        fxyclyy.add(new Md_cartype("04", "原车"));
        fxyclyy.add(new Md_cartype("05", "车辆基本信息未更新"));
        fxyclyy.add(new Md_cartype("06", "内部车辆"));
        fxyclyy.add(new Md_cartype("07", "车辆已年检"));
        fxyclyy.add(new Md_cartype("08", "违法已处理"));
        fxyclyy.add(new Md_cartype("09", "车辆品牌识别错误"));
        fxyclyy.add(new Md_cartype("10", "识别与登记品牌未对应"));
        fxyclyy.add(new Md_cartype("11", "接驳车辆"));
        fxyclyy.add(new Md_cartype("12", "卡口未校时"));
        fxyclyy.add(new Md_cartype("13", "驾驶人驾驶证正常"));
        fxyclyy.add(new Md_cartype("14", "非车主本人驾驶"));
        fxyclyy.add(new Md_cartype("15", "检查无异常"));
        return fxyclyy;
    }

    public static List<Md_cartype> getZdaq() {
        if (zdaq != null) {
            return zdaq;
        }
        zdaq = new ArrayList<>();
        zdaq.add(new Md_cartype("00", "无"));
        zdaq.add(new Md_cartype("10", "作案车辆"));
        zdaq.add(new Md_cartype("20", "A级逃犯"));
        return zdaq;
    }

    /**
     * 拒收拒签
     * @return
     */
    public static List<Md_cartype> getJsjqbj() {
        if (jsjqbj != null) {
            return jsjqbj;
        }
        jsjqbj = new ArrayList<>();
        jsjqbj.add(new Md_cartype("0", "否"));
        jsjqbj.add(new Md_cartype("1", "是"));
        return jsjqbj;
    }

}
